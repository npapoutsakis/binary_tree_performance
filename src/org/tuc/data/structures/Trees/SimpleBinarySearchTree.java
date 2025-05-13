package org.tuc.data.structures.Trees;

import org.tuc.data.structures.BinarySearchTree;
import org.tuc.data.structures.MultiCounter.MultiCounter;
/**
 * Simple Binary Search Tree class implements BinarySearchTree
 * @author Nick PC
 *
 */
public class SimpleBinarySearchTree implements BinarySearchTree {
	
	private int data[][];//store our keys
	private int avail; //gives us the next available positions to place the new node
	private int root; //the root of the tree( is 0 in our case)
	
	public SimpleBinarySearchTree(int numberOfElements) {
		setup(numberOfElements);
	}//Constructor
	
/////////////////////essential methods////////////////////////
	
	public int[][] getData() {
		return data;
	}
	
	public int getRoot() {
		return root;
	}
	
	public int getAvail() {
		return avail;
	}
	
	public int getNumberOfNodes() {
		return this.data.length;
	}
	
/////////////////////////class functions////////////////////////////////
	/**
	 * Setup, fills the stack with the available positions (on right column) and all others with -1 (null) 
	 * @param size is the number of nodes we want to insert in our tree
	 */
	private void setup(int size) {
		this.data = new int[size][3];
		
		for(int row = 0; row < data.length; row++) {
			for(int col = 0; col < 2; col++) {
				data[row][col] = -1; //null
			}
			
			data[row][2] = row + 1;
			
			if(data[row][2] == size) {
				data[row][2] = -1;
			}
		}
		root = avail = 0; //setting root and avail zero at first
		return;
	}
	
	/**
	 * findRange uses findRangeHelper and it finds all keys given in a specific range
	 * @param low is lower bound
	 * @param high is the higher bound
	 */
	public void findRange(int low, int high) {
		findRangeHelper(root, low, high);
		return;
	}	
	
	/**
	 * find uses findHelp and returns 1 if find was success -1 otherwise
	 * @param key is the key we want to search for
	 */
	public int find(int key) {
		boolean checker = findHelp(root, key);
		if(checker == true) {
			return 1;
		}
		return -1;
	}
	
	/**
	 * insert uses insertHelper and just inserts a new key in to the tree
	 * @param new_key is the new key we insert
	 */
	public void insert(int new_key) {
		if(MultiCounter.increaseCounter() && getAvail() != -1) {//check if stack is empty
			root = insertHelper(root, new_key);
			MultiCounter.increaseCounter();
		}else {
			System.out.println("Tree is full, cannot insert more numbers");
			return;
		}
	}
	
	/**
	 * prints the whole tree using printTree
	 */
	public void print() {
		if(data[root][0] == -1)
			System.out.println("The BST is Empty!");
		else {
			printTree(root, 0);
			System.out.println();
		}
	}
	
	/**
	 * findHelp is a recursive function that with a given key searches to find it and then return true false otherwise
	 * @param array_pos is position that root will be placed(start of the tree)
	 * @param key	is the key we want to find
	 * @return	true if found, false if not
	 */
	private boolean findHelp(int array_pos, int key) {
		if (MultiCounter.increaseCounter() && array_pos == -1) {
			return false;
		}	
		if (MultiCounter.increaseCounter() && data[array_pos][0] > key)
			return findHelp(data[array_pos][1], key);
		else if (MultiCounter.increaseCounter() && data[array_pos][0] == key)
			return true;
		else
			return findHelp(data[array_pos][2], key);
	}
	
	
	/**
	 * insertHelper is a function that helps insert and does recursive calls so to find the right position in the tree
	 * @param array_pos is position that root will be placed(start of the tree)
	 * @param new_key is the key we want to insert
	 * @return the position of the new key in the tree
	 */
	private int insertHelper(int array_pos, int new_key) {
		
		if ((MultiCounter.increaseCounter() && array_pos == -1) || (MultiCounter.increaseCounter() && data[array_pos][0] == -1)) {
			int next_avail = avail;			//take avail position in the array
			avail = initNode(avail, new_key);//creating the new Node in the array, using avail to tell us the position
			MultiCounter.increaseCounter(2);
			return next_avail;//if new node is created then return its position
		}
		
		if ((MultiCounter.increaseCounter() && data[array_pos][0] > new_key)) {
			data[array_pos][1] = insertHelper(data[array_pos][1], new_key);
			MultiCounter.increaseCounter();
		}
		else {
			data[array_pos][2] = insertHelper(data[array_pos][2], new_key);
			MultiCounter.increaseCounter();
		}	
		return array_pos;
	}
	
	
	/**
	 * initNode creates the new Node in the array by assigning the key and left and right children(if exist)
	 * @param pos is the position(line in the array) we store the node
	 * @param key is the key that will store 
	 * @return the next available position from stack(3rd column)
	 */
	private int initNode(int pos, int key) {
		data[pos][0] = key;
		int new_avail = data[pos][2];//take next avail from 3rd column and return it
		data[pos][1] = data[pos][2] = -1;
		MultiCounter.increaseCounter(4);
		return new_avail;
	}
	
	
	/**
	 * Helps print to print the whole tree in console
	 * @param rootIndex is position that root will be placed(start of the tree)
	 * @param level is an index that tell us when we go down a level on the tree(from parent to children)
	 */
	private void printTree(int rootIndex, int level) {
		
		if (rootIndex == -1)
			return;
		
		printTree(data[rootIndex][2], level + 1);
		
		for (int i = 0; i < level; i++) // Indent based on level
			System.out.print("   ");
		
		System.out.println(data[rootIndex][0]); // Print node value
		
		printTree(data[rootIndex][1], level + 1);
		
		return;
	}
	
	
	/**
	 * findRangeHelper is a function that helps findRange and does recursive calls in order to print(or find) all keys
	 * between a specific range
	 * @param array_pos is position that root will be placed(start of the tree)
	 * @param low is the lower bound
	 * @param high is the upper bound
	 */
	private void findRangeHelper(int array_pos, int low, int high) {
		if((MultiCounter.increaseCounter() && array_pos == -1))
			return;
		
		if (MultiCounter.increaseCounter() && high < data[array_pos][0]) // all to left
			findRangeHelper(data[array_pos][1], low, high);
		
		else if (MultiCounter.increaseCounter() && low > data[array_pos][0]) // all to right
			findRangeHelper(data[array_pos][2], low, high);
		
		else {
			findRangeHelper(data[array_pos][1], low, high);
			//Found!!!
			findRangeHelper(data[array_pos][2], low, high);
		}
		return;
	}
}
