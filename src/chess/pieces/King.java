package src.chess.pieces;

import src.board.Board;
import src.chess.ChessPiece;
import src.chess.Color;

public class King extends ChessPiece {
    public King(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "K";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];
        // TODO: IMPLEMENT KING POSSIBLE MOVES LOGIC

        return matrix;
    }
}
