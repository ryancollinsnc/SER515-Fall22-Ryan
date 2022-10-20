public class ReminderVisitor extends NodeVisitor {

	private Reminder m_Reminder;

	private ClassProductList classProductList;

	public void visitProduct(Product product) {
		System.out.println("Visiting product");
	}

	public void visitTrading(Trading trading) {
		System.out.println("Visiting trading");
	}

	public void visitFacade(Facade facade) {
		System.out.println("Visiting facade");
	}

	public void visitProductList(ClassProductList productList) {
		System.out.println("Visiting Product List");
	}


}
