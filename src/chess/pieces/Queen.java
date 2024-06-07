package src.chess.pieces;

import src.board.Board;
import src.board.Position;
import src.chess.ChessPiece;
import src.chess.Color;

public class Queen extends ChessPiece {
    public Queen(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "Q";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position aux = new Position(0, 0);

        aux.setPositionValues(position.getRow() - 1, position.getColumn());
        while (this.getBoard().positionExists(aux) && !this.getBoard().thereIsAPiece(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
            aux.setPositionValues(aux.getRow() - 1, aux.getColumn());
        }
        if (this.getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
        }

        aux.setPositionValues(position.getRow() + 1, position.getColumn());
        while (this.getBoard().positionExists(aux) && !this.getBoard().thereIsAPiece(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
            aux.setPositionValues(aux.getRow() + 1, aux.getColumn());
        }
        if (this.getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
        }

        aux.setPositionValues(position.getRow(), position.getColumn() + 1);
        while (this.getBoard().positionExists(aux) && !this.getBoard().thereIsAPiece(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
            aux.setPositionValues(aux.getRow(), aux.getColumn() + 1);
        }
        if (this.getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
        }

        aux.setPositionValues(position.getRow(), position.getColumn() - 1);
        while (this.getBoard().positionExists(aux) && !this.getBoard().thereIsAPiece(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
            aux.setPositionValues(aux.getRow(), aux.getColumn() - 1);
        }
        if (this.getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
        }

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
