public class Seller extends Person {
	public void showMenu() {
		System.out.println("\nSeller Menu: ");
		productMenu.showMenu();
	}
	public ProductMenu createProductMenu(int mt){
		if(mt!=0)
			return new ProduceProductMenu();
		else
			return new MeatProductMenu();
	}
}
