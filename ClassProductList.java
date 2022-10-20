import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
public class ClassProductList extends ArrayList {

	private ReminderVisitor reminderVisitor;

	private ProductIterator productIterator;
	public void accept(NodeVisitor visitor) {
		System.out.println("Class Product List Reminder ...");
		visitor.visitProductList(this);
	}

}
