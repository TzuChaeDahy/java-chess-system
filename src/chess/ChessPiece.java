package src.chess;

import src.board.Board;
import src.board.Piece;
import src.board.Position;

public abstract class ChessPiece extends Piece {
    private final Color color;
    protected int moveCount;

    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void increaseMoveCount() {
        moveCount++;
    }

    public void decreaseMoveCount() {
        moveCount--;
    }

    public Color getColor() {
        return color;
    }

    public ChessPosition getChessPosition() {
        return ChessPosition.fromPosition(position);
    }

    protected boolean isThereOpponentPiece(Position position) {
        ChessPiece piece = (ChessPiece) this.getBoard().piece(position);

        return piece != null && piece.getColor() != this.getColor();
    }
}
