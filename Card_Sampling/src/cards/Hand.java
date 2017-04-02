package cards;

import java.util.ArrayList;

/**
 * This class is represented as an array of cards
 * 
 * @author Melvin Bosnjak
 * @author Britton Gaul
 */
public class Hand
{
	
	public enum Rank
	{
		ROYAL_FLUSH(10), STRAIGHT_FLUSH(9), FOUR_OF_A_KIND(8), FULL_HOUSE(7), 
		FLUSH(6), STRAIGHT(5), THREE_OF_A_KIND(4), TWO_PAIR(3), ONE_PAIR(2), HIGH_CARD(1);
		
		private int value;
		
		Rank (int value)
		{
			this.value = value;
		}
		
		public int get_value()
		{
			return this.value;
		}

	}
	
	private Card[] hand;
	private Card[] two_hand;
	private Card[] five_hand;
	private Deck deck;
	// this variable flags which cards have been pulled
	private ArrayList<Card> called;
	private boolean hold_em;
	
	/**
	 * Creates a hand of random cards with the given amount of cards 
	 * and a boolean to tell if playing texas holdem or not
	 * 
	 * @param num_cards- number of cards in the hand 
	 * @param hold_em- true if playing texas holdem false if not
	 */
	public Hand(int num_cards, boolean hold_em)
	{
		deck = new Deck();
		hand = new Card[num_cards];
		two_hand = new Card[2];
		five_hand = new Card[5];
		this.hold_em = hold_em;
		called = new ArrayList<Card>();
	}
	
	/**
	 * Creates a hand of five cards with the cards taken from an index in the deck
	 * @param c1
	 * @param c2
	 * @param c3
	 * @param c4
	 * @param c5
	 */
	public Hand(Card c1, Card c2, Card c3, Card c4, Card c5)
	{
		hand = new Card[5];
		hand[0] = c1;
		hand[1] = c2;
		hand[2] = c3;
		hand[3] = c4;
		hand[4] = c5;
	}

	/**
	 * Creates a hand of seven cards with the cards taken from an index in the deck
	 * @param c1
	 * @param c2
	 * @param c3
	 * @param c4
	 * @param c5
	 */
	public Hand(Card c1, Card c2, Card c3, Card c4, Card c5, Card c6, Card c7)
	{
		hand = new Card[7];
		hand[0] = c1;
		hand[1] = c2;
		hand[2] = c3;
		hand[3] = c4;
		hand[4] = c5;
		hand[5] = c6;
		hand[6] = c7;
	}

	/**
	 * Returns a string representation of the hand
	 */
	public String toString()
	{
		if(hold_em)
		{
			int count = 1;
			String result = "";
			result += "YOUR TWO CARD HAND\n";
			for(Card card : two_hand)
			{
				result += "Card " + count + ": " + card.toString() + "\n";
				count++;
			}
			result += "PUBLIC FIVE CARD HAND\n";
			for(Card card : five_hand)
			{
				result += "Card " + count + ": " + card.toString() + "\n";
				count++;
			}
			result += "Hand Rank: " + this.get_rank();
			return result;

		}
		else
		{
			String result = "";
			int count = 1;
			for(Card card : hand)
			{
				result += "Card " + count + ": " + card.toString() + "\n";
				count++;
			}
			result += "Hand Rank: " + this.get_rank();
			return result;
		}
	}
	
	/**
	 * Fills the hand with random cards
	 */
	public void get_random_hand()
	{
		//fills the hand for texas holdem
		if(hold_em)
		{
			hand = new Card[7];
			for(int index = 0; index < hand.length; index++)
			{
				Card card = deck.get_random_card();
				if(!called.contains(card))
				{
					hand[index] = card;
					called.add(card);
				}
				else
				{
					card = deck.get_random_card();
					index--;
				}
			}
			for(int i = 0; i < 2; i++)
			{
				two_hand[i] = hand[i];
			}
			int count = 0;
			for(int i = 2; i < 7; i++)
			{
				five_hand[count] = hand[i];
				count++;
			}
			
		}
		else
		{
			for(int index = 0; index < hand.length; index++)
			{
				Card card = deck.get_random_card();
				if(!called.contains(card))
				{
					hand[index] = card;
					called.add(card);
				}
				else
				{
					card = deck.get_random_card();
					index--;
				}
			}
			this.sort_hand();
		}
	}
	
	/**
	 * Gets two random hands with two cards being held seperately and five cards shared between the hands
	 * 
	 * @param hand_one
	 * @param hand_two
	 * @param h1c1
	 * @param h1c2
	 * @param h2c1
	 * @param h2c2
	 */
	public static void get_two_random_hands(Hand hand_one, Hand hand_two, Card h1c1, Card h1c2, Card h2c1, Card h2c2)
	{
		hand_one.hand = new Card[7];
		hand_two.hand = new Card[7];
		
		// places random cards in the last 5 places of both hands
		Hand shared_cards = new Hand(5, false);
		shared_cards.get_random_hand();
		
		// count so the shared cards doesnt get an array out of bounds exception 
		int count = 0;
		for (int card = 2; card < 7; card++)
		{
			hand_one.hand[card] = shared_cards.hand[count];
			hand_two.hand[card] = shared_cards.hand[count];
			count++;
		}
		
		//sets the assigned two cards as the first two places
		hand_one.hand[0] = h1c1;
		hand_one.hand[1] = h1c2;
		hand_two.hand[0] = h2c1;
		hand_two.hand[1] = h2c2;
	}
	
	/**
	 * Computes the basic rank of the hand and does most of the work
	 * 
	 * @return
	 */
	public Rank get_rank_helper()
	{
		//looks for royal flush and flush by adding
		int same_suit = 0;
		int royal = 0;
			for(int i = 0; i < hand.length; i++)
			{
				if(hand[0].get_suit() == hand[i].get_suit())
				{
					same_suit++;
					if(hand[i].get_value() >= 10)
					{
						royal++;
					}
				}
			}
		
		//looks for straight
		boolean straight = true;
		int straight_count = 0;
		for(int i = 0; i < hand.length-1; i++)
		{
			if(!straight)
			{
				break;
			}
			if(straight_count == 5)
			{
				straight = true;
				break;
			}
			for(int j = i+1; j == i+1; j++)
			{
				if(hand[i].get_value()+1 == hand[j].get_value())
				{
					straight = true;
					straight_count++;
				}
				else
				{
					straight = false;
					break;
				}
			}
		}
		
		//look for pairs
		int num_pair = 0;
		for(int i = 0; i < hand.length; i++)
		{
			for(int j = i+1; j < hand.length; j++)
			{
				if(hand[i].equals(hand[j]))
				{
					num_pair++;
				}
			}
		}
		if(same_suit == 5 && royal == 5)
		{
			return Rank.ROYAL_FLUSH;
		}
		else if(straight && same_suit == 5)
		{
			return Rank.STRAIGHT_FLUSH;
		}
		else if(same_suit == 5)
		{
			return Rank.FLUSH;
		}
		else if(straight)
		{
			return Rank.STRAIGHT;
		}
		else if(num_pair == 3)
		{
			return Rank.THREE_OF_A_KIND;
		}
		else if(num_pair == 2)
		{
			return Rank.TWO_PAIR;
		}
		else if(num_pair == 1)
		{
			return Rank.ONE_PAIR;
		}
		else if(num_pair == 4)
		{
			return Rank.FULL_HOUSE;
		}
		else if(num_pair == 6)
		{
			return Rank.FOUR_OF_A_KIND;
		}
		return Rank.HIGH_CARD;
	}
	
	/**
	 * Computes the actual rank of the hand returning the highest possible rank
	 * with the ACE being a low or high value
	 * 
	 * @return
	 */
	public Rank get_rank()
	{
		if (!this.contains_ace())
		{
			return this.get_rank_helper();
		} else 
		{
			Hand ace_low = new Hand(this.hand.length, false);
			for (int index = 0; index < this.hand.length; index++)
			{
				ace_low.hand[index] = this.hand[index];
			}
			for (Card card: this.hand)
			{
				if (card.get_face_value() == Card_Value.ACE)
				{
					card.get_face_value().set_value(1);
				}
			}
			if (ace_low.compare(this) > 0)
			{
				ace_low.sort_hand();
				return ace_low.get_rank_helper();
			} else
			{
				for (Card card: this.hand)
				{
					if (card.get_face_value() == Card_Value.ACE)
					{
						card.get_face_value().set_value(14);
					}
				}
				this.sort_hand();
				return this.get_rank_helper();
			}
		}
	}
	
	/**
	 * Returns if whether or not the hand contains an ACE
	 * 
	 * @return
	 */
	public boolean contains_ace()
	{
		for (int card = 0; card < this.hand.length; card++)
		{
			if (this.hand[card].get_face_value() == Card_Value.ACE)
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns a number value associated with the hand
	 * 
	 * @return
	 */
	public int value()
	{
		int value = 0;
		for(int index = 0; index < hand.length; index++)
		{
			value += this.hand[index].get_value() * Math.pow(13, index);
		}

		return value;
	}
	
	/**
	 * Compares two hands and returns 1 if the first hand is larger 
	 * -1 if smaller and 0 if equal
	 * @param other
	 * @return
	 */
	public int compare(Hand other)
	{
		if(this.get_rank_helper().get_value() > other.get_rank_helper().get_value())
		{
			return 1;
		}
		else if(this.get_rank_helper().get_value() < other.get_rank_helper().get_value())
		{
			return -1;
		}
		else
		{
			if(this.value() > other.value())
			{
				return 1;
			}
			else if(this.value() < other.value())
			{
				return -1;
			}
			return 0;
		}
	}
	
	/**
	 * Sorts the hand based off the value of the card
	 */
	public void sort_hand()
	{
		for(int i = 0; i < hand.length; i++)
		{
			for(int j = i+1; j < hand.length; j++)
			{
				if(hand[i].get_value() > hand[j].get_value())
				{
					Card temp = hand[j];
					hand[j] = hand[i];
					hand[i] = temp;
				}

			}
		}
	}
}
