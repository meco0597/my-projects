package assignment04;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import javax.swing.JOptionPane;

/**
 * @author Adrian Bollerslev, u1115021 Melvin Bosnjak, u0989241
 *
 *         This class is used to implement and time all of the methods in the
 *         AnagramUtil class
 */

public class Main {

	private static final int Iterations = 100;
	private static String filename;

	public static void main(String args[]) {

//		// opens up a JOption pane to decide which size of word list desired
//		Object[] possibleWordSizes = { "Small", "Moderate", "Large" };
//		Object selectedWordSize = JOptionPane.showInputDialog(null, "Select a word list size", "Input",
//				JOptionPane.INFORMATION_MESSAGE, null, possibleWordSizes, possibleWordSizes[0]);
//
//		// determines which option was selected
//		if (selectedWordSize == "Small") {
//			filename = "Resources/words";
//		} else if (selectedWordSize == "Moderate") {
//			filename = "Resources/moderate_word_list";
//		} else {
//			filename = "Resources/words_english";
//		}
//
//		String[] largestAnagramGroup = AnagramUtil.getLargestAnagramGroup(filename);
//		System.out.println("the largest group of anagrams in this word list is: ");
//		for (int index = 0; index < largestAnagramGroup.length; index++) {
//			System.out.println(largestAnagramGroup[index]);
//		}

		timeAreAnagrams();
//		timeGetLargestAnagramGroup();
//		timeInsertionSort();
	}

	/**
	 * Times the areAnagrams method in AnagramUtil
	 */
	public static void timeAreAnagrams() {

		System.out.println("areAnagrams timing:");
		try (FileWriter fileWriter = new FileWriter(new File("contains_experiment.tsv"))) {
			// This is used as the exponent to calculate the size of the set
			for (int exp = 1; exp <= 10000; exp += 500) {

				// the expiriment gets run several times then averages the
				// result
				long elapsedTime = 0;

				for (int iter = 0; iter < Iterations; iter++) {
					String component = AnagramUtil.randLowCaseString(exp);
					String component2 = AnagramUtil.randLowCaseString(exp);

					long start = System.nanoTime();
					AnagramUtil.areAnagrams(component, component2);
					long stop = System.nanoTime();
					elapsedTime += stop - start;
				}

				double averageTime = elapsedTime / (double) Iterations;
				System.out.println(exp + "\t" + averageTime / 1000000);
				fileWriter.write(exp + "\t" + averageTime + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Times the getLargestAnagram method in AnagramUtil
	 * 
	 * @throws IOException
	 *             if file not found or file does not exist
	 */
	public static void timeGetLargestAnagramGroup() {
		// Change numWords to change how big the array of words should be
		int numWords = 100;
		System.out.println("getLargestAnagramGroup timing:");
		Random randomStringSize = new Random();
		String[] testList = new String[numWords];

		// makes an array of Strings of numWords long
		for (int index = 0; index < numWords; index++) {
			testList[index] = AnagramUtil.randLowCaseString(randomStringSize.nextInt(10) + 5);
		}

		long elapsedTime = 0;
		double averageTime = 0;
		for (int exp = 1; exp <= 10000; exp += 500) {

			// the expiriment gets run several times then averages the result
			long start = System.nanoTime();
			AnagramUtil.getLargestAnagramGroup(testList);
			long stop = System.nanoTime();
			elapsedTime += stop - start;

			averageTime = elapsedTime / (double) Iterations;
		}
		System.out.println("Number of Components: " + numWords + " Time: " + averageTime / 1000000);
	}

	/**
	 * Times the insertionSort method in AnagramUtil
	 * 
	 */
	public static <T> void timeInsertionSort() {
		// Change numWords to change how big the array of words should be
		int numComponents = 10000;
		Random randomSize = new Random();
		Comparator<String> compString = Collections.reverseOrder();
		Comparator<Integer> compInteger = Collections.reverseOrder();
		String[] testStringList = new String[numComponents];
		Integer[] testIntList = new Integer[numComponents];

		// makes an array of Strings of numWords long
		for (int index = 0; index < numComponents; index++) {
			testStringList[index] = AnagramUtil.randLowCaseString(randomSize.nextInt(10) + 5);
			testIntList[index] = randomSize.nextInt(9);
		}

		// times the insertion sort for Strings
		long elapsedStringTime = 0;
		double averageStringTime = 0;
		for (int exp = 1; exp <= 10000; exp += 500) {

			// the expiriment gets run several times then averages the result
			long start = System.nanoTime();
			AnagramUtil.insertionSort(testStringList, compString);
			long stop = System.nanoTime();
			elapsedStringTime += stop - start;

			averageStringTime = elapsedStringTime / (double) Iterations;
		}
		System.out.println("Insertion sort timing for Strings: ");
		System.out.println("Number of Components: " + numComponents + " Time: " + averageStringTime / 1000000);

		// times the insertion sort for ints
		long elapsedIntTime = 0;
		double averageIntTime = 0;
		for (int exp = 1; exp <= 10000; exp += 500) {

			// the expiriment gets run several times then averages the result
			long start = System.nanoTime();
			AnagramUtil.insertionSort(testIntList, compInteger);
			long stop = System.nanoTime();
			elapsedIntTime += stop - start;

			averageIntTime = elapsedIntTime / (double) Iterations;
		}
		System.out.println("Insertion sort timing for Integers: ");
		System.out.println("Number of Components: " + numComponents + " Time: " + averageIntTime / 1000000);
	}
}
