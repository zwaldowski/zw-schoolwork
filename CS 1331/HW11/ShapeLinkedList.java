//
// Homework 11
// Zachary Waldowski
// CS 1331
//

public class ShapeLinkedList
{
	private class ShapeLinkedListNode {
		Shape shape;
		ShapeLinkedListNode next;

		ShapeLinkedListNode(Shape iShape) {
			shape = iShape;
		}

	} // end private class ShapeLinkedListNode

	private ShapeLinkedListNode head;
	private int length = 0;

	public ShapeLinkedList()
	{
		//Do not modify this constructor or the provided code will not work
		//Your initial call to addToRear should deal with any initialization
		//that may be required for the LL
	}

	private ShapeLinkedListNode getNodeAtIndex(int idx) {
		// fire an array exception if it's out of bounds
		if (idx >= length || idx < 0) {
			throw new ArrayIndexOutOfBoundsException(idx);
		}

		// start out with head (idx == 0)
		// loop through each next until we get to our node
		int i = 0;
		ShapeLinkedListNode node = head;
		while (i != idx) {
			node = node.next;
			i++;
		}
		return node;
	}

	public void addToRear(Shape shape)
	{
		ShapeLinkedListNode node = new ShapeLinkedListNode(shape);
		if (head == null) { // "initial" condition: set head
			head = node;
		} else { // set the next field on the last node
			// i.e., the last one in an array of size 5 is index 4
			getNodeAtIndex(length - 1).next = node;
		}
		length++;
	}

	public Shape get(int index)
	{
		return getNodeAtIndex(index).shape;
	}

	public int size()
	{
		return length;
	}

	public Shape remove(int index)
	{
		Shape ret;
		if (index == 0) { // if it's the head, then just reset the head
			ret = head.shape;
			head = head.next;
		} else { // if it isn't, then get the previous node, reset its next, and that's it
			ShapeLinkedListNode previousNode = getNodeAtIndex(index - 1);
			ShapeLinkedListNode thisNode = previousNode.next;
			ret = thisNode.shape;
			previousNode.next = thisNode.next;
		}
		length--;
		return ret;
	}
}
