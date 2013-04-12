import java.util.Arrays;
import java.util.Random;

public class Sort {
	
	private static <T extends Comparable<T>> boolean isGreaterThan(T first, T second) {
		return second.compareTo(first) < 0;
	}
	
	private static <T extends Comparable<T>> boolean isLessThan(T first, T second) {
		return second.compareTo(first) > 0;
	}
	
	private static <T extends Comparable<T>> void swap(T[] arr, int a, int b) throws ArrayIndexOutOfBoundsException {
		if (a < 0 || a > arr.length) throw new ArrayIndexOutOfBoundsException(a);
		if (b < 0 || b > arr.length) throw new ArrayIndexOutOfBoundsException(b);
		T first = arr[a];
		T second = arr[b];
		arr[b] = first;
		arr[a] = second;
	}
	
	private static <T extends Comparable<T>> boolean swapAscending(T[] arr, int a, int b) throws ArrayIndexOutOfBoundsException {
		if (a < 0 || a > arr.length) throw new ArrayIndexOutOfBoundsException(a);
		if (b < 0 || b > arr.length) throw new ArrayIndexOutOfBoundsException(b);
		T first = arr[a];
		T second = arr[b];
		if (isGreaterThan(first, second)) {
			arr[b] = first;
			arr[a] = second;
			return true;
		} else {
			return false;
		}
	}
	
	private static <T extends Comparable<T>> boolean swapAscendingWithNext(T[] arr, int a) throws ArrayIndexOutOfBoundsException {
		return swapAscending(arr, a, a + 1);
	}

	/**
	 * Implement bubble sort.
	 * 
	 * It should be:
	 *  inplace
	 *  stable
	 *  
	 * Have a worst case running time of:
	 *  O(n^2)
	 *  
	 * And a best case running time of:
	 *  O(n)
	 * 
	 * @param arr
	 */
	public static <T extends Comparable<T>> void bubblesort(T[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			boolean swapped = false;
			for (int j = 0; j < arr.length - 1; j++) {
				if (swapAscendingWithNext(arr, j)) {
					swapped = true;
				}
			}
			if (!swapped) break;
		}
	}
	
	/**
	 * Implement insertion sort.
	 * 
	 * It should be:
	 *  inplace
	 *  stable
	 *  
	 * Have a worst case running time of:
	 *  O(n^2)
	 *  
	 * And a best case running time of:
	 *  O(n)
	 * 
	 * @param arr
	 */
	public static <T extends Comparable<T>> void insertionsort(T[] arr) {
		for (int i = 1; i < arr.length; i++) {
			T value = arr[i];
			int hole = i;
			while (hole > 0) {
				if (swapAscendingWithNext(arr, hole - 1)) {
					hole--;
				} else {
					break;
				}
			}
			arr[hole] = value;
		}
	}
	
	/**
	 * Implement quick sort. 
	 * 
	 * Use the provided random object to select your pivots.
	 * For example if you need a pivot between a (inclusive)
	 * and b (exclusive) where b > a, use the following code:
	 * 
	 * int pivotIndex = r.nextInt(b - a) + a;
	 * 
	 * It should be:
	 *  inplace
	 *  
	 * Have a worst case running time of:
	 *  O(n^2)
	 *  
	 * And a best case running time of:
	 *  O(n log n)
	 * 
	 * @param arr
	 */
	public static <T extends Comparable<T>> void quicksort(T[] arr, Random r) {
		quicksortRecursive(arr, r, 0, arr.length - 1);
	}
	
	private static <T extends Comparable<T>> void quicksortRecursive(T[] arr, Random r, int min, int max) {
		if (min >= max) return;
		int pivot = randBoundInt(r, min, max);
		int divider = partition(arr, min, max, pivot);
		quicksortRecursive(arr, r, min, divider - 1);
		quicksortRecursive(arr, r, divider, max);
	}
	
	static int randBoundInt(Random r, int min, int max){ 
		return r.nextInt(max - min) + min;
	}
	
	private static <T extends Comparable<T>> int partition(T[]arr, int min, int max, int pivot) {
		T pivotValue = arr[pivot];
		while (min <= max) {
			while (isLessThan(arr[min], pivotValue)) min++;
			while (isGreaterThan(arr[max], pivotValue)) max--;
			if (min <= max) {
				swap(arr, min, max);
				min++;
				max--;
			}
		}
		return min;
	}
	
	/**
	 * Implement merge sort.
	 * 
	 * It should be:
	 *  stable
	 *  
	 * Have a worst case running time of:
	 *  O(n log n)
	 *  
	 * And a best case running time of:
	 *  O(n log n)
	 *  
	 * @param arr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Comparable<T>> T[] mergesort(T[] arr) {
		if (arr.length == 1) return arr; // recursive termination
		
		// int truncation here accounts for odd sizes
		T[] left = (T[])new Comparable<?>[arr.length / 2];
		T[] right = (T[])new Comparable<?>[arr.length - left.length];
		
		arraycopy(arr, 0, left, left.length);
		arraycopy(arr, left.length, right, right.length);
		
		// Why clone and not (T[])(new Comparable[])?
		// Because Java 7's casting FREAKING hates me
		return merge(mergesort(left), mergesort(right), arr.clone());
	}
	
	private static <T extends Comparable<T>> void arraycopy(T[] src, int srcPos, T[] dest, int length) {
		// this would be faster done by C memcopy (i.e., System.arraycopy), but
		// I don't want any mysterious imports. ;-)
		for (int i = 0; i < length; i++) {
			dest[i] = src[srcPos + i];
		}
	}
	
	private static <T extends Comparable<T>> T[] merge(T[] left, T[] right, T[] together) {
		int lIdx = 0, rIdx = 0, tIdx = 0;
		while (lIdx < left.length && rIdx < right.length) {
			if (isLessThan(left[lIdx], right[rIdx])) {
				together[tIdx] = left[lIdx];
				lIdx++;
			} else {
				together[tIdx] = right[rIdx];
				rIdx++;
			}
			tIdx++;
		}
		
		if (lIdx >= left.length) {
			for (int i = rIdx; i < right.length; i++) {
				together[tIdx] = right[i];
				tIdx++;
			}
		} else if (rIdx >= right.length) {
			for (int i = lIdx; i < left.length; i++) {
				together[tIdx] = left[i];
				tIdx++;
			}
		}
		
		
		/*if (lIdx < left.length && rIdx < right.length) {
			
		} else if (lIdx >= left.length) {
			together[tIdx] = right[rIdx];
			rIdx++;
			tIdx++;
		} else if (rIdx >= right.length) {
			together[tIdx] = left[lIdx];
			lIdx++;
		} else {
			break;
		}*/
		
		return together;
	}
	
	/**
	 * Implement radix sort
	 * 
	 * Hint: You can use Integer.toString to get a string
	 * of the digits. Don't forget to account for negative
	 * integers, they will have a '-' at the front of the
	 * string.
	 * 
	 * It should be:
	 *  stable
	 *  
	 * Have a worst case running time of:
	 *  O(kn)
	 *  
	 * And a best case running time of:
	 *  O(kn)
	 * 
	 * @param arr
	 * @return
	 */
	public static int[] radixsort(int[] arr) {
		arr = arr.clone();
		final int kNumberOfBuckets = 18;
		
		int buckets[][] = new int[kNumberOfBuckets][10];
		for (int i = 0; i < kNumberOfBuckets; i++) {
			buckets[i][0] = 1;
		}
		
		int numberOfDigits = maxNumberOfDigits(arr), divisor = 1;
		
		for (int i = 0; i < numberOfDigits; i++) {
			for (int d : arr) {
				int normalizedDigit = ((d / divisor) % 10) + 9;
				buckets[normalizedDigit] = add(d, buckets[normalizedDigit]);
			}
			
			int index = 0;
			for (int j = 0; j < kNumberOfBuckets; j++) {
				int[] bucket = buckets[j];
				
				for (int k = 1; k < bucket[0]; k++) {
					arr[index++] = bucket[k];
				}
				buckets[j][0] = 1;
			}
			
			divisor *= 10;
		}
		
		return arr;
	}
	
	private static int maxNumberOfDigits(int[] a) {
		int ret = Integer.MIN_VALUE;
		for (int i : a) {
			int length = (int)(Math.log10(i)+1);
			if (length > ret) ret = length;
		}
		return ret;
	}
	
	private static int[] add(int integer, int[] bucket) {
		int size = bucket[0];
		if (size >= bucket.length) {
			int newSize = (2 * bucket.length) + 1;
			int[] src = bucket;
			int[] dest = new int[newSize];
			
			// array xfer
			int j = 0;
			for (int i = 0; i < src.length; i++) {
				int entry = src[i];
				dest[j] = entry;
				j++;
			}
		}
		bucket[size] = integer;
		bucket[0] = ++size;
		return bucket;
	}
}
