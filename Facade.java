import java.util.*;
import java.io.*;

public class Facade{

	private int UserType;

	private Product theSelectedProduct;

	private int nProductCategory;

	private ClassProductList theProductList;

	private Person thePerson;

	public ArrayList<String> BuyerName = new ArrayList<>();

	public ArrayList<String> BuyerPass = new ArrayList<>();

	public ArrayList<String> SellerName = new ArrayList<>();

	public ArrayList<String> SellerPass = new ArrayList<>();

	public boolean login() {
		int temp=-1;
		try {
			File myObj = new File("BuyerInfo.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String data1[]= data.split(":");
				BuyerName.add(data1[0]);
				BuyerPass.add(data1[1]);
				//System.out.println(BuyerPass);
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
			if(BuyerName.get(i).equals(username)==true){
				temp=i;
				System.out.println(temp);
			}
		}
		if(password.equals(BuyerPass.get(temp))==true){
			UserType=0;
			System.out.println("Buyer");
			return true;
		}
		for(int i=0; i< SellerName.size();i++){
			if(SellerName.get(i).equals(username)==true){
				temp=i;
				System.out.println(temp);
			}
		}
		if(password.equals(SellerPass.get(temp))==true){
			UserType=1;
			System.out.println("Seller");
			return true;
		}
		System.out.println("No user");
		//UserType = validation(username, password);
		return false;
	}

	public void addTrading() {

	}

	public void viewTrading() {

	}

	public void decideBidding() {

	}

	public void discussBidding() {

	}

	public void submitBudding() {

	}

	public void remind() {

	}

	public void createUser(Person userinfoitem) {

	}

	public void createProductList() {

	}

	public void attachProductToUser() {

	}

	public Product selectProduct() {
		return null;
	}

	public void productOperation() {

	}

	public void accept(NodeVisitor visitor) {
		System.out.println("Facade Reminder ...");
	}
}
