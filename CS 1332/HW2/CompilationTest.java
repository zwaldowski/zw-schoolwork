import java.util.Collections;

import org.junit.Test;

/*
 * This is a compilation test, it passes if it compiles. Running
 * the test is not necessary, and may not actually pass when you
 * run it.
 */
public class CompilationTest {

	@Test
	public void test() {
		List<String> l1 = new LinkedList<>();
		l1.add("A");
		l1.addAll(Collections.<String>emptySet());
		l1.clear();
		boolean b = l1.contains(null);
		String s = l1.get(0);
		int i = l1.indexOf(null);
		b = l1.isEmpty();
		s = l1.remove(0);
		s = l1.remove(null);
		l1.set(0, null);
		i = l1.size();*/
		
		/*LinkedList<String> l2 = new LinkedList<>();
		l2.setSize(0);
		l2.setHead(new Node<String>("A"));
		Node<String> n = l2.getHead();
		TwistList<String> l3 = new TwistList<>();
		l3.reverse(0, 0);
		l3.flipFlop(0);
		l3.swing(0);
	}
	

}
