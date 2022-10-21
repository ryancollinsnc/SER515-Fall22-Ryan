import java.util.*;
import java.io.*;

public class Facade{

	int UserType;
	Product theSelectedProduct;
	private int nProductCategory;
	ClassProductList theProductList;
	private Person thePerson;
	private ArrayList<String> BuyerName = new ArrayList<>();
	private ArrayList<String> BuyerPass = new ArrayList<>();
	private ArrayList<String> SellerName = new ArrayList<>();
	private ArrayList<String> SellerPass = new ArrayList<>();
	private String name;
	Trading trades = new Trading();
	private OfferingList bidProducts = new OfferingList();

	public boolean login() {
		int temp=-1;
		try {
			File myObj = new File("BuyerInfo.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String data1[] = data.split(":");
				BuyerName.add(data1[0]);
				BuyerPass.add(data1[1]);
			}
			myReader.close();
			myObj = new File("SellerInfo.txt");
			myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String data1[]= data.split(":");
				SellerName.add(data1[0]);
				SellerPass.add(data1[1]);
				//System.out.println(SellerPass);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		System.out.println("Enter Username ");
		//@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		String username = scan.next();
		System.out.println("Enter Password ");
		String password = scan.next();

		for(int i=0; i< BuyerName.size();i++){
			if(BuyerName.get(i).equalsIgnoreCase(username)==true){
				temp=i;
			}
		}
		if(temp!=-1 && password.equalsIgnoreCase(BuyerPass.get(temp))==true){
			UserType=0;
			this.name = username;
			return true;
		}
		for(int i=0; i< SellerName.size();i++){
			if(SellerName.get(i).equalsIgnoreCase(username)==true){
				temp=i;
			}
		}
		if(temp!=-1 && password.equalsIgnoreCase(SellerPass.get(temp))==true){
			UserType=1;
			this.name = username;
			return true;
		}

		System.out.println("No user");
		return false;
	}

	public void addTrading() {
		System.out.print("\nOffer to be made available: ");
		Offering o = new Offering(this.theSelectedProduct.name, this.theSelectedProduct.category);
		o.seller_name = this.name;
		trades.offeringList.add(o);
		System.out.println(o.name);
		this.thePerson.productList.remove(theSelectedProduct);
		System.out.println("Offer for " + o.name + " added to Trading");
	}

	public void viewTrading() {
		System.out.println("\nOffers Currently for Trade: ");
		OfferingIterator iter = new OfferingIterator(trades.offeringList);
		System.out.println("\n<<ITERATOR DESIGN PATTERN USED HERE TO ITERATE ELEMENTS>>");
		while (iter.hasNext()) {
			Offering i = iter.next();
			System.out.println("Product Name: " + i.name + "\t\tSeller: " + i.seller_name + "\t\tBid: " + i.bid + "\t\tBid by: " + i.bid_name);

		}
	}

	public void decideBidding() {
		Scanner inp = new Scanner(System.in);
		viewTrading();
		Product a = selectProduct();
		this.theSelectedProduct = a;
		if(a==null)
			return;
		System.out.println("Enter seller name: ");
		String seller = inp.nextLine();
		Offering o = new Offering(a.name, a.category);
		System.out.println("Enter bid amount for " + o.name);
		o.bid = inp.nextInt();
		o.bid_name = name;
		o.seller_name = seller;
		bidProducts.add(o);
	}

	public void discussBidding() {
		viewTrading();
		System.out.println("These are current trades and prices - Make bid higher that current bid");
	}

	public void submitBidding() {
		OfferingIterator iter=new OfferingIterator(bidProducts);
		OfferingIterator iter1=new OfferingIterator(trades.offeringList);
		for(Offering i : bidProducts){
			System.out.println(i.name + " " + i.seller_name + " " + i.bid);
		}

		for(Offering i : trades.offeringList){
			System.out.println(i.name + " " + i.seller_name + " " + i.bid);
		}

		System.out.println("\n<<ITERATOR DESIGN PATTERN USED HERE TO ITERATE ELEMENTS>>");
		while(iter.hasNext()) {
			Offering i=iter.next();
			iter1.moveToHead();
			while(iter1.hasNext()){ // Iterator here
				Offering j=iter1.next();
				if(i.name.equalsIgnoreCase(j.name) && i.seller_name.equalsIgnoreCase(j.seller_name)){
					if(i.bid <= j.bid) {
						System.out.println("Make Higher Bid for " + j.name + "\tSeller: " + j.seller_name);
					}
					else {
						j.bid_name = i.bid_name;
						j.bid = i.bid;
						System.out.println("BIDS MADE FOR - " + j.name + " " + "\tSeller: " + j.seller_name);
					}
				}
			}
		}
		this.bidProducts = new OfferingList();
	}

	public void remind() {

	}

	public void createUser() {
		if(this.UserType==0)
			this.thePerson = new Buyer();
		else
			this.thePerson = new Seller();
		System.out.println("User Created\n");
	}

	public void createProductList() {
		this.theProductList = new ClassProductList();
		try {
			File myObj = new File("ProductInfo.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String data1[] = data.split(":");
				Product p = new Product(data1[1], data1[0]);
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
			File myObj = new File("UserProduct.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String data1[]= data.split(":");
				if(data1[0].equalsIgnoreCase(this.name)){
					ProductIterator iter = new ProductIterator(this.theProductList);
					System.out.println("\n<<ITERATOR DESIGN PATTERN USED HERE TO ADD ELEMENTS TO USER>>");
					while(iter.hasNext()){
						Product temp= iter.next();
						if(temp.name.equalsIgnoreCase(data1[1])) {
							this.thePerson.productList.add(temp);
							break;
						}
					}
				}
			}
			myReader.close();
		}
		catch(FileNotFoundException e){
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public Product selectProduct() {
		if(this.UserType==1)
			System.out.println("\n\nProducts Not Traded Currently:");
		else
			System.out.println("\n\nProduct Requested by User: ");
		ProductIterator iter=new ProductIterator(this.thePerson.productList);
		System.out.println("\n<<ITERATOR DESIGN PATTERN USED HERE TO ITERATE ELEMENTS>>");
		while(iter.hasNext()) {
			Product temp=iter.next();
			System.out.println(temp.name);
		}

		if(this.UserType==1)
			System.out.println("\nEnter Product Name (Or Enter 'e' to Exit):");
		else
			System.out.println("\nEnter Product to Bid on (Or Enter 'e' to Exit):");
		Scanner scan = new Scanner(System.in);
		String product_chosen = scan.next();
		ProductIterator iter1=new ProductIterator(this.thePerson.productList);
		System.out.println("\n<<ITERATOR DESIGN PATTERN USED HERE TO ITERATE ELEMENTS>>");
		while(iter1.hasNext()) {
			Product i=iter1.next();
			if(product_chosen.equalsIgnoreCase(i.name)) {
				return i;
			}
			else if(product_chosen.equalsIgnoreCase("e"))
				return null;
		}
		return null;
	}

	public void productOperation() {
		Product a = null;
		int option;
		if(this.UserType==1) {
			do {
				System.out.println("\n\n1) Add Product to Trade\n2) View Trading\n3) Exit");
				Scanner inp = new Scanner(System.in);
				option = inp.nextInt();
				switch (option) {
					case 1:
						a = selectProduct();
						this.theSelectedProduct = a;
						if (a == null) {
							continue;
						}
						System.out.println(a.name);
						if (a.category.equalsIgnoreCase("Meat"))
							this.thePerson.productMenu = this.thePerson.createProductMenu(0);
						else
							this.thePerson.productMenu = this.thePerson.createProductMenu(1);
						System.out.println("<<BRIDGE DESIGN PATTERN USED HERE TO LINK APPROPRIATE MENU>>");
						System.out.println("\n\n<<FACTORY DESIGN PATTERN USED HERE TO BUILD APPROPRIATE MENU>>");
						this.thePerson.showMenu();
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

		else{
			do{
				System.out.println("\n\n1) See Current Bids\n2) Make Bid(s)\n3) Submit Bids\n4) Exit");
				Scanner inp = new Scanner(System.in);
				System.out.println("Enter option:");
				option = inp.nextInt();
				switch(option){
					case 1:
						discussBidding();
						break;
					case 2:
						decideBidding();
						break;
					case 3:
						submitBidding();
						break;
					case 4:
						break;
				}
			}while(option!=4);
		}
	}

	public void accept(NodeVisitor visitor) {
		System.out.println("Facade Reminder ...");
		visitor.visitFacade(this);
	}
}
