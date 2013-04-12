import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MST {

	/**
	 * Run Kruskal's algorithm on the given graph and return the MST, return
	 * null if no MST exists for the graph
	 * 
	 * @param g
	 *            the graph, g will never be null
	 * @return the MST of the graph, null of no valid MST exists
	 */
	public static Collection<Edge> kruskals(Graph g) {
		ArrayList<Edge> edges = new ArrayList<>(g.getEdgeList());
		Collections.sort(edges);
		DisjointSets<Vertex> disjointSet = new DisjointSets<>(g.getVertices());
		ArrayList<Edge> ret = new ArrayList<>();
		
		for (Edge smallest : edges) {
			Vertex U = smallest.getU(), V = smallest.getV();
			if (!disjointSet.sameSet(U, V)) {
				disjointSet.merge(U, V);
				ret.add(smallest);
			}
		}
		
		return ret;
	}
}
