import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;

import org.junit.Test;


/**
 * These are the tests that will test for correctness
 * of your code. You do not normally get these.
 */
public class TATests {

	@Test(timeout = 100)
	//@Worth(5) // these annotations are used by us to determine how much a method is worth
	public void hello() {
		assertEquals("Hello World", SimpleClass.hello());
	}
	
	@Test(timeout = 100)
	//@Worth(5)
	public void helloName() {
		assertEquals("Hello Kyle", SimpleClass.hello("Kyle"));
	}
	
	@Test(timeout = 100)
	//@Worth(3)
	public void helloThing1() {
		assertEquals("Hello 5", SimpleClass.helloThing(new Integer(5)));
	}
	
	@Test(timeout = 100)
	//@Worth(2)
	public void helloThing2() {
		assertEquals("Hello null", SimpleClass.helloThing(null));
	}
	
	@Test(timeout = 100)
	//@Worth(3)
	public void same1() {
		assertTrue(SimpleClass.same("ab", "ab"));
	}
	
	@Test(timeout = 100)
	//@Worth(3)
	public void same2() {
		assertFalse(SimpleClass.same("ab", null));
	}
	
	@Test(timeout = 100)
	//@Worth(3)
	public void same3() {
		assertFalse(SimpleClass.same(null, "ab"));
	}
	
	@Test(timeout = 100)
	//@Worth(3)
	public void same4() {
		assertTrue(SimpleClass.same(null, null));
	}
	
	@Test(timeout = 100)
	//@Worth(3)
	public void same5() {
		assertFalse(SimpleClass.same("ab", "AB"));
	}
	
	@Test(timeout = 100)
	//@Worth(3)
	public void strictlyLess1() {
		assertTrue(SimpleClass.strictlyLess(new Integer(1), new Integer(2)));
	}
	
	@Test(timeout = 100)
	//@Worth(3)
	public void strictlyLess2() {
		assertTrue(SimpleClass.strictlyLess(new Integer(-1), new Integer(Integer.MAX_VALUE)));
	}
	
	@Test(timeout = 100)
	//@Worth(3)
	public void strictlyLess3() {
		assertTrue(SimpleClass.strictlyLess(new Integer(Integer.MIN_VALUE + 1), new Integer(Integer.MAX_VALUE)));
	}

	@Test(timeout = 100)
	//@Worth(3)
	public void strictlyLess4() {
		assertFalse(SimpleClass.strictlyLess(new Integer(-1), new Integer(Integer.MIN_VALUE)));
	}
	
	@Test(timeout = 100)
	//@Worth(3)
	public void strictlyLess5() {
		assertFalse(SimpleClass.strictlyLess(new Integer(Integer.MAX_VALUE), new Integer(0)));
	}
	
	@Test(timeout = 100)
	//@Worth(3)
	public void strictlyLess6() {
		assertTrue(SimpleClass.strictlyLess("A", "BB", stringCompNoNulls()));
	}
	
	@Test(timeout = 100)
	//@Worth(3)
	public void strictlyLess7() {
		assertFalse(SimpleClass.strictlyLess("AAA", "BB", stringCompNoNulls()));
	}
	
	@Test(timeout = 100, expected = NullPointerException.class)
	//@Worth(3)
	public void strictlyLess8() {
		SimpleClass.strictlyLess("A", null, stringCompNoNulls());
	}
	
	@Test(timeout = 100)
	//@Worth(3)
	public void strictlyLess9() {
		assertTrue(SimpleClass.strictlyLess(null, "BB", stringCompWithNulls()));
	}
	
	@Test(timeout = 100)
	//@Worth(3)
	public void strictlyLess10() {
		assertFalse(SimpleClass.strictlyLess("AAA", null, stringCompWithNulls()));
	}
	
	@Test(timeout = 100)
	//@Worth(5)
	public void invert1() {
		assertEquals(new Pair<String, Integer>("A", 1), PairTools.invert(new Pair<Integer, String>(1, "A")));
	}
	
	@Test(timeout = 100)
	//@Worth(5)
	public void invert2() {
		assertEquals(new Pair<String, Integer>("Apple", null), PairTools.invert(new Pair<Integer, String>(null, "Apple")));
	}
	
	@Test(timeout = 100)
	//@Worth(5)
	public void copyA1() {
		assertEquals(new Pair<String, String>("A", "A"), PairTools.copyA(new Pair<String, Long>("A", 0xDEADBEEFL)));
	}
	
	@Test(timeout = 100)
	//@Worth(5)
	public void copyA2() {
		assertEquals(new Pair<String, String>("A", "A"), PairTools.copyA(new Pair<String, Long>("A", null)));
	}
	
	@Test(timeout = 100)
	//@Worth(5)
	public void copyA3() {
		assertEquals(new Pair<String, String>(null, null), PairTools.copyA(new Pair<String, Long>(null, 0xDEADBEEFL)));
	}
	
	@Test(timeout = 100)
	//@Worth(5)
	public void compose1() {
		assertEquals(new Pair<Pair<String, Integer>, Pair<String, Integer>>(new Pair<String, Integer>("A", 1), new Pair<String, Integer>("b", 2)), PairTools.compose("A", 1, "b", 2));
	}
	
	@Test(timeout = 100)
	//@Worth(5)
	public void compose2() {
		assertEquals(new Pair<Pair<String, Integer>, Pair<String, Integer>>(new Pair<String, Integer>("A", 1), new Pair<String, Integer>(null, 2)), PairTools.compose("A", 1, null, 2));
	}
	
	@Test(timeout = 100)
	//@Worth(5)
	public void compose3() {
		assertEquals(new Pair<Pair<String, Integer>, Pair<Integer, Integer>>(new Pair<String, Integer>("A", 1), new Pair<Integer, Integer>(2, null)), PairTools.compose("A", 1, 2, null));
	}
	
	/*
	 * Some comparators
	 */
	
	private static Comparator<String> stringCompNoNulls() {
		// compares strings by their length
		return new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if (o1 == null || o2 == null) {
					throw new NullPointerException("Attempting to compare null Strings");
				}
				return Integer.compare(o1.length(), o2.length());
			}
		};
	}
	
	private static Comparator<String> stringCompWithNulls() {
		// compares strings by their length
		return new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				// this treats a null string of having length 0
				return Integer.compare(o1 == null ? 0 : o1.length(), o2 == null ? 0 : o2.length());
			}
		};
	}
}
