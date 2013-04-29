import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class MaxFlow {

	/**
	 * We will be grading this method solely based on the values you return. Yes
	 * it will be strict, but this is extra credit. There is only 1 method so we
	 * obviously won't be testing the intermediate parts of the algorithm. We
	 * are giving you the freedom to code it however you want though.
	 * 
	 * (the same things about changing the method headers and class headers from
	 * all previous homework applies to this, I just didn't feel the need to
	 * attach an entirely separate pdf just containing it. reference an old
	 * homework if you forgot what it says!)
	 * 
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * 
	 * This method should return a single integer, the maximum flow from s to t.
	 * You can assume that the max flow exists and is greater than 0.
	 * 
	 * There are n vertices in the graph. Numbered 0 to n-1 (inclusive).
	 * 
	 * capacities will be a n by n matrix, that represents an adjacency matrix
	 * of the network. capacities[i][j] is the capacity that can flow from
	 * vertex i to vertex j. A 0 represents there being no edge between i and j
	 * in the network.
	 * 
	 * note that the graph is directed, you may assume that if capacities[i][j]
	 * is greater than 0, then capacities[j][i] is 0, in other words there will
	 * be only one edge between two vertices in either direction
	 * 
	 * s is the source vertex that the flow should start from, s will be in the
	 * range [0, n)
	 * 
	 * t is the sink where the flow should end at, t will be in the range [0, n)
	 * 
	 * @param n
	 *            the number of vertices, this will be less than 500
	 * @param s
	 *            the source vertex
	 * @param t
	 *            the sink vertex
	 * @param capacities
	 *            the capacities of the network
	 * @return the maximum flow of the network
	 */
	public static int flow(int n, int s, int t, int[][] capacities) {
		int[][] flowNetwork = new int[n][n];
		for (int[] e : flowNetwork) {
			Arrays.fill(e, 0);
		}

		while (true) {
			int[] path = new int[n];
			Arrays.fill(path, -1);
			path[s] = -2; // avoid

			LinkedList<Integer> queue = new LinkedList<>();
			queue.add(s);

			while (!queue.isEmpty()) {
				int u = queue.poll();
				for (int v : getNeighbors(capacities, u)) {
					if (path[v] != -1) continue;
					if (flowNetwork[u][v] < capacities[u][v]) {
						path[v] = u;
						queue.add(v);
					}
				}
			}

			// no path found, we're done here
			if (path[t] == -1) break;

			// bottleneck
			int bottleneck = Integer.MAX_VALUE;
			for (int right = t, left = path[right]; left >= s; right = left, left = path[right]) {
				bottleneck = Math.min(bottleneck, capacities[left][right] - flowNetwork[left][right]);
			}

			for (int right = t, left = path[right]; left >= s; right = left, left = path[right]) {
				flowNetwork[left][right] += bottleneck;
			}

		}

		int maximumFlow = 0;
		for (int v : getNeighbors(capacities, s))  {
			maximumFlow += Math.abs(flowNetwork[s][v]);
		}
		return maximumFlow;
	}

	private static Set<Integer> getNeighbors(int[][] capacities, int v) {
		HashSet<Integer> ret = new HashSet<>();
		int[] relations = capacities[v];
		for (int i = 0; i < relations.length; i++) {
			if (relations[i] != 0) {
				ret.add(i);
			}
		}
		return ret;
	}

}