public class Trading {

	OfferingList offeringList = new OfferingList();
	ReminderVisitor reminderVisitor;

	public void accept(NodeVisitor visitor) {
		System.out.println("Trading Reminder ...");
		visitor.visitTrading();
	}

}
