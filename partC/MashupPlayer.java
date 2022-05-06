public class MashupPlayer extends Player {
	private String playerHandSuit;
	private Deck playerHand;

	public MashupPlayer(String name) {
		super(name);
	}

	//returns string of what suit the player's hand is made up of
	public String getPlayerHandSuit() {
		return playerHandSuit;
	}

	public void setPlayerHand(String suit) {
		if (suit.equals("SPADES") || suit.equals("spades")) {
			playerHand = new Deck(Card.Suit.SPADES);
			playerHandSuit = "SPADES";
		} else if (suit.equals("HEARTS") || suit.equals("hearts")) {
			playerHand = new Deck(Card.Suit.HEARTS);
			playerHandSuit = "HEARTS";
		} else if (suit.equals("DIAMONDS") || suit.equals("diamonds")) {
			playerHand = new Deck(Card.Suit.DIAMONDS);
			playerHandSuit = "DIAMONDS";
		} else if (suit.equals("CLUBS") || suit.equals("clubs")) {
			playerHand = new Deck(Card.Suit.CLUBS);
			playerHandSuit = "CLUBS";
		}
	}

	public Deck getPlayerHand() {
		//FOR TESTING
		//System.out.println(playerHand);
		return playerHand;
	}

}