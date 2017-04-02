
package sort_evaluations;

import java.util.ArrayList;

/**
 * @author Melvin Bosnjak
 * @author Britton Gaul
 * 
 *       regular merge sort
 */
public class Merge_Sort <Type extends Comparable<? super Type>> implements Sorter<Type>
{

	/**
	 *  value for switching over to insertion sort
	 */
	private int switchValue = 4;
	
	/**
	 * The name of the sort
	 */
	public String name_of_sort()
	{
		return "Merge sort";
	}

	/**
	 * Sorts the given array using the merge sort method.
	 * 
	 * @param array          the values to sort from small to high
	 * @param low            the index of the starting value in the "virtual array"
	 * @param high           the index of the ending value in the "virtual array"
	 * 
	 */
	private void merge_sort( ArrayList<Type> array, ArrayList<Type> auxillary, int low, int high )
	{
		// base case
		if ((high - low) <= switchValue)
		{
			Sort_Utils.insertion_sort(array, low, high);
		}
		
		if (low < high)
		{
			int middle = low + (high - low)/2;
			
			// recursively sort the two halves of the array
			merge_sort(array, auxillary, low, middle);
			merge_sort(array, auxillary, middle + 1, high);
			combine(array, auxillary, low, middle, high);
		}
}
	
	/**
	 * combine the values in array into the auxiliary
	 * 
	 * @param array           - original values (the entire array)
	 * @param auxillary       - spare space 
	 * @param low             - low,mid are the lower array
	 * @param mid             - mid,high are the upper array
	 * @param high 
	 */

	private void combine( ArrayList<Type> array, ArrayList<Type> auxillary, int low, int mid, int high )
	{
		//copy the array into auxillary array for scratchwork
		for (int index = low; index <= high; index++ )
		{
			auxillary.set(index, array.get(index));
		}
		
		int lowIndex = low;
		int midIndex= mid + 1;
		for (int count= low; count <= high; count++)
		{
			if (lowIndex > mid)
			{
				array.set(count, auxillary.get(midIndex));
				midIndex++;
			}
			else if (midIndex > high)
			{
				array.set(count, auxillary.get(lowIndex));
				lowIndex++;
			}
			// if the element at midIndex is smaller than the element at the lowIndex, place it into the
			// real array before the element at lowIndex
			else if((auxillary.get(midIndex).compareTo(auxillary.get(lowIndex)) == -1))
			{
				array.set(count, auxillary.get(midIndex));
				midIndex++;
			}
			else
			{
				array.set(count, auxillary.get(lowIndex));
				lowIndex++;
			}
		}
	
	}

	/**
	 * Allow the insertion sort cut off to be changed
	 */
	public void set_constant(double cutoff)
	{
		this.switchValue = (int)cutoff;	
	}

	/**
	 * Driver sort
	 */
	public void sort(ArrayList<Type> array)
	{
		ArrayList<Type> auxillary = new ArrayList<Type>(array);
		merge_sort(array, auxillary, 0, array.size() - 1);	
	}
	
	/**
	 * Returns the expected complexity of the sort
	 */
	@Override
	public Complexity_Class get_expected_complexity_class()
	{
		return Complexity_Class.N_log_N;
	}

}
