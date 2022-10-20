public class ProductIterator implements ListIterator {

	private ClassProductList productList;
	int pos=0;

	public boolean hasNext() {
		if (pos >= productList.size() || productList.get(pos) == null)
			return false;
		else
			return true;
	}

	public Product next() {
		Product product = (Product) productList.get(pos++);
		return product;
	}

	public void moveToHead() {
		pos=0;
	}
	public void remove() {
		productList.remove(pos);
	}

}
