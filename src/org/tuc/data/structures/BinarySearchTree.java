package org.tuc.data.structures;
/**
 * Binary Search Tree Interface
 * @author Nick PC
 *
 */
public interface BinarySearchTree {
		
	public void insert(int key); //insert a new key into tree
	
	public int find(int key);   //find a key
	
	public void print();		//print the whole tree 
	
	public void findRange(int low, int high); // find(or print) keys given between a range
	
}
