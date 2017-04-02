
package sort_evaluations;

import java.util.ArrayList;

import java.util.ArrayList;

/**
 *  @author Melvin Bosnjak
 *  @author Britton Gaul
 *  use the median of three technique to compare vs random pivot selection, etc.
 */
public class Quick_Sort_Inplace_M3 <Type extends Comparable<? super Type>> extends Quick_Sort<Type> 
{

	/**
	 * Runs the quick sort with the Median of Three (choose the middle element position)
	 * as the pivot.
	 * 
	 * @param array the array with all the data (we sort a sub piece of the array)
	 * @param start  = index of start of array
	 * @param end    = index of end of array
	 */
	protected Type choose_pivot( ArrayList<Type> array, int start, int end )
	{
		int mid = array.size() / 2;
		ArrayList<Type> insert_sort = new ArrayList<Type>();
		
		// adds the three into an array to sort them
		insert_sort.add(array.get(start));
		insert_sort.add(array.get(mid));
		insert_sort.add(array.get(end));
		
		// sorts the array and chooses the median as the pivot
		Sort_Utils.insertion_sort(insert_sort, 0, 2);
		Sorter.swap(array, mid, end-1);
		Sorter.swap(array, end-1, end);
		return array.get(end);
	}


	/**
	 * Name the of the sort plus the insertion sort cutoff
	 */
	public String name_of_sort()
	{
		return "Quick Sort M3 (Cutoff: " + Quick_Sort.switchover_level + ")";
	}


}
