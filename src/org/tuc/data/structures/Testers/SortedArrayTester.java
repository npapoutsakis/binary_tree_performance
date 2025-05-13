package org.tuc.data.structures.Testers;

import org.tuc.data.structures.Tester;
import org.tuc.data.structures.MultiCounter.MultiCounter;
import org.tuc.data.structures.SortedArray.BinarySearch;
import org.tuc.data.structures.SortedArray.SortedArray;
/**
 * In this class we perform the binary searches on a sorted array.
 * @author Nick PC
 *
 */
public class SortedArrayTester implements Tester{

	private int[] data;
	private BinarySearch bsearch = null;
	private SortedArray sortArray = null;
	
	public SortedArrayTester() {
		data = Tester.getKeys();
		sortArray = new SortedArray(data);
		bsearch = new BinarySearch(sortArray.sortKeys());
	}
	/**
	 * We take the already generated keys(so to have a valid result) and 
	 * then using BinarySearch methods we calculate the results
	 */
	@Override
	public void doTest() {
		int total_num_of_comparisons = 0;
		int[] searchingKeys = Tester.getStoredKeys();
		
		/**find**/
		MultiCounter.resetCounter();
		for(int index = 0; index < searches; index++) {
			bsearch.search(searchingKeys[index]);
			total_num_of_comparisons += MultiCounter.getCount();
			MultiCounter.resetCounter();
		}
		System.out.println("Average Comparison Number Per Random Search:   "+ total_num_of_comparisons/searches + " comparisons");
		
		
		/**findRange (+100)**/
		MultiCounter.resetCounter();
		total_num_of_comparisons = 0;
		for(int index = 0; index < searches; index++) {
			int lowBound = searchingKeys[index];
			bsearch.searchRange(lowBound, lowBound + minRangeValue);
			total_num_of_comparisons += MultiCounter.getCount();
			MultiCounter.resetCounter();
		}
		System.out.println("Average Comparison Number Per Random Range Search (Range = 100):   " + total_num_of_comparisons/searches + " comparisons");
		
		
		/**findRange (+1000)**/
		MultiCounter.resetCounter();
		total_num_of_comparisons = 0;
		for(int index = 0; index < searches; index++) {
			int lowBound = searchingKeys[index];
			bsearch.searchRange(lowBound, lowBound + maxRangeValue);
			total_num_of_comparisons += MultiCounter.getCount();
			MultiCounter.resetCounter();
		}
		System.out.println("Average Comparison Number Per Random Range Search (Range = 1000):  " + total_num_of_comparisons/searches + " comparisons");
		
		return;
	}
}
