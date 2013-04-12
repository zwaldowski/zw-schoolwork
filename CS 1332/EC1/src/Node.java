
/**
 * Don't modify this code. When graded your changes will not be used.
 */
public class Node {

	public final String name;
	public Node next;
	
	public Node() {
		this("(no name)");
	}
	
	public Node(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
