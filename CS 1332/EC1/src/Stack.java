import java.util.LinkedList;

/**
 * Don't modify this code. When graded your changes will not be used.
 */
public class Stack<T> {

	private LinkedList<T> l;
	
	public Stack() {
		l = new LinkedList<>();
	}
	
	public void push(T e) {
		l.addLast(e);
	}
	
	public T pop() {
		return l.removeLast();
	}
	
	public void clear() {
		l.clear();
	}
	
	public boolean isEmpty() {
		return l.isEmpty();
	}
}
