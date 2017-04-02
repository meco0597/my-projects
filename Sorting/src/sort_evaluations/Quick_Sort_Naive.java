
package sort_evaluations;

import java.util.ArrayList;

import javax.lang.model.element.QualifiedNameable;

/**
 * @author Melvin Bosnjak
 * @author Britton Gaul
 * 
 * The Naive implementation of quick sort. It doesnt do it "inplace"
 * 
 */
public class Quick_Sort_Naive<Type extends Comparable<? super Type>> extends Quick_Sort<Type>
{

	/**
	 * Uses the median of three as the pivot
	 */
	@Override
	protected Type choose_pivot( ArrayList<Type> array, int start, int end )
	{
		return median_of_three( array );
	}

	/**
	 * Median of Three, chooses the middle element position
	 * 
	 */
	private Type median_of_three( ArrayList<Type> array )
	{
		return array.get(array.size()/2);
	}

	/**
	 * The "Naive" implementation of Quick Sort which uses an extra array instead of doing it inplace
	 *
	 */
	public void quick_sort_naive( ArrayList<Type> array )
	{
		/*
		 * Base Case
		 */
		if (array.size() <= switchover_level)
		{
			Sort_Utils.insertion_sort(array, 0, array.size() - 1);
			return;
		}

		/*
		 * Some extra storage to make things easier
		 */
		ArrayList<Type> small_elements = new ArrayList<Type>();
		ArrayList<Type> large_elements = new ArrayList<Type>();
		ArrayList<Type> pivots = new ArrayList<Type>();

		/*
		 * Partition the list into three (small, pivot, large) lists
		 */
		Type pivot_value = this.median_of_three(array);

		for (int i = 0; i < array.size(); i++)
		{
			if (array.get(i).compareTo(pivot_value) < 0)
			{
				small_elements.add(array.get(i));
			}
			else if (array.get(i).equals((pivot_value)))
			{
				pivots.add(array.get(i));
			}
			else
			{
				large_elements.add(array.get(i));
			}
		}

		/*
		 * Quick Sort each half
		 */
		quick_sort_naive(small_elements);
		quick_sort_naive(large_elements);

		/*
		 * Put back together
		 */
		array.clear();
		array.addAll(small_elements);
		array.addAll(pivots);
		array.addAll(large_elements);

	}

	/**
	 * Name of Sort_Tester
	 */
	public String name_of_sort()
	{
		return "Quick Sort Naive (Cutoff: " + Quick_Sort.switchover_level + ")";
	}

	/**
	 * Converts the Type[] array into an arraylist and
	 * then calls the appropriate sort routine
	 * 
	 * @param array
	 *            the array of data to sort from small to large
	 */
	public void sort( ArrayList<Type> array )
	{
		quick_sort_naive(array);
	}

}
