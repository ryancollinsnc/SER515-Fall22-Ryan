public abstract class Person {

	ProductMenu productMenu;

	public abstract void showMenu();

	public void showAddButton() {
		this.productMenu.showAddButton();
	}

	public void showViewButton() {
		this.productMenu.showViewButton();
	}

	public void showRadioButton() {
		this.productMenu.showRadioButton();
	}

	public void showLabels() {
		this.productMenu.showLabels();
	}

	public void showCombos(){
		this.productMenu.showComboxes();
	}

	public abstract ProductMenu createProductMenu(int menuType);

}
