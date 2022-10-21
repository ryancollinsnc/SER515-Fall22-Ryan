
import java.util.ArrayList;
public class ClassProductList extends ArrayList<Product> {

	private ReminderVisitor reminderVisitor;
	private ProductIterator productIterator;
	public void accept(NodeVisitor visitor) {
		System.out.println("Class Product List Reminder ...");
	}

}
