public class Buyer extends Person {

	public void showMenu() {
		System.out.println("\nBuyer Menu: ");
		productMenu.showMenu();
	}

	public ProductMenu createProductMenu(int mtype){
		if(mtype==1) {
			return new ProduceProductMenu();
		}
		else
			return new MeatProductMenu();
	}
}
