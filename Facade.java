import java.util.*;
import java.io.*;

public class Facade{

	int UserType;
	Product theSelectedProduct;
	@SuppressWarnings("all")
	private int nProductCategory;
	ClassProductList theProductList;
	private Person thePerson;

	ArrayList<String> bUserName = new ArrayList<>();
	ArrayList<String> bPass = new ArrayList<>();
	ArrayList<String> sUserName = new ArrayList<>();
	ArrayList<String> sPass = new ArrayList<>();
	private String name;
	Trading trades = new Trading();

	//OfferingList bidProducts = new OfferingList();

	public boolean login() {
		int temp=-1;
		File reader;
		Scanner myReader;
		try {
			reader = new File("BuyerInfo.txt");
			myReader = new Scanner(reader);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String[] data_array = data.split(":");
				bUserName.add(data_array[0]);
				bPass.add(data_array[1]);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}

		try{
			reader = new File("SellerInfo.txt");
			myReader = new Scanner(reader);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String[] data_array = data.split(":");
				sUserName.add(data_array[0]);
				sPass.add(data_array[1]);
			}
			myReader.close();
		}  catch (FileNotFoundException e){
			System.out.println("File not found");
		}

		System.out.println("Enter Username ");
		Scanner scan = new Scanner(System.in);
		String username = scan.next();
		System.out.println("Enter Password ");
		String password = scan.next();

		for(int i=0; i< sUserName.size();i++){
			if(sUserName.get(i).equalsIgnoreCase(username)){
				temp=i;
			}
		}
		if(temp!=-1 && password.equalsIgnoreCase(sPass.get(temp))){
			UserType=1;
			this.name = username;
			return true;
		}

		for(int i=0; i< bUserName.size();i++){
			if(bUserName.get(i).equalsIgnoreCase(username)){
				temp=i;
			}
		}
		if(temp!=-1 && password.equalsIgnoreCase(bPass.get(temp))){
			UserType=0;
			this.name = username;
			return true;
		}

		System.out.println("Username / Password Not Found");
		return false;
	}

	public void addTrading() {
		System.out.println();
		System.out.println("Buyer Items: ");
		Offering o = new Offering(this.theSelectedProduct.n, this.theSelectedProduct.t);
		o.sellerName = this.name;
		trades.offeringList.add(o);
		this.thePerson.productList.remove(theSelectedProduct);
		System.out.println(o.n + " added to Trade List");
	}

	public void viewTrading() {
		System.out.println();
		System.out.println("Current Trade List: ");
		OfferingIterator iterator = new OfferingIterator(trades.offeringList); // Iterator Design Pattern
		while (iterator.hasNext()) {
			Offering i = iterator.next();
			System.out.println("Product Name: " + i.n);
			System.out.println("Bid: " + i.biddingAmount);
			System.out.println("Seller: " + i.sellerName);
			System.out.println("Bid by: " + i.bidderName);
			System.out.println();

		}
	}

	public void decideBidding() {
		Scanner inp = new Scanner(System.in);
		viewTrading();
		Product prod = selectProduct();
		this.theSelectedProduct = prod;
		if(prod==null)
			return;
		System.out.println("Enter seller name of product: ");
		String seller = inp.nextLine();
		Offering o = new Offering(prod.n, prod.t);
		System.out.println("Enter bid amount for " + o.n + ": ");
		o.biddingAmount = inp.nextInt();
		o.bidderName = name;
		o.sellerName = seller;
		submitBidding(o);
	}

	public void discussBidding() {
		viewTrading();
	}

	public void submitBidding(Offering o) {
		OfferingIterator iterator = new OfferingIterator(trades.offeringList); //Iterator Design Pattern
		while (iterator.hasNext()) {
			Offering i = iterator.next();
			if (i.n.equalsIgnoreCase(o.n) && i.sellerName.equalsIgnoreCase(o.sellerName)) {
				if (i.biddingAmount >= o.biddingAmount) {
					System.out.println("Bid higher for " + i.n);
					System.out.println("Seller: " + i.sellerName);
				} else {
					i.bidderName = o.bidderName;
					i.biddingAmount = o.biddingAmount;
					System.out.println("Bid for " + i.n);
					System.out.println("Seller: " + i.sellerName);
					System.out.println("Completed");
				}
			}
		}
	}

	public void createUser() {
		if(this.UserType==0) {
			this.thePerson = new Buyer();
			this.thePerson.tradingMenu = new BuyerTradingMenu();
		}
		else {
			this.thePerson = new Seller();
			this.thePerson.tradingMenu = new SellerTradingMenu();
		}
	}

	public void createProductList() {
		this.theProductList = new ClassProductList();
		try {
			File myObj = new File("ProductInfo.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String[] data_array = data.split(":");
				Product p = new Product(data_array[1], data_array[0]);
				this.theProductList.add(p);
			}
			myReader.close();
		}
		catch(FileNotFoundException e){
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public void attachProductToUser() {
		try {
			File reader = new File("UserProduct.txt");
			Scanner myReader = new Scanner(reader);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String[] data_array = data.split(":");
				if(data_array[0].equalsIgnoreCase(this.name)){
					ProductIterator iterator = new ProductIterator(this.theProductList); // Iterator Design Pattern
					while(iterator.hasNext()){
						Product temp= iterator.next();
						if(temp.n.equalsIgnoreCase(data_array[1])) {
							this.thePerson.productList.add(temp);
							break;
						}
					}
				}
			}
			myReader.close();
		}
		catch(FileNotFoundException e){
			System.out.println("File not found");
		}
	}

	public Product selectProduct() {
		if(this.UserType==0)
			System.out.println("User Products Demanded:");
		else
			System.out.println("Available products to put up for trade: ");
		ProductIterator iterator =new ProductIterator(this.thePerson.productList);
		while(iterator.hasNext()) { // Iterator Design Pattern
			Product temp=iterator.next();
			System.out.println(temp.n);
		}

		if(this.UserType==0)
			System.out.println("\nEnter product name to bid on it");
		else
			System.out.println("\nEnter product name to put for trade");

		Scanner scan = new Scanner(System.in);
		String product_chosen = scan.nextLine();
		iterator.moveToHead(); // Iterator Design Pattern
		while(iterator.hasNext()) {
			Product prod=iterator.next();
			if(product_chosen.equalsIgnoreCase(prod.n)) {
				return prod;
			}
		}
		return null;
	}

	public void productOperation() {
		Product prod;
		int option;
		if(this.UserType==0) {
			do {
				System.out.println();
				System.out.println("1) View items being traded");
				System.out.println("2) Make Bid for Item");
				System.out.println("3) Logout");
				Scanner inp = new Scanner(System.in);
				System.out.println("Enter option:");
				option = inp.nextInt();
				switch (option) {
					case 1:
						discussBidding();
						break;
					case 2:
						decideBidding();
						break;
					case 3:
						break;
				}
			}while (option != 3) ;
		}

		else{
			do {
				System.out.println("\n\n1) Add Trade\n2) View Trade\n3) Exit");
				Scanner sc = new Scanner(System.in);
				option = sc.nextInt();
				switch (option) {
					case 1:
						prod = selectProduct();
						this.theSelectedProduct = prod;
						if (prod == null) {
							continue;
						}
						System.out.println(prod.n);
						/*Bridge Pattern*/
						if (prod.t.equalsIgnoreCase("Produce"))
							this.thePerson.productMenu = this.thePerson.createProductMenu(1);
						else
							this.thePerson.productMenu = this.thePerson.createProductMenu(0);
						this.thePerson.showMenu(); // Factory Pattern
						this.thePerson.showAddButton();
						this.thePerson.showViewButton();
						this.thePerson.showRadioButton();
						this.thePerson.showLabels();
						this.thePerson.showCombos();
						addTrading();
						break;

					case 2:
						viewTrading();
						break;
					case 3:
						break;
				}
			}while(option!=3);
		}
	}

	public void accept(NodeVisitor visitor) {
		System.out.println("Facade Reminder ...");
		visitor.visitFacade(this);
	}
}
