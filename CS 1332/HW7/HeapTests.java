import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

import struct.Worth;

public class HeapTests {

	//@Worth(points = 10)
	@Test(timeout = 300)
	public void test0() {
		BinaryHeap<Thing> h = binaryHeap(1, 2, 3);
		assertEquals(thing(1), h.peek());
	}
	
	//@Worth(points = 10)
	@Test(timeout = 300)
	public void test1() {
		BinaryHeap<Thing> h = binaryHeap(3, 2, 1);
		assertEquals(thing(1), h.peek());
	}
	
	//@Worth(points = 10)
	@Test(timeout = 300)
	public void test2() {
		BinaryHeap<Thing> h = binaryHeap(2, 1, 3);
		assertEquals(thing(1), h.peek());
	}
	
	//@Worth(points = 5)
	@Test(timeout = 300)
	public void test3() {
		BinaryHeap<Thing> h = inverseHeap(1, 2, 3);
		assertEquals(thing(3), h.peek());
	}
	
	//@Worth(points = 5)
	@Test(timeout = 300)
	public void test4() {
		BinaryHeap<Thing> h = inverseHeap(3, 2, 1);
		assertEquals(thing(3), h.peek());
	}
	
	//@Worth(points = 5)
	@Test(timeout = 300)
	public void test5() {
		BinaryHeap<Thing> h = inverseHeap(2, 1, 3);
		assertEquals(thing(3), h.peek());
	}
	
	//@Worth(points = 10)
	@Test(timeout = 300)
	public void test6() {
		BinaryHeap<Thing> h = binaryHeap(5, 1, 2, 3, 4, 0);
		for (Thing t : list(0, 1, 2, 3, 4, 5)) {
			assertEquals(t, h.remove());
		}
	}
	
	//@Worth(points = 10)
	@Test(timeout = 300)
	public void test7() {
		BinaryHeap<Thing> h = binaryHeap(0, 2, 5, 3, 1, 4);
		for (Thing t : list(0, 1, 2, 3, 4, 5)) {
			assertEquals(t, h.remove());
		}
	}
	
	//@Worth(points = 5)
	@Test(timeout = 300)
	public void test8() {
		BinaryHeap<Thing> h = inverseHeap(5, 1, 2, 3, 4, 0);
		for (Thing t : list(5, 4, 3, 2, 1)) {
			assertEquals(t, h.remove());
		}
	}
	
	//@Worth(points = 5)
	@Test(timeout = 300)
	public void test9() {
		BinaryHeap<Thing> h = binaryHeap(5, 1, 2, 3, 4, 0);
		assertEquals(thing(0), h.remove());
		h.add(thing(0));
		assertEquals(thing(0), h.remove());
	}
	
	//@Worth(points = 5)
	@Test(timeout = 300)
	public void test10() {
		BinaryHeap<Thing> h = binaryHeap(5, 1, 2, 3, 4);
		assertEquals(thing(1), h.remove());
		h.add(thing(0));
		assertEquals(thing(0), h.remove());
	}
	
	//@Worth(points = 5)
	@Test(timeout = 300)
	public void test11() {
		BinaryHeap<Thing> h = binaryHeap(5, 1, 2, 3, 4);
		assertEquals(thing(1), h.remove());
		h.add(thing(10));
		assertEquals(thing(2), h.remove());
		assertEquals(thing(3), h.remove());
		h.add(thing(0));
		assertEquals(thing(0), h.remove());
	}
	
	//@Worth(points = 5)
	@Test(timeout = 300)
	public void test12() {
		BinaryHeap<Thing> h = binaryHeap(5, 1, 2, 3, 4);
		assertEquals(thing(1), h.remove());
		assertEquals(thing(2), h.remove());
		assertEquals(thing(3), h.remove());
		h.add(thing(0));
		assertEquals(thing(0), h.remove());
		assertEquals(thing(4), h.remove());
		h.add(thing(0));
		assertEquals(thing(0), h.remove());
		h.add(thing(-1));
		assertEquals(thing(-1), h.remove());
	}
	
	//@Worth(points = 5)
	@Test(timeout = 300)
	public void test13() {
		BinaryHeap<Thing> h = binaryHeap();
		assertTrue(h.isEmpty());
		h.add(thing(0));
		assertFalse(h.isEmpty());
	}
	
	//@Worth(points = 2)
	@Test(timeout = 300)
	public void test14() {
		BinaryHeap<Thing> h = binaryHeap();
		assertEquals(0, h.size());
		h.add(thing(0));
		assertEquals(1, h.size());
		h.add(thing(1));
		assertEquals(2, h.size());
	}
	
	//@Worth(points = 3)
	@Test(timeout = 300)
	public void test15() {
		BinaryHeap<Thing> h = binaryHeap();
		assertEquals(0, h.size());
		h.add(thing(0));
		assertEquals(1, h.size());
		h.remove();
		assertEquals(0, h.size());
	}
	
	/*
	 * Methods for testing
	 */
	private BinaryHeap<Thing> binaryHeap(int...ints) {
		BinaryHeap<Thing> bb = new BinaryHeap<>();
		for (int i : ints) {
			bb.add(thing(i));
		}
		return bb;
	}
	
	private BinaryHeap<Thing> inverseHeap(int...ints) {
		BinaryHeap<Thing> bb = new BinaryHeap<>(new Comparator<Thing>() {
			@Override
			public int compare(Thing o1, Thing o2) {
				return Integer.compare(o2.i, o1.i);
			}
		});
		
		for (int i : ints) {
			bb.add(thing(i));
		}
		
		return bb;
	}
	
	private List<Thing> list(int...ints) {
		List<Thing> list = new ArrayList<>();
		for (int i : ints) {
			list.add(thing(i));
		}
		return list;
	}
	
	/**
	 * Returns a thing representing the given integer
	 */
	private Thing thing(int i) {
		return new Thing(i);
	}
	
	/**
	 * A comparable class used for testing, gives each object
	 * created a unique identifier, that is not factored into
	 * equals or compareTo. Useful for testing purposes.
	 */
	public static class Thing implements Comparable<Thing> {
		private static int counter = 0;
		
		final int id = counter++;
		int i;
		
		public Thing(int i) {
			this.i = i;
		}
		
		@Override
		public int compareTo(Thing tht) {
			return Integer.compare(i, tht.i);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Thing) {
				return ((Thing) obj).i == i;
			}
			return false;
		}
		
		@Override
		public int hashCode() {
			return i;
		}
		
		@Override
		public String toString() {
			return i + " (" + id + ")";
		}
	}
}
