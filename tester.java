public class tester {
	public static void main(String[] args) {
		Card c1 = new Card(Card.Suit.HEARTS, Card.Face.ACE);
		System.out.println("Print Ace of Hearts card: " + c1); //prints 1:H
		System.out.println("Ace of Hearts card token value: " + c1.getTokenValue()); //prints H

		Card blank = new Card();
		System.out.println("\nPrint blank card: " + blank); //prints ---
		System.out.println("blank card token value: " + blank.getTokenValue()); //prints out ----

		Card c2 = new Card(Card.Suit.HEARTS, Card.Face.KING);
		System.out.println("\nPrint King of Hearts card: " + c2); //prints K:H
		System.out.println("King of Hearts card token value: " + c2.getTokenValue()); //prints H

		System.out.println("\nMatch 1:H and K:H token values: " + c1.match(c2)); //returns true
		System.out.println("Match 1:H and blank card token values: " + c1.match(blank)); //returns false

		Deck d = new Deck(Card.Suit.HEARTS);
		System.out.println("\nPrinting deck of hearts: " + d);

        SquareBoard currentBoard = new SquareBoard(4, new Card());
 
        System.out.println("\nHere are the board coordinates:");
        currentBoard.printBoardCoordinates();
        System.out.println("When prompted, enter coordinate numbers [row, column] to mark down a piece.");
        System.out.println("\nCurrent board:");
        System.out.println(currentBoard);

        currentBoard.setPiece(c1, 1, 1);
        currentBoard.setPiece(c2, 2, 2);
        System.out.println(currentBoard);    	

    	System.out.println("\nFOR TESTING:");

    	Token playerCard = currentBoard.getPiece(1,1);
    	System.out.println("Printing player card: " + playerCard);

        Card blankSpace = new Card();    	
    	System.out.println("Printing blankSpace card: " + blankSpace.getTokenValue());

    	boolean check = playerCard.match(blankSpace);
    	System.out.println("currentBoard at [1,1]: " + playerCard);
    	System.out.println("Current player move [1,1] match blank: " + check);  //returns false
    	check = currentBoard.getPiece(0,0).match(blankSpace);
    	System.out.println("currentBoard at [0,0]: " + currentBoard.getPiece(0,0));
    	System.out.println("[0,0] matches blank space: " + check); //returns true
    	check = playerCard.match(currentBoard.getPiece(2,2));
    	System.out.println("[1,1] matches [2,2]: " + check);

    	boolean result = playerCard.match(blankSpace);
    	if (!result) {
    		System.out.println("Space is not empty. Please choose different coordinates.");
    	}

    	System.out.println("\nCreating new player: Aly");
    	MashupPlayer player = new MashupPlayer("Aly");
    	player.setPlayerHand("SPADES");
    	System.out.println("Print Aly's cards: " + player.getPlayerHand());
    	System.out.println("Print Aly's hand suit: " + player.getPlayerHandSuit());
    	player.getPlayerHand().shuffle();
    	Card topCard = player.getPlayerHand().deal();
    	System.out.println("Aly's deck, top card dealt: " + topCard);
    	System.out.println("topCard's token value: " + topCard.getTokenValue());
    	//System.out.println("Aly's deck token value: " + player.getHandTokenValue()); //deleted method

	}
}