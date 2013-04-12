
/**
 * This class should simulate a queue using the two stacks provided. 
 * Do not add any other fields to this class and do not modify the
 * stack class. The average running times for all of the methods
 * should be the same as a regular queue.
 * 
 * When we say average running time think about how adding to the
 * end of an array list is still O(1) even though you might have to
 * resize.
 */
public class Queue<T> {

	private Stack<T> a, b;
	
	public Queue() {
		a = new Stack<>();
		b = new Stack<>();
	}
	
	/*
	 * Don't modify code above this point
	 * * * * * * * * * * * * * * * * * * *
	 * Do Modify code below this point
	 */
	
	public void enqueue(T e) {
		a.push(e);
	}
	
	public T dequeue() {
		if (b.isEmpty()) {
			while (!a.isEmpty()) {
				b.push(a.pop());
			}
		}
		return b.pop();
	}
	
	public boolean isEmpty() {
		return a.isEmpty() && b.isEmpty();
	}
	
	public void clear() {
		a.clear();
		b.clear();
	}
	
}
