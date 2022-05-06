public class SquareBoard {
    private int size;
    private Token[][] board;

    /**
      * Constructor that creates an n x n board of
      * Tokens and each Token is set to an initial Token
      */ 
    public SquareBoard(int size, Token e) 
    {
        board = new Token[size][size];
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                board[row][column] = e;
            }
        }
    }

   /**
     * String representation of the board 
     * showing the contents of each space 
     */
    public String toString()
    {
        String text = "";
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                text += "[" + getPiece(row,column) + "] ";
            }
            text += "\n";
        }
        return text;
    }

    /**
      * Method that prints out the board with
      * the coordinates of each slot
      * to show the users how to identify each slot
      */
    public void printBoardCoordinates()
    {
        for (int i=0; i<board.length; i++) 
        {
            for (int j=0; j<board[i].length; j++)
            {
                System.out.print("["+i+","+j+"] ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
      * Sets a Token on the board with coordinates (i,j)
      */
    public boolean setPiece(Token t, int i, int j)
    {
        board[i][j] = t;
        return true;
    }

    /**
      * Gets a Token that is on the board with coordinates (i,j)
      */  
    public Token getPiece(int i, int j)
    {
        return board[i][j];
    }

}

