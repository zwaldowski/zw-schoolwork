import java.util.Arrays; /* Only for testing. */
import java.util.Comparator;

/**
 * This is an implementation of a heap that is backed by an array.
 * 
 * This implementation will accept a comparator object that can be used to
 * define an ordering of the items contained in this heap, other than the
 * objects' default compareTo method (if they are comparable). This is useful if
 * you wanted to sort strings by their length rather than their lexicographic
 * ordering. That's just one example.
 * 
 * Null should be treated as positive infinity if no comparator is provided. If
 * a comparator is provided, you should let it handle nulls, which means it
 * could possibly throw a NullPointerException, which in this case would be
 * fine.
 * 
 * If a comparator is provided that should always be what you use to compare
 * objects. If no comparator is provided you may assume the objects are
 * Comparable and cast them to type Comparable<T> for comparisons. If they
 * happen to not be Comparable you do not need to handle anything, and you can
 * just let your cast throw a ClassCastException.
 * 
 * This is a minimum heap, so the smallest item should always be at the root.
 * 
 * @param <T>
 *            The type of objects in this heap
 */
public class BinaryHeap<T> implements Heap<T> {
	
	/* Instance variables. */

	/**
	 * The comparator that should be used to order the elements in this heap
	 */
	private Comparator<T> comp;

	/**
	 * The backing array of this heap
	 */
	private T[] data;

	/**
	 * The number of elements that have been added to this heap, this is NOT the
	 * same as data.length
	 */
	private int size;
	
	private static final int DEFAULT_DATA_SIZE = 11;
	
	/* Private methods. */
	
	@SuppressWarnings("unchecked")
	private int compare(T obj1, T obj2) {
		if (comp != null) {
			return comp.compare(obj1,  obj2);
		} else {
			if (obj1 != null && obj2 != null) {
				return ((Comparable<T>)obj1).compareTo(obj2);
			} else if (obj1 == null) {
				return 1;
			} else if (obj2 == null) {
				return -1;
			} else {
				return 0;
			}
		}
	}
	
	private void tableTransfer(T[] src, T[] dest) {
		int j = 0;
		for (int i = 0; i < src.length; i++) {
			T entry = src[i];
			if (entry != null) {
				src[i] = null;
				dest[j] = entry;
				j++;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void resize() {
		int newSize = (2 * data.length) + 1;
		
		T[] oldData = data;
		T[] newData = (T[])new Comparable[newSize];
		
		tableTransfer(oldData, newData);
		
		data = newData;
	}
	
	private int swap(int index1, int index2) {
        T tmp = data[index1];
        data[index1] = data[index2];
        data[index2] = tmp;      
		return index2;
	}
	
	private int parent(int index) {
		return index / 2;
	}
	
	private void bubbleUp(int index) {
		while ((index > 1) && (compare(data[parent(index)], data[index]) > 0)) {
			index = swap(index, parent(index));
		}
	}
	
	private int rightChild(int index) {
		int ret = (index * 2) + 1;
		if (ret > size) return -1;
		return ret;
	}
	
	private int leftChild(int index) {
		int ret = (index * 2);
		if (ret > size) return -1;
		return ret;
	}
	
	private void bubbleDown(int index) {
		if (size <= 1) return;
		
		int rightIndex = rightChild(index);
		int leftIndex = leftChild(index);
		
		while (rightIndex != -1 || leftIndex != -1) {
			T right = rightIndex == -1 ? null : data[rightIndex];
			T left = leftIndex == -1 ? null : data[leftIndex];
			
			int smallerIndex = left == null ? rightIndex : leftIndex;
			if (right != null && left != null) {
				smallerIndex = compare(left, right) < 0 ? leftIndex : rightIndex;
			}
			
			if (compare(data[index], data[smallerIndex]) > 0) {
                swap(index, smallerIndex);
            } else {
                break;
            }
			
			index = smallerIndex;
			rightIndex = rightChild(index);
			leftIndex = leftChild(index);
		}
	}
	
	/* Project methods. */

	/**
	 * Default constructor, this should initialize data to a default size (11 is
	 * normally a good choice)
	 * 
	 * This assumes that the generic objects are Comparable, you will need to
	 * cast them when comparing since there are no bounds on the generic
	 * parameter
	 */
	@SuppressWarnings("unchecked")
	public BinaryHeap() {
		data = (T[])new Comparable[DEFAULT_DATA_SIZE];
	}

	/**
	 * Constructor that accepts a comparator to use with this heap. Also
	 * initializes data to a default size.
	 * 
	 * When a comparator is provided it should be preferred over the objects'
	 * compareTo method
	 * 
	 * If the comparator given is null you should attempt to cast the objects to
	 * Comparable as if a comparator were not given
	 * 
	 * @param comp
	 */
	public BinaryHeap(Comparator<T> aComp) {
		this();
		comp = aComp;
	}

	@Override
	public void add(T item) {
		if (size + 1 >= data.length) {
			resize();
		}
		
		size++;
		data[size] = item;
		
		bubbleUp(size);
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public T peek() {
		if (isEmpty()) return null;
		return data[1];
	}

	@Override
	public T remove() {
		if (size == 0) return null;
		T value = peek();
		
		data[1] = data[size];
		data[size] = null;
		size--;
		
		bubbleDown(1);

		return value;
	}

	@Override
	public int size() {
		return size;
	}
	
	/* For testing. */

	public T[] getContents() {
		return Arrays.copyOfRange(data, 1, size + 1);
	}
}
