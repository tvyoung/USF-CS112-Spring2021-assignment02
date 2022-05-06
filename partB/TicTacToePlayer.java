public class TicTacToePlayer extends Player {
	private XOPiece playerPiece;

	public TicTacToePlayer(String name) {
		super(name);
	}

	public void setPlayerPiece(char piece) {
		if (piece == 'X' || piece == 'x') {
			playerPiece = new XOPiece(XOPiece.XO.X);
		} else if (piece == 'O' || piece == 'o') {
			playerPiece = new XOPiece(XOPiece.XO.O);
		}
	}

	public XOPiece getPlayerPiece() {
		return playerPiece;
	}

}