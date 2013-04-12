import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import struct.Worth;

public class SplayTests {

	//@Worth(points = 5)
	@Test(timeout = 300)
	public void testAdd0() {
		SplayTree<Thing> s = splayTree(1, 2, 3);
		List<Thing> list = list(3, 2, 1);
		assertEquals(list, preorder(s));
		Collections.sort(list);
		assertEquals(list, inorder(s));
	}
	
	//@Worth(points = 5)
	@Test(timeout = 300)
	public void testAdd1() {
		SplayTree<Thing> s = splayTree(3, 2, 1);
		List<Thing> list = list(1, 2, 3);
		assertEquals(list, preorder(s));
		Collections.sort(list);
		assertEquals(list, inorder(s));
	}
	
	//@Worth(points = 5)
	@Test(timeout = 300)
	public void testAdd2() {
		SplayTree<Thing> s = splayTree(3, 2, 1, 4);
		List<Thing> list = list(4, 1, 3 , 2);
		assertEquals(list, preorder(s));
		Collections.sort(list);
		assertEquals(list, inorder(s));
	}
	
	//@Worth(points = 5)
	@Test(timeout = 300)
	public void testAdd3() {
		SplayTree<Thing> s = splayTree(3, 2, 1, 4, 5);
		List<Thing> list = list(5, 4, 1, 3 , 2);
		assertEquals(list, preorder(s));
		Collections.sort(list);
		assertEquals(list, inorder(s));
	}
	
	//@Worth(points = 5)
	@Test(timeout = 300)
	public void testAdd4() {
		SplayTree<Thing> s = splayTree(3, 2, 1, 4, 5, 0);
		List<Thing> list = list(0, 5, 1, 4, 3, 2);
		assertEquals(list, preorder(s));
		Collections.sort(list);
		assertEquals(list, inorder(s));
	}
	
	//@Worth(points = 5)
	@Test(timeout = 300)
	public void testAdd5() {
		SplayTree<Thing> s = splayTree(2, 6, 4);
		List<Thing> list = list(4, 2, 6);
		assertEquals(list, preorder(s));
		Collections.sort(list);
		assertEquals(list, inorder(s));
	}
	
	//@Worth(points = 5)
	@Test(timeout = 300)
	public void testAdd6() {
		SplayTree<Thing> s = splayTree(2, 6, 4, 3);
		List<Thing> list = list(3, 2, 4, 6);
		assertEquals(list, preorder(s));
		Collections.sort(list);
		assertEquals(list, inorder(s));
	}
	
	//@Worth(points = 5)
	@Test(timeout = 300)
	public void testAdd7() {
		SplayTree<Thing> s = splayTree(2, 6, 4, 3, 5);
		List<Thing> list = list(5, 3, 2, 4, 6);
		assertEquals(list, preorder(s));
		Collections.sort(list);
		assertEquals(list, inorder(s));
	}
	
	//@Worth(points = 5)
	@Test(timeout = 300)
	public void testRemove0() {
		SplayTree<Thing> s = t1();
		Thing input = thing(0);
		Thing output = s.remove(input);
		assertEquals(input, output);
		List<Thing> list = list(1, 2, 3);
		assertEquals(list, preorder(s));
		Collections.sort(list);
		assertEquals(list, inorder(s));
	}
	
	//@Worth(points = 5)
	@Test(timeout = 300)
	public void testRemove1() {
		SplayTree<Thing> s = t1();
		Thing input = thing(1);
		Thing output = s.remove(input);
		assertEquals(input, output);
		List<Thing> list = list(0, 2, 3);
		assertEquals(list, preorder(s));
		Collections.sort(list);
		assertEquals(list, inorder(s));
	}
	
	//@Worth(points = 5)
	@Test(timeout = 300)
	public void testRemove2() {
		SplayTree<Thing> s = t1();
		Thing input = thing(2);
		Thing output = s.remove(input);
		assertEquals(input, output);
		List<Thing> list = list(1, 0, 3);
		assertEquals(list, preorder(s));
		Collections.sort(list);
		assertEquals(list, inorder(s));
	}
	
	//@Worth(points = 5)
	@Test(timeout = 300)
	public void testRemove3() {
		SplayTree<Thing> s = t1();
		Thing input = thing(3);
		Thing output = s.remove(input);
		assertEquals(input, output);
		List<Thing> list = list(2, 1, 0);
		assertEquals(list, preorder(s));
		Collections.sort(list);
		assertEquals(list, inorder(s));
	}
	
	@Test(timeout = 300)
	//@Worth(points = 5)
	public void testGet0() {
		SplayTree<Thing> s = t1();
		Thing input = thing(2);
		Thing output = s.get(input);
		assertEquals(input, output);
		List<Thing> list = list(2, 1, 0, 3);
		assertEquals(list, preorder(s));
		Collections.sort(list);
		assertEquals(list, inorder(s));
	}
	
	@Test(timeout = 300)
	//@Worth(points = 5)
	public void testGet1() {
		SplayTree<Thing> s = t1();
		Thing input = thing(4);
		Thing output = s.get(input);
		assertEquals(null, output);
		List<Thing> list = list(1, 0, 2, 3);
		assertEquals(list, preorder(s));
		Collections.sort(list);
		assertEquals(list, inorder(s));
	}
	
	@Test(timeout = 300)
	//@Worth(points = 5)
	public void testContains0() {
		SplayTree<Thing> s = t1();
		Thing input = thing(2);
		boolean output = s.contains(input);
		assertTrue(output);
		List<Thing> list = list(2, 1, 0, 3);
		assertEquals(list, preorder(s));
		Collections.sort(list);
		assertEquals(list, inorder(s));
	}
	
	@Test(timeout = 300)
	//@Worth(points = 5)
	public void testContains1() {
		SplayTree<Thing> s = t1();
		Thing input = thing(4);
		boolean output = s.contains(input);
		assertFalse(output);
		List<Thing> list = list(1, 0, 2, 3);
		assertEquals(list, preorder(s));
		Collections.sort(list);
		assertEquals(list, inorder(s));
	}
	
	@Test(timeout = 300)
	//@Worth(points = 5)
	public void testIsEmpty() {
		assertTrue(new SplayTree<>().isEmpty());
		assertFalse(t1().isEmpty());
	}

	@Test(timeout = 300)
	//@Worth(points = 5)
	public void testSize() {
		assertEquals(4, t1().size());
	}
	
	@Test(timeout = 300)
	//@Worth(points = 5)
	public void testSizeAdd() {
		SplayTree<Thing> s = t1();
		s.add(thing(-1));
		assertEquals(5, s.size());
	}
	
	@Test(timeout = 300)
	//@Worth(points = 5)
	public void testSizeRemove() {
		SplayTree<Thing> s = t1();
		s.remove(thing(3));
		assertEquals(3, s.size());
	}

	/*
	 * Some trees to test with
	 */
	private SplayTree<Thing> t1() {
		SplayTree.Node<Thing> n0 = node(0);
		SplayTree.Node<Thing> n1 = node(1);
		SplayTree.Node<Thing> n2 = node(2);
		SplayTree.Node<Thing> n3 = node(3);

		n1.setLeft(n0);
		n0.setParent(n1);
		
		n1.setRight(n2);
		n2.setParent(n1);
		
		n2.setRight(n3);
		n3.setParent(n2);
		
		SplayTree<Thing> SplayTree = new SplayTree<>();
		SplayTree.setRoot(n1);
		SplayTree.setSize(4);
		return SplayTree;
	}
	
	/*
	 * Methods for testing
	 */
	private SplayTree<Thing> splayTree(int...ints) {
		SplayTree<Thing> SplayTree = new SplayTree<>();
		for (int i : ints) {
			SplayTree.add(thing(i));
		}
		return SplayTree;
	}
	
	private List<Thing> list(int...ints) {
		List<Thing> list = new ArrayList<>();
		for (int i : ints) {
			list.add(thing(i));
		}
		return list;
	}
	
	private List<Thing> preorder(SplayTree<Thing> SplayTree) {
		List<Thing> l = new ArrayList<>();
		preorder(SplayTree.getRoot(), l);
		return l;
	}
	
	private void preorder(SplayTree.Node<Thing> n, List<Thing> l) {
		if (n != null) {
			l.add(n.getData());
			preorder(n.getLeft(), l);
			preorder(n.getRight(), l);
		}
	}
	
	private List<Thing> inorder(SplayTree<Thing> SplayTree) {
		List<Thing> l = new ArrayList<>();
		inorder(SplayTree.getRoot(), l);
		return l;
	}
	
	private void inorder(SplayTree.Node<Thing> n, List<Thing> l) {
		if (n != null) {
			inorder(n.getLeft(), l);
			l.add(n.getData());
			inorder(n.getRight(), l);
		}
	}
	
	/**
	 * Returns a node with a thing representing the given integer
	 */
	private SplayTree.Node<Thing> node(int i) {
		return new SplayTree.Node<Thing>(thing(i));
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
