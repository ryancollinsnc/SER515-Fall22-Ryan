public class Offering {
	String name;
	String category;

	int bid;
	String bid_name;
	String seller_name;

	Offering(String name, String category){
		this.name = name;
		this.category = category;
		this.bid = 0;
		this.bid_name = "No name";
		this.seller_name = "no name";
	}
}
