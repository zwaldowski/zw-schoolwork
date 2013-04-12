/**
 * A circular, singly linked list... with some twists. (HW02)
 * @author Zachary Waldowski
 */
public class TwistList<E extends Comparable<E>> extends LinkedList<E> {

	/**
	 * Another helper method, this time for reverse.
	 * Takes care of swapping out the objects in the nodes.
	 * Generally we assume a < b and swap them if necessary.
	 * 
	 * @param a The index of the first object to swap.
	 * @param b The index of the second object to swap.
	 */
	protected void exchangeObjectsAtIndexes(int a, int b) {
		// fire an array exception if it's out of bounds
		if (a >= size || a < 0) {
			throw new ArrayIndexOutOfBoundsException(a);
		}

		if (b >= size || b < 0) {
			throw new ArrayIndexOutOfBoundsException(b);
		}

		if (a == b) {
			return;
		}

		// Assuming a < b
		if (a > b) {
			int c = a;
			a = b;
			b = c;
		}

		Node<E> aNode = head;
		int i = 0;
		while (i < a) {
			aNode = aNode.getNext();
			i++;
		}

		Node<E> bNode = aNode;
		while (i < b) {
			bNode = bNode.getNext();
			i++;
		}
	
		E aData = aNode.getData();
		E bData = bNode.getData();
		aNode.setData(bData);
		bNode.setData(aData);
	}

	/**
	 * Add a piece of data either at the front of the list if the data
	 * is less than the head. If the data to be added is not less then 
	 * the data at the front of the list then find the first place in the
	 * list where the data is between two other points of data. If this is
	 * never true then place the new piece of data at the end of the list.
	 * 
	 * Last of all call swing with the index at which the new piece of
	 * data was added.
	 */
	public void add(E e) {
		int i = 0;

		if (size == 0) { // empty, idx 0
			super.add(e);
		} else if (get(0).compareTo(e) > 0) { // idx 0
			addToHead(e);
		} else { // idx 1+
			Node<E> node = head.getNext().getNext();
			Node<E> next = node.getNext();

			while (!node.equals(head)) {
				node = next;
				next = next.getNext();
				i++;
				if (node.getData().compareTo(e) <= 0
					&& node.getNext().getData().compareTo(e) >= 0) {
					break;
				}
			}

			if (node.equals(head)) {
				super.add(e);
			} else {
				Node<E> newNode = new Node<E>(e);
				newNode.setNext(next);
				node.setNext(newNode);
				size++;
			}
		}

		swing(i);
	}
	
	/**
	 * Reverses the order of the list between the start and stop index inclusively.
	 * 
	 * Assume the indices given are valid and start <= stop
	 * 
	 * @param start The beginning index of the sub section to be reversed
	 * @param stop The end index (inclusive) of the sub section to be reversed
	 */
	public void reverse(int start, int stop) {
		int i = start, j = stop;
		while (i < j) {
			exchangeObjectsAtIndexes(i, j);
			i++;
			j--;
		}
	}
	
	/**
	 * This method will take in an index and move everything after 
	 * that index to the front of the list
	 * 
	 * Assume the index given is valid
	 * 
	 * @param index The index at which to cut the list
	 */
	public void flipFlop(int index) {
		Node<E> prev = getNode(index);
		Node<E> node = prev.getNext();

		while (!node.equals(head)) {
			addToHead(node.getData());

			removeNodeFromPrevious(prev);

			prev = node;
			node = prev.getNext();
		}
	}
	
	/**
	 * This method will reverse the order of the first half of the list up to 
	 * the index argument (inclusive), and also reverse the second half of the 
	 * list from index + 1 to the end of the list
	 * 
	 * Assume the index given is valid, however the second half may be empty
	 * 
	 * @param index The index to swing around
	 */
	public void swing(int index) {
		reverse(0, index);
		if (size > index + 1) {
			reverse(index + 1, size - 1);
		}
	}
}
