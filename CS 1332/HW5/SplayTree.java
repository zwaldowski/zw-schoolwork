//
// Homework 5
// Zachary Waldowski
// CS 1332
//

/**
 * A binary search tree that reorganizes itself upon access. (HW05)
 * @author Zachary Waldowski
 */
public class SplayTree<T extends Comparable<? super T>> {

	private Node<T> root;

	private int size;
	
	/**
	 * Here's the magic!
	 * We splay the tree.
	 * 
	 * @param data The value to splay the tree by.
	 */
	private void splay(T data) {
		Node<T> node = getNode(data);
		if (node != null) {
			splayNode(node);
		}
	}
	
	/**
	 * Internal node splayer: this is the big one.
	 * @param node The node to recursively splay
	 */
	private void splayNode(Node<T> node) {
		int rootCompare = node.compareTo(root);
		if (rootCompare == 0) {
			return;
		} else if (node.getParent().compareTo(root) == 0) {
			// zig
			if (rootCompare < 0) {
				// left of root
				root = rotateRight(node);
				root.setParent(null);
			} else {
				// right of root
				root = rotateLeft(node);
				root.setParent(null);
			}
			return;
		} else {
			Node<T> grandParent = node.getParent().getParent();
			boolean found = false;
			
			Node<T> gpLeft = grandParent.getLeft();
			Node<T> gpRight = grandParent.getRight();
			
			if (gpLeft != null) {
				Node<T> gpLeftLeft = gpLeft.getLeft();
				Node<T> gpLeftRight = gpLeft.getRight();
				
				if (gpLeftLeft != null && gpLeftLeft.compareTo(node) == 0) {
					node = rotateRightRight(node);
					found = true;
				} else if (gpLeftRight != null && gpLeftRight.compareTo(node) == 0) {
					node = rotateLeftRight(node);
					found = true;
				}
			}
			
			if (gpRight != null && !found) {
				Node<T> gpRightLeft = gpRight.getLeft();
				
				if (gpRightLeft != null && gpRightLeft.compareTo(node) == 0) {
					node = rotateRightLeft(node);
				} else {
					node = rotateLeftLeft(node);
				}
			}
			
			if (root.compareTo(grandParent) == 0) {
				root = node;
				node.setParent(null);
			}
		}
		splayNode(node);
	}

	/**
	 * Gets a node containing data, without splaying.
	 * @param data The data object to find.
	 * @return An internal node.
	 */
	private Node<T> getNode(T data) {
		return getNode(root, data);
	}
	
	/**
	 * Internal recursive helper for 
	 * @param node
	 * @param data
	 * @return
	 */
	private Node<T> getNode(Node<T> node, T data) {
		if (node == null) return null;
		int compare = safeCompareData(data, node.getData());
		if (compare < 0) {
			return getNode(node.getLeft(), data);
		} else if (compare > 0) {
			return getNode(node.getRight(), data);
		}
		return node;
	}
	
	/**
	 * Safely compares two data objects using the
	 * null = infinity rule.
	 * @param one A data object, or null; left operand of comapre
	 * @param two A data object, or null; right operand of compare
	 * @return The same result of one.compareTo(two), with null accomodations
	 */
	private int safeCompareData(T one, T two) {
		if (one == null || two == null) {
			if (one == null && two == null) {
				return 0;
			} else if (two == null) {
				return -1;
			} else {
				return 1;
			}
		} else {
			return one.compareTo(two);
		}
	}
	
	/**
	 * Rotates a node left without fiddling with parents
	 * @param node The node whose parent to rotate left
	 * @return The new root of the subtree
	 */
	private Node<T> rotateLeft(Node<T> node) {
		Node<T> parent = node.getParent();
		T parentData = parent.getData();
		parent.setData(node.getData());
		node.setData(parentData);
		
		parent.setRight(node.getRight());
		node.setRight(node.getLeft());
		
		node.setLeft(parent.getLeft());
		parent.setLeft(node);

		Node<T> parentRight = parent.getRight();
		Node<T> nodeLeft = node.getLeft();
		
		if (parentRight != null) {
			parentRight.setParent(parent);
		}

		if (nodeLeft != null) {
			nodeLeft.setParent(node);
		}
		
		return parent;
	}
	
	/**
	 * Rotates a node right without fiddling with parents
	 * @param node The node whose parent to rotate right around
	 * @return The new root of the subtree
	 */
	private Node<T> rotateRight(Node<T> node) {
		Node<T> parent = node.getParent();
		T parentData = parent.getData();
		parent.setData(node.getData());
		node.setData(parentData);
		
		parent.setLeft(node.getLeft());
		node.setLeft(node.getRight());
		
		node.setRight(parent.getRight());
		parent.setRight(node);
		
		Node<T> parentLeft = parent.getLeft();
		Node<T> nodeRight = node.getRight();
		
		if (parentLeft != null) {
			parentLeft.setParent(parent);
		}

		if (nodeRight != null) {
			nodeRight.setParent(node);
		}
		
		return parent;
	}
	
	/**
	 * Performs a double rotation left without too much fiddling with parents
	 * @param node The node which to perform a left-left rotation on.
	 * @return The new root of the subtree
	 */
	private Node<T> rotateLeftLeft(Node<T> node) {
		Node<T> parent = node.getParent();
		Node<T> grandParent = parent.getParent();
		
		T grandParentData = grandParent.getData();
		grandParent.setData(node.getData());
		node.setData(grandParentData);
		
		grandParent.setRight(node.getRight());
		parent.setRight(node.getLeft());
		node.setRight(parent.getLeft());
		node.setLeft(grandParent.getLeft());
		
		grandParent.setLeft(parent);
		parent.setLeft(node);
		
		Node<T> grandParentRight = grandParent.getRight();
		Node<T> parentRight = parent.getRight();
		Node<T> nodeRight = node.getRight();
		Node<T> nodeLeft = node.getLeft();
		
		if (grandParentRight != null) {
			grandParentRight.setParent(grandParent);
		}
		
		if (parentRight != null) {
			parentRight.setParent(parent);
		}
		
		if (nodeRight != null) {
			nodeRight.setParent(node);
		}
		
		if (nodeLeft != null) {
			nodeLeft.setParent(node);
		}
		
		return grandParent;
	}
	
	/**
	 * Performs a double rotation right without too much fiddling with parents
	 * @param node The node which to perform a right-right rotation on.
	 * @return The new root of the subtree
	 */
	private Node<T> rotateRightRight(Node<T> node) {
		Node<T> parent = node.getParent();
		Node<T> grandParent = parent.getParent();
		
		T grandParentData = grandParent.getData();
		grandParent.setData(node.getData());
		node.setData(grandParentData);
		
		grandParent.setLeft(node.getLeft());
		parent.setLeft(node.getRight());
		node.setLeft(parent.getRight());
		node.setRight(grandParent.getRight());

		grandParent.setRight(parent);
		parent.setRight(node);

		Node<T> grandParentLeft = grandParent.getLeft();
		Node<T> parentLeft = parent.getLeft();
		Node<T> nodeLeft = node.getLeft();
		Node<T> nodeRight = node.getRight();
		
		if (grandParentLeft != null) {
			grandParentLeft.setParent(grandParent);
		}
		
		if (parentLeft != null) {
			parentLeft.setParent(parent);
		}
		
		if (nodeLeft != null) {
			nodeLeft.setParent(node);
		}
		
		if (nodeRight != null) {
			nodeRight.setParent(node);
		}
		
		return grandParent;
	}
	
	/**
	 * Performs a rotation left, then a rotation right without too much fiddling with parents
	 * @param node The node which to perform a left-right rotation on.
	 * @return The new root of the subtree
	 */
	private Node<T> rotateLeftRight(Node<T> node) {
		Node<T> parent = node.getParent();
		Node<T> grandParent = parent.getParent();
		
		T grandParentData = grandParent.getData();
		grandParent.setData(node.getData());
		node.setData(grandParentData);
		
		parent.setRight(node.getLeft());
		node.setLeft(node.getRight());
		node.setRight(grandParent.getRight());
		grandParent.setRight(node);
		
		node.setParent(grandParent);
		
		Node<T> parentRight = parent.getRight();
		Node<T> nodeRight = node.getRight();
		
		if (parentRight != null) {
			parentRight.setParent(parent);
		}
		
		if (nodeRight != null) {
			nodeRight.setParent(node);
		}

		return grandParent;
	}
	
	/**
	 * Performs a rotation right, then a rotation left without too much fiddling with parents
	 * @param node The node which to perform a right-left rotation on.
	 * @return The new root of the subtree
	 */
	private Node<T> rotateRightLeft(Node<T> node) {
		Node<T> parent = node.getParent();
		Node<T> grandParent = parent.getParent();
		
		T grandParentData = grandParent.getData();
		grandParent.setData(node.getData());
		node.setData(grandParentData);

		parent.setLeft(node.getRight());
		node.setRight(node.getLeft());
		node.setLeft(grandParent.getLeft());
		grandParent.setLeft(node);
		
		node.setParent(grandParent);
		
		Node<T> parentLeft = parent.getLeft();
		Node<T> nodeLeft = node.getLeft();
		
		if (parentLeft != null) {
			parentLeft.setParent(parent);
		}
		
		if (nodeLeft != null) {
			nodeLeft.setParent(node);
		}

		return grandParent;
	}

	/**
	 * Splay the node containing data after adding
	 * 
	 * @param data The data to add
	 */
	public void add(T data) {
		root = addRecursive(root, data);
		splay(data);
		size++;
	}

	/**
	 * Recursive BST add
	 * 
	 * @param node The node to compare for adding
	 * @param data The data to add
	 */
	private Node<T> addRecursive(Node<T> node, T data) {
		Node<T> newNode = new Node<T>(data);
		if (node == null) {
			node = newNode;
		} else {
			int compare = newNode.compareTo(node);
			if (compare == 0) {
				return node;
			} else if (compare > 0) {
				// if data is null or data > node
				Node<T> newRight = addRecursive(node.getRight(), data);
				if (newRight != null) {
					newRight.setParent(node);
				}
				node.setRight(newRight);
			} else {
				// if data is !null and data <= node
				Node<T> newLeft = addRecursive(node.getLeft(), data);
				if (newLeft != null) {
					newLeft.setParent(node);
				}
				node.setLeft(newLeft);
			}
		}
		return node;
	}

	/**
	 * Splay the parent of the node removed, do nothing
	 * if o is not in the tree, or was the root
	 * 
	 * @param data The data to remove
	 * @return null if not found, otherwise the data from the tree
	 */
	public T remove(T data) {
		// This could be implemented like BST remove
		// (my add() is a straight BST add)
		// However, splaying first saves a whole lot of time
		splay(data);
		
		T rootData = root.getData();
		
		if ((rootData == null && data == null) || (data.compareTo(root.getData()) != 0)) {
			// if we don't need to remove anything,
			// we're already properly splayed
			return null;
		}
		
		T retData = root.getData();
		
		Node<T> rootLeft = root.getLeft();
		Node<T> rootRight = root.getRight();
		
		if (rootLeft == null) {
			root = rootRight;
		} else { // only left child
			// by re-splaying here, this satisfies the predecessor requirement
			root = rootLeft;
			splay(data);
			root.setRight(rootRight);
		}
		
		size--;
		
		return retData;
	}

	/**
	 * Splay the object found matching the data, do nothing
	 * if o is not in the tree
	 * 
	 * @param data The data to retrieve
	 * @return null if the data wasn't found, otherwise the data in the tree.
	 */
	public T get(T data) {
		if (root == null) return null;
		splay(data);
		T rootData = root.getData();
		if (rootData.compareTo(data) != 0) return null;
		return rootData;
	}

	/**
	 * Splay the object found matching the data, do nothing if
	 * o is not in the tree
	 * 
	 * @param data The data to locate
	 * @return true if the data was found in the tree, false otherwise
	 */
	public boolean contains(T data) {
		// ideally we should just call (get(data) != null),
		// but that false fails for contains(null)
		if (root == null) return false;
		splay(data);
		T rootData = root.getData();
		if ((rootData == null && data == null) || (rootData.compareTo(data) == 0)) return true;
		return false;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return (root == null);
	}

	/*
	 * These methods are for grading, don't modify them
	 */

	public void setSize(int size) {
		this.size = size;
	}

	public Node<T> getRoot() { 
		return root;
	}

	public void setRoot(Node<T> root) {
		this.root = root;
	}

	public static class Node<E extends Comparable<? super E>> implements Comparable<Node<E>>{

		private Node<E> parent, left, right;
		private E data;

		public Node(E data) {
			this.data = data;
		}

		public Node<E> getParent() {
			return parent;
		}

		public void setParent(Node<E> parent) {
			this.parent = parent;
		}

		public Node<E> getLeft() {
			return left;
		}

		public void setLeft(Node<E> left) {
			this.left = left;
		}

		public Node<E> getRight() {
			return right;
		}

		public void setRight(Node<E> right) {
			this.right = right;
		}

		public E getData() {
			return data;
		}
		
		public void setData(E data) {
			this.data = data;
		}
		
		@Override
		public int compareTo(Node<E> tht) {
			if (data == null && tht.data == null) return 0;
			if (tht.data == null) return -1332;
			if (data == null) return 1332;
			return data.compareTo(tht.data);
		}
	}

}
