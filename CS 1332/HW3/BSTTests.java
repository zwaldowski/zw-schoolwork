import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.Test;

import struct.Worth;

public class BSTTests {

	
	@Test(timeout = 300)
	//@Worth(points = 5)
	public void testAdd1() {
		BST<Thing> b = new BST<>();
		b.add(thing(1));
		b.add(thing(0));
		b.add(thing(2));
		b.add(thing(3));
		
		assertTrue(same(t1(), b));
	}
	
	@Test(timeout = 300)
	//@Worth(points = 5)
	public void testAdd2() {
		BST<Thing> b = new BST<>();
		b.add(thing(5));
		b.add(thing(1));
		b.add(thing(10));
		b.add(thing(3));
		b.add(thing(7));
		
		assertTrue(same(t2(), b));
	}

	@Test(timeout = 300)
	//@Worth(points = 5)
	public void testAdd3() {
		BST<Thing> b = new BST<>();
		b.add(thing(5));
		b.add(thing(1));
		b.add(thing(10));
		b.add(thing(3));
		b.add(thing(7));
		b.add(thing(0));
		b.add(thing(2));
		b.add(thing(8));
		b.add(thing(11));
		
		assertTrue(same(t3(), b));
	}

	@Test(timeout = 300)
	//@Worth(points = 5)
	public void testAddAll1() {
		BST<Thing> b = new BST<>();
		
		List<Thing> l = new ArrayList<>();
		l.add(thing(1));
		l.add(thing(0));
		l.add(thing(2));
		l.add(thing(3));
		b.addAll(l);
		
		assertTrue(same(t1(), b));
	}
	
	@Test(timeout = 300)
	//@Worth(points = 5)
	public void testAddAll2() {
		BST<Thing> b = new BST<>();
		
		List<Thing> l = new ArrayList<>();
		l.add(thing(5));
		l.add(thing(1));
		l.add(thing(10));
		l.add(thing(3));
		l.add(thing(7));
		b.addAll(l);
		
		assertTrue(same(t2(), b));
	}
	
	@Test(timeout = 300)
	//@Worth(points = 5)
	public void testRemove1() {
		BST<Thing> b = t1();
		Thing t = b.remove(thing(2));
		assertEquals(thing(2), t);
		
		BST.Node<Thing> n = node(1);
		n.setLeft(node(0));
		n.setRight(node(3));
		BST<Thing> b2 = new BST<>();
		b2.setRoot(n);
		b2.setSize(3);
		
		assertTrue(same(b2, b));
	}
	
	@Test(timeout = 300)
	//@Worth(points = 5)
	public void testRemove2() {
		BST<Thing> b = t2();
		Thing t = b.remove(thing(5));
		assertEquals(thing(5), t);
		
		BST.Node<Thing> n = node(3);
		n.setLeft(node(1));
		n.setRight(node(10));
		n.getRight().setLeft(node(7));
		BST<Thing> b2 = new BST<>();
		b2.setRoot(n);
		b2.setSize(4);
		
		assertTrue(same(b2, b));
	}
	
	@Test(timeout = 300)
	//@Worth(points = 5)
	public void testRemove3() {
		BST<Thing> b = t3();
		Thing t = b.remove(thing(5));
		assertEquals(thing(5), t);
		
		BST.Node<Thing> n = node(3);
		n.setLeft(node(1));
		n.getLeft().setLeft(node(0));
		n.getLeft().setRight(node(2));
		n.setRight(node(10));
		n.getRight().setLeft(node(7));
		n.getRight().setRight(node(11));
		n.getRight().getLeft().setRight(node(8));
		
		BST<Thing> b2 = new BST<>();
		b2.setRoot(n);
		b2.setSize(8);
		
		assertTrue(same(b2, b));
	}
	
	@Test(timeout = 300)
	//@Worth(points = 5)
	public void testRemove4() {
		BST<Thing> b = t1();
		Thing t1 = thing(2);
		Thing t2 = b.remove(t1);
		// Make sure you return the data from the tree, not the data given
		assertTrue(t1.id != t2.id);
	}

	@Test(timeout = 300)
	//@Worth(points = 2)
	public void testContains1() {
		assertTrue(t1().contains(thing(0)));
		assertFalse(t1().contains(thing(-1)));
	}
	
	@Test(timeout = 300)
	//@Worth(points = 2)
	public void testContains2() {
		assertTrue(t2().contains(thing(7)));
		assertFalse(t2().contains(thing(9)));
	}
	
	@Test(timeout = 300)
	//@Worth(points = 2)
	public void testContains3() {
		assertTrue(t3().contains(thing(3)));
		assertFalse(t3().contains(thing(4)));
	}

	@Test(timeout = 300)
	//@Worth(points = 4)
	public void testPreOrder1() {
		List<Thing> l = new ArrayList<>();
		l.add(thing(1));
		l.add(thing(0));
		l.add(thing(2));
		l.add(thing(3));
		assertEquals(l, t1().preOrder());
	}

	@Test(timeout = 300)
	//@Worth(points = 4)
	public void testPreOrder2() {
		List<Thing> l = new ArrayList<>();
		l.add(thing(5));
		l.add(thing(1));
		l.add(thing(0));
		l.add(thing(3));
		l.add(thing(2));
		l.add(thing(10));
		l.add(thing(7));
		l.add(thing(8));
		l.add(thing(11));
		assertEquals(l, t3().preOrder());
	}
	
	@Test(timeout = 300)
	//@Worth(points = 4)
	public void testInOrder1() {
		List<Thing> l = new ArrayList<>();
		l.add(thing(0));
		l.add(thing(1));
		l.add(thing(2));
		l.add(thing(3));
		assertEquals(l, t1().inOrder());
	}
	
	@Test(timeout = 300)
	//@Worth(points = 4)
	public void testInOrder2() {
		List<Thing> l = new ArrayList<>();
		l.add(thing(0));
		l.add(thing(1));
		l.add(thing(2));
		l.add(thing(3));
		l.add(thing(5));
		l.add(thing(7));
		l.add(thing(8));
		l.add(thing(10));
		l.add(thing(11));
		assertEquals(l, t3().inOrder());
	}

	@Test(timeout = 300)
	//@Worth(points = 4)
	public void testPostOrder1() {
		List<Thing> l = new ArrayList<>();
		l.add(thing(0));
		l.add(thing(3));
		l.add(thing(2));
		l.add(thing(1));
		assertEquals(l, t1().postOrder());
	}
	
	@Test(timeout = 300)
	//@Worth(points = 4)
	public void testPostOrder2() {
		List<Thing> l = new ArrayList<>();
		l.add(thing(0));
		l.add(thing(2));
		l.add(thing(3));
		l.add(thing(1));
		l.add(thing(8));
		l.add(thing(7));
		l.add(thing(11));
		l.add(thing(10));
		l.add(thing(5));
		assertEquals(l, t3().postOrder());
	}

	@Test(timeout = 300)
	//@Worth(points = 5)
	public void testIsEmpty() {
		assertTrue(new BST<>().isEmpty());
		assertFalse(t1().isEmpty());
	}

	@Test(timeout = 300)
	//@Worth(points = 5)
	public void testClear() {
		BST<Thing> b = t1();
		assertEquals(4, b.size());
		b.clear();
		assertEquals(0, b.size());
		assertTrue(b.isEmpty());
	}

	@Test(timeout = 300)
	//@Worth(points = 1)
	public void testSize1() {
		assertEquals(4, t1().size());
	}
	
	@Test(timeout = 300)
	//@Worth(points = 2)
	public void testSize2() {
		BST<Thing> b = t1();
		b.add(thing(5));
		assertEquals(5, b.size());
	}
	
	@Test(timeout = 300)
	//@Worth(points = 2)
	public void testSize3() {
		BST<Thing> b = t1();
		b.remove(thing(2));
		assertEquals(3, b.size());
	}

	@Test(timeout = 300)
	//@Worth(points = 5)
	public void testReconstruct1() {
		BST<Thing> b = new BST<Thing>();
		List<Thing> pre = new ArrayList<Thing>();
		pre.add(thing(1));
		pre.add(thing(0));
		pre.add(thing(2));
		pre.add(thing(3));
		
		List<Thing> in = new ArrayList<Thing>();
		in.add(thing(0));
		in.add(thing(1));
		in.add(thing(2));
		in.add(thing(3));
		
		b.reconstruct(pre, in);
		assertTrue(same(t1(), b));
	}

	@Test(timeout = 300)
	//@Worth(points = 5)
	public void testReconstruct2() {
		BST<Thing> b = new BST<Thing>();
		List<Thing> pre = new ArrayList<Thing>();
		pre.add(thing(5));
		pre.add(thing(1));
		pre.add(thing(0));
		pre.add(thing(3));
		pre.add(thing(2));
		pre.add(thing(10));
		pre.add(thing(7));
		pre.add(thing(8));
		pre.add(thing(11));
		
		List<Thing> in = new ArrayList<Thing>();
		in.add(thing(0));
		in.add(thing(1));
		in.add(thing(2));
		in.add(thing(3));
		in.add(thing(5));
		in.add(thing(7));
		in.add(thing(8));
		in.add(thing(10));
		in.add(thing(11));
		
		b.reconstruct(pre, in);
		assertTrue(same(t3(), b));
	}
	
	/*
	 * Some trees to test with
	 */
	private BST<Thing> t1() {
		BST.Node<Thing> n = node(1);
		n.setLeft(node(0));
		n.setRight(node(2));
		n.getRight().setRight(node(3));
		
		BST<Thing> b = new BST<>();
		b.setRoot(n);
		b.setSize(4);
		return b;
	}
	
	private BST<Thing> t2() {
		BST.Node<Thing> n = node(5);
		n.setLeft(node(1));
		n.getLeft().setRight(node(3));
		n.setRight(node(10));
		n.getRight().setLeft(node(7));
		
		BST<Thing> b = new BST<>();
		b.setRoot(n);
		b.setSize(5);
		return b;
	}
	
	private BST<Thing> t3() {
		BST.Node<Thing> n = node(5);
		n.setLeft(node(1));
		n.getLeft().setLeft(node(0));
		n.getLeft().setRight(node(3));
		n.getLeft().getRight().setLeft(node(2));
		
		n.setRight(node(10));
		n.getRight().setRight(node(11));
		n.getRight().setLeft(node(7));
		n.getRight().getLeft().setRight(node(8));
		
		BST<Thing> b = new BST<>();
		b.setRoot(n);
		b.setSize(9);
		return b;
	}
	
	/*
	 * Methods for testing
	 */
	
	/**
	 * Recursively checks that two trees are the same
	 */
	private boolean same(BST<Thing> b1, BST<Thing> b2) {
		if (b1 == null || b2 == null) {
			return b1 == b2;
		} else {
			return same(b1.getRoot(), b2.getRoot());
		}
	}
	
	/**
	 * Recursively checks that two subtrees are the same
	 */
	private boolean same(BST.Node<Thing> n1, BST.Node<Thing> n2) {
		if (n1 == null || n2 == null) {
			return n1 == n2;
		} else {
			return Objects.equals(n1.getData(), n2.getData())
					&& same(n1.getLeft(), n2.getLeft())
					&& same(n1.getRight(), n2.getRight());
		}
	}
	
	/**
	 * Returns a node with a thing representing the given integer
	 */
	private BST.Node<Thing> node(int i) {
		return new BST.Node<BSTTests.Thing>(thing(i));
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
