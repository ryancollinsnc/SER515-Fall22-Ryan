public class Reminder {

    Facade fo = new Facade();
    public Reminder(Facade object) {
        this.fo=object;
    }

    public void remind() {
        ReminderVisitor rv = new ReminderVisitor();
        rv.visitFacade(fo);
        rv.visitProduct(fo.theSelectedProduct);
        rv.visitProductList(fo.theProductList);
        rv.visitTrading(fo.trades);
    }
}
