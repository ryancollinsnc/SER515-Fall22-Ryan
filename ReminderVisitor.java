public class ReminderVisitor extends NodeVisitor {
	Trading tradeList;
	ReminderVisitor(Trading trade){
		this.tradeList = trade;
	}
	public void visitProduct() {
		OfferingIterator itr = new OfferingIterator(tradeList.offeringList);
		//Iterator Design Pattern
		while(itr.hasNext()) {
			Offering off = itr.next();
			System.out.println();
			System.out.println("Product Name:\t" + off.n);
			System.out.println("Bidder: " + off.bidderName);
			System.out.println("Bidder for "+ off.n +" :" + off.biddingAmount);
			System.out.println(off.n + " Seller: " + off.sellerName);
		}
	}
	public void visitTrading() {
		System.out.println();
		System.out.println("Timestamps checked for trades");
	}
	public void visitFacade(Facade fac) {
		System.out.println("\n\nProduct List present in the Facade:\n");
		ProductIterator itr = new ProductIterator(fac.theProductList);
		//Iterator Design Pattern
		while(itr.hasNext()){
			Product pr = itr.next();
			System.out.println("Product:" + pr.n);
		}
	}
}
