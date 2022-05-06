/**
 * A Card class represents a playing card with a face 
 * value and a suit.
 * @author: Vicki Young
 * @version: 
 */

public class Card extends Token
{

    /** These are the unicode characters to display the typical suits of a playing card */
    /*private static final char spades = '\u2660';
    private static final char hearts = '\u2661';
    private static final char diamonds = '\u2662';
    private static final char clubs = '\u2663';*/

    private static final char spades = 'S';
    private static final char hearts = 'H';
    private static final char diamonds = 'D';
    private static final char clubs = 'C';

    public static enum Suit {SPADES, HEARTS, DIAMONDS, CLUBS}
    public static enum Face {ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING}

    private Suit mySuit;
    private Face myFace;

    /** 
     * Constructor that creates an empty Card
     * Instance data should be initialized to null
     */
    public Card() 
    {
        mySuit = null;
        myFace = null;
        setTokenValue();
    }

    /** 
     * Constructor that inializes all 
     * all instance data to proper values.
     */
    public Card(Suit s, Face f)
    {
        mySuit = s;
        myFace = f;
        setTokenValue();
    }

    /* ADD OTHER METHODS YOU NEED */

    public void setTokenValue() {
        tokenValue = "";
        if (mySuit == null) {
            tokenValue = "---";
            return; //exit method
            //need to exit method here before goes into switch/case check, which results in error
        }        
        switch (mySuit) {
            case SPADES:
                tokenValue = Character.toString(spades);
                break;
            case HEARTS:
                tokenValue = Character.toString(hearts);
                break;
            case DIAMONDS:
                tokenValue = Character.toString(diamonds);
                break;
            case CLUBS:
                tokenValue = Character.toString(clubs);
                break;
        }
    }

    public String getTokenValue() {
        return tokenValue;
    }

    @Override
    public String toString() {
        String text = "";
        if (myFace == null && mySuit == null) {
            text = "---";
            return text;
            //need to exit method here before goes into switch/case check, which results in error
        }        
        switch (myFace) {
            case ACE: 
                text = "1:";
                break;
            case TWO:
                text = "2:";
                break;
            case THREE:
                text = "3:";
                break;
            case FOUR:
                text = "4:";
                break;
            case FIVE:
                text = "5:";
                break;
            case SIX:
                text = "6:";
                break;
            case SEVEN:
                text = "7:";
                break;
            case EIGHT: 
                text = "8:";
                break;
            case NINE:
                text = "9:";
                break;
            case TEN:
                text = "t:";
                break;
            case JACK:
                text = "J:";
                break;
            case QUEEN:
                text = "Q:";
                break;
            case KING:
                text = "K:";
                break;
        }
        switch (mySuit) {
            case SPADES:
                text += Character.toString(spades);
                break;
            case HEARTS:
                text += Character.toString(hearts);
                break;
            case DIAMONDS:
                text += Character.toString(diamonds);
                break;
            case CLUBS:
                text += Character.toString(clubs);
                break;
        }
        return text;
    }

}
