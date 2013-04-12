import java.util.Collections;

import org.junit.Test;
import static org.junit.Assert.*;

/*
 * This is a compilation test, it passes if it compiles. Running
 * the test is not necessary, and may not actually pass when you
 * run it.
 */
public class ZachTest {

	@Test
	public void test() {
		List<String> l1 = new LinkedList<>();

		String eee = "E";
		String ccc = "C";

		l1.add("A");
		l1.add("B");
		l1.add(ccc);
		l1.add("D");
		l1.add(eee);
		l1.add("F");

		assertEquals(4, l1.indexOf(eee));
		assertTrue(l1.contains(eee));

		assertEquals("A", l1.get(0));
		assertEquals("B", l1.get(1));
		assertEquals(ccc, l1.get(2));
		assertEquals("D", l1.get(3));
		assertEquals(eee, l1.get(4));
		assertEquals("F", l1.get(5));

		assertEquals(eee, l1.remove(eee));

		assertEquals("A", l1.get(0));
		assertEquals("B", l1.get(1));
		assertEquals(ccc, l1.get(2));
		assertEquals("D", l1.get(3));
		assertEquals("F", l1.get(4));

		assertEquals(ccc, l1.remove(2));

		assertEquals("A", l1.get(0));
		assertEquals("B", l1.get(1));
		assertEquals("D", l1.get(2));
		assertEquals("F", l1.get(3));

		l1.set(3, eee);

		assertEquals(eee, l1.get(3));

		/*List<String> l1 = new LinkedList<>();
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
		l3.swing(0);*/
	}
	

}
