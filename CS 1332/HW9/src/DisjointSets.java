import java.util.HashMap;
import java.util.Set;

public class DisjointSets<T> {
	
	private class Node {
		int rank;
		T parent;
		Node(T parent, int rank) {
			this.parent = parent;
			this.rank = rank;
		}
	}
	
	private final HashMap<T, Node> objectsToNodes = new HashMap<>();

	/**
	 * @param setItems
	 *            the initial items (sameSet and merge will never be called with
	 *            items not in this set, this set will never contain null
	 *            elements)
	 */
	public DisjointSets(Set<T> setItems) {
		for (T object : setItems) {
			makeSet(object);
		}
	}
	
	void makeSet(T object) {
		objectsToNodes.put(object, new Node(null, 0));
	}
	
	T findSet(T object) {
		Node node = objectsToNodes.get(object);
		if (node.parent != null) {
			node.parent = findSet(node.parent);
			return node.parent;
		} else {
			return object;
		}
	}

	/**
	 * @param u
	 * @param v
	 * @return true if the items given are in the same set, false otherwise
	 */
	public boolean sameSet(T u, T v) {
		T parentU = findSet(u), parentV = findSet(v);
		return parentU.equals(parentV);
	}

	/**
	 * merges the sets u and v are in, do nothing if they are in the same set
	 * 
	 * @param u
	 * @param v
	 */
	public void merge(T u, T v) {
		T parentU = findSet(u), parentV = findSet(v);
		if (parentU.equals(parentV)) return;
		Node pnodeU = objectsToNodes.get(parentU), pnodeV = objectsToNodes.get(parentV);
		
		if (pnodeU.rank >= pnodeV.rank){
			pnodeV.parent = parentU;
			if (pnodeU.rank == pnodeV.rank) {
				pnodeU.rank++;
			}
		} else {
			pnodeU.parent = parentV;
		}
	}
}
