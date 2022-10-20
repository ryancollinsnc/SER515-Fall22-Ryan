public class OfferingIterator implements ListIterator{

	private OfferingList offeringList;
	int pos=0;

	OfferingIterator(OfferingList offeringList){
		this.offeringList = offeringList;
	}

	public boolean hasNext() {
		if (pos >= offeringList.size() || offeringList.get(pos) == null)
			return false;
		else
			return true;
	}

	public Offering next() {
		Offering offering = (Offering) offeringList.get(pos++);
		return offering;
	}

	public void moveToHead() {
		pos = 0;
	}
	public void remove() {
		offeringList.remove(pos);
	}

}
