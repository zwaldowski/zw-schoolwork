import java.util.ArrayList;
import org.junit.Test;

public class CompilationTest {

	@SuppressWarnings("unused")
	@Test
	public void test() {
		AVL<String> avl = new AVL<String>();
		avl.add("");
		avl.addAll(new ArrayList<String>());
		avl.clear();
		boolean b = avl.contains("");
		AVL.Node<String> r = avl.getRoot();
		b = avl.isEmpty();
		String s = avl.remove("");
		avl.setRoot(r);
		avl.setSize(0);
		int i = avl.size();
	}
}



