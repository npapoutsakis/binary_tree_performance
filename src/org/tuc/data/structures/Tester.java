package org.tuc.data.structures;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

/**
 * Interface for Tester
 * @author NP
 */
public interface Tester {
	/**
	 * Variables that contain useful info for our test
	 */
	public int numberOfNodes = 100000;
	public int max_int = 1000000;
	public int min_int = 1;
	public int searches = 100;
	public int minRangeValue = 100;
	public int maxRangeValue = 1000;
	
	/**
	 * Tester has doTest() method which will give us the results
	 */
	public void doTest();

	
	/**
	 * Tester creates random keys and will share them
	 */
	Random rand = new Random();
	int[] key = rand.ints(min_int, max_int + 1).distinct().limit(numberOfNodes).toArray();
	int randKeys[] = new int[searches];
	
	
	/**
	 * Static method returns keys we created
	 * @return key[]
	 */
	public static int[] getKeys() {
		return key;
	}
	
	
	/**
	 * @return the keys we created in order to get the same keys in all tests (find, findRange)
	 */
	public static int[] getRandomKeys() {
		for(int i = 0; i < searches; i++) {
			randKeys[i] = rand.nextInt(numberOfNodes);
		}
		return randKeys;
	}
	
	
	/**
	 * This method will return keys that will be generated in the first place
	 * @return randKeys
	 */
	public static int[] getStoredKeys() {
		return randKeys;
	}
	
	/**
	 * InsertKeysFromFile opens the file with name fileName, if it exists then we seek at 0 and read data to a byte buffer
	 * Buffer has file length. Then we readInt all integers from file and insert them on key array(test uses).
	 * If file does not exist method return void and a message "File does not exists!" appears.
	 * @param fileName is the name of the file
	 */
	public static void insertKeysFromFile(String fileName) {
		File f = new File(fileName);
		int sizeInt = 4;
		
		if(f.exists()) {
			try {
				RandomAccessFile file = new RandomAccessFile(fileName, "rw");
				
				byte[] buffer = new byte[(int)file.length()];///must be 4*100000 positions
				
				file.seek(0);
				file.read(buffer);
				
				//calculating number of nodes
				int nodes = (int)file.length()/sizeInt;
				
				ByteArrayInputStream bis = new ByteArrayInputStream(buffer);
				DataInputStream din = new DataInputStream(bis);
				
				for(int index = 0; index < nodes; index++) {
					int num = din.readInt();
					key[index] = num; //overwriting generated keys, with those from file
				}
				
				din.close();
				bis.close();
				file.close();
			}
			catch(IOException e) {
				System.out.println("File Not Found!");
				return;
			}
		}
		System.out.println("File does not exists!");
		return;
	}
	
	
}
