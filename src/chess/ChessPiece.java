package src.chess;

import src.board.Board;
import src.board.Piece;
import src.board.Position;

public abstract class ChessPiece extends Piece {
    private final Color color;

    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    protected boolean isThereOpponentPiece(Position position) {
        ChessPiece piece = (ChessPiece) this.getBoard().piece(position);

        return piece != null && piece.getColor() != this.getColor();
    }
}
