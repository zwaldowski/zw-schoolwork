import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;


public class ZachTests {

	/*@Test
	public void test() {
		fail("Not yet implemented");
	}*/
	
	private static <T extends Comparable<T>> boolean isAsc(T[] a) {
		for (int i = 1; i < a.length; i++) {
			if (a[i].compareTo(a[i-1]) < 0)
				return false;
		}
		return true;
	}

	@Test /*(timeout=24)*/
	public void testHeapsort() {
		Integer[] a = {10,4,6,45,68,-1,30,61,5,21,11};
		Integer[] b = {10,4,6,45,68,-1,30,61,5,21,11};
		
		Sort.<Integer>heapSort(b);

		//System.out.println(Arrays.asList(a));
		//System.out.println(Arrays.asList(b));
		
		assertTrue(isAsc(b));
	}
	
	@Test
	public void testQueue() {
		Integer a = 2;
		Integer b = 6;
		Integer c = 9;
		Integer d = -1;
		Integer e = 3;
		Queue<Integer> q = new Queue<Integer>();
		q.enqueue(a);
		q.enqueue(b);
		q.enqueue(c);
		q.enqueue(d);
		q.enqueue(e);
		assertFalse(q.isEmpty());
		assertEquals(q.dequeue(), a);
		assertEquals(q.dequeue(), b);
		assertEquals(q.dequeue(), c);
		assertEquals(q.dequeue(), d);
		assertEquals(q.dequeue(), e);
    }
	
	@Test
	public void testListsFind() {
		Node one = new Node("1");
		Node two = new Node("2");
		Node thr = new Node("3");
		Node fur = new Node("4");
		Node fiv = new Node("5");
		Node six = new Node("6");
		one.next = two;
		two.next = thr;
		thr.next = fur;
		fur.next = fiv;
		fiv.next = six;
		six.next = thr; // loop!
		assertTrue(Lists.hasLoop(one));
    }
	
	@Test
	public void testListsLength() {
		Node one = new Node("1");
		Node two = new Node("2");
		Node thr = new Node("3");
		Node fur = new Node("4");
		Node fiv = new Node("5");
		Node six = new Node("6");
		one.next = two;
		two.next = thr;
		thr.next = fur;
		fur.next = fiv;
		fiv.next = six;
		six.next = thr; // loop!
		assertEquals(Lists.loopLength(one), 4);
    }
	
	@Test
	public void floyd() {
		int[][] m = new int[5][5];
    	m[0][0] = 0; m[0][1] = 3; m[0][2] = 8; m[0][3] = 10000; m[0][4] = -4;
    	m[1][0] = 10000; m[1][1] = 0; m[1][2] = 10000; m[1][3] = 1;
    	m[1][4]=7;
    	m[2][0] = 10000; m[2][1] = 4; m[2][2] = 0; m[2][3] = 10000; 
    	m[2][4] = 10000;
    	m[3][0] = 2; m[3][1] = 10000; m[3][2] = -5; m[3][3] = 0; 
    	m[3][4] = 10000;
    	m[4][0] = 10000; m[4][1] = 10000; m[4][2] = 10000; m[4][3] = 6;
    	m[4][4] =0;
    	
    	int[][] path = new int[5][5];
    	
    	for (int i=0; i<5; i++)
    		for (int j=0; j<5; j++)
    			if (m[i][j] == 10000)
    				path[i][j] = -1;
    			else
    				path[i][j] = i;

    	Floyd.floydWarshall(5, m, 10000);
    	
    	
	}

}
