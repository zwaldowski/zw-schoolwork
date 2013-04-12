//
// Homework 6, Problem 1
// Zachary Waldowski
// CS 1331
//

/**
 * This class is a generic implementation that conforms to ArrayServicesInterface. (HW6, Problem 1)
 *
 * @author Zachary Waldowski
 * @version 1.0
 */
public class ArrayServices implements ArrayServicesInterface {
	/** 
	 * Calculates and returns the average of the values within the array.
	 * @param array An array of integer values to calculate based on
	 * @return A double-precision average of the given array's values
	 */
	public double average(int[] array) {
		double count = array.length, total = 0.0;
		for (int item : array) {
			total += item;
		}
		return total / count;
	}
	
	/**
	 * Calculates and returns the standard deviation of the values within the array.
	 * @param array An array of integer values to calculate based on
	 * @return A double-precision standard deviation of the given array's values
	 */
	public double standardDeviation(int[] array) {
		double count = array.length, total = 0.0, average = average(array);
		for (int item : array) {
			total += Math.pow(2, item - average);
		}
		return Math.sqrt(total / count);
	}
	
	/** 
	 * Performs a linear search of the given integer array for a given value.
	 * @param array An array of integer values to search
	 * @param value An integer to search for
	 * @return The index of the first occurrence of the value, or -1 if not found
	 */
	public int findIndexOf(int[] array, int value) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == value)
				return i;
		}
		return -1;
	}
	
	/** 
	 * Performs a linear search of a given integer array for its smallest value.
	 * @param array An array of integer values to search
	 * @return The index of the first occurrence of the minimum value
	 */
	public int indexOfMin(int[] array) {
		int index = 0, currentLeast = array[0];
		for (int i = 0; i < array.length; i++) {
			if (array[i] < currentLeast) {
				index = i;
				currentLeast = array[i];
			}
		}
		return index;
	}

	/** 
	 * Performs a linear search of a given integer array for its largest value.
	 * @param array An array of integer values to search
	 * @return The index of the first occurrence of the maximum value
	 */
	public int indexOfMax(int[] array) {
		int index = 0, currentMost = array[0];
		for (int i = 0; i < array.length; i++) {
			if (array[i] > currentMost) {
				index = i;
				currentMost = array[i];
			}
		}
		return index;
	}

	/**
	 * Performs a linear search of a given integer array to see if it contains a given value.
	 * @param array An array of integer values to search
	 * @param value An integer to search for
	 * @return true if the value was found, false otherwise
	 */
	public boolean contains(int[] array, int value) {
		for (int item : array) {
			if (item == value)
				return true;
		}
		return false;
	}

	/**
	 * Performs a linear search of a given integer array for the number of times a given value is contained.
	 * @param array An array of integer values to search
	 * @param value An integer to search for
	 * @return Number of times the value was found
	 */
	public int count(int[] array, int value) {
		int count = 0;
		for (int item : array) {
			if (item == value)
				count++;
		}
		return count;
	}
	
	/**
	 * Increments or decrements each item of the given array by a given value.
	 * @param array An array of integer values to perform the transformation on
	 * @param inc An integer value by which to increment each element.
	 */
	public void increment(int[] array, int inc) {
		for (int i = 0; i < array.length; i++) {
			array[i] += inc;
		}
	}

	/**
	 * Increments or decrements each integer value of the given array by a scalar factor.
	 * Increasing a value of 5 by factor of .2 will yield 6.
	 * @param array An array of integer values to perform the transformation on
	 * @param factor A double-precision scalar increase for each array value
	 */
	public void update(int[] array, double factor) {
		for (int i = 0; i < array.length; i++) {
			array[i] = (int)(array[i] + (factor * array[i]));
		}
	}

	/**
	 * Returns a comma-separated string representation of the given array.
	 * @param array An array of integer values to be concatenated
	 * @return A string representation of the given array
	 */
	public String arrayToString(int[] array) {
		StringBuilder build = new StringBuilder("[");
		
		for (int i = 0; i < array.length; i++) {
			build.append(array[i]);

			if (i < array.length - 1)
				build.append(", ");
		}

		build.append("]");

		return build.toString();
	}

	/**
	 * A unit test for this implementation of array services.
	 */
	public static void main(String[] args) {
		ArrayServicesInterface as = new ArrayServices();

		// Provided unit tests
		int[] array = {1,1,2,2,2,4,6,-1,10,5};
		System.out.println("Testing average:");
	 	System.out.println("Expected value: 3.2");
	 	System.out.println("Computed value: " + as.average(array));
	 	System.out.println();
		System.out.println("Testing indexOfMax:");
		System.out.println("Expected value: 8");
		System.out.println("Computed value: " + as.indexOfMax(array));
		System.out.println();
		System.out.println("Testing indexOfMin:");
		System.out.println("Expected value: 7");
		System.out.println("Computed value: " + as.indexOfMin(array));
		System.out.println();
		System.out.println("Testing contains:");
		System.out.println("Expected value: true");
		System.out.println("Computed value: " + as.contains(array, 4));
		System.out.println();
		System.out.println("Testing update:");
		System.out.println("Expected value: [1, 1, 2, 2, 2, 4, 6, -1, 10, 5]");
		System.out.println("Computed value: " + as.arrayToString(array));
		System.out.println();

		// My unit tests
		int[] myArray = {7, -17, -15, -19, 5, 10, -6, -10, -3, 20, -17, 14, 2, 6, 18};

		System.out.println("Testing standardDeviation:");
		double expected1 = 334.02832860113523;
		double result1 = as.standardDeviation(myArray);
		System.out.println("Expected value: " + expected1);
		System.out.println("Computed value: " + result1);
		if (expected1 == result1)
			System.out.println("Pass.");
		else
			System.out.println("Fail.");
		System.out.println();

		System.out.println("Testing findIndexOf:");
		int expected2 = 1;
		int result2 = as.findIndexOf(myArray, -17);
		System.out.println("Expected value: " + expected2);
		System.out.println("Computed value: " + result2);
		if (expected2 == result2)
			System.out.println("Pass.");
		else
			System.out.println("Fail.");
		System.out.println();

		System.out.println("Testing indexOfMin:");
		int expected3 = 3;
		int result3 = as.indexOfMin(myArray);
		System.out.println("Expected value: " + expected3);
		System.out.println("Computed value: " + result3);
		if (expected3 == result3)
			System.out.println("Pass.");
		else
			System.out.println("Fail.");
		System.out.println();

		System.out.println("Testing indexOfMax:");
		int expected4 = 9;
		int result4 = as.indexOfMax(myArray);
		System.out.println("Expected value: " + expected4);
		System.out.println("Computed value: " + result4);
		if (expected4 == result4)
			System.out.println("Pass.");
		else
			System.out.println("Fail.");
		System.out.println();

		System.out.println("Testing contains:");
		boolean expected5 = false;
		boolean result5 = as.contains(myArray, -1);
		System.out.println("Expected value: " + expected5);
		System.out.println("Computed value: " + result5);
		if (expected5 == result5)
			System.out.println("Pass.");
		else
			System.out.println("Fail.");
		System.out.println();

		System.out.println("Testing count:");
		int expected6 = 2;
		int result6 = as.count(myArray, -17);
		System.out.println("Expected value: " + expected6);
		System.out.println("Computed value: " + result6);
		if (expected6 == result6)
			System.out.println("Pass.");
		else
			System.out.println("Fail.");
		System.out.println();

		System.out.println("Testing increment:");
		int[] result7 = myArray;
		as.increment(result7, 1);
		int[] expected7 = {8, -16, -14, -18, 6, 11, -5, -9, -2, 21, -16, 15, 3, 7, 19};
		System.out.println("Expected value: " + as.arrayToString(expected7));
		System.out.println("Computed value: " + as.arrayToString(result7));
		if (as.arrayToString(expected7).equals(as.arrayToString(result7)))
			System.out.println("Pass.");
		else
			System.out.println("Fail.");
		System.out.println();

		System.out.println("Testing update:");
		int[] result8 = myArray;
		as.update(result8, 0.42);
		int[] expected8 = {11, -22, -19, -25, 8, 15, -7, -12, -2, 29, -22, 21, 4, 9, 26};
		System.out.println("Expected value: " + as.arrayToString(expected8));
		System.out.println("Computed value: " + as.arrayToString(result8));
		if (as.arrayToString(expected8).equals(as.arrayToString(result8)))
			System.out.println("Pass.");
		else
			System.out.println("Fail.");
		System.out.println();

		System.out.println("Testing arrayToString:");
		String result9 = as.arrayToString(myArray);
		String expected9 = "[11, -22, -19, -25, 8, 15, -7, -12, -2, 29, -22, 21, 4, 9, 26]";
		System.out.println("Expected value: " + expected9);
		System.out.println("Computed value: " + result9);
		if (expected9.equals(result9))
			System.out.println("Pass.");
		else
			System.out.println("Fail.");
		System.out.println();
	}
}