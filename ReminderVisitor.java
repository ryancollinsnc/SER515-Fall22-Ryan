public class ReminderVisitor extends NodeVisitor {

	private Reminder m_Reminder;

	private ClassProductList classProductList;
	Trading trades;

	ReminderVisitor(Trading trades){
		this.trades = trades;
	}

	public void visitProduct() {
		OfferingIterator itr = new OfferingIterator(trades.offeringList);
		System.out.println("\n<<ITERATOR DESIGN PATTERN USED HERE TO ITERATE ELEMENTS>>");
		while(itr.hasNext()) {
			Offering o = itr.next();
			System.out.println("\n\nVisiting product trade:\t" + o.name);
			System.out.println("Seller: " + o.seller_name);
			System.out.println("Bid: " + o.bid);
			System.out.println("Current Bid Holder: " + o.bid_name);
		}
	}

	public void visitTrading() {
		System.out.println("\n\nVisiting Trading Times");
		System.out.println("All Products timestamp have been checked and expired products are removed");
	}

	public void visitFacade(Facade facade) {
		System.out.println("\n\nVisiting facade's product list:\n");
		ProductIterator itr = new ProductIterator(facade.theProductList);
		System.out.println("\n<<ITERATOR DESIGN PATTERN USED HERE TO ITERATE ELEMENTS>>");
		while(itr.hasNext()){
			Product p = itr.next();
			System.out.println("Visited product:" + p.name);
		}
	}

}
