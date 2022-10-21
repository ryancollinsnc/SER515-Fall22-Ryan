public class OfferingIterator implements ListIterator{
	OfferingList offeringList;
	int index=0;
	public OfferingIterator(OfferingList offeringList) {
		this.offeringList=offeringList;
	}
	public boolean hasNext() {
		return index < offeringList.size() && offeringList.get(index) != null;
	}
	public Offering next() {
		Offering offering = offeringList.get(index);
		index+=1;
		return offering;
	}
	public void moveToHead() {
		index = 0;
	}
}
