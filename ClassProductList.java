
import java.util.ArrayList;
public class ClassProductList extends ArrayList<Product> {

	public void accept(NodeVisitor visitor) {

		System.out.println("Class Product List Reminder ...");
		visitor.visitProduct();
	}

}
