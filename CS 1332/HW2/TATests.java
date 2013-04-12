import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class TATests {

	@Test(timeout = 300)
	//@Worth(points = 2)
	public void testAdd0() {
		LinkedList<Thing> l = ll(0);
		l.add(t(0));
		Node<Thing> r = l.getHead();
		assertEquals(t(0), a(r, 0));
		assertEquals(t(0), a(r, 1));
		assertEquals(1, l.size());
	}
	
	@Test(timeout = 300)
	//@Worth(points = 2)
	public void testAdd1() {
		LinkedList<Thing> l = ll(1);
		l.add(t(1));
		Node<Thing> r = l.getHead();
		assertEquals(t(0), a(r, 0));
		assertEquals(t(1), a(r, 1));
		assertEquals(t(0), a(r, 2));
	}
	
	@Test(timeout = 300)
	//@Worth(points = 3)
	public void testAdd2() {
		LinkedList<Thing> l = ll(2);
		l.add(t(2));
		Node<Thing> r = l.getHead();
		assertEquals(t(0), a(r, 0));
		assertEquals(t(1), a(r, 1));
		assertEquals(t(2), a(r, 2));
		assertEquals(t(0), a(r, 3));
	}
	
	@Test(timeout = 300)
	//@Worth(points = 3)
	public void testAdd3() {
		LinkedList<Thing> l = ll(5);
		l.add(t(5));
		Node<Thing> r = l.getHead();
		assertEquals(t(0), a(r, 0));
		assertEquals(t(1), a(r, 1));
		assertEquals(t(2), a(r, 2));
		assertEquals(t(3), a(r, 3));
		assertEquals(t(4), a(r, 4));
		assertEquals(t(5), a(r, 5));
		assertEquals(t(0), a(r, 6));
	}
	
	@Test(timeout = 300)
	//@Worth(points = 4)
	public void testAddAll() {
		ArrayList<Thing> al = new ArrayList<>();
		al.add(t(5));
		al.add(t(6));
		al.add(t(7));
		LinkedList<Thing> l = ll(5);
		l.addAll(al);
		Node<Thing> r = l.getHead();
		assertEquals(t(0), a(r, 0));
		assertEquals(t(1), a(r, 1));
		assertEquals(t(2), a(r, 2));
		assertEquals(t(3), a(r, 3));
		assertEquals(t(4), a(r, 4));
		assertEquals(t(5), a(r, 5));
		assertEquals(t(6), a(r, 6));
		assertEquals(t(7), a(r, 7));
		assertEquals(t(0), a(r, 8));
	}

	@Test(timeout = 300)
	//@Worth(points = 2)
	public void testClear() {
		LinkedList<Thing> l = ll(5);
		assertEquals(5, l.size());
		l.clear();
		assertEquals(0, l.size());
		assertEquals(null, l.getHead());
	}

	@Test(timeout = 300)
	//@Worth(points = 3)
	public void testContains0() {
		LinkedList<Thing> l = ll(5);
		assertTrue(l.contains(t(0)));
	}
	
	@Test(timeout = 300)
	//@Worth(points = 3)
	public void testContains1() {
		LinkedList<Thing> l = ll(5);
		assertTrue(l.contains(t(3)));
	}
	
	@Test(timeout = 300)
	//@Worth(points = 4)
	public void testContains2() {
		LinkedList<Thing> l = ll(5);
		assertEquals(5, l.size());
		assertFalse(l.contains(t(6)));
	}
	
	@Test(timeout = 300)
	//@Worth(points = 3)
	public void testGet0() {
		LinkedList<Thing> l = ll(1);
		assertEquals(t(0), l.get(0));
	}
	
	@Test(timeout = 300)
	//@Worth(points = 3)
	public void testGet1() {
		LinkedList<Thing> l = ll(2);
		assertEquals(t(0), l.get(0));
		assertEquals(t(1), l.get(1));
	}
	
	@Test(timeout = 300)
	//@Worth(points = 4)
	public void testGet2() {
		LinkedList<Thing> l = ll(6);
		assertEquals(t(0), l.get(0));
		assertEquals(t(1), l.get(1));
		assertEquals(t(5), l.get(5));
	}

	@Test(timeout = 300)
	//@Worth(points = 2)
	public void testIndexOf0() {
		LinkedList<Thing> l = ll(1);
		assertEquals(0, l.indexOf(t(0)));
		assertEquals(1, l.size());
	}
	
	@Test(timeout = 300)
	//@Worth(points = 2)
	public void testIndexOf1() {
		LinkedList<Thing> l = ll(2);
		assertEquals(0, l.indexOf(t(0)));
		assertEquals(1, l.indexOf(t(1)));
	}

	@Test(timeout = 300)
	//@Worth(points = 3)
	public void testIndexOf2() {
		LinkedList<Thing> l = ll(6);
		assertEquals(0, l.indexOf(t(0)));
		assertEquals(1, l.indexOf(t(1)));
		assertEquals(5, l.indexOf(t(5)));
	}

	@Test(timeout = 300)
	//@Worth(points = 3)
	public void testIndexOf3() {
		LinkedList<Thing> l = ll(6);
		assertEquals(0, l.indexOf(t(0)));
		assertEquals(1, l.indexOf(t(1)));
		assertEquals(5, l.indexOf(t(5)));
		assertEquals(-1, l.indexOf(t(6)));
	}
	

	@Test(timeout = 300)
	//@Worth(points = 2)
	public void testIsEmpty() {
		LinkedList<Thing> ll = ll(3);
		assertFalse(ll.isEmpty());
		ll = ll(0);
		assertTrue(ll.isEmpty());
	}

	@Test(timeout = 300)
	//@Worth(points = 2)
	public void testRemoveInt0() {
		LinkedList<Thing> ll = ll(1);
		Node<Thing> r = ll.getHead();
		assertEquals(t(0), a(r, 0));
		assertEquals(t(0), a(r, 1));
		ll.remove(0);
		r = ll.getHead();
		assertEquals(null, r);
		assertTrue(ll.isEmpty());
	}
	
	@Test(timeout = 300)
	//@Worth(points = 2)
	public void testRemoveInt1() {
		LinkedList<Thing> ll = ll(2);
		Node<Thing> r = ll.getHead();
		assertEquals(t(0), a(r, 0));
		assertEquals(t(1), a(r, 1));
		assertEquals(t(0), a(r, 2));
		ll.remove(0);
		r = ll.getHead();
		assertEquals(t(1), a(r, 0));
		assertEquals(t(1), a(r, 1));
	}
	
	@Test(timeout = 300)
	//@Worth(points = 2)
	public void testRemoveInt2() {
		LinkedList<Thing> ll = ll(5);
		Node<Thing> r = ll.getHead();
		assertEquals(t(0), a(r, 0));
		assertEquals(t(1), a(r, 1));
		assertEquals(t(2), a(r, 2));
		assertEquals(t(3), a(r, 3));
		assertEquals(t(4), a(r, 4));
		assertEquals(t(0), a(r, 5));
		ll.remove(2);
		r = ll.getHead();
		assertEquals(t(0), a(r, 0));
		assertEquals(t(1), a(r, 1));
		assertEquals(t(3), a(r, 2));
		assertEquals(t(4), a(r, 3));
		assertEquals(t(0), a(r, 4));
		assertEquals(4, ll.size());
	}
	
	@Test(timeout = 300)
	//@Worth(points = 2)
	public void testRemoveInt3() {
		LinkedList<Thing> ll = ll(5);
		Node<Thing> r = ll.getHead();
		assertEquals(t(0), a(r, 0));
		assertEquals(t(1), a(r, 1));
		assertEquals(t(2), a(r, 2));
		assertEquals(t(3), a(r, 3));
		assertEquals(t(4), a(r, 4));
		assertEquals(t(0), a(r, 5));
		ll.remove(0);
		r = ll.getHead();
		assertEquals(t(1), a(r, 0));
		assertEquals(t(2), a(r, 1));
		assertEquals(t(3), a(r, 2));
		assertEquals(t(4), a(r, 3));
		assertEquals(t(1), a(r, 4));
	}
	
	@Test(timeout = 300)
	//@Worth(points = 2)
	public void testRemoveInt4() {
		LinkedList<Thing> ll = ll(5);
		Node<Thing> r = ll.getHead();
		assertEquals(t(0), a(r, 0));
		assertEquals(t(1), a(r, 1));
		assertEquals(t(2), a(r, 2));
		assertEquals(t(3), a(r, 3));
		assertEquals(t(4), a(r, 4));
		assertEquals(t(0), a(r, 5));
		ll.remove(4);
		r = ll.getHead();
		assertEquals(t(0), a(r, 0));
		assertEquals(t(1), a(r, 1));
		assertEquals(t(2), a(r, 2));
		assertEquals(t(3), a(r, 3));
		assertEquals(t(0), a(r, 4));
	}
	
	@Test(timeout = 300)
	//@Worth(points = 2)
	public void testRemoveObject0() {
		LinkedList<Thing> l = ll(1);
		assertEquals(1, l.size());
		assertEquals(t(0), l.remove(t(0)));
		assertEquals(0, l.size());
	}
	
	@Test(timeout = 300)
	//@Worth(points = 2)
	public void testRemoveObject1() {
		LinkedList<Thing> l = ll(3);
		assertEquals(t(1), l.remove(t(1)));
		assertEquals(2, l.size());
	}
	
	@Test(timeout = 300)
	//@Worth(points = 2)
	public void testRemoveObject2() {
		LinkedList<Thing> l = ll(10);
		assertEquals(t(9), l.remove(t(9)));
		assertEquals(9, l.size());
	}
	
	@Test(timeout = 300)
	//@Worth(points = 4)
	public void testRemoveObject3() {
		LinkedList<Thing> l = ll(10);
		Thing h = l.getHead().getData();
		Thing r = l.remove(t(0));
		assertEquals(h.id, r.id);
		assertEquals(9, l.size());
	}
	
	@Test(timeout = 300)
	//@Worth(points = 1)
	public void testSet0() {
		LinkedList<Thing> l = ll(1);
		l.set(0, t(1));
		Node<Thing> r = l.getHead();
		assertEquals(t(1), a(r, 0));
		assertEquals(t(1), a(r, 1));
	}
	
	@Test(timeout = 300)
	//@Worth(points = 1)
	public void testSet1() {
		LinkedList<Thing> l = ll(4);
		l.set(3, t(10));
		Node<Thing> r = l.getHead();
		assertEquals(t(0), a(r, 0));
		assertEquals(t(1), a(r, 1));
		assertEquals(t(2), a(r, 2));
		assertEquals(t(10), a(r, 3));
	}
	
	@Test(timeout = 300)
	//@Worth(points = 3)
	public void testSet2() {
		LinkedList<Thing> l = ll(4);
		l.set(2, t(8));
		l.set(3, t(9));
		l.set(3, t(10));
		Node<Thing> r = l.getHead();
		assertEquals(t(0), a(r, 0));
		assertEquals(t(1), a(r, 1));
		assertEquals(t(8), a(r, 2));
		assertEquals(t(10), a(r, 3));
	}

	@Test(timeout = 300)
	//@Worth(points = 2)
	public void testSize() {
		LinkedList<Thing> ll = ll(3);
		assertEquals(3, ll.size());
		ll.add(t(4));
		assertEquals(4, ll.size());
	}

	/*
	 * Twist list things
	 */
	
	@Test(timeout = 300)
	//@Worth(points = 5)
	public void testTwistAdd0() {
		TwistList<Thing> tl = tl(6);
		Node<Thing> r = tl.getHead();
		r.getNext().getNext().getNext().setData(t(8));
		tl.add(t(6));
		r = tl.getHead();
		assertEquals(t(6), a(r, 0));
		assertEquals(t(2), a(r, 1));
		assertEquals(t(1), a(r, 2));
		assertEquals(t(0), a(r, 3));
		assertEquals(t(5), a(r, 4));
		assertEquals(t(4), a(r, 5));
		assertEquals(t(8), a(r, 6));
		assertEquals(t(6), a(r, 7));
	}
	
	@Test(timeout = 300)
	//@Worth(points = 5)
	public void testTwistAdd1() {
		TwistList<Thing> tl = tl(2);
		Node<Thing> r = tl.getHead();
		assertEquals(t(0), a(r, 0));
		assertEquals(t(1), a(r, 1));
		assertEquals(t(0), a(r, 2));
		
		tl.add(t(4));
		r = tl.getHead();
		assertEquals(t(4), a(r, 0));
		assertEquals(t(1), a(r, 1));
		assertEquals(t(0), a(r, 2));
		assertEquals(t(4), a(r, 3));
		
		tl.add(t(10));
		r = tl.getHead();
		assertEquals(t(10), a(r, 0));
		assertEquals(t(0), a(r, 1));
		assertEquals(t(1), a(r, 2));
		assertEquals(t(4), a(r, 3));
		assertEquals(t(10), a(r, 4));
		
		tl.add(t(3));
		r = tl.getHead();
		assertEquals(t(3), a(r, 0));
		assertEquals(t(4), a(r, 1));
		assertEquals(t(1), a(r, 2));
		assertEquals(t(0), a(r, 3));
		assertEquals(t(10), a(r, 4));
		assertEquals(t(3), a(r, 5));
	}
	
	@Test(timeout = 300)
	//@Worth(points = 5)
	public void testTwistSwing0() {
		TwistList<Thing> tl = tl(5);
		tl.swing(3);
		Node<Thing> r = tl.getHead();
		assertEquals(t(3), a(r, 0));
		assertEquals(t(2), a(r, 1));
		assertEquals(t(1), a(r, 2));
		assertEquals(t(0), a(r, 3));
		assertEquals(t(4), a(r, 4));
		assertEquals(t(3), a(r, 5));
	}
	
	@Test(timeout = 300)
	//@Worth(points = 5)
	public void testTwistReverse0() {
		TwistList<Thing> tl = tl(5);
		tl.reverse(1, 3);
		Node<Thing> r = tl.getHead();
		assertEquals(t(0), a(r, 0));
		assertEquals(t(3), a(r, 1));
		assertEquals(t(2), a(r, 2));
		assertEquals(t(1), a(r, 3));
		assertEquals(t(4), a(r, 4));
		assertEquals(t(0), a(r, 5));
	}
	
	@Test(timeout = 300)
	//@Worth(points = 5)
	public void testTwistFlipFlop0() {
		TwistList<Thing> tl = tl(5);
		tl.flipFlop(2);
		Node<Thing> r = tl.getHead();
		assertEquals(t(3), a(r, 0));
		assertEquals(t(4), a(r, 1));
		assertEquals(t(0), a(r, 2));
		assertEquals(t(1), a(r, 3));
		assertEquals(t(2), a(r, 4));
		assertEquals(t(3), a(r, 5));
	}
	
	/*
	 * Some methods for testing
	 */
	
	
	/**
	 * constructs a linked list containing 0 to n-1 (inclusive)
	 */
	public LinkedList<Thing> ll(int n) {
		LinkedList<Thing> ll = new LinkedList<>();
		ll.setHead(l(n));
		ll.setSize(n);
		return ll;
	}
	
	/**
	 * constructs a twist list containing 0 to n-1 (inclusive)
	 */
	public TwistList<Thing> tl(int n) {
		TwistList<Thing> tl = new TwistList<>();
		tl.setHead(l(n));
		tl.setSize(n);
		return tl;
	}
	
	/**
	 * creates a circular list from 0 to n - 1 (inclusive)
	 * and returns the head node 
	 */
	public Node<Thing> l(int n) {
		if (n <= 0) return null;
		Node<Thing> h = n(0);
		Node<Thing> t = h;
		for (int i = 1; i < n; i++) {
			t.setNext(n(i));
			t = t.getNext();
		}
		t.setNext(h);
		return h;
	}
	
	/**
	 * Creates a node containing a thing
	 * representing the given int
	 */
	public Node<Thing> n(int i) {
		return new Node<>(t(i));
	}
	
	/**
	 * Manually advances the node n, a nodes forward,
	 * then returns the data
	 */
	public Thing a(Node<Thing> n, int a) {
		for (int i = 0; i < a; i++) {
			n = n.getNext();
		}
		return n.getData();
	}
	
	
	/**
	 * Prints out your Linked List
	 */
	public void print(LinkedList<?> l) {
		if (l == null) return;
		Node<?> n = l.getHead(), p = n;
		System.out.print("[");
		do {
			if (p == null) continue;
			System.out.print(p.getData() + ", ");
			p = p.getNext();
		} while(p != n && p != null);
		System.out.println("]");
	}
	
	/**
	 * Returns a thing representing the given integer
	 */
	public Thing t(int i) {
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
