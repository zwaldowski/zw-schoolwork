import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class HashTableTests {

	//@Worth(points = 4)
	@Test(timeout = 200)
	public void testPut0() {
		HashTable<Thing, Thing> h = table(10); 
		Thing v = h.put(thing(1), thing(1));
		assertEquals(null, v);
		HashTable<Thing, Thing> expected = table(10, null, 1);
		assertTrue(same(expected, h));
	}

	//@Worth(points = 4)
	@Test(timeout = 200)
	public void testPut1() {
		HashTable<Thing, Thing> h = table(10, null, 1); 
		h.put(thing(11), thing(11));

		HashTable<Thing, Thing> expected = table(10, null, 1, 11);
		assertTrue(same(expected, h));
	}

	//@Worth(points = 4)
	@Test(timeout = 200)
	public void testPut2() {
		HashTable<Thing, Thing> h = table(10, null, 1, 11); 
		h.put(thing(12), thing(12));

		HashTable<Thing, Thing> expected = table(10, null, 1, 11, 12);
		assertTrue(same(expected, h));
	}

	//@Worth(points = 4)
	@Test(timeout = 200)
	public void testPut3() {
		HashTable<Thing, Thing> h = table(10, null, 1, 11, 12); 
		h.put(thing(21), thing(21));

		HashTable<Thing, Thing> expected = table(10, null, 1, 11, 12, 21);
		assertTrue(same(expected, h));
	}

	//@Worth(points = 4)
	@Test(timeout = 200)
	public void testPut4() {
		HashTable<Thing, Thing> h = table(10); 
		h.put(thing(1), thing(1));
		h.put(thing(3), thing(3));
		h.put(thing(5), thing(5));

		HashTable<Thing, Thing> expected = table(10, null, 1, null, 3, null, 5);
		assertTrue(same(expected, h));
	}

	//@Worth(points = 4)
	@Test(timeout = 200)
	public void testPut5() {
		HashTable<Thing, Thing> h = table(10); 
		h.put(thing(1), thing(10));
		h.put(thing(3), thing(3));
		h.put(thing(5), thing(5));

		Thing v = h.put(thing(1), thing(1));
		assertEquals(thing(10), v);

		HashTable<Thing, Thing> expected = table(10, null, 1, null, 3, null, 5);
		assertTrue(same(expected, h));
	}

	//@Worth(points = 4)
	@Test(timeout = 200)
	public void testPut6() {
		HashTable<Thing, Thing> h = table(10, entry(0, 1, true)); 
		h.put(thing(0), thing(0));

		HashTable<Thing, Thing> expected = table(10, 0);
		assertTrue(same(expected, h));
	}

	//@Worth(points = 4)
	@Test(timeout = 200)
	public void testPut7() {
		HashTable<Thing, Thing> h = table(10, entry(0, 1, true)); 
		Thing v = h.put(thing(0), thing(0));
		assertEquals(null, v);

		HashTable<Thing, Thing> expected = table(10, 0);
		assertTrue(same(expected, h));
	}

	//@Worth(points = 4)
	@Test(timeout = 200)
	public void testPut8() {
		HashTable<Thing, Thing> h = table(10, entry(0, 0, true), 10); 
		Thing v = h.put(thing(10), thing(11));
		assertEquals(thing(10), v);

		HashTable<Thing, Thing> expected = table(10, entry(0, 0, true), entry(10, 11, false));
		assertTrue(similar(expected, h));
	}
	
	//@Worth(points = 4)
	@Test(timeout = 200)
	public void testPut9() {
		HashTable<Thing, Thing> h = table(5, 0, 1, 2); 
		h.put(thing(3), thing(3));

		// Checks for regrow
		assertTrue(h.getTable().length > 5);
		
		HashTable<Thing, Thing> expected = table(5, 0, 1, 2, 3);
		assertTrue(similar(expected, h));
	}
	
	//@Worth(points = 4)
	@Test(timeout = 200)
	public void testPut10() {
		// This checks efficiency, you should not probe until the existing 0
		HashTable<Thing, Thing> h = table(10, 10, null, null, 0); 
		h.put(thing(0), thing(0));

		HashTable<Thing, Thing> expected = table(10, 10, 0, null, 0);
		assertTrue(same(expected, h));
	}

	//@Worth(points = 4)
	@Test(timeout = 200)
	public void testRemove0() {
		HashTable<Thing, Thing> h = table(10, null, 1, 11, 12, 21);
		Thing v = h.remove(thing(1));
		assertEquals(thing(1), v);
		
		HashTable<Thing, Thing> expected = table(10, null, entry(1, 1, true), 11, 12, 21);
		assertTrue(same(expected, h));
	}
	
	//@Worth(points = 4)
	@Test(timeout = 200)
	public void testRemove1() {
		HashTable<Thing, Thing> h = table(10, null, 1, 11, 12, 21);
		Thing v = h.remove(thing(12));
		assertEquals(thing(12), v);
		
		HashTable<Thing, Thing> expected = table(10, null, 1, 11, entry(12, 12, true), 21);
		assertTrue(same(expected, h));
	}
	
	//@Worth(points = 4)
	@Test(timeout = 200)
	public void testRemove2() {
		HashTable<Thing, Thing> h = table(10, null, entry(1, 1, true), 11, 12, 21);
		Thing v = h.remove(thing(21));
		assertEquals(thing(21), v);
		
		HashTable<Thing, Thing> expected = table(10, null, entry(1, 1, true), 11, 12, entry(21, 21, true));
		assertTrue(same(expected, h));
	}
	
	//@Worth(points = 4)
	@Test(timeout = 200)
	public void testRemove3() {
		HashTable<Thing, Thing> h = table(10, null, 1, 11, 12, 21);
		Thing v = h.remove(thing(3));
		assertEquals(null, v);
		
		HashTable<Thing, Thing> expected = table(10, null, 1, 11, 12, 21);
		assertTrue(same(expected, h));
	}
	
	//@Worth(points = 4)
	@Test(timeout = 200)
	public void testRemove4() {
		// Efficiency check, should not probe to 11
		HashTable<Thing, Thing> h = table(10, null, 1, null, null, 11);
		h.remove(thing(11));
		
		HashTable<Thing, Thing> expected = table(10, null, 1, null, null, 11);
		assertTrue(same(expected, h));
	}

	//@Worth(points = 3)
	@Test(timeout = 200)
	public void testGet0() {
		HashTable<Thing, Thing> h = table(10, null, 1, 11, 12, 21);
		Thing v = h.get(thing(1));
		assertEquals(thing(1), v);
	}
	
	//@Worth(points = 3)
	@Test(timeout = 200)
	public void testGet1() {
		HashTable<Thing, Thing> h = table(10, null, 1, 11, 12, 21);
		Thing v = h.get(thing(21));
		assertEquals(thing(21), v);
	}
	
	//@Worth(points = 2)
	@Test(timeout = 200)
	public void testGet2() {
		HashTable<Thing, Thing> h = table(10, null, 1, 11, 12, 21);
		Thing v = h.get(thing(1));
		assertEquals(thing(1), v);
		v = h.get(thing(13));
		assertEquals(null, v);
	}

	//@Worth(points = 3)
	@Test(timeout = 200)
	public void testContainsKey0() {
		HashTable<Thing, Thing> h = table(10, null, 1, 11, 12, 21);
		assertTrue(h.containsKey(thing(1)));
	}
	
	//@Worth(points = 3)
	@Test(timeout = 200)
	public void testContainsKey1() {
		HashTable<Thing, Thing> h = table(10, null, 1, 11, 12, 21);
		assertTrue(h.containsKey(thing(21)));
	}
	
	//@Worth(points = 2)
	@Test(timeout = 200)
	public void testContainsKey2() {
		HashTable<Thing, Thing> h = table(10, null, 1, 11, 12, 21);
		assertTrue(h.containsKey(thing(1)));
		assertFalse(h.containsKey(thing(13)));
	}

	//@Worth(points = 3)
	@Test(timeout = 200)
	public void testClear() {
		HashTable<Thing, Thing> h = table(10, null, 1, 11, 12, 21);
		h.clear();
		
		HashTable<Thing, Thing> expected = table(10);
		assertTrue(similar(expected, h));
	}

	//@Worth(points = 3)
	@Test(timeout = 200)
	public void testIsEmpty() {
		HashTable<Thing, Thing> h = table(10, null, 1, 11, 12, 21);
		assertFalse(h.isEmpty());
		h = table(10);
		assertTrue(h.isEmpty());
	}

	//@Worth(points = 4)
	@Test(timeout = 200)
	public void testValues() {
		HashTable<Thing, Thing> h = table(10, null, 1, entry(11, 11, true), 12, 21);
		List<Thing> actual = new ArrayList<>(h.values());
		Collections.sort(actual);
		List<Thing> expected = list(1, 12, 21);
		Collections.sort(expected);
		assertEquals(expected, actual);
	}

	//@Worth(points = 4)
	@Test(timeout = 200)
	public void testKeySet() {
		HashTable<Thing, Thing> h = table(10, null, 1, entry(11, 11, true), 12, 21);
		Set<Thing> actual = h.keySet();
		Set<Thing> expected = new HashSet<>(list(1, 12, 21));
		assertEquals(expected, actual);
	}

	//@Worth(points = 3)
	@Test(timeout = 200)
	public void testEntrySet() {
		HashTable<Thing, Thing> h = table(10, null, 1, entry(11, 11, true), 12, 21);
		Set<HashTable.Entry<Thing, Thing>> actual = h.entrySet();

		Set<Thing> actualKeys = new HashSet<>();
		List<Thing> actualValues = new ArrayList<>();
		for (HashTable.Entry<Thing, Thing> e : actual) {
			actualKeys.add(e.getKey());
			actualValues.add(e.getKey());
		}
		Collections.sort(actualValues);
		
		Set<Thing> expectedKeys = new HashSet<>(list(1, 12, 21));
		List<Thing> expectedValues = new ArrayList<>(list(1, 12, 21));
		Collections.sort(expectedValues);
		
		assertEquals(expectedKeys, actualKeys);
		assertEquals(expectedValues, actualValues);
	}

	//@Worth(points = 3)
	@Test(timeout = 200)
	public void testSize() {
		HashTable<Thing, Thing> h = table(10, null, 1, entry(11, 11, true), 12, 21);
		assertEquals(3, h.size());
		h.put(thing(11), thing(11));
		assertEquals(4, h.size());
	}

	/*
	 * Helpful methods for testing
	 */

	/**
	 * Checks that the tables are the exact same
	 * 
	 * this method is necessarily complicated because we
	 * forget to add an equals/hashCode method to Entry... :(
	 */
	private boolean same(HashTable<Thing, Thing> expected, HashTable<Thing, Thing> actual) {
		HashTable.Entry<Thing, Thing>[] expectedArray = expected.getTable();
		HashTable.Entry<Thing, Thing>[] actualArray = actual.getTable();
		
		if (expectedArray.length != actualArray.length) {
			return false;
		}
		
		for (int i = 0; i < expectedArray.length; i++) {
			if (expectedArray[i] == actualArray[i]) {
				continue;
			} else if (expectedArray[i] == null || actualArray[i] == null) {
				return false;
			} else if (expectedArray[i].getKey().equals(actualArray[i].getKey()) &&
					expectedArray[i].getValue().equals(actualArray[i].getValue()) &&
					expectedArray[i].isAvailable() == actualArray[i].isAvailable()) {
				continue;
			} else {
				return false;
			}
		}
		
		return true;
	}

	/**
	 * Checks that the tables are similar
	 * 
	 * this method is necessarily complicated because we
	 * forget to add an equals/hashCode method to Entry... :(
	 */
	private boolean similar(HashTable<Thing, Thing> expected, HashTable<Thing, Thing> actual) {
		Set<Thing> expectedKeys = new HashSet<>();
		List<Thing> expectedValues = new ArrayList<>();
		for (HashTable.Entry<Thing, Thing> e : expected.getTable()) {
			if (e != null && !e.isAvailable()) {
				expectedKeys.add(e.getKey());
				expectedValues.add(e.getKey());
			}
		}
		Collections.sort(expectedValues);
		
		Set<Thing> actualKeys = new HashSet<>();
		List<Thing> actualValues = new ArrayList<>();
		for (HashTable.Entry<Thing, Thing> e : actual.getTable()) {
			if (e != null && !e.isAvailable()) {
				actualKeys.add(e.getKey());
				actualValues.add(e.getKey());
			}
		}
		Collections.sort(actualValues);
		
		return expectedKeys.equals(actualKeys) && expectedValues.equals(actualValues);
	}
	
	/**
	 * Creates a list of things
	 */
	private List<Thing> list(int...ints) {
		List<Thing> list = new ArrayList<>();
		for (int i : ints) {
			list.add(thing(i));
		}
		return list;
	}
	
	/**
	 * Returns a hash table with the given entries
	 */
	@SuppressWarnings("unchecked")
	private HashTable<Thing, Thing> table(int capacity, Object...objects) {
		HashTable<Thing, Thing> hashTable = new HashTable<>();
		int size = 0;
		int index = 0;
		HashTable.Entry<Thing, Thing>[] entries = new HashTable.Entry[capacity];
		for (Object o : objects) {
			if (o instanceof HashTable.Entry) {
				entries[index] = (HashTable.Entry<Thing, Thing>) o;
				if (!entries[index].isAvailable()) {
					size++;
				}
			} else if (o instanceof Integer) {
				entries[index] = entry((Integer) o, (Integer) o);
				size++;
			}
			index++;
		}
		hashTable.setSize(size);
		hashTable.setTable(entries);
		return hashTable;
	}

	/**
	 * Returns an entry with the given parameters
	 */
	private HashTable.Entry<Thing, Thing> entry(int k, int v) {
		return entry(k, v, false);
	}

	/**
	 * Returns an entry with the given parameters
	 */
	private HashTable.Entry<Thing, Thing> entry(int k, int v, boolean a) {
		HashTable.Entry<Thing, Thing> e = new HashTable.Entry<>(thing(k), thing(v));
		e.setAvailable(a);
		return e;
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
