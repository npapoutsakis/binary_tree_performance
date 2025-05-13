package org.tuc.data.structures.Testers;

import org.tuc.data.structures.Tester;
import org.tuc.data.structures.MultiCounter.MultiCounter;
import org.tuc.data.structures.Trees.ThreadedBinarySearchTree;
/**
 * Does the test using a Threaded binary tree.
 * @author Nick PC
 *
 */
public class ThreadedBinaryTreeTester implements Tester{
	
	private ThreadedBinarySearchTree threadedBST = null;
	private int[] data;
	
	public ThreadedBinaryTreeTester() {
		threadedBST = new ThreadedBinarySearchTree(numberOfNodes);
	}
	
	public ThreadedBinarySearchTree getSimpleBST() {
		return threadedBST;
	}
	public void setThreadedBST(ThreadedBinarySearchTree threadedBST) {
		this.threadedBST = threadedBST;
	}
	
	/**
	 * doTest() starts the test.At first, calculating insertion average comparison number for numberOfNodes keys
	 * Then find, and findRange
	 */
	@Override
	public void doTest() {
		data = Tester.getKeys(); //we take the already existing keys(nodes)
		int[] searchingKeys = Tester.getStoredKeys(); //This array is used so that we can run the tests(insertion, find, findRange) with the same keys
		int total_num_of_comparisons = 0;
		
		/**insert**/
		MultiCounter.resetCounter();
		for(int index = 0; index < numberOfNodes; index++) {//for each insertion we count comparisons and we average
			threadedBST.insert(data[index]);
			total_num_of_comparisons += MultiCounter.getCount();
			MultiCounter.resetCounter();
		}
		System.out.println("Average Comparison Number Per Insertion:       "+ total_num_of_comparisons/numberOfNodes+ " comparisons");
		
		/**find**/
		MultiCounter.resetCounter();
		total_num_of_comparisons = 0;
		for(int index = 0; index < searches; index++) {
			threadedBST.find(searchingKeys[index]);
			total_num_of_comparisons += MultiCounter.getCount();
			MultiCounter.resetCounter();
		}
		System.out.println("Average Comparison Number Per Random Search:   "+ total_num_of_comparisons/searches +" comparisons");
		
		/**findRange (+100)**/
		MultiCounter.resetCounter();
		total_num_of_comparisons = 0;
		for(int index = 0; index < searches; index++) {
			int lowBound = searchingKeys[index];
			threadedBST.findRange(lowBound, lowBound + minRangeValue);
			total_num_of_comparisons += MultiCounter.getCount();
			MultiCounter.resetCounter();
		}
		System.out.println("Average Comparison Number Per Random Range Search (Range = 100):   "+ total_num_of_comparisons/searches +" comparisons");
		
		/**findRange (+1000)**/
		MultiCounter.resetCounter();
		total_num_of_comparisons = 0;
		for(int index = 0; index < searches; index++) {
			int lowBound = searchingKeys[index];
			threadedBST.findRange(lowBound, lowBound + maxRangeValue);
			total_num_of_comparisons += MultiCounter.getCount();
			MultiCounter.resetCounter();
		}
		System.out.println("Average Comparison Number Per Random Range Search (Range = 1000):  "+ total_num_of_comparisons/searches +" comparisons");
		
		return;
	}
}
