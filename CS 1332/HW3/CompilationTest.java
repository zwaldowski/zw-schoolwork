import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/*
 * This is a compilation test, it passes if it compiles. Running
 * the test is not necessary, and may not actually pass when you
 * run it.
 */
public class CompilationTest {

	@SuppressWarnings("unused")
	@Test
	public void test() {
		BST<String> bst = new BST<>();
		bst.add("");
		bst.addAll(new ArrayList<String>());
		bst.clear();
		boolean b = bst.contains("");
		BST.Node<String> r = bst.getRoot();
		List<String> l = bst.inOrder();
		l = bst.preOrder();
		l = bst.postOrder();
		b = bst.isEmpty();
		bst.reconstruct(l, l);
		String s = bst.remove("");
		bst.setRoot(r);
		bst.setSize(0);
		int i = bst.size();
	}

}
