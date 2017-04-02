package cards;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Melvin Bosnjak
 * @author Britton Gaul
 */
public class Deck
{
	ArrayList<Card> deck;
	private final Card_Value[] values =
	{ Card_Value.TWO, Card_Value.THREE, Card_Value.FOUR, Card_Value.FIVE, Card_Value.SIX, Card_Value.SEVEN,
			Card_Value.EIGHT, Card_Value.NINE, Card_Value.TEN, Card_Value.JACK, Card_Value.QUEEN, Card_Value.KING,
			Card_Value.ACE };
	private final Card_Suit[] suits =
	{ Card_Suit.CLUBS, Card_Suit.DIAMONDS, Card_Suit.HEARTS, Card_Suit.SPADES };

	/**
	 * Constructor, creates an in order deck of 52 cards
	 */
	public Deck()
	{
		deck = new ArrayList<Card>();
		for (Card_Suit suit : suits)
		{
			for (Card_Value value : Card_Value.values())
			{
				deck.add(new Card(value, suit));
			}
		}
	}

	/**
	 * @return the decks contents in String format
	 */
	public String toString()
	{
		String result = "";
		for (Card card : deck)
		{
			result += " " + card.toString();
		}
		return result;
	}

	/**
	 * Uses a Random generator to @return a random card in a deck
	 */
	public Card get_random_card()
	{
		// change the run time initialization to test different random
		// generators
		Random_Generator rand = new Javas_Random_Generator();
		return deck.get(rand.next_int(52));
	}

	/**
	 * @return this deck in ArrayList form
	 */
	public ArrayList<Card> getDeck()
	{
		return this.deck;
	}

}
