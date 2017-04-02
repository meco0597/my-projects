/**
 * 
 */
package sort_evaluations;

import java.util.ArrayList;

/**
 * @author Melvin Bosnjak
 * @author Britton Gaul
 * 
 * Builds a list of sorters and test and times them all
 *
 */
public class Main
{
	public static void main( String[] args )
	{
		ArrayList<Sorter<Integer>> sort_methods = new ArrayList<>();
		Sort_Utils test = new Sort_Utils();
		sort_methods.add(new Merge_Sort<Integer>());
		sort_methods.add(new Java_Sort<Integer>());
		sort_methods.add(new Insertion_Sort<Integer>());
		sort_methods.add(new Quick_Sort_Inplace_First_Pivot<Integer>());
		sort_methods.add(new Quick_Sort_Inplace_M3<Integer>());
		sort_methods.add(new Quick_Sort_Inplace_Random_Pivot<Integer>());
		sort_methods.add(new Quick_Sort_Naive<Integer>());
		sort_methods.add(new Shell_Sort<Integer>());
		
		for(Sorter<Integer> sort : sort_methods)
		{
			test.test_and_time(sort, 10, 1000, 10000, 1000);
			System.out.println("\n");
		}	
	}
}
