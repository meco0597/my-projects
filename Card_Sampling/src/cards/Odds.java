package cards;

import cards.Hand.Rank;

/**
 * This class computes various odds of certain hands compared to others, using a
 * histogram of the different Ranks of hands
 * 
 * @author Melvin Bosnjak
 * @author Britton Gaul
 *
 */
public class Odds
{

	/**
	 * Given two card values for two different hands, this method will compute
	 * the chance of hand one beating hand two given a number of samples to
	 * randomly test.
	 * 
	 * @param h1c1
	 *            Hand one, Card one
	 * @param h1c2
	 *            Hand one, Card two
	 * @param h2c1
	 *            Hand two, Card one
	 * @param h2c2
	 *            Hand two, Card two
	 * @param samples:
	 *            number of samples tested
	 * @return
	 */
	static double odds_to_win(int h1c1, int h1c2, int h2c1, int h2c2, int samples)
	{
		double wins = 0;
		Hand hand_one = new Hand(7, false);
		Hand hand_two = new Hand(7, false);

		// Creates the two random hands and compares the two N number of times.
		for (int random_samples = 0; random_samples < samples; random_samples++)
		{
			Hand.get_two_random_hands(hand_one, hand_two, new Card(h1c1), new Card(h1c2), new Card(h2c1),
					new Card(h2c2));
			if (hand_one.compare(hand_two) > 0)
			{
				wins++;
			}
		}
		return (wins / samples) * 100;
	}

	/**
	 * Given a hand size this method exaustively goes through all of the
	 * possibilities of hands and returns a histogram of how often each rank of
	 * hand shows up.
	 * 
	 * @param hand_size
	 * @return histogram
	 */
	static double[] percentage_per_hand_category_exhaustive(int hand_size)
	{
		double[] histogram = new double[10];
		Deck deck = new Deck();
		// Five card exaustive count
		if (hand_size == 5)
		{
			for (int cardOne = 0; cardOne < 52; cardOne++)
			{
				for (int cardTwo = cardOne + 1; cardTwo < 52; cardTwo++)
				{
					for (int cardThree = cardTwo + 1; cardThree < 52; cardThree++)
					{
						for (int cardFour = cardThree + 1; cardFour < 52; cardFour++)
						{
							for (int cardFive = cardFour + 1; cardFive < 52; cardFive++)
							{
								// makes the specific hand using the card value
								// index
								Card c1 = deck.deck.get(cardOne);
								Card c2 = deck.deck.get(cardTwo);
								Card c3 = deck.deck.get(cardThree);
								Card c4 = deck.deck.get(cardFour);
								Card c5 = deck.deck.get(cardFive);
								Hand currentHand = new Hand(c1, c2, c3, c4, c5);
								Rank handRank = currentHand.get_rank();
								// once the rank for the current hand is found,
								// increment the histogram using a switch
								// statement for the correct index
								switch (handRank)
								{
								case HIGH_CARD:
									histogram[9]++;
									break;
								case ONE_PAIR:
									histogram[8]++;
									break;
								case TWO_PAIR:
									histogram[7]++;
									break;
								case THREE_OF_A_KIND:
									histogram[6]++;
									break;
								case STRAIGHT:
									histogram[5]++;
									break;
								case FLUSH:
									histogram[4]++;
									break;
								case FULL_HOUSE:
									histogram[3]++;
									break;
								case FOUR_OF_A_KIND:
									histogram[2]++;
									break;
								case STRAIGHT_FLUSH:
									histogram[1]++;
									break;
								case ROYAL_FLUSH:
									histogram[0]++;
									break;
								}
							}
						}
					}
				}
			}
		}
		// for seven card exaustive count
		else if (hand_size == 7)
		{
			for (int cardOne = 0; cardOne < 52; cardOne++)
			{
				for (int cardTwo = cardOne + 1; cardTwo < 52; cardTwo++)
				{
					for (int cardThree = cardTwo + 1; cardThree < 52; cardThree++)
					{
						for (int cardFour = cardThree + 1; cardFour < 52; cardFour++)
						{
							for (int cardFive = cardFour + 1; cardFive < 52; cardFive++)
							{
								for (int cardSix = cardFive + 1; cardSix < 52; cardSix++)
								{
									for (int cardSeven = cardSix + 1; cardSeven < 52; cardSeven++)
									{
										Card c1 = deck.deck.get(cardOne);
										Card c2 = deck.deck.get(cardTwo);
										Card c3 = deck.deck.get(cardThree);
										Card c4 = deck.deck.get(cardFour);
										Card c5 = deck.deck.get(cardFive);
										Card c6 = deck.deck.get(cardSix);
										Card c7 = deck.deck.get(cardSeven);
										Hand currentHand = new Hand(c1, c2, c3, c4, c5, c6, c7);
										Rank handRank = currentHand.get_rank();
										switch (handRank)
										{
										case HIGH_CARD:
											histogram[9]++;
											break;
										case ONE_PAIR:
											histogram[8]++;
											break;
										case TWO_PAIR:
											histogram[7]++;
											break;
										case THREE_OF_A_KIND:
											histogram[6]++;
											break;
										case STRAIGHT:
											histogram[5]++;
											break;
										case FLUSH:
											histogram[4]++;
											break;
										case FULL_HOUSE:
											histogram[3]++;
											break;
										case FOUR_OF_A_KIND:
											histogram[2]++;
											break;
										case STRAIGHT_FLUSH:
											histogram[1]++;
											break;
										case ROYAL_FLUSH:
											histogram[0]++;
											break;
										}
									}
								}
							}
						}
					}
				}
			}
		} else
		{
			System.out.println("Not a valid hand size.");
		}
		double handSum = 0;
		// computes the total number of hand possibilities in order to compute
		// the odds
		for (int ranks = 0; ranks < histogram.length; ranks++)
		{
			handSum += histogram[ranks];
		}
		// computes the percentage at each index of the histogram
		for (int ranks = 0; ranks < histogram.length; ranks++)
		{
			histogram[ranks] = (histogram[ranks] / handSum) * 100;
		}
		return histogram;
	}

	/**
	 * Similar to the method above but instead of computing every possible hand,
	 * this method makes @param random_samples amount of random hands and
	 * computes the rank and adjusts the histogram accordingly
	 * 
	 * @param hand_size
	 * @param random_samples
	 * @return histogram
	 */
	static double[] percentage_per_hand_category_stochastic(int hand_size, int random_samples)
	{
		double[] histogram = new double[10];
		Deck deck = new Deck();
		// Five card exaustive count
		if (hand_size == 5)
		{
			for (int samples = 0; samples < random_samples; samples++)
			{
				Hand currentHand = new Hand(5, false);
				// makes a random hand based this time
				currentHand.get_random_hand();
				Rank handRank = currentHand.get_rank();
				switch (handRank)
				{
				case HIGH_CARD:
					histogram[9]++;
					break;
				case ONE_PAIR:
					histogram[8]++;
					break;
				case TWO_PAIR:
					histogram[7]++;
					break;
				case THREE_OF_A_KIND:
					histogram[6]++;
					break;
				case STRAIGHT:
					histogram[5]++;
					break;
				case FLUSH:
					histogram[4]++;
					break;
				case FULL_HOUSE:
					histogram[3]++;
					break;
				case FOUR_OF_A_KIND:
					histogram[2]++;
					break;
				case STRAIGHT_FLUSH:
					histogram[1]++;
					break;
				case ROYAL_FLUSH:
					histogram[0]++;
					break;
				}
			}
		}
		// for seven card exaustive count
		else if (hand_size == 7)
		{
			for (int samples = 0; samples < random_samples; samples++)
			{
				Hand currentHand = new Hand(7, false);
				currentHand.get_random_hand();
				Rank handRank = currentHand.get_rank();
				switch (handRank)
				{
				case HIGH_CARD:
					histogram[9]++;
					break;
				case ONE_PAIR:
					histogram[8]++;
					break;
				case TWO_PAIR:
					histogram[7]++;
					break;
				case THREE_OF_A_KIND:
					histogram[6]++;
					break;
				case STRAIGHT:
					histogram[5]++;
					break;
				case FLUSH:
					histogram[4]++;
					break;
				case FULL_HOUSE:
					histogram[3]++;
					break;
				case FOUR_OF_A_KIND:
					histogram[2]++;
					break;
				case STRAIGHT_FLUSH:
					histogram[1]++;
					break;
				case ROYAL_FLUSH:
					histogram[0]++;
					break;
				}
			}
		} else
		{
			System.out.println("Not a valid hand size.");
		}
		double handSum = 0;
		for (int ranks = 0; ranks < histogram.length; ranks++)
		{
			handSum += histogram[ranks];
		}
		for (int ranks = 0; ranks < histogram.length; ranks++)
		{
			histogram[ranks] = (histogram[ranks] / handSum) * 100;
		}
		return histogram;
	}
}
