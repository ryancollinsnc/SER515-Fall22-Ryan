public class Product {

	private Trading trading;

	String name;
	String category;

	Product(String name, String category){
		this.name = name;
		this.category = category;
	}

	public void accept(NodeVisitor visitor) {
		System.out.println("Product Reminder ...");
		visitor.visitProduct(this);
	}

}
