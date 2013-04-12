
public class Lists {
	
	private static Node getNext(Node n) {
		if (n == null) return null;
		return n.next;
	}
	
	private static Node firstLoopHare(Node n) {
		if (n == null) return null;
		
		Node tortoise = getNext(n), hare = getNext(getNext(n));
		
		while (tortoise != hare && tortoise != null && hare != null) {
			tortoise = getNext(tortoise);
			hare = getNext(getNext(hare));
		}
		
		return hare;
	}

	/**
	 * Given the head of a singly linked list, return true if there is a loop
	 * in the list. False otherwise.
	 * 
	 * A loop just means that at some point in the list a node points to a node
	 * earlier in the list. So the linked list would be invalid.
	 * 
	 * Restrictions:
	 * 		O(n) time
	 * 		O(n) space
	 * 
	 * @param n
	 * @return
	 */
	public static boolean hasLoop(Node n) {
		return hasLoop2(n);
	}
	
	/**
	 * Given the head of a single linked list, return true if there is a loop
	 * in the list.
	 * 
	 * (you can use this as your solution to the previous method if you get it working!)
	 * 
	 * Restrictions:
	 * 		O(n) time
	 * 		O(1) space
	 * 
	 * @param n
	 * @return
	 */
	public static boolean hasLoop2(Node n) {
		return firstLoopHare(n) != null;
	}
	
	/**
	 * Return the length of the loop (the number of nodes involved in the loop)
	 * from the given linked list. You may assume that there is a loop.
	 * 
	 * Restrictions:
	 * 		O(n) time
	 * 		O(1) space
	 * 
	 * @param n
	 * @return
	 */
	public static int loopLength(Node n) {
		Node hare = firstLoopHare(n);
		if (hare == null) return -1;
		
		Node tortoise = n;
		while (tortoise != hare && tortoise != null && hare != null) {
			tortoise = getNext(tortoise);
			hare = getNext(hare);
		}
		
		int lambda = 1;
		hare = getNext(tortoise);
		while (tortoise != hare && tortoise != null && hare != null) {
			hare = getNext(hare);
			lambda++;
		}
		
		return lambda;
	}
	
}
