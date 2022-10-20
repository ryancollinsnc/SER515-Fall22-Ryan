public class ProductIterator implements ListIterator {

	private ClassProductList productList;
	int pos=0;

	public ProductIterator(ClassProductList theProductList) {
		this.productList=theProductList;
	}


	public boolean hasNext() {
		if (pos >= productList.size() || productList.get(pos) == null)
			return false;
		else
			return true;
	}

	public Product next() {
		Product product = productList.get(pos);
		pos+=1;
		return product;
	}

	public void moveToHead() {
		pos=0;
	}
	public void remove() {
		productList.remove(pos);
	}

}
