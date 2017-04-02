package cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main
{
	public static void main(String[] args)
	{
		
		//accuracy code
		double[] exaustive_histogram = Odds.percentage_per_hand_category_exhaustive(7);	
		double[] stochastic_histogram = new double[10];
		double[] accuracy = new double[14];
		int count = 0;
		for (int samples = 100; samples < 10000100; samples += samples*2)
		{
			stochastic_histogram = Odds.percentage_per_hand_category_stochastic(7, samples);
			double avg_accuracy = 0.0;
			for (int index = 0; index < stochastic_histogram.length; index++)
			{
				avg_accuracy += (Math.abs(stochastic_histogram[index] - exaustive_histogram[index]));
			}
			accuracy[count] = avg_accuracy;
			count++;
		}
		System.out.println("The percent accuracy for each sampling:");
		for (int index = 0; index < accuracy.length; index++)
		{
			System.out.println(accuracy[index]);
		}
		
		//Timing code for stochastic sampling
		System.out.println("Timing for stochastic sampling 5 card hand:");
		for (int samples = 1000; samples < 10000000; samples = samples *2)
		{
			long startTime = System.nanoTime();
			Odds.percentage_per_hand_category_stochastic(5, samples);
			long endTime = System.nanoTime();
			System.out.println((endTime - startTime)/1e6);
		}
		
		System.out.println("Timing for stochastic sampling 7 card hand:");
		for (int samples = 1000; samples < 10000000; samples = samples *2)
		{
			long startTime = System.nanoTime();
			Odds.percentage_per_hand_category_stochastic(7, samples);
			long endTime = System.nanoTime();
			System.out.println((endTime - startTime)/1e6);
		}
		
		System.out.println("Timing for exhaustive sampling 5 card hand:");
		long startTime = System.nanoTime();
		Odds.percentage_per_hand_category_exhaustive(5);
		long endTime = System.nanoTime();
		System.out.println((endTime - startTime)/1e6);
		
		System.out.println("Timing for exhaustive sampling 7 card hand:");
		long startTime1 = System.nanoTime();
		Odds.percentage_per_hand_category_exhaustive(7);
		long endTime1 = System.nanoTime();
		System.out.println((endTime1 - startTime1)/1e6);
		
		//texas hold em code
		ArrayList<Double> percentages = new ArrayList<>();
		for (int cardOne = 0; cardOne < 52; cardOne++)
		{
			for (int cardTwo = cardOne + 1; cardTwo < 52; cardTwo++)
			{
				double percentage = Odds.odds_to_win(cardOne, cardTwo, 0, 9, 200000);
				percentages.add(percentage);				
			}
		}
		Collections.sort(percentages);
		System.out.println(percentages.toString());
	}
}
