public class Edge implements Comparable<Edge> {

	private Vertex u, v;
	private int weight;

	/**
	 * Comparable edge class based on weight. Order of u and v does not matter.
	 * 
	 * @param u
	 * @param v
	 * @param weight
	 */
	public Edge(Vertex u, Vertex v, int weight) {
		setU(u);
		setV(v);
		setWeight(weight);
	}

	@Override
	public int hashCode() {
		return (u == null ? 0 : u.hashCode()) ^ (v == null ? 0 : v.hashCode())
				^ weight;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Edge) {
			Edge e = (Edge) o;
			return weight == e.weight
					&& ((u.equals(e.u) && v.equals(e.v)) || (u.equals(e.v) && v
							.equals(e.u)));
		} else {
			return false;
		}
	}

	public int compareTo(Edge e) {
		return weight - e.getWeight();
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Vertex getU() {
		return u;
	}

	public void setU(Vertex u) {
		this.u = u;
	}

	public Vertex getV() {
		return v;
	}

	public void setV(Vertex v) {
		this.v = v;
	}
	
	public String toString() {
		return "{(" + u + ", " + v + "): " + weight + "}";
	}
}