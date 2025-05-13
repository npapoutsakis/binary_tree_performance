package org.tuc.data.structures.Trees;

import org.tuc.data.structures.BinarySearchTree;
import org.tuc.data.structures.MultiCounter.MultiCounter;
/**
 * Threaded Binary Search Tree class implements BinarySearchTree
 * @author Nick PC
 *
 */
public class ThreadedBinarySearchTree implements BinarySearchTree {
	
	private int data[][];//store our keys
	private int root;	 //gives us the next available positions to place the new node
	private int avail;	 //the root of the tree( is 0 in our case)
	 
	public ThreadedBinarySearchTree(int numberOfNodes) {
		setup(numberOfNodes);
	}//Constructor

/////////////////////essential methods////////////////////////	

	public int[][] getData() {
		return data;
	}
	
	public int getNumberOfNodes() {
		return this.data.length;
	}
		
	public boolean isLeftThread(int pos) {
		return data[pos][3] == 1;
	}
	
	public boolean isRightThread(int pos) {
		return data[pos][4] == 1;
	}
	
	public int getRoot() {
		return root;
	}

	public int getAvail() {
		return avail;
	}
	
/////////////////////////class functions//////////////////////////////////
	/**
	 * Setup, fills the stack with the available positions (on right column) and all others with -1 (null) except leftThread and RightThread 
	 * @param size is the number of nodes we want to insert in our tree
	 */
	private void setup(int size) {
		this.data = new int[size][5];
		
		for(int row = 0; row < data.length; row++) {
			for(int col = 0; col < 2; col++) {
				data[row][col] = -1;
			}
			
			data[row][2] = row + 1;
			
			if(data[row][2] == size) {//last avail position set to -1 because of the length of data
				data[row][2] = -1;
			}
		}
		root = avail = 0;  //setting root and avail zero at first
		return;
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
	 * find uses findHelp and returns 1 if find was success -1 otherwise
	 * @param key is the key we want to search for
	 */
	public int find(int key) {
		boolean checker = findhelp(root, key);
		if(checker == true) {
			return 1;
		}
		return -1;
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
	 * Helps print to print the whole tree in console
	 * @param rootIndex is position that root will be placed(start of the tree)
	 * @param level is an index that tell us when we go down a level on the tree(from parent to children)
	 */
	private void printTree(int rootIndex, int level) {
		if (rootIndex == -1)
			return;
		if(data[rootIndex][4] != 1) {
			printTree(data[rootIndex][2], level + 1);
		}
		for (int i = 0; i < level; i++) // Indent based on level
			System.out.print("   ");
		
		System.out.println(data[rootIndex][0]); // Print node value
		
		if(data[rootIndex][3] != 1) {
			printTree(data[rootIndex][1], level + 1);
		}
		return;
	}
	
	
	/**
	 * initNode creates the new Node in the array by assigning the key and left and right children(if exist) and left, right threads
	 * @param pos is the position(line in the array) we store the node
	 * @param key is the key that will store 
	 * @return the next available position from stack(3rd column)
	 */
	private int initNode(int pos, int key) {
		data[pos][0] = key;
		int new_avail = data[pos][2];//take next avail from 3rd column and return it
		data[pos][1] = data[pos][2] = -1;
		data[pos][3] = data[pos][4] = 1;
		MultiCounter.increaseCounter(6);
		return new_avail;
	}
	
	
	/**
	 * insertHelper is a function that helps insert and finds the right position in the tree
	 * @param array_pos is position that root will be placed(start of the tree)
	 * @param new_key is the key we want to insert
	 * @return the position of the new key in the tree
	 */
	private int insertHelper(int array_pos, int new_key) {

		int ptr = array_pos;
		int parent = -1;
		
		while(MultiCounter.increaseCounter() && data[ptr][0] != -1) {
			
			parent = ptr;
			MultiCounter.increaseCounter();
			
			if(MultiCounter.increaseCounter() && new_key < data[ptr][0]) {
				if(MultiCounter.increaseCounter() && !isLeftThread(ptr)) {
					ptr = data[ptr][1];
					MultiCounter.increaseCounter();
				}
				else{
					break;
				}
			}
			else{
				if(MultiCounter.increaseCounter() && !isRightThread(ptr)) {
					ptr = data[ptr][2];
					MultiCounter.increaseCounter();
				}
				else{
					break;
				}
			}
		}
		
		int position = avail;
		avail = initNode(position, new_key);
		MultiCounter.increaseCounter(2);
		
		if (MultiCounter.increaseCounter() && parent == -1){
			array_pos = position;
			data[position][1] = -1;
			data[position][2] = -1;
			MultiCounter.increaseCounter(3);
	    }
	    else if (MultiCounter.increaseCounter() && new_key < data[parent][0]){
	    	data[position][1] = data[parent][1];
	    	data[position][2] = parent;
	    	data[parent][3] = 0;
	    	data[parent][1] = position;
	    	MultiCounter.increaseCounter(4);
		}
	    else{
	    	data[position][1] = parent;
	    	data[position][2] = data[parent][2];
	    	data[parent][4] = 0;
	    	data[parent][2] = position;
	    	MultiCounter.increaseCounter(4);
		}
		return array_pos;
	}

	
	/**
	 * findHelp is a function that with a given key searches to find it and then return true false otherwise
	 * @param array_pos is position that root will be placed(start of the tree)
	 * @param key	is the key we want to find
	 * @return	true if found, false if not
	 */
	private boolean findhelp(int array_pos, int key) {
		int temp_pos = array_pos;
	   
		while(true){
	        if(MultiCounter.increaseCounter() && data[temp_pos][0] == key) {
	        	return true;						// indicating that the element is found then

	        }else if(MultiCounter.increaseCounter() && data[temp_pos][0] < key && !isRightThread(temp_pos)) {
	        	temp_pos = data[temp_pos][2];		// moving to in order predecessor of the current node 
	        	MultiCounter.increaseCounter();
	        }else if(MultiCounter.increaseCounter() && !isLeftThread(temp_pos)) {
	        	temp_pos = data[temp_pos][1];		// moving to in order successor of the current node
	        	MultiCounter.increaseCounter();
	        }else {
	        	break;
	        }
	    }
	    // if element is not found then we can return false indicating element not 
	    // found in the given binary search tree
	    return false;
	}
	
	
	/**
	 * leftMost finds the leftmost node on the tree
	 * @param root is the root of the tree
	 * @returns the position of the leftmost node, -1 otherwise
	 */
	private int leftMost(int root) {
		if (MultiCounter.increaseCounter() && root == -1)
			return -1;
		while (MultiCounter.increaseCounter() && !isLeftThread(root)) {//find leftMost node
			root = data[root][1];
			MultiCounter.increaseCounter();
		}
		return root;
	}
	
	
	/**
	 * findRangeHelper is a function that helps findRange and prints(or finds) all keys
	 * between a specific range
	 * @param array_pos is position that root will be placed(start of the tree)
	 * @param low is the lower bound
	 * @param high is the upper bound
	 */
	private void findRangeHelper(int array_pos, int low, int high) {

		int current = array_pos;
		
		while(true) {
			if((MultiCounter.increaseCounter() && data[current][0] > low) && (MultiCounter.increaseCounter() && !isLeftThread(current))) {
				current = data[current][1]; //if low is lower than current node info and left of current is not a thread, then move to the left sub-tree
				MultiCounter.increaseCounter();
			}
			else if((MultiCounter.increaseCounter() && data[current][0] != low ) && (MultiCounter.increaseCounter() && !isRightThread(current))) {
				//else if current is not the low then it must be greater than low so move to the right sub-tree(if right is not a thread)
				if(MultiCounter.increaseCounter() && data[current][0] > low) { //in case we pass low(means low does not exist) and current is greater, then we break
					break; 													
				}
				current = data[current][2];
				MultiCounter.increaseCounter();
			}
			else {
				//in case where we cannot move on left or right sub tree
				//if low is still greater than current, and current is a leaf(means that has left and right thread), then the next node will be the right treaded node
				if((MultiCounter.increaseCounter() && data[current][0] < low) && (MultiCounter.increaseCounter() && isLeftThread(current)) && (MultiCounter.increaseCounter() && isRightThread(current))) {
					current = data[current][2];//points to the greater value
					MultiCounter.increaseCounter();
				}
				else if((MultiCounter.increaseCounter() && data[current][0] == low)) {//else if not a leaf, if low equals current then break
					break;
				}
				//else if we reach the biggest node of the tree(right thread is on and points to -1) move to the right so that current = -1, and break the in order loop
				else if((MultiCounter.increaseCounter() && isRightThread(current)) && (MultiCounter.increaseCounter() && !isLeftThread(current))){
					current = data[current][2];
				}
				break;
			}
		}
		//60 to 100 comparisons each search for finding low
		
		while((MultiCounter.increaseCounter() && current != -1) && (MultiCounter.increaseCounter() && data[current][0] <= high)) {
			//Found!
			//System.out.print(data[current][0] + " ");
				
			if(MultiCounter.increaseCounter() && isRightThread(current)) {
				current = data[current][2];
				MultiCounter.increaseCounter();
			}
			else {
				current = leftMost(data[current][2]);
				MultiCounter.increaseCounter();
			}
		}
		return;
	}	
	
}