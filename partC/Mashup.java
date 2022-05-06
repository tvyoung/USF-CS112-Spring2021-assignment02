import java.util.*;
import java.lang.*;
public class Mashup 
{
	//count number of moves to determine when a game ends in a draw (no more moves can be made)
	private static int moveCount = 0;

	//print game instructions
	public static void printGameInstructions() {
        System.out.println("Welcome to the Mashup game of tic-tac-toe and cards. This is a game for two players, each having a hand of cards belonging to the same suit (spades, hearts, diamonds, or clubs).");
        System.out.println("The players will take turns placing down their cards in a 4x4 grid. The player who succeeds in placing four of their cards in a diagonal, horizontal, or vertical row is the winner.");
        System.out.println("The game will continue until someone wins or all spaces have been filled in, resulting in a draw.");
        System.out.println("Both players will share the terminal to play.\n");
	}

	//assign a group of cards (of one suit) to given player
	public static void assignCards(MashupPlayer player) {
		Scanner input = new Scanner(System.in);
		String givenChoice = "asdfgh";
		Exception invalidInput = new Exception("Invalid input.");
		while (!(givenChoice.equals("SPADES"))   && !(givenChoice.equals("spades"))   &&
			   !(givenChoice.equals("HEARTS"))   && !(givenChoice.equals("hearts"))   &&
			   !(givenChoice.equals("DIAMONDS")) && !(givenChoice.equals("diamonds")) && 
			   !(givenChoice.equals("CLUBS"))    && !(givenChoice.equals("clubs"))       ) {
			try {
				System.out.println("\nFor Player " + player.getName() + ", choose a suit of cards (SPADES, HEARTS, DIAMONDS, CLUBS):");
				givenChoice = input.nextLine();

				//if player input is not a valid suit name, throws exception
				if (!(givenChoice.equals("SPADES"))   && !(givenChoice.equals("spades"))   &&
			   		!(givenChoice.equals("HEARTS"))   && !(givenChoice.equals("hearts"))   &&
			   		!(givenChoice.equals("DIAMONDS")) && !(givenChoice.equals("diamonds")) && 
			   		!(givenChoice.equals("CLUBS"))    && !(givenChoice.equals("clubs"))       ) {
					throw invalidInput;
				}

			//if player entered something that isnt a suit name, the exception is caught here
			} catch (Exception e) {
				System.out.println("Not a valid input. Try again.");
				//input.nextLine(); //throw away current input
			}
		}
		player.setPlayerHand(givenChoice);
	}

	//view player records
	public static void viewRecords(MashupPlayer player) {
		System.out.println("\nPlayer name: " + player.getName());
		System.out.println("Player hand suit: " + player.getPlayerHandSuit());
		System.out.print("Player hand cards: " + player.getPlayerHand());
		System.out.println("Number of wins: " + player.getWins());
		System.out.println("Number of games played: " + player.getGames());
		//System.out.println(player);
	}

	//start a new game
	public static void newGame(MashupPlayer player1, MashupPlayer player2) {
		Scanner input = new Scanner(System.in);
		Deck player1Hand = player1.getPlayerHand();
		Deck player2Hand = player2.getPlayerHand();

		System.out.println("Shuffling cards . . .");
		input.nextLine();
		player1Hand.shuffle();
		player2Hand.shuffle();
		System.out.println("Cards shuffled.");

		//Display the board with coordinates for the players to understand how to identify each spot on the board.
        SquareBoard board = new SquareBoard(4, new Card());
        System.out.println("\nHere are the board coordinates:");
        board.printBoardCoordinates();
        System.out.println("When prompted, enter coordinate numbers [row, column] to mark down a piece.");
        System.out.println("\nCurrent board:");
        System.out.println(board);

        //While keeping track of whose turn it is, ask the current player for coordinates on the board to put down their piece.
        
        /*
        gameStatus:
        0 = ONGOING
        1 = WIN
        2 = DRAW

       	if gameStatus returns 0, keep looping game
    	if gameStatus returns 1 (someone has won) then break out of while loop and say who won
    	if gameStatus returns 2 (game ends in a draw) then break out of while loop and say game is a draw
    	*/
        int gameStatus = 0;
        int playerTurn = 1;

        /*
        System.out.println("Which player goes first?");
        System.out.println("Player 1: " + player1.getName());
        System.out.println("Player 2: " + player2.getName());
        System.out.println("Enter 1 or 2: ");
        playerTurn = input.nextInt();*/

        System.out.println("Player 1/" + player1.getName() + " will go first.");
        while (gameStatus == 0) {

        	//if it is player 1's turn, player 1 moves. next turn will be player 2's
        	if (playerTurn == 1) {
        		System.out.println("Player 1/" + player1.getName() + "'s turn.");
        		gameStatus = move(board, player1);
        		playerTurn = 2;
        	//else it is player 2's turn, so player 2 moves. next turn will be player 1's
        	} else {
        		System.out.println("Player 2/" + player2.getName() + "'s turn.");
        		gameStatus = move(board, player2);
        		playerTurn = 1;
        	}

        	System.out.println("\nBoard coordinates:");
        	board.printBoardCoordinates();

        	System.out.println("Current board:");
        	System.out.println(board);
        }

        //game ends with a WIN
        if (gameStatus == 1) {
        	//if playerTurn is 2, meaning player 1 made the last move and won
            //extra win message because Player.java won() method has a print line
        	if (playerTurn == 2) {
        		System.out.println("Player 1/" + player1.getName() + " wins!");
                player1.won();
                player2.lost();
        	//if playerTurn is 1, meaning player 2 made the last move and won
            //extra win message because Player.java won() method has a print line
        	} else if (playerTurn == 1) {
        		System.out.println("Player 2/" + player2.getName() + " wins!");
                player2.won();
                player1.lost();
        	}
        //game ends with a DRAW
        } else if (gameStatus == 2) {
        	System.out.println("Game is a draw.");
            player1.lost();
            player2.lost();
        }
	}

    //given player makes a move
    public static int move(SquareBoard currentBoard, MashupPlayer player) {
    	Scanner enter = new Scanner(System.in);
    	int boardSize = 4;
    	int column = 0;
        int row = 0;

        Exception invalidCoordinates = new Exception("Invalid coordinates.");
        boolean isFree = false;

        while (!isFree) {
        	try {
				System.out.print("Enter a row number: ");
        		row = enter.nextInt();
        		System.out.print("Enter a column number: ");
        		column = enter.nextInt();

        		//if row or column is not within boardSize range, throws exception
        		if (row > boardSize-1 || row < 0 || column > boardSize-1 || column < 0) {
        			throw invalidCoordinates;
        		}

        		//check if given coordinates correspond to a free space on the current board
        		isFree = checkFreeSpace(currentBoard, row, column);
        		//if given coordinates point to free space (return True), break out of while loop (and continue)
        		//if given coordinates don't point to free space (return False), continues while loop and asks for coordinates again

        	//if row or column is not an integer, the exception is caught here
        	} catch (InputMismatchException e) {
        		System.out.println("Not integer coordinates. Try again.");
        		enter.nextLine(); //throw away current input
        	//if row or column is out of boardSize range, the exception is caught here
        	} catch (Exception e) {
        		System.out.println("Invalid coordinates. Try again.");
        		enter.nextLine(); //throw away current input
        	}        	
        }

        //draws the top card from player's hand and removes it from player's hand
        Card topCard = player.getPlayerHand().deal();

        //place down topCard at player's given coordinates
        currentBoard.setPiece(topCard, row, column);

        //check if this move was a winning move
        int result = checkWin(currentBoard, row, column, player);
        return result;
    }

    //check if given player coordinates point to a free space on the current board
    public static boolean checkFreeSpace(SquareBoard currentBoard, int x, int y) {
    	Card blankSpace = new Card();
    	Token playerCard = currentBoard.getPiece(x,y); //the player's current card move location

    	/*
    	System.out.println("FOR TESTING:");
    	System.out.println("Printing blankSpace card: " + blankSpace.getTokenValue());
    	boolean check = playerCard.match(blankSpace);
    	System.out.println("Current player move [1,1] match blank: " + check);  //returns false
    	check = currentBoard.getPiece(0,0).match(blankSpace);
    	System.out.println("[0,0] matches blank space: " + check); //returns true
    	*/

    	boolean result = playerCard.match(blankSpace);
    	if (!result) {
    		System.out.println("Space is not empty. Please choose different coordinates.");
    	}
    	return result;
    }

    /*
    checkWin() returns 0, 1, or 2 
    0 = ONGOING
    1 = WIN
	2 = DRAW

    checks if anybody has won (if the Tokens in a row, column or diagonal all match)
    inherited match method (of the Token class) is invoked in checkWin when comparing elements on the board
    also check number of moves to see if there is a draw
    
    method is not original. method derived from here:
	https://stackoverflow.com/questions/1056316/algorithm-for-determining-tic-tac-toe-game-over

	checkWin() works by examining the most recently made move and whether or not it is a winning move. 
	uses the coordinates of the most recently made move and examines if the player wins horizontally, vertically, diagonally, or anti-diagonally
	also checks for if there is a draw (if the max number of moves have been made based on the size of the board)
	*/
    public static int checkWin(SquareBoard currentBoard, int x, int y, MashupPlayer player) {
    	int boardSize = 4;
    	Token playerCard = currentBoard.getPiece(x,y); //the player's current card as a token; its token value should be its suit

    	//check column (if win is horizontal)
        for (int column = 0; column < boardSize; column++) {
        	//if any token value/suit in row x does not equal to given player token value/suit, break out of for loop  
            if (!(currentBoard.getPiece(x, column).match(playerCard))) {
            	break;
            //if by the last column all token value/suits in row x equal given player token value/suit, return player win
            } if (column == boardSize-1) {
            	return 1;
            }   
        }

        //check row (if win is vertical)
        for (int row = 0; row < boardSize; row++) {
        	//if any token value/suit in column x does not equal to given player token value/suit, break out of for loop  
            if (!(currentBoard.getPiece(row, y).match(playerCard))) {
            	break;
            //if by the bottom row all token value/suits in column x equal given player token value/suit, return player win
            } if (row == boardSize-1) {
            	return 1;
            }   
        }

        //check diagonal (top left to bottom right)
        if (x == y) {
        	//check if currentMove is on a diagonal
        	for (int i = 0; i < boardSize; i++) {
        		//if any diagonal token value/suit does not equal to given player token value/suit, break out of for loop
        		if (!(currentBoard.getPiece(i, i).match(playerCard))) {
        			break;
        		//if by the last diagonal (bottom rightmost) all token value/suits equal given player token value/suit, return player win
        		} if (i == boardSize-1) {
        			return 1;
        		}
        	}
        }

        //check anti diagonal (top right to bottom left)
        if (x + y == boardSize-1) {
        	//check if currentMove is on an anti diagonal
        	for (int i = 0; i < boardSize; i++) {
        		//starting from the top right, if any anti diagonal token value/suit does not equal given player token value/suit, break out of for loop
        		if (!(currentBoard.getPiece(i,(boardSize-1)-i).match(playerCard))) {
					break;
				//if by the last diagonal (bottom leftmost) all token value/suits equal given player token value/suit, return player win
				} if (i == boardSize-1) {
					return 1;
				}
        	}
        }

        //check for a draw
        //(max number of moves that can be made is based on board size; if there is no win by max number of moves, automatic draw)
        moveCount++;
        if (moveCount == (Math.pow(boardSize,2))) {
        	return 2;
        }

        //if has not returned 1 (a player WIN) or 2 (game is a DRAW) then game is still ONGOING
        return 0;
    }


	public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        MashupPlayer player1;
        MashupPlayer player2;
        String player1HandSuit;
        String player2HandSuit;

        //Welcome and explain the game to the 2 users who will share the same terminal to play.
        printGameInstructions();

		//Ask each player for their name.
		System.out.println("Please enter Player 1's name:");
		String givenName = input.nextLine();
		player1 = new MashupPlayer(givenName);

		System.out.println("Please enter Player 2's name:");
		givenName = input.nextLine();
		player2 = new MashupPlayer(givenName);


		//Assign a hand of the same suit to each MashupPlayer.
		assignCards(player1);
		assignCards(player2);

		//String capitalizedChoice = Character.toUpperCase(givenChoice);
		//String lowercaseChoice = Character.toLowerCase(givenChoice);
		while (player1.getPlayerHandSuit().equals(player2.getPlayerHandSuit())) {
			System.out.println("\nThat suit is taken by Player 1. Player 2, please choose a different suit.");
			assignCards(player2);
		}

		player1HandSuit = player1.getPlayerHandSuit();
		player2HandSuit = player2.getPlayerHandSuit();

		/*
		FOR TESTING
		System.out.println("Player 1's hand suit: " + player1.getPlayerHandSuit());
		System.out.println("Player 1's hand: " + player1.getPlayerHand());
		System.out.println("Player 2's hand suit: " + player2.getPlayerHandSuit());
		System.out.println("Player 2's hand: " + player2.getPlayerHand());
		*/

        Scanner menuInput = new Scanner(System.in);
        int userChoice = 0;
        boolean gameMode = true;
        char repeatGame = 'y';
        int playerNumber = 0;

        //loops while gameMode = true
        do {
        	/*
			menu option
			1 new game
                - play again?
			2 view player records
				- select player
				- view number of games played and won
			3 quit
        	*/
            try {

        	System.out.println("\nMENU");
        	System.out.println("1: New game");
        	System.out.println("2: View player records");
        	System.out.println("3: Quit");
        	System.out.println("Choose (1, 2, or 3): ");
        	userChoice = menuInput.nextInt();

            //start a new game
			if (userChoice == 1) {

                //will continue to repeat new game as long as user enters 'y' or 'Y'
                while (repeatGame == 'y') {
                    //new game begins
				    System.out.println("\nNew game starting.");
				    newGame(player1, player2);
                    //after new game, resets moveCount and resets player 1 and player 2's hands
                    moveCount = 0;
                    player1.setPlayerHand(player1HandSuit);
                    player2.setPlayerHand(player2HandSuit);

                    //asks user if want to play again
                    System.out.println("\nPlay again? (y/n)");
                    repeatGame = input.nextLine().charAt(0);

                    //if user enters 'y' or 'Y', repeats the while loop
                    if (repeatGame == 'y' || repeatGame == 'Y') {
                        continue;
                    //if user enters 'n' or 'N', breaks out of the while loop + returns to menu
                    } else if (repeatGame == 'n' || repeatGame == 'N') {
                        break;
                    //if user enters some other char, breaks out of while loop anyways + returns to menu
                    } else {
                        System.out.println("Invalid choice.");
                        break;
                    }
                }
                //repeatGame reset to 'y' so new game can start again next time if userChoice is 1
                repeatGame = 'y';
                input.nextLine(); //throw away current input

            //view player records
			} else if (userChoice == 2) {
                //choose which player's records to view
				System.out.println("\nView which player's records?");
				System.out.println("Player 1: " + player1.getName());
				System.out.println("Player 2: " + player2.getName());
				System.out.println("Enter 1 or 2: ");
				playerNumber = input.nextInt();

                //view player 1's records
				if (playerNumber == 1) {
					viewRecords(player1);
                //view player 2's records
				} else if (playerNumber == 2) {
					viewRecords(player2);
                //if any other int is entered, invalid player number
				} else {
					System.out.println("Invalid player number.");
				}
                input.nextLine(); //throw away current input

            //quit game, gameMode is false, ends the while loop
			} else if (userChoice == 3) {
				System.out.println("Thanks for playing.");
				gameMode = false;
			}

            //if any invalid inputs are made in the menu selection, the exception is caught here
            } catch (InputMismatchException e) {
                System.out.println("\nInputMismatchException: Invalid input.");
                menuInput.nextLine(); //throw away current input
                input.nextLine(); //throw away current input
            }


        } while (gameMode);		






	}
}