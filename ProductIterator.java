public class ProductIterator implements ListIterator {
	ClassProductList productList;
	int index=0;
	public ProductIterator(ClassProductList theProductList) {
		this.productList=theProductList;
	}
	public boolean hasNext() {
		return index < productList.size() && productList.get(index) != null;
	}
	public Product next() {
		Product product = productList.get(index);
		index+=1;
		return product;
	}
	public void moveToHead() {
		index=0;
	}
}
