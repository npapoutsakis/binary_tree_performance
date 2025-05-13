package org.tuc.data.structures.MultiCounter;

/**
 * A class with static member variables and methods that can be used to count multiple stuff. 
 * safe.
 * 
 * @author sk
 *
 */
public class MultiCounter {

	/**
	 * variable holding our counter;
	 */
	private static int counter;


	/**
	 * Resets the internal counter to zero
	 */
	public static void resetCounter() {
		counter = 0;
	}

	/**
	 * returns the current count
	 * @return the current count
	 */
	public static int getCount() {
		return counter;
	}

	/**
	 * Increases the current count of counterIndex by 1. Returns always true so that it can be used
	 * in boolean statements
	 * 
	 * @return always true
	 */
	public static boolean increaseCounter() {
		counter = counter + 1;
		return true;
	}
	
	/**
	 * Increases the current count of counter given by counterIndex by step. Returns always true so that it can be
	 * used in boolean statements. Step could be negative. It is up to the specific
	 * usage scenario whether this is desirable or not.
	 * 
	 * @param step The amount to increase the counter
	 * @return always true
	 */
	public static boolean increaseCounter(int step) {
			counter = counter + step;
		return true;
	}
	
}
