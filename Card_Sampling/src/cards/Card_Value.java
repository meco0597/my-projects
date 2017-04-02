package cards;

/**
 * Holds all of the values for the cards
 * 
 * @author Melvin Bosnjak
 * @author Britton Gaul
 */
public enum Card_Value
{
	TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13),
	ACE(14);

	private int value;

	/**
	 * Constructs the enums
	 */
	Card_Value(int value)
	{
		this.value = value;
	}

	/**
	 * Getter for the card values
	 */
	public int get_value()
	{
		return this.value;
	}

	/**
	 * Setter for the card values
	 */
	public void set_value(int value)
	{
		this.value = value;
	}
}
