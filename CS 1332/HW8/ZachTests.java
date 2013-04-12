import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;


public class ZachTests {
	
	private static <T extends Comparable<T>> boolean isAsc(T[] a) {
		for (int i = 1; i < a.length; i++) {
			if (a[i].compareTo(a[i-1]) < 0)
				return false;
		}
		return true;
	}
	
	private static boolean isAscInt(int[] a) {
		for (int i = 1; i < a.length; i++) {
			if (a[i] < a[i-1])
				return false;
		}
		return true;
	}

	@Test(timeout=110)
	public void testBubbleSort() {
		//Integer[] a = {10,4,6,45,68,30,61,5,21,11};
		Integer[] b = {10,4,6,45,68,30,61,5,21,11};
		
		Sort.<Integer>bubblesort(b);

		//System.out.println(Arrays.asList(a));
		//System.out.println(Arrays.asList(b));
		
		assertTrue(isAsc(b));
	}

	@Test(timeout=110)
	public void testInsertionSort() {
		//Integer[] a = {10,4,6,45,68,30,61,5,21,11};
		Integer[] b = {10,4,6,45,68,30,61,5,21,11,-3};

		//System.out.println(Arrays.asList(a));
		//System.out.println(Arrays.asList(b));
		
		Sort.<Integer>insertionsort(b);
		
		assertTrue(isAsc(b));
	}

	@Test(timeout=24)
	public void testQuicksort() {
		//Integer[] a = {10,4,6,45,68,30,61,5,21,11};
		Integer[] b = {10,4,6,45,68,30,61,5,21,11};
		
		Sort.<Integer>quicksort(b, new Random());

		//System.out.println(Arrays.asList(a));
		//System.out.println(Arrays.asList(b));
		
		assertTrue(isAsc(b));
	}

	@Test (timeout=24)
	public void testMergesort() {
		Integer[] a = {10,4,6,45,68,30,61,5,21,11};
		Integer[] b = Sort.<Integer>mergesort(a);

		//System.out.println(Arrays.asList(a));
		//System.out.println(Arrays.asList(b));
		
		assertTrue(isAsc(b));
	}

	@Test //(timeout=24)
	public void testRadixsort() {
		int[] a = {10,4,6,45,68, -99, 30,61,5,21,-11, -22, -33};
		int[] b = Sort.radixsort(a);
		
		System.out.print("[");
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]);
			if (i != a.length - 1) System.out.print(", ");
		}
		System.out.print("]\n");
		
		System.out.print("[");
		for (int i = 0; i < b.length; i++) {
			System.out.print(b[i]);
			if (i != b.length - 1) System.out.print(", ");
		}
		System.out.print("]\n");
		
		assertTrue(isAscInt(b));
	}
	
	private static final Random r = new Random();

	//@Test
	public void graphBubbleSort() {
		
	    System.out.println("size, time");
	    
		for (int i = 0; i < 255; i++) {
			int size = Sort.randBoundInt(r, 100, 10000);
			
			Random pop = new Random(42);
			
			ArrayList<Integer> arr = new ArrayList<Integer>(size);
			for (int j = 0; j < size; j++) {
				arr.add(pop.nextInt());
			}
			
			Integer[] a = arr.toArray(new Integer[0]);
			
			long startTime = System.nanoTime();
			
			Sort.<Integer>bubblesort(a);

			long stopTime = System.nanoTime();
			long elapsedTime = stopTime - startTime;
		    System.out.println(size + ", " + elapsedTime);
		}
	}

	//@Test
	public void graphInsertionSort() {
		
	    System.out.println("size, time");
	    
		for (int i = 0; i < 300; i++) {
			int size = Sort.randBoundInt(r, 100, 10000);
			
			Random pop = new Random(42);
			
			ArrayList<Integer> arr = new ArrayList<Integer>(size);
			for (int j = 0; j < size; j++) {
				arr.add(pop.nextInt());
			}
			
			Integer[] a = arr.toArray(new Integer[0]);
			
			long startTime = System.nanoTime();
			
			Sort.<Integer>insertionsort(a);

			long stopTime = System.nanoTime();
			long elapsedTime = stopTime - startTime;
		    System.out.println(size + ", " + elapsedTime);
		}
	}

	//@Test
	public void graphQuicksort() {
		
	    System.out.println("size, time");
	    
		for (int i = 0; i < 300; i++) {
			int size = Sort.randBoundInt(r, 100, 10000);
			
			Random pop = new Random(42);
			
			ArrayList<Integer> arr = new ArrayList<Integer>(size);
			for (int j = 0; j < size; j++) {
				arr.add(pop.nextInt());
			}
			
			Integer[] a = arr.toArray(new Integer[0]);
			
			long startTime = System.nanoTime();
			
			Sort.<Integer>quicksort(a, new Random());

			long stopTime = System.nanoTime();
			long elapsedTime = stopTime - startTime;
		    System.out.println(size + ", " + elapsedTime);
		}
	}

}
