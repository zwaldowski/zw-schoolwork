import java.util.Collection;

/**
 * A circular, singly linked list. (HW02)
 * @author Zachary Waldowski
 */
public class LinkedList<E> implements List<E> {

	protected Node<E> head;
	protected int size;

	/**
	 * Helper method. Adds only to the beginning of the list.
	 *
	 * @param An object to add to the begining of the list.
	 */
	protected void addToHead(E e) {
		Node<E> newNode = new Node<>(e);

		if (head == null) {
			head = newNode;
		} else {
			newNode.setNext(head.getNext());
		}

		head.setNext(newNode);
		
		size++;
	}

	public void add(E e) {
		if (head == null) {
			addToHead(e);
		} else {
			Node<E> newNode = new Node<>(e);
			newNode.setNext(head.getNext());
			head.setNext(newNode);
			head = newNode;
		}

		size++;
	}

	public void addAll(Collection<? extends E> c) {
		for (E i : c) {
			add(i);
		}
	}

	public void clear() {
		head = null;
		size = 0;
	}

	public boolean contains(Object o) {
		return (indexOf(o) != -1);
	}
	
	/**
	 * Helper method for, among others get(int).

	 * @param index The index of the node to retrieve
	 * @return The node for the specified index.
	 */
	protected Node<E> getNode(int index) {
		// fire an array exception if it's out of bounds
		if (index >= size || index < 0) {
			throw new ArrayIndexOutOfBoundsException(index);
		}

		Node<E> node = head.getNext();
		int i = 0;

		while (i < index) {
			node = node.getNext();
			i++;
		}
	
		return node;
	}

	public E get(int index) {
		return getNode(index).getData();
	}

	public int indexOf(Object o) {
		if (size != 0) {
			Node<E> node = head.getNext();
			int i = 0;
			do {
				if (node.getData().equals(o)) return i;
				node = node.getNext();
				i++;
			} while (!node.equals(head));
		}
		return -1;
	}

	public boolean isEmpty() {
		return (size == 0);
	}

	/**
	 * Private helper method, just some conservation of code
	 * between remove(int) and remove(Object).
	 *
	 * "Jumps" the previous node to the one two nodes after, i.e.,
	 * removing it.
	 *
	 * @param previousNode the node previous to the node that should be removed
	 */
	protected void removeNodeFromPrevious(Node<E> previousNode) {
		Node<E> node = previousNode.getNext();
		if (previousNode == node) {
			clear();
		} else {
			previousNode.setNext(node.getNext());
			if (node == head)
				head = previousNode;
			size--;
		}
	}

	public E remove(int index) {
		// fire an array exception if it's out of bounds
		if (index >= size || index < 0) {
			throw new ArrayIndexOutOfBoundsException(index);
		}

		if (size == 1) {
			E data = head.getData();
			clear();
			return data;
		}

		Node<E> prev = getNode(index-1);
		E data = prev.getNext().getData();
		removeNodeFromPrevious(prev);
		return data;
	}

	public E remove(Object o) {
		if (size != 0) {
			Node<E> node = head.getNext(), prev = head;

			while (!node.equals(head)) {
				prev = node;
				node = prev.getNext();
				if (node.getData().equals(o)) break;
			}

			E data = node.getData();
			if (node.getData().equals(o)) {
				removeNodeFromPrevious(prev);
				return data;
			}
		}

		return null;
	}

	public E set(int index, E e) {
		if (index == size) {
			add(e);
			return null;
		} else {
			Node<E> thisNode = getNode(index);
			E old = thisNode.getData();
			thisNode.setData(e);
			return old;
		}
	}

	public int size() {
		return size;
	}

	/*
	 * The following methods are for grading. Do not modify them, and you do not
	 * need to use them.
	 */

	public void setSize(int size) {
		this.size = size;
	}

	public Node<E> getHead() {
		return head;
	}

	public void setHead(Node<E> head) {
		this.head = head;
	}
	
	public static void main(String args[]) {
		System.out.println("Test.");
	}
}
