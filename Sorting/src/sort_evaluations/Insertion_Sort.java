package sort_evaluations;

import java.util.ArrayList;


/**
 *  @author Melvin Bosnjak
 *  @author Britton Gaul
 *  
 */

public class Insertion_Sort <Type extends Comparable<? super Type>> implements Sorter<Type>
{

	/**
	 * Returns the Name of the sort
	 */
	public String name_of_sort()
	{
		return "Insertion sort";
	}

	/**
	 * This doesnt apply to insertion sort
	 */
	public void set_constant( double constant )
	{
		System.out.println("The constant doesnt apply to insertion sort.");
	}

	/**
	 * The driver sort for this class, the actual insertion sort is called in Sort_Utils
	 */
	public void sort( ArrayList<Type> array )
	{
		Sort_Utils.insertion_sort(array, 0, array.size() - 1);
	}

	/**
	 * returns what the expected Big O of insertion sort should be
	 * @return the expected complexity of this algorithm
	 */
	public Complexity_Class get_expected_complexity_class()
	{
		return Complexity_Class.Nsquared;
	}


}
