
package sort_evaluations;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Melvin Bosnjak
 * @author Britton Gaul
 */
public class Quick_Sort_Inplace_Random_Pivot <Type extends Comparable<? super Type>> extends Quick_Sort<Type>
{

	/**
	 * Runs the quick sort with a random pivot.
	 * 
	 * @param array     = the array with all the data (we sort a sub piece of the array)
	 * @param start      = index of start of array
	 * @param end        = index of end of array
	 */
	protected Type choose_pivot(ArrayList<Type> array, int start, int end)
	{
		Random rand = new Random();
		int pivot = rand.nextInt(end);
		Type pivot_value = array.get(pivot);
		Type temp = array.get(end);
		array.set(end, pivot_value);
		array.set(pivot, temp);
		return pivot_value;
	}

	/**
	 * Name of the sort plus the insertion sort cutoff
	 */
	public String name_of_sort()
	{
		return "Quick Sort Random Pivot (Cutoff: " + Quick_Sort.switchover_level + ")";
	}


}
