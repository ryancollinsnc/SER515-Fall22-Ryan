public class Reminder {
    Facade fac;

    static {
        new Facade();
    }

    public Reminder(Facade fac) {
        this.fac = fac;
    }
    public void remind() {
        ReminderVisitor rv = new ReminderVisitor(fac.trades);
        //Visitor Design Pattern
        rv.visitFacade(fac);
        rv.visitProduct();
        rv.visitTrading();
        ClassProductList cl = new ClassProductList();
        cl.accept(rv);
        fac.accept(rv);
        Product pr = new Product(" ", " ");
        pr.accept(rv);
        Trading tr = new Trading();
        tr.accept(rv);
    }
}
