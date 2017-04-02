
package sort_evaluations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 *  This class uses Javas built in sort so we can see how the performance is compared to other sorts
 * 
 */
public class Java_Sort<Type extends Comparable<? super Type>> implements Sorter<Type>
{

	/**
	 * implements the built in sort and natural order comparator
	 */
	public void sort( ArrayList<Type> array ) {
		Comparator<Type> compare = Comparator.<Type>naturalOrder();
		array.sort(compare);
	}

	/**
	 * returns the Name the sort (in this case Java's sort)
	 */
	public String name_of_sort()
	{
		return "Java's sort";
	}

	/**
	 * Prints a message that you can't modify Java's sort algorithm
	 */
	public void set_constant( double constant )
	{
		System.out.println("Java's sort algorithm can't be modified.");
	}


	/**
	 * 
	 * @return the expected complexity for javas sorts
	 */
	public Complexity_Class get_expected_complexity_class()
	{
		return Complexity_Class.N_log_N;
	}
	

}
