public class XOPiece extends Token
{
    /** enumerated type that dictates the two values of 
      * tic tac toe
      */
    public static enum XO {X, O}

    /** Constructor to hold an empty piece 
      * that is neither X or O. An empty 
      * piece should just display 3 spaces
      */
    public XOPiece() 
    {
        tokenValue = "   ";
    }

    /** Constructor that creates a piece for 
      * the TicTacToe board -- either an X or O
      * See sample output.
      */
    public XOPiece(XO piece)
    {
        tokenValue = "-" + piece + "-";
    }

    public String toString()
    {
        return tokenValue;
    }
}
