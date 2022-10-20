public class Product {

	private Trading trading;

	private ClassProductList classProductList;

	public void accept(NodeVisitor visitor) {
		System.out.println("Product Reminder ...");
		visitor.visitProduct(this);
	}

}
