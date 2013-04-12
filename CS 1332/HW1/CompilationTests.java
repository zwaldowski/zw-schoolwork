import java.util.Comparator;

import org.junit.Test;


/**
 * This file will be provided with most homework assignments and
 * just calls every method to check for compilation issues, you
 * should re-download it and run it right before submitting. It
 * is pretty common that people accidently change the types of
 * parameters or return types of methods on accident.
 */
public class CompilationTests {

	@SuppressWarnings("unused")
	@Test
	public void test() {
		Pair<String, String> p1 = new Pair<>("A", "B");
		Pair<String, String> p2 = new Pair<>("A", "B");
		Pair<String, Integer> p3 = new Pair<>("A", 1);

		String s;
		s= SimpleClass.hello();
		s = SimpleClass.hello("Kyle");
		s = SimpleClass.helloThing(p1);
		
		boolean b;
		b = SimpleClass.same("A", "B");
		b = SimpleClass.strictlyLess(new Integer(1), new Integer(2));
		
		// this is how you do an anonymous inner class
		b = SimpleClass.strictlyLess(p2, p3, new Comparator<Pair<String, ?>>() {
			@Override
			public int compare(Pair<String, ?> o1, Pair<String, ?> o2) {
				return Integer.compare(o1.a.length(), o2.a.length());
			}
		});

		Pair<Pair<String, Pair<String, String>>, Pair<Pair<String, String>, Pair<String, Integer>>> p4 = PairTools.compose("A", p1, p2, p3);
		Pair<String, String> p5 = PairTools.copyA(p3);
		Pair<Integer, String> p6 = PairTools.invert(p3);
	}

}
