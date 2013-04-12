
public class Floyd {

	/**
	 * Run Floyd Warshall on the given graph. At the end the given
	 * adjacency matrix should contain the length of all of the
	 * shortest paths between vertices.
	 * 
	 * graph[i][j] should be the length of the shortest path
	 * between nodes i and j
	 * 
	 * You will also detect if there are negative cycles in the
	 * given graph.
	 * 
	 * The graph is directed. graph[i][j] represents the weight of
	 * the edge going from node i to j
	 * 
	 * @param n			the number of nodes in the graph
	 * @param graph		the n by n adjacency matrix representing the graph
	 * @param INF		the value in graph that represents no edge/path between vertices
	 * @return			true if there are no negative cycles in the graph, false otherwise
	 */
	public static boolean floydWarshall(int n, int[][] graph, int INF) {
		
		int[][] path = new int[n][n];
		
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				if (graph[i][k] == INF) continue;
				
				for (int j = 0; j < n; j++) {
					graph[i][j] = Math.min( graph[i][k] + graph[k][j], graph[i][j]);
					path[i][j] = path[k][j];
				}
			}
		}
		
		for (int v = 0; v < n; v++) {
			if (path[v][v] < 0) return false;
		}
		
		return true;
	}
	
}
