
public class Sort {
	
	private static <T extends Comparable<T>> boolean isGreaterThan(T[] arr, int idx1, int idx2) {
		if (arr[idx1] != null && arr[idx2] != null) {
			return arr[idx1].compareTo(arr[idx2]) > 0;
		} else if (arr[idx1] == null) {
			return true;
		}
		return false;
	}
	
	private static <T extends Comparable<T>> int swap(T[] data, int index1, int index2) {
        T tmp = data[index1];
        data[index1] = data[index2];
        data[index2] = tmp;      
		return index2;
	}
	
	private static <T extends Comparable<T>> boolean swapAscending(T[] arr, int a, int b) throws ArrayIndexOutOfBoundsException {
		if (a < 0 || a > arr.length) throw new ArrayIndexOutOfBoundsException(a);
		if (b < 0 || b > arr.length) throw new ArrayIndexOutOfBoundsException(b);
		if (isGreaterThan(arr, a, b)) {
			swap(arr, a, b);
			return true;
		} else {
			return false;
		}
	}

	private static <T extends Comparable<T>> void bubbleUp(T[] arr, int index) {
		while (index > 0) {
			int parent = (index - 1) / 2;
            if (swapAscending(arr, index, parent)) {
            	index = parent;
            } else {
            	break;
            }
		}
	}
	
	private static <T extends Comparable<T>> void bubbleDown(T[] arr, int index, int size) {
        while (true) {
            int left = (index * 2) + 1;
            int right = left + 1;
            
            if (left >= size) // node has no left child
                break; // reached the bottom; heap is heapified
            
            if (right >= size) { // node has a left child, but no right child
            	swapAscending(arr, left, index);
                break; // heap is heapified
            }
            
            if (isGreaterThan(arr, left, index)) { // (left > n)
            	int smallerIndex = isGreaterThan(arr, left, right) ? left : right;
            	index = swap(arr, index, smallerIndex);
            } else if (swapAscending(arr, right, index)) { // (n > left)
            	index = right;
            } else {
            	break;
            }
        }
	}

	/**
	 * Perform heap sort on the given array.
	 * 
	 * Restrictions:
	 * 		O(n log n)
	 * 		In-place
	 */
	public static <T extends Comparable<T>> void heapSort(T[] arr) {
		// "insert" into heap (located at end of array)
	    for (int heapsize = 0; heapsize < arr.length; heapsize++) {
	    	bubbleUp(arr, heapsize);
	    }
	    
	    // at this point the array is inherently /ordered/, but not sorted
	    // so now we "remove" from the "heap"/* Removal from heap */
	    for (int heapsize = arr.length; heapsize > 0; heapsize--) {
	        swap(arr, 0, heapsize - 1);
	        bubbleDown(arr, 0, heapsize - 1);
	    }
	}
}
