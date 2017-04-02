package assignment04;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import org.junit.*;



/**
 * @author Adrian Bollerslev, u1115021
 * 			Melvin Bosnjak, u0989241
 * 		
 * This class is used to test the AnagramUtil class and ensure that all of its 
 * functions are performing correctly
 */
public class AnagramUtilTester {
	
	//Initialize variables in constructor
	String cat = "cat";
	String catUpper = "Cat";
	String a = "fdsa";
	
	@Test
	public void sortTestSimple() {
		String expected = AnagramUtil.sort(cat);
		Assert.assertEquals("act", expected);
	}
	
	@Test
	public void sortTestEmpty() {
		String expected = AnagramUtil.sort("");
		Assert.assertEquals("", expected);
	}
	
	@Test
	public void sortTestUpper() {
		String expected = AnagramUtil.sort(catUpper);
		Assert.assertEquals("act", expected);
	}
	
	@Test
	public void sortTestOneChar() {
		String expected = AnagramUtil.sort("c");
		Assert.assertEquals("c", expected);
	}
	@Test
	public void testGenericSortString(){
		Comparator<String> compString = Collections.reverseOrder();
		String[] testStringList = {"burger","cheese","apple","salad","milk"};
		String[] expected = {"salad", "milk", "cheese", "burger", "apple" };
		AnagramUtil.insertionSort(testStringList, compString);
		Assert.assertArrayEquals(testStringList,expected);
	}
	@Test
	public void testGenericSortInt(){
		Comparator<Integer> compInteger = Collections.reverseOrder();
		Integer[] testIntList = {5,2,6,1,3,9,10,4,11,8};
		Integer[] expected = {11,10,9,8,6,5,4,3,2,1};
		AnagramUtil.insertionSort(testIntList, compInteger);
		Assert.assertArrayEquals(testIntList,expected);
	}
	@Test
	public void areAnagramsSimple() {
		String expected = AnagramUtil.sort(cat);
		String testvar = "tac";
		Assert.assertEquals(true, AnagramUtil.areAnagrams(expected, testvar));	
	}

	@Test
	public void areAnagramsFalse() {
		String expected = AnagramUtil.sort(a);
		String testvar = "tack";
		Assert.assertEquals(false, AnagramUtil.areAnagrams(expected, testvar));	
	}
	@Test
	public void areAnagramsEmpty() {
		String expected = AnagramUtil.sort("");
		String testvar = "";
		Assert.assertEquals(true, AnagramUtil.areAnagrams(expected, testvar));	
	}
	@Test
	public void areAnagramsOneChar() {
		String expected = AnagramUtil.sort("c");
		String testvar = "c";
		Assert.assertEquals(true, AnagramUtil.areAnagrams(expected, testvar));	
	}
	@Test
	public void areAnagramsUpper() {
		String expected = AnagramUtil.sort(cat);
		String testvar = AnagramUtil.sort(catUpper);
		Assert.assertEquals(true, AnagramUtil.areAnagrams(expected, testvar));	
	}
	
	@Test
	public void getLargestAnagramTestArray(){
		String[] findElements = {"hate", "leet", "Heated", "Heat", "control","map","aim"};
		String[] expected = {"hate", "Heat"};
		String[] result = AnagramUtil.getLargestAnagramGroup(findElements);
		Assert.assertArrayEquals(expected, result);
	}
	@Test
	public void getLargestAnagramTestArrayEmpty(){
		String[] findElements = {"hate", "leet", "Heated", "Heat", "control","map","aim",""};
		String[] expected = {"hate", "Heat"};
		String[] result = AnagramUtil.getLargestAnagramGroup(findElements);
		Assert.assertArrayEquals(expected, result);
	}
	@Test
	public void getLargestAnagramTestFileSimple(){
		String filen = "words/words";
		String[] expected = {"carets", "Caters", "caster", "crates", "Reacts", "recast", "traces"};
		String[] result = AnagramUtil.getLargestAnagramGroup(filen);
		Assert.assertArrayEquals(expected, result);
	}
	@Test
	public void getLargestAnagramTestFileModerate(){
		String filen = "words/moderate_word_list";
		String[] expected = {"act", "cat"};
		String[] result = AnagramUtil.getLargestAnagramGroup(filen);
		Assert.assertArrayEquals(expected, result);
	}

}


