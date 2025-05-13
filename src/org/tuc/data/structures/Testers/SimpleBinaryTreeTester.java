	package org.tuc.data.structures.Testers;

import org.tuc.data.structures.Tester;
import org.tuc.data.structures.MultiCounter.MultiCounter;
import org.tuc.data.structures.Trees.SimpleBinarySearchTree;
/**
 * Does the test using a Simple binary tree.
 * @author Nick PC
 *
 */
public class SimpleBinaryTreeTester implements Tester {
	
	private SimpleBinarySearchTree simpleBST = null;
	private int[] data;
	
	public SimpleBinaryTreeTester() {
		simpleBST = new SimpleBinarySearchTree(numberOfNodes);
	}
	
	public SimpleBinarySearchTree getSimpleBST() {
		return simpleBST;
	}

	public void setSimpleBST(SimpleBinarySearchTree simpleBST) {
		this.simpleBST = simpleBST;
	}
	
	/**
	 * doTest() starts the test.At first, calculating insertion average comparison number for numberOfNodes keys
	 * Then find, and findRange
	 */
	@Override
	public void doTest() {
		data = Tester.getKeys(); //storing generated keys into an array (from nodes)
		int[] searchingKeys = Tester.getRandomKeys();	//This array is used so that we can run the tests(insertion, find, findRange) with the same keys
		int total_num_of_comparisons = 0;
		
		/**insert**/
		MultiCounter.resetCounter();
		for(int index = 0; index < numberOfNodes; index++) {//for each insertion we count comparisons and we average
			simpleBST.insert(data[index]);
			total_num_of_comparisons += MultiCounter.getCount();
			MultiCounter.resetCounter();
		}
		System.out.println("Average Comparison Number Per Insertion:       "+ total_num_of_comparisons/numberOfNodes+ " comparisons");
		
		/**find**/
		MultiCounter.resetCounter();		//reseting counter is not needed here
		total_num_of_comparisons = 0;
		for(int index = 0; index < searches; index++) {//for each search we count comparisons and we average
			simpleBST.find(searchingKeys[index]);
			total_num_of_comparisons += MultiCounter.getCount();
			MultiCounter.resetCounter();
		}
		System.out.println("Average Comparison Number Per Random Search:   "+ total_num_of_comparisons/searches +" comparisons");
		
		/**findRange (+100)**/
		MultiCounter.resetCounter();
		total_num_of_comparisons = 0;
		for(int index = 0; index < searches; index++) {
			int lowBound = searchingKeys[index];		//fixed ranges are placed(+100)
			simpleBST.findRange(lowBound, lowBound + minRangeValue);
			total_num_of_comparisons += MultiCounter.getCount();
			MultiCounter.resetCounter();
		}
		System.out.println("Average Comparison Number Per Random Range Search (Range = 100):   "+ total_num_of_comparisons/searches +" comparisons");
		
		/**findRange (+1000)**/
		MultiCounter.resetCounter();
		total_num_of_comparisons = 0;
		for(int index = 0; index < searches; index++) {
			int lowBound = searchingKeys[index];
			simpleBST.findRange(lowBound, lowBound + maxRangeValue);
			total_num_of_comparisons += MultiCounter.getCount();
			MultiCounter.resetCounter();
		}
		System.out.println("Average Comparison Number Per Random Range Search (Range = 1000):  "+ total_num_of_comparisons/searches +" comparisons");
		
		return;
	}
}
