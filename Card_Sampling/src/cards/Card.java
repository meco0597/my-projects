package cards;

/**
 * This card class creates a card and stores its value compared to other cards,
 * its facevalue and its suit.
 * 
 * @author Melvin Bosnjak
 * @author Britton Gaul
 */
public class Card
{
	private Card_Value value;
	private Card_Suit suit;
	private int actualValue;

	/**
	 * A constructor that creates a card using its card value and its suit
	 * 
	 * @param value
	 * @param suit
	 */
	public Card(Card_Value value, Card_Suit suit)
	{
		this.value = value;
		this.suit = suit;
	}

	/**
	 * A constructor that makes a card using just its rank among other cards.
	 * i.e 2 of Clubs is 0, Ace of Spades is 51
	 *
	 * @param cardRank
	 */
	public Card(int cardRank)
	{
		// breaks down the ranks into the different suits and does some math to
		// compute the value
		if (cardRank >= 0 && cardRank < 13)
		{
			this.suit = Card_Suit.CLUBS;
			this.actualValue = (cardRank + 2);
		} else if (cardRank >= 13 && cardRank < 26)
		{
			this.suit = Card_Suit.DIAMONDS;
			this.actualValue = ((cardRank + 2) - 13);
		} else if (cardRank >= 26 && cardRank < 39)
		{
			this.suit = Card_Suit.HEARTS;
			this.actualValue = ((cardRank + 2) - 26);
		} else if (cardRank >= 39 && cardRank < 52)
		{
			this.suit = Card_Suit.SPADES;
			this.actualValue = ((cardRank + 2) - 39);
		} else
		{
			System.out.println("Not a valid card value");
		}

		// associates the facevalue of the card to the card value enum
		switch (actualValue)
		{
		case 2:
			this.value = Card_Value.TWO;
			break;
		case 3:
			this.value = Card_Value.THREE;
			break;
		case 4:
			this.value = Card_Value.FOUR;
			break;
		case 5:
			this.value = Card_Value.FIVE;
			break;
		case 6:
			this.value = Card_Value.SIX;
			break;
		case 7:
			this.value = Card_Value.SEVEN;
			break;
		case 8:
			this.value = Card_Value.EIGHT;
			break;
		case 9:
			this.value = Card_Value.NINE;
			break;
		case 10:
			this.value = Card_Value.TEN;
			break;
		case 11:
			this.value = Card_Value.JACK;
			break;
		case 12:
			this.value = Card_Value.QUEEN;
			break;
		case 13:
			this.value = Card_Value.KING;
			break;
		case 14:
			this.value = Card_Value.ACE;
			break;
		}
	}

	/**
	 * Returns the string representation of the Card
	 */
	public String toString()
	{
		return value + " of " + suit;
	}

	/**
	 * Returns true if the cards are equivalent, false otherwise
	 * 
	 * @param other
	 *            card
	 */
	public boolean equals(Card other)
	{
		if (this.get_value() == other.get_value())
		{
			return true;
		}
		return false;
	}

	/**
	 * @return the facevalue of the card
	 */
	public Card_Value get_face_value()
	{
		return this.value;
	}

	/**
	 * @return the enum value of the card
	 */
	public int get_value()
	{
		return this.value.get_value();
	}

	/**
	 * @return the suit of the card
	 */
	public Card_Suit get_suit()
	{
		return this.suit;
	}
}
