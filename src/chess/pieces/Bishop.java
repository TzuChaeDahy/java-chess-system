package src.chess.pieces;

import src.board.Board;
import src.board.Position;
import src.chess.ChessPiece;
import src.chess.Color;

public class Bishop extends ChessPiece {

    public Bishop(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "B";
    }

    public boolean[][] possibleMoves() {
        boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position aux = new Position(0, 0);

        aux.setPositionValues(position.getRow() - 1, position.getColumn() - 1);
        while (this.getBoard().positionExists(aux) && !this.getBoard().thereIsAPiece(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
            aux.setPositionValues(aux.getRow() - 1, aux.getColumn() - 1);
        }
        if (this.getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
        }

        aux.setPositionValues(position.getRow() + 1, position.getColumn() + 1);
        while (this.getBoard().positionExists(aux) && !this.getBoard().thereIsAPiece(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
            aux.setPositionValues(aux.getRow() + 1, aux.getColumn() + 1);
        }
        if (this.getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
        }

        aux.setPositionValues(position.getRow() - 1, position.getColumn() + 1);
        while (this.getBoard().positionExists(aux) && !this.getBoard().thereIsAPiece(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
            aux.setPositionValues(aux.getRow() -1, aux.getColumn() + 1);
        }
        if (this.getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
        }

        aux.setPositionValues(position.getRow() + 1, position.getColumn() - 1);
        while (this.getBoard().positionExists(aux) && !this.getBoard().thereIsAPiece(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
            aux.setPositionValues(aux.getRow() + 1, aux.getColumn() - 1);
        }
        if (this.getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
        }

        return matrix;
    }
}
