package org.tuc.data.structures.SortedArray;

import org.tuc.data.structures.MultiCounter.MultiCounter;

/**
 * A class implementing the binary search algorithm.
 * Keys can have values from Integer.MIN_VALUE + 1 to Integer.MAX_VALUE, so Integer.MIN_VALUE itself is not a valid key in the array.
 * Based on https://www.geeksforgeeks.org/binary-search/
 * @author sk
 *
 */
public class BinarySearch {
	private int data[];
	
	/**
	 * Constructor. Given newData must be sorted!
	 * @param newData
	 */
	public BinarySearch(int newData[]) {
		this.data = newData;
	}
	
	/**
	 * Given newData must be sorted!
	 * @param newData
	 */
	public void setData(int newData[]) {
		this.data = newData;
	}
	
	/**
	 * Searches data array for given key. Returns the key if found, otherwise Integer.MIN_VALUE
	 * @param key
	 * @return
	 */
	public int search(int key) {
		if (data == null) {
			return Integer.MIN_VALUE;
		}
		return doSearch(0, data.length - 1, key);
	}	
	
	/**
	 * Searches data array for given key. Returns the key if found, otherwise Integer.MIN_VALUE
	 * @param leftIndex
	 * @param rightIndex
	 * @param key
	 * @return key if found or Integer.MIN_VALUE otherwise
	 */
    private int doSearch(int leftIndex, int rightIndex, int key) 
    { 
        if (MultiCounter.increaseCounter() && rightIndex >= leftIndex) { 
            int mid = leftIndex + (rightIndex - leftIndex) / 2;
            MultiCounter.increaseCounter();
            // If the element is present at the 
            // middle itself 
            if (MultiCounter.increaseCounter() && data[mid] == key) 
                return mid; 
  
            // If element is smaller than mid, then 
            // it can only be present in left sub-array 
            if (MultiCounter.increaseCounter() && data[mid] > key) 
                return doSearch(leftIndex, mid - 1, key); 
  
            // Else the element can only be present 
            // in right sub-array 
            return doSearch(mid + 1, rightIndex, key); 
        } 
  
        // We reach here when element is not present in array. 
        // We return Integer.MIN_VALUE in this case, so the data array can not contain this value!
        return Integer.MIN_VALUE; 
    }	
    
    /**
     *  Search Range performs a range search in a sorted integer array with in a given range. Returns Integer.MIN_VALUE if data is empty
     * @param low is lowest bound of range
     * @param high is high bound of range
     * @return
     */
    public int searchRange(int low, int high) {
    	if (data == null) {
			return Integer.MIN_VALUE;
		}
    	return doSearchRange(0, data.length - 1, low, high);
    }
    
    /**
     * doSearchRange is a helper method for searchRange.Due to search testing, first finds lower bound and then keeps moving
     * ahead until it finds high.	
     * @param left
     * @param right
     * @param low
     * @param high
     * @return
     */
    private int doSearchRange(int left, int right, int low, int high) {
    	
    	int position = 0; //pos will tell us where is the low bound
    	MultiCounter.increaseCounter();
    	
    	if(MultiCounter.increaseCounter() && low <= high) { //while we have a normal range perform binary search to find low
    		
    		while(MultiCounter.increaseCounter() && left <= right) {
    			
    			int mid = left + (right - left) / 2;
    			MultiCounter.increaseCounter();
    			
    			// Check if low is present at mid
    			if(MultiCounter.increaseCounter() && data[mid] == low) {
    				position = mid;
    				MultiCounter.increaseCounter();
    				break;
    			}
    			// If low greater, ignore left half
    			if(MultiCounter.increaseCounter() && data[mid] < low) {
    				left = mid + 1;
    				MultiCounter.increaseCounter();
    				if((MultiCounter.increaseCounter() && left != -1) && (MultiCounter.increaseCounter() && data[left] > low)) {
    					//in case low does not exist we check if data[left] > low. 
    					position = left; //then the left of it must be the node closest to low bound!
    					MultiCounter.increaseCounter();
    					break;
    				}
    			}
    			else { // If low is smaller, ignore right half
    				right = mid - 1;
    				MultiCounter.increaseCounter();
    				if((MultiCounter.increaseCounter() && right != -1) && (MultiCounter.increaseCounter() && data[right] < low)) {
    					position = mid;//if data[right] < low means that the current mid pointer is to the first key after low
    					MultiCounter.increaseCounter();
    					break;
    				}
    			}
    		}
    		//after position of low is found then we move our position until we find high(meanwhile we can print or adjust every key)
    		while((MultiCounter.increaseCounter() && position != -1) && (MultiCounter.increaseCounter() && data[position] <= high)) {
    			//FOUND!
    			//System.out.println(data[position]);
    			position = position + 1;
    			MultiCounter.increaseCounter();
    		}
    		
    	}else {
    		System.out.println("Index out of Bounds!");
    		return Integer.MIN_VALUE;
    	}
    	return 0;
    }
    
}
