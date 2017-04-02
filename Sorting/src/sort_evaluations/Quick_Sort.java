
package sort_evaluations;

import java.util.ArrayList;

/**
 * This code is an abstract base class for all versions of quicksort.
 * The other methods will have some of these default characteristics but
 * some different such as choose pivot
 * 
 */
public abstract class Quick_Sort <Type extends Comparable<? super Type>> implements Sorter<Type>
{
	/**
	 * The point in which the sort switches over to insertion sort
	 */
	public static double switchover_level = 4;

	/**
	 *  Decides what the pivot will be. This method will be different in the other quick sort children classes
	 *
	 * @param array - the array of all values (we only sort a sub piece of the array)
	 * @param start - the start position in the (sub) array
	 * @param end   - the end position in the (sub) array
	 * @return the pivot value
	 */
	protected abstract Type choose_pivot( ArrayList<Type> array, int start, int end );

	/**
	 * Partitions the array so that all of the elements smaller than the pivot to the left and elements 
	 * greater than the pivot to the right
	 * 
	 * @param array   - data data to sort
	 * @param start    - the start index of the sub array (inclusive)
	 * @param end   - the end index of the sub array (inclusive)
	 * 
	 * @return the location of the pivot
	 */

	protected int partition( ArrayList<Type> array, int start, int end )
	{
		int i = start;
		int j = end + 1;
		while(true)
		{
			while(array.get(++i).compareTo(array.get(start)) == -1)
			{
				if(i == end) break;
			}
			while(array.get(start).compareTo(array.get(--j)) == -1)
			{
				if(j == start) break;
			}
			
			if(i >= j) break;
			Sorter.swap(array, i, j);
		}
		Sorter.swap(array, start, j);
		return j;
     }

	/**
	 * The actual Quick Sort on an Array routine.
	 * 
	 * @param array - data to be sorted
	 * @param start is the index of the beginning element
	 * @param end is the index of the last element
	 * 
	 * 
	 */
	private void quick_sort( ArrayList<Type> array, int start, int end )
	{
		// the base case of this recursive sort
		if(end <= start + switchover_level - 1)
		{
			Sort_Utils.insertion_sort(array, start, end);
			return;
		}
		
		//splits the array to quick sort the left/right virtual arrays
		int mid_position = partition(array, start, end);
		quick_sort(array, start, mid_position-1);
		quick_sort(array, mid_position + 1, end);
	}

	/**
	 * 
	 * driver method for quicksort, It calls quick_sort
	 * 
	 */
	@Override
	public void sort( ArrayList<Type> array )
	{
		quick_sort(array, 0, array.size() - 1);
	}

	/**
	 * Name the of the sort
	 */
	public abstract String name_of_sort();

	/**
	 * The constant in this case is the insertion sort cutoff level... always greater than 3
	 */
	public void set_constant( double constant )
	{
		switchover_level = constant;
	}
	
	/**
	 * @return the expected complexity for quick sort 
	 */
	public Complexity_Class get_expected_complexity_class()
	{
		return Complexity_Class.N_log_N;
	}
	
}