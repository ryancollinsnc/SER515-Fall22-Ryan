public class Product {
	String n;
	String t;
	Product(String n, String t){
		this.n = n;
		this.t = t;
	}
	public void accept(NodeVisitor visitor) {
		System.out.println("Product Reminder ...");
		visitor.visitProduct();
	}
}
