public class Offering {
	String n;
	String t;
	int biddingAmount;
	String bidderName;
	String sellerName;
	Offering(String n, String t){
		this.biddingAmount = 0;
		this.n = n;
		this.t = t;
		this.sellerName = "Seller does not exist";
		this.bidderName = "Bidder does not exist";
	}
}
