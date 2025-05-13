package org.tuc.data.structures.MainConsole;

import org.tuc.data.structures.Testers.SimpleBinaryTreeTester;
import org.tuc.data.structures.Testers.SortedArrayTester;
import org.tuc.data.structures.Testers.ThreadedBinaryTreeTester;
/**
 * Main Class, its just the console that we use to start the test
 * @author Nick PC
 *
 */
public class Main {
	public static void main(String[] args) {

//		To insert keys from file just set fileName parameter and run application
//		String fileName = " ";
//		Tester.insertKeysFromFile(fileName);
		
		System.out.println("*****************************************************");
		System.out.println("---------------Simple Binary Search Tree-------------\n");
		SimpleBinaryTreeTester simpleTree = new SimpleBinaryTreeTester();
		simpleTree.doTest();
		System.out.println("*****************************************************\n");
		
		
		System.out.println("*****************************************************");
		System.out.println("---------------Threaded Binary Search Tree-----------\n");
		ThreadedBinaryTreeTester threadedTree = new ThreadedBinaryTreeTester();
		threadedTree.doTest();
		System.out.println("*****************************************************\n");
		
		
		System.out.println("*****************************************************");
		System.out.println("--------------Sorted Array & Binary Search-----------\n");
		SortedArrayTester array = new SortedArrayTester();
		array.doTest();
		System.out.println("*****************************************************\n");

		return;
	}
}
