import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class ZachTests {

	@Test
	public void basicAdd() {
		BinaryHeap<Integer> test = new BinaryHeap<>();
		test.add(5);
		assertEquals(test.size(), 1);
	}
	
	@Test
	public void multiAscAdd() {
		BinaryHeap<Integer> test = new BinaryHeap<>();
		test.add(5);
		test.add(7);
		test.add(8);
		test.add(9);
		test.add(13);
		test.add(15);
		assertEquals(test.size(), 6);
		Integer[] exp = { 5, 7, 8, 9, 13, 15 };
		Object[] out = test.getContents();
		assertArrayEquals(exp, out);
	}
	
	@Test
	public void multiDescAdd() {
		BinaryHeap<Integer> test = new BinaryHeap<>();
		test.add(15);
		test.add(13);
		test.add(9);
		test.add(8);
		test.add(7);
		test.add(5);
		assertEquals(test.size(), 6);
		Integer[] exp = { 5, 8, 7, 15, 9, 13 };
		Object[] out = test.getContents();
		assertArrayEquals(exp, out);
	}
	
	@Test
	public void multiDescAddRemove() {
		BinaryHeap<Integer> test = new BinaryHeap<>();
		test.add(15);
		test.add(13);
		test.add(9);
		test.add(8);
		test.add(7);
		test.add(5);
		assertEquals(test.size(), 6);
		Integer[] exp = { 5, 8, 7, 15, 9, 13 };
		Object[] out = test.getContents();
		assertArrayEquals(exp, out);
		assertEquals(test.remove(), (Integer)5);
		assertEquals(test.remove(), (Integer)7);
		assertEquals(test.remove(), (Integer)8);
		assertEquals(test.size(), 3);
		Integer[] exp2 = { 9, 15, 13 };
		Object[] out2 = test.getContents();
		assertArrayEquals(exp2, out2);
		
	}

}
