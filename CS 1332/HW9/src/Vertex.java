public class Vertex {

	private int id;

	/**
	 * A simple vertex class
	 * 
	 * @param id
	 */
	public Vertex(int id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (o != null && o instanceof Vertex) {
			return id == ((Vertex) o).id;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return id;
	}

	public int getId() {
		return id;
	}
	
	public String toString() {
		return ((Integer)id).toString();
	}

}
