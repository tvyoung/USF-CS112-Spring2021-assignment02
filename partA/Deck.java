import java.util.*;

/**
 * A Deck class represents a complete deck of Cards
 * @author: Vicki Young
 * @version: 
 */
public class Deck 
{
    protected ArrayList<Card> deck;

    /** Constructor creates a group of 52 cards using the Card's
      * enumerated types of face and suits 
      */
    public Deck()
    {
        deck = new ArrayList<Card>();
        Card c;
        for (Card.Suit s : Card.Suit.values()) {
            for (Card.Face f : Card.Face.values()) {
                c = new Card(s, f);
                deck.add(c);
            }
        }
    }

     /** Constructor creates a group of 13 cards 
       * that all belong to one suit
       */
     public Deck(Card.Suit s)
     {
        deck = new ArrayList<Card>();
        Card c;
        for (Card.Face f : Card.Face.values()) {
            c = new Card(s, f);
            deck.add(c);
        }
     }

    /** 
     * toString method:
     * @overrides toString method to display all 52 cards
     * Print out the Deck of Cards 
     * 13 cards per line please.
     */
    public String toString()
    {
        String text = "";
        int count = 0; //counts number of cards (13 faces per suit)
        for (Card c : deck) {
            text += c + " ";
            count++;
            if (count == 13) { //when counts 13 cards, makes new line
                text += "\n";
                count = 0;
            }
        }
        return text;
    }

    /**
     * getCard method:
     * Get a card from the Deck at a specific location
     */
    public Card getCard(int index)
    {
        return deck.get(index);
    }

    /** 
    * shuffle method:
    * Randomizes the order of the stored cards
    * One easy way to shuffle is to loop through the 
    * cards and randomly swap each card with another one.
    */
    public void shuffle()
    {
        Random randGen = new Random();
        Card tempCard;
        Card tempCard2;
        int index;
        for (int i = 0; i < deck.size(); i++) {
            //tempCard receives the current card at index i
            tempCard = deck.get(i);
            //randomly choose another index in deck
            index = randGen.nextInt(deck.size());
            //tempCard2 receives the other card at randomly chosen index in deck
            tempCard2 = deck.get(index);
            //performs the swap; switches current card and other card positions
            deck.set(i, tempCard2);
            deck.set(index, tempCard);
        }
    }

    /**   
     * deal method:
     * removes the top Card from the deck
     */ 
    public Card deal() 
    {
        Card c = deck.get(0);
        deck.remove(0);
        return c;
    }

    /**
     * getCardCount
     * determines how many cards are left on the deck.
     */ 
    public int getCardCount()
    {
        return deck.size();
    }
}
