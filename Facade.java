import java.util.*;
import java.io.*;

public class Facade{

	private int UserType;
	private Product theSelectedProduct;
	private int nProductCategory;
	private ClassProductList theProductList;
	private Person thePerson;
	private ArrayList<String> BuyerName = new ArrayList<>();
	private ArrayList<String> BuyerPass = new ArrayList<>();
	private ArrayList<String> SellerName = new ArrayList<>();
	private ArrayList<String> SellerPass = new ArrayList<>();
	private String name;
	private Trading trades = new Trading();


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
		trades.offeringList.add(o);
		System.out.println(o.name);
		this.thePerson.productList.remove(theSelectedProduct);
		System.out.println("Offer for " + o.name + " added to Trading");
	}

	public void viewTrading() {
		System.out.println("\nOffers Currently for Trade: ");
		for(Offering i: trades.offeringList) // Iterator here
			System.out.println(i.name);
	}

	public void decideBidding() {

	}

	public void discussBidding() {

	}

	public void submitBidding() {

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
				String data1[]= data.split(":");
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
					for(Product i: this.theProductList){ // Iterator here
						if(i.name.equalsIgnoreCase(data1[1])) {
							this.thePerson.productList.add(i);
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
		System.out.println("\n\n");
		for(Product i: this.thePerson.productList) { // Iterator here
			System.out.println(i.name);
		}
		System.out.println("Enter Product Name:");
		Scanner scan = new Scanner(System.in);
		String product_chosen = scan.next();
		for(Product i: this.thePerson.productList) { // Iterator here
			if(product_chosen.equalsIgnoreCase(i.name)) {
				return i;
			}
		}
		return null;
	}

	public void productOperation() {
		Product a = selectProduct();
		this.theSelectedProduct = a;
		System.out.println(a.name);
		if (a.category.equalsIgnoreCase("Meat"))
			this.thePerson.productMenu = this.thePerson.createProductMenu(0);
		else
			this.thePerson.productMenu = this.thePerson.createProductMenu(1);
		this.thePerson.showMenu();
		this.thePerson.showAddButton();
		this.thePerson.showViewButton();
		this.thePerson.showRadioButton();
		this.thePerson.showLabels();
		this.thePerson.showCombos();

		if(this.UserType==0){
			this.decideBidding();
			this.discussBidding();
			this.submitBidding();
		}

		else{
			this.addTrading();
			this.viewTrading();
		}
	}

	public void accept(NodeVisitor visitor) {
		System.out.println("Facade Reminder ...");
		visitor.visitFacade(this);
	}
}
