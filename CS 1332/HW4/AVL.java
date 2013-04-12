import java.util.Collection;
import java.lang.Math;
import java.util.List;
import java.util.ArrayList; // for the order/traversal methods

public class AVL<T extends Comparable<T>> {
	
	private Node<T> root;
	private int size;
	
	/**
	 * Adds a data entry to the AVL tree
	 * 
	 * @param data The data entry to add
	 */
	public void add(T data) {
		root = addRecursive(root, data);
		size++;
	}
	
	private Node<T> addRecursive(Node<T> node, T data) {
		if (node == null) {
			node = new Node<T>(data);
		} else {
			int compare = data.compareTo((T)node.getData());
			if (compare > 0 || data == null) {
				// if data is null or data > node
				node.setRight(addRecursive(node.getRight(), data));
			} else if (data != null && compare < 0) {
				// if data is !null and data <= node
				node.setLeft(addRecursive(node.getLeft(), data));
			} else {
				return node;
			}
		}
		return rotate(node);
	}
	
	/**
	 * Adds each data entry from the collection to this AVL tree
	 * 
	 * @param c The collection 
	 */
	public void addAll(Collection<? extends T> c) {
		for (T i : c) {
			add(i);
		}
	}
	
	/**
	 * Removes a data entry from the AVL tree
	 * 
	 * Return null if the value does not exist
	 * 
	 * @param data The data entry to be removed
	 * @return The removed data entry
	 */
	public T remove(T data) {
		if (!contains(data)) return null;
		if (size == 1) { 
			root = null;
		} else {
			root = removeRecursive(root, data);
		}
		size--;
		return data;
	}
	
	private T rightmost(Node<T> node) {
		if (node == null) return null;
		Node<T> right = node.getRight();
		if (right == null) {
			return node.getData();
		} else {
			return rightmost(right);
		}
	}
	
	private Node<T> removeRecursive(Node<T> node, T data) {
		if (node == null) return null;
		
		int compare = data.compareTo(node.getData());
		Node<T> left = node.getLeft();
		Node<T> right = node.getRight();
		
		if (compare == 0) { // we have to remove /this/ node
			if (left == null) {
				return right;
			} else if (right == null) {
				return left;
			} else {
				// replace this with the rightmost (greatest) of the left tree
				T replacement = rightmost(left);
				node.setData(replacement);
				node.setLeft(removeRecursive(left, replacement));
			}
		} else if (compare < 0) {
			node.setLeft(removeRecursive(left, data));
		} else {
			node.setRight(removeRecursive(right, data));
		}
		return rotate(node);
	}
	
	/**
	 * Checks if the AVL tree contains a data entry
	 * 
	 * @param data The data entry to be checked
	 * @return If the data entry is in the AVL tree 
	 */
	public boolean contains(T data) {
		return containsRecursive(root, data);
	}
	
	private boolean containsRecursive(Node<T> node, T data) {
		if (node == null) return false;
		T thisNodeData = node.getData();
		if (thisNodeData.equals(data)) return true;
		else if (thisNodeData == null || data.compareTo(thisNodeData) < 0) { // data < thisNodeData
			return containsRecursive(node.getLeft(), data);
		} else { // data > thisNodeData, or data == null
			return containsRecursive(node.getRight(), data);
		}
	}
	
	/**
	 * Calculates the current height and balance factor for a node and updates the values
	 * 
	 * THIS DOES NOT RECURSIVELY UPDATE N AND ALL OF N'S CHILDREN, ONLY UPDATE N
	 * (caps because it's important! Don't kill the running time of everything!)
	 * 
	 * @param n The node whose values are to be calculated and updated
	 * @return The node passed in with updated values
	 */
	private Node<T> updateHeightAndBF(Node<T> n) {
		if (n != null) {
			Node<T> left = n.getLeft();
			Node<T> right = n.getRight();
			int leftHeight = (left == null) ? -1 : left.getHeight();
			int rightHeight = (right == null) ? -1 : right.getHeight();
			n.setHeight(1 + Math.max(leftHeight, rightHeight));
			n.setBf(leftHeight - rightHeight);
		}
		return n;
	}
	
	/**
	 * Determines what rotation, if any, needs to be performed on a node and does the appropriate rotation
	 * 
	 * @param n The node to potentially be rotated
	 * @return The new root of the subtree that is now balanced due to the rotation 
	 * 			(possibly the same node that was passed in) 
	 */
	private Node<T> rotate(Node<T> n) {
		if (n == null) return null;
		n = updateHeightAndBF(n);
		
		int bf = n.getBf();
		if (bf > 1) { // i.e., left > right
			Node<T> left = updateHeightAndBF(n.getLeft());
			if (left == null) return n;
			int lbf = left.getBf();
			if (lbf < 0) {
				n = left(left);
			} else if (lbf > 0) {
				n = leftRight(left);
			}
		} else if (bf < -1) { // i.e., right > left
			Node<T> right = updateHeightAndBF(n.getRight());
			if (right == null) return n;
			int rbf = right.getBf();
			if (rbf < 0) {
				n = right(right);
			} else if (rbf > 0) {
				n = rightLeft(right);
			}
		}
		
		return updateHeightAndBF(n);
	}
	
	/**
	 * Performs a left rotation on a node
	 * 
	 * @param n The node to have the left rotation performed on
	 * @return The new root of the subtree that is now balanced due to the rotation
	 */
	private Node<T> left(Node<T> n) {
		if (n == null) return null;
		if (n.getLeft() == null || n.getRight() == null) return n;
		
        Node<T> left = n.getLeft();
        Node<T> leftRight = left.getRight();
        left.setRight(n);
        n.setLeft(leftRight);
        updateHeightAndBF(n);
        updateHeightAndBF(left);
        return left;
	}
	
	/**
	 * Performs a right rotation on a node
	 * 
	 * @param n The node to have the right rotation performed on
	 * @return The new root of the subtree that is now balanced due to the rotation
	 */
	private Node<T> right(Node<T> n) {
		if (n == null) return null;
		if (n.getLeft() == null || n.getRight() == null) return n;
		
        Node<T> right = n.getRight();
        Node<T> rightLeft = right.getLeft();
        right.setLeft(n);
        n.setRight(rightLeft);
        updateHeightAndBF(n);
        updateHeightAndBF(right);
        
        return right;
	}
	
	/**
	 * Performs a left right rotation on a node
	 * 
	 * @param n The node to have the left right rotation performed on
	 * @return The new root of the subtree that is now balanced due to the rotation
	 */
	private Node<T> leftRight(Node<T> n) {
		if (n == null) return null;
		if (n.getLeft() == null || n.getRight() == null) return n;
		
		n.setRight(left(n.getLeft()));
		return right(n);
	}
	
	/**
	 * Performs a right left rotation on a node
	 * 
	 * @param n The node to have the right left rotation performed on
	 * @return The new root of the subtree that is now balanced due to the rotation
	 */
	private Node<T> rightLeft(Node<T> n) {
		if (n == null) return null;
		if (n.getLeft() == null || n.getRight() == null) return n;
		
		n.setRight(right(n.getRight()));
		return left(n);
	}
	
	/**
	 * Checks to see if the AVL tree is empty
	 * 
	 * @return If the AVL tree is empty or not
	 */
	public boolean isEmpty() {
		return (root == null);
	}
	
	/**
	 * Clears this AVL tree
	 */
	public void clear() {
		root = null;
		size = 0;
	}
	
	/*
	 * From BST, for testing only.
	 */

	public List<T> inOrder() {
		if (root == null) return null;
		ArrayList<T> list = new ArrayList<T>();
		inOrderRecursive(root, list);
		return list;
	}
	
	private void inOrderRecursive(Node<T> node, ArrayList<T> listToAddTo) {
		if (node == null) return; // break recursion
		inOrderRecursive(node.getLeft(), listToAddTo);
		listToAddTo.add(node.getData());
		inOrderRecursive(node.getRight(), listToAddTo);
	}
	
	/*
	 * Getters and Setters: Do not modify anything below this point
	 */
	
	public Node<T> getRoot() {
		return root;
	}

	public void setRoot(Node<T> root) {
		this.root = root;
	}
	
	public int size() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public static class Node<K extends Comparable<K>> {
		
		private K data;
		private Node<K> left, right;
		private int height;
		private int bf;
		
		public Node(K data) {
			setData(data);
		}

		public K getData() {
			return data;
		}

		public void setData(K data) {
			this.data = data;
		}
		
		public Node<K> getLeft() {
			return left;
		}
		
		public void setLeft(Node<K> left) {
			this.left = left;
		}
		
		public Node<K> getRight() {
			return right;
		}
		
		public void setRight(Node<K> right) {
			this.right = right;
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}

		public int getBf() {
			return bf;
		}

		public void setBf(int bf) {
			this.bf = bf;
		}
	}
}
