package sort_evaluations;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class Sort_Methods_Test {

	@Test
	public void test_Quick_Sort_Naive() {
		ArrayList<Integer> test = Sort_Utils.generate_random_array(10, 9);
		Quick_Sort_Naive<Integer> testSort = new Quick_Sort_Naive<Integer>();
		testSort.sort(test);
		System.out.println(test.toString());
		assertTrue(Sort_Utils.is_sorted(test));
	}

	@Test
	public void test_Insertion_Sort() {
		ArrayList<Integer> test = Sort_Utils.generate_random_array(10, 9);
		Insertion_Sort<Integer> testSort = new Insertion_Sort<Integer>();
		testSort.sort(test);
		System.out.println(test.toString());
		assertTrue(Sort_Utils.is_sorted(test));
	}
	
	@Test
	public void test_Quick_Sort_Random_Pivot()
	{
		ArrayList<Integer> test = Sort_Utils.generate_random_array(10, 9);
		Quick_Sort_Inplace_Random_Pivot<Integer> testSort = new Quick_Sort_Inplace_Random_Pivot<Integer>();
		testSort.sort(test);
		System.out.println(test.toString());
		assertTrue(Sort_Utils.is_sorted(test));
	}
	
	@Test
	public void test_Quick_Sort_First_Pivot()
	{
		ArrayList<Integer> test = Sort_Utils.generate_random_array(10, 9);
		Quick_Sort_Inplace_First_Pivot<Integer> testSort = new Quick_Sort_Inplace_First_Pivot<Integer>();
		testSort.sort(test);
		System.out.println(test.toString());
		assertTrue(Sort_Utils.is_sorted(test));
	}
	
	@Test
	public void test_Quick_Sort_Inplace_M3()
	{
		ArrayList<Integer> test = Sort_Utils.generate_random_array(10, 9);
		Quick_Sort_Inplace_M3<Integer> testSort = new Quick_Sort_Inplace_M3<Integer>();
		testSort.sort(test);
		System.out.println(test.toString());
		assertTrue(Sort_Utils.is_sorted(test));
	}
	
	@Test
	public void testMergeRandomArray() {
		ArrayList<Integer> test = Sort_Utils.generate_random_array(100, 9);
		Merge_Sort<Integer> testSort = new Merge_Sort<Integer>();
		testSort.sort(test);
		assertTrue(Sort_Utils.is_sorted(test));
	}
	
	@Test
	public void testMergeReverseArray() {
		ArrayList<Integer> test = Sort_Utils.generate_reverse_sorted_array(10);
		Merge_Sort<Integer> testSort = new Merge_Sort<Integer>();
		testSort.sort(test);
		assertTrue(Sort_Utils.is_sorted(test));
	}
	
	@Test
	public void testMergeSortedArray() {
		ArrayList<Integer> test = Sort_Utils.generate_sorted_array(10);
		Merge_Sort<Integer> testSort = new Merge_Sort<Integer>();
		testSort.sort(test);
		assertTrue(Sort_Utils.is_sorted(test));
	}
}
