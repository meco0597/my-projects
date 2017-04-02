package assignment04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;

import java.util.Random;

/**
 * @author Adrian Bollerslev, u1115021
 * 			Melvin Bosnjak, u0989241
 *
 *This class is used to sort a line separated word list 
 *and determine how many Anagrams are in it, as well as other utilities
 */
public class AnagramUtil {
	
	/**
	 * This method sorts the input string using an insertion sort 
	 * 
	 * @param str The input string
	 * @return A sorted version of the input
	 */
	public static String sort(String str){
		return new String(insrt(str.trim().toLowerCase().toCharArray()));
	}		
	

	/**
	 * This method uses an insertion sort to return a char array in sorted order as a String
	 * 
	 * @param strarray a char array generated from the method above (sort)
	 * @return A sorted String version of the input char array
	 */
	private static String insrt(char[] strarray) {
		for (int idx = strarray.length - 1; idx >= 1; idx--) {
            if (strarray[idx] < strarray[idx - 1]) {
            	//swap them
            	char curr = strarray[idx];
                strarray[idx] = strarray[idx-1];
                strarray[idx-1] = curr;
            }
        }
		
		//scan through the array once more
        for (int indx = 2; indx < strarray.length; indx++) {
            char curr = strarray[indx];
            int spot = indx;
            //if out of place keep adjusting the position
            while (curr < strarray[spot - 1]) {
                strarray[spot] = strarray[spot - 1];
                spot--;
            }
            strarray[spot] = curr;
        }
        return new String(strarray);
	}
	
	/**
	 * This method sorts a generic type input array using an insertion sort and a comparator
	 * @param unsorted an unsorted generic array
	 * @param comp a comparator to sort the elements
	 */
	public static <T> void insertionSort(T[] unsorted, Comparator<? super T> comp){
 
		//if the array or comp is is null, throw an exception
		if (unsorted == null) {
			throw new IllegalArgumentException("The Array is null");
		}
		if (comp == null) {
			throw new IllegalArgumentException("The Comparator is null");
		}
		
		T currinsert; 
		int currindx = 0;
		for (int indx = 1; indx < unsorted.length; indx++){
			currinsert= unsorted[indx];
			currindx = indx;
			
			//if the current index is out of position keep adjusting it to the right position
			while (currindx > 0 && comp.compare(unsorted[currindx - 1], currinsert) > 0 ) {
				unsorted[currindx] = unsorted[currindx - 1];
				currindx--;
			}
			unsorted[currindx] = currinsert;}
	}
	/**
	 * @param item1 String to be compared
	 * @param item2 Other String to be compared
	 * @return true if the strings are Anagrams, false otherwise
	 */
	public static boolean areAnagrams(String item1, String item2){
		return sort(item1).equals(sort(item2));
	}
	
	/**
	 * This method returns the largest group of anagrams
	 * found throughout the input array of words
	 * 
	 * @param input The String array containing all the worlds
	 * @return the most common anagram and the words which are in it
	 * if the input array does not contain anagrams 
	 * 	or is empty an empty array is returned
	 */
	public static String[] getLargestAnagramGroup(String[] input){
		String[] inputarr = input.clone();
		//the current anagram and the largest anagram
		ArrayList<String> curr = new ArrayList<>();
		ArrayList<String> maxgroup = new ArrayList<>();

		for(String currword : inputarr){
			curr.clear();
			
			for(String checkword : inputarr){
				if(areAnagrams(currword, checkword)){
					if(!curr.contains(checkword)){
						curr.add(checkword);
					}
				}
			}
			if(curr.size() > maxgroup.size()){
				//delete all the elements and set the new max
				maxgroup.clear();
				maxgroup.addAll(curr);
			}
		}
		//if no anagram match was found return a null String
		if(maxgroup.size() <= 1){
			return new String[0];
		}
		//return the largest group as a String Array
		return maxgroup.toArray(new String[maxgroup.size()]);

	}

	/**
	 * Reads a file in the specified location 
	 * the words are then output as an Array of words
	 * (It is assumed that the file contains one word per line)
	 * 
	 * @param filename The location and name of the wordlist
	 * @return an array of  words
	 */
	public static String[] readFile(String filename) {
		//the word list as an array
		ArrayList<String> wordarr = new ArrayList<String>();
		try(BufferedReader input = new BufferedReader(new FileReader(filename))) {
			while(input.ready()) {
				//each word is on a line
				wordarr.add(input.readLine());
			}
			input.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		//return the new String array
		return wordarr.toArray(new String[wordarr.size()]);
	}	
	/**
	 * Similar too the previous method, but reads the list 
	 * of words from a one word per line file instead of an array 
	 * 
	 * @param filename The location and name of the word list
	 * @return the most common anagram and the words which are in it
	 * If the file does not exist or is empty an empty array is returned
	 */
	public static String[] getLargestAnagramGroup(String filename){
		return getLargestAnagramGroup(readFile(filename));
	}

	/**
	 * This method generates a random String. It is useful for testing and timing 
	 * this class (AnagramUtil) externally
	 * 
	 * @param length The length of the String to be generated
	 * @return the String which is generated of random lowercase characters
	 */
	public static String randLowCaseString(int length)
	{
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i < length; i++)
		{		
			Random r = new Random();
			//this will only append lowercase letters "a"-"z"
			int randch = (byte)'a' + (r.nextInt(26));
			stringBuilder.append((char)randch); 
		}
		return stringBuilder.toString();
	}
}


