
package sort_evaluations;

import java.util.ArrayList;

/**
 * test quick sort just choosing the first value as the pivot. 
 */
public class Quick_Sort_Inplace_First_Pivot <Type extends Comparable<? super Type>> extends Quick_Sort<Type>
{

	/**
	 * Runs the quick sort with the first element as the pivot
	 * 
	 * 
	 * @param array          = the array with all the data (we sort a sub piece of the array)
	 * @param start          = index of start of array
	 * @param end            = index of end of array
	 */
	protected Type choose_pivot( ArrayList<Type> array, int start, int end )
	{
		Sorter.swap(array, start, end);
		return array.get(end);
	}

	/**
	 * Name of the sort plus the insertion sort cutoff
	 */
	public String name_of_sort()
	{
		return "Quick Sort First Pivot (Cutoff: " + Quick_Sort.switchover_level + ")";
	}


}

