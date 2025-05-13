package org.tuc.data.structures.SortedArray;

import java.util.Arrays;
/**
 *	SortedArray takes an array of random keys between 1 and 1.000.000
 * @author Nick PC
 *
 */
public class SortedArray {
	/**
	 * Array that will hold all random keys generated before (from Tester)
	 */
	private int[] data;
	
	public SortedArray(int[] data) {
		this.data = data;
	}
	
	public int[] getData() {
		return data;
	}
	
	public void setData(int[] keys) {
		this.data = keys;
	}
	/**
	 * SortKeys sorts the given array o integers
	 * @return
	 */
	public int[] sortKeys() {
		Arrays.sort(data);
		return data;
	}
	
}
