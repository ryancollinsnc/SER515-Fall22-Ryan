public class Trading {

	private OfferingList offeringList;

	private Product product;

	public void accept(NodeVisitor visitor) {
		System.out.println("Trading Reminder ...");
	}

}
