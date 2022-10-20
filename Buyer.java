public class Buyer extends Person {

	public void showMenu() {
		System.out.println("\nBuyer Menu Items: ");
		productMenu.showMenu();
	}

	public ProductMenu createProductMenu(int menuType){
		if(menuType==0) {
			return new MeatProductMenu();
		}
		else
			return new ProduceProductMenu();
	}
}
