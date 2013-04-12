import java.util.Collection;
import java.util.List;
import java.util.ArrayList; // for the order/traversal methods

/**
 * A binary search tree. (HW03)
 * @author Zachary Waldowski
 */
public class BST<T extends Comparable<T>> {
	
	private Node<T> root;
	private int size = 0;

	/**
	 * Adds a data entry to the binary search tree.
	 * 
	 * For the purposes of this implementation, null
	 * values are sorted as being if they were positive
	 * infitity.
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
			if (compare == 0) {
				return node;
			} else if (compare > 0 || data == null) {
				// if data is null or data > node
				node.setRight(addRecursive(node.getRight(), data));
			} else {
				// if data is !null and data <= node
				node.setLeft(addRecursive(node.getLeft(), data));
			}
		}
		return node;
	}
	
	/**
	 * Adds each data entry from the collection to this BST
	 * 
	 * @param c
	 */
	public void addAll(Collection<? extends T> c) {
		for (T i : c) {
			add(i);
		}
	}
	
	/**
	 * Removes a data entry from the BST
	 * 
	 * null is positive infinity
	 * 
	 * @param data The data entry to be removed
	 * @return The removed data entry (null if nothing is removed)
	 */
	public T remove(T data) {
		T realData = get(data);
		if (realData == null) return null;
		root = removeRecursive(root, data);
		size--;
		return realData;
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
		return node;
	}
	
	/**
	 * Checks if the binary search tree contains a data entry.
	 * 
	 * For the purposes of this implementation, null
	 * values are sorted as being if they were positive
	 * infitity.
	 * 
	 * @param data The data entry to be checked
	 * @returns true if the the data entry is in the tree, false otherwise 
	 */
	public boolean contains(T data) {
		return (get(data) != null);
	}

	private T get(T data) {
		Node<T> node = getNode(data);
		if (node == null) return null;
		return node.getData();
	}

	public Node<T> getNode(T data) {
		return getNodeRecursive(root, data);
	}
	
	private Node<T> getNodeRecursive(Node<T> node, T data) {
		if (node == null) return null;
		T thisNodeData = node.getData();
		if (thisNodeData.equals(data)) return node;
		else if (thisNodeData == null || data.compareTo(thisNodeData) < 0) { // data < thisNodeData
			return getNodeRecursive(node.getLeft(), data);
		} else { // data > thisNodeData, or data == null
			return getNodeRecursive(node.getRight(), data);
		}
	}
	
	/**
	 * Finds the pre-order traversal of the BST
	 * 
	 * @return A list of the data set in the BST in pre-order
	 */
	public List<T> preOrder() {
		if (root == null) return null;
		ArrayList<T> list = new ArrayList<T>();
		preOrderRecursive(root, list);
		return list;
	}
	
	private void preOrderRecursive(Node<T> node, ArrayList<T> listToAddTo) {
		if (node == null) return; // break recursion
		listToAddTo.add(node.getData());
		preOrderRecursive(node.getLeft(), listToAddTo);
		preOrderRecursive(node.getRight(), listToAddTo);
	}

	/**
	 * Finds the in-order traversal of the BST
	 * 
	 * @return A list of the data set in the BST in in-order
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
	
	/**
	 * Finds the post-order traversal of the BST
	 * 
	 * @return A list of the data set in the BST in post-order
	 */
	public List<T> postOrder() {
		if (root == null) return null;
		ArrayList<T> list = new ArrayList<T>();
		postOrderRecursive(root, list);
		return list;
	}
	
	private void postOrderRecursive(Node<T> node, ArrayList<T> listToAddTo) {
		if (node == null) return; // break recursion
		postOrderRecursive(node.getLeft(), listToAddTo);
		postOrderRecursive(node.getRight(), listToAddTo);
		listToAddTo.add(node.getData());
	}
	
	/**
	 * Checks to see if the BST is empty
	 * 
	 * @return If the BST is empty or not
	 */
	public boolean isEmpty() {
		return (root == null);
	}
	
	/**
	 * Clears this BST
	 */
	public void clear() {
		root = null;
		size = 0;
	}
	
	/**
	 * @return the size of this BST
	 */
	public int size() {
		return size;
	}
	
	/**
	 * First clears this BST, then reconstructs the BST that is
	 * uniquely defined by the given preorder and inorder traversals
	 * 
	 * (When you finish, this BST should produce the same preorder and
	 * inorder traversals as those given)
	 * 
	 * @param preorder a preorder traversal of the BST to reconstruct
	 * @param inorder an inorder traversal of the BST to reconstruct
	 */
	public void reconstruct(List<? extends T> preorder, List<? extends T> inorder) {
		assert((preorder.size() != 0) && (inorder.size() != 0) && (preorder.size() == inorder.size()));
		clear();
		root = reconstructRecursive(preorder, inorder);
		size = preorder.size();
	}
	
	private Node<T> reconstructRecursive(List<? extends T> preorder, List<? extends T> inorder) {
		if ((preorder.size() != 0) && (inorder.size() != 0)) {
			T first = preorder.get(0);
			Node<T> node = new Node<T>(first);
			
			int inorderIdx = inorder.indexOf(first);
			List<? extends T> leftInorder = inorder.subList(0, inorderIdx);
			List<? extends T> rightInorder = inorder.subList(inorderIdx + 1, inorder.size());

			int preorderIdx = leftInorder.size();
			List<? extends T> leftPreorder = preorder.subList(1, preorderIdx + 1);
			List<? extends T> rightPreorder = preorder.subList(preorderIdx + 1, preorder.size());

			node.setLeft(reconstructRecursive(leftPreorder, leftInorder));
			node.setRight(reconstructRecursive(rightPreorder, rightInorder));
			
			return node;
		}
		return null;
	}
	
	/*
	 * The following methods are for grading, do not modify them
	 */
	
	public Node<T> getRoot() {
		return root;
	}

	public void setRoot(Node<T> root) {
		this.root = root;
	}
	
	public void setSize(int size) {
		this.size = size;
	}

	public static class Node<K extends Comparable<K>> {
		
		private K data;
		private Node<K> left, right;
		
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
	}

}
