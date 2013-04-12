/** 
 * You are not allowed to use any built-in methods of java.util.Arrays.
 * 
 * With the exception of the update method and increment, you are not allowed 
 * to convert, modify, copy, or otherwise alter the array within
 * these methods.  
 * 
 * You are expected to work with the array directly.
 * Do not use ArrayLists or any other container class, rather work
 * with the array directly.
 * 
 * You may assume in every case that the array passed in is instantiated
 * and contains at least one element.
 * 
 * @author Bill Panfel
 * @author Sundeep Ghuman
 * @version 2.0
 */
public interface ArrayServicesInterface {

    /** 
     * Calculates and returns the double valued average of the 
     * values within the array.
     * @param array The array of int values being averaged
     * @return The double valued average of the array's values
     */
    public double average(int[] array);
    
    
    /**
     * Calculates and returns the standard deviation of the array.
     * @param array The array of ints to calculate
     * @return The double valued standard deviation of the array
     */
    public double standardDeviation(int[] array);
    
    /** 
     * The method is to perform a linear search through the array
     * returning the index of the value sought or -1 otherwise.
     * Return the index of the specified value.  Note:
     * In the case the value occurs more than once, this method
     * is to return the index of the first occurrence.
     * Return -1 if the value is not found.
     * @param array The array to be searched
     * @param value The value sought
     * @return The index of the first occurrence of the value or -1 if not found
     */
    public int findIndexOf(int[] array, int value);
    
    /** 
     * The method is determine the minimum value in the array and return
     * its index.  In the case the minimum value occurs more than once, 
     * this method returns the index of the first occurrence.
     * @param array The array to be searched
     * @return The index of the first occurrence of the minimum value
     */
    public int indexOfMin(int[] array);

    /** 
     * The method is determine the maximum value in the array and return
     * its index.  In the case the maximum value occurs more than once, 
     * this method returns the index of the first occurrence.
     * @param array The array to be searched
     * @return The index of the first occurrence of the maximum value
     */
    public int indexOfMax(int[] array);

    /**
     * This method returns true if the value given is found 
     * within the array, false otherwise.
     * @param array The array to be searched.
     * @param value The value sought
     * @return If the value was found
     */
    public boolean contains(int[] array, int value);

    /**
     * This method returns the number of times the value is found in 
     * the array.
     * @param array The array to be searched.
     * @param value The value sought
     * @return Number of times the value was found
     */
    public int count(int[] array, int value);
    
    /**
     * This method increments each item of the array by a given value.
     * For example, if you were incrementing the element of value 1 with
     * a inc value of 2, the element would now be equal to 3
     * @param array The array to be incremented
     * @param inc The amount to increment each element
     */
    public void increment(int[] array, int inc);

    /**
     * This method updates the array by increasing each value
     * in the array by the specified percentage.  (Increasing a 
     * value of 5 by .2 will yield 6.  
     * It does not literally add .2, but 20%.)
     * Be sure to cast each new result to an int.  Do not round.  
     * Do not build a new array, literally update the array given 
     * as a parameter.
     * @param array The array to be updated
     * @param factor The percentage increase to apply expressed in 
     * decimal form.  i.e. to achieve a 20% increase, pass in 0.2.  
     * For a 5% increase, use 0.05.
     */
    public void update(int[] array, double factor);

    /**
     * A generic int array 'printing' method. Note that you do not actually
     * write to standard out or any other form of a print command. You
     * should only be creating and returning a String.
     * 
     * @param array The array to be converted to a String
     *
     * @return the array as a string representation
     */

    public String arrayToString(int[] array);

}
