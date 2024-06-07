package src.chess.pieces;

import src.board.Board;
import src.board.Position;
import src.chess.ChessPiece;
import src.chess.Color;

public class Knight extends ChessPiece {
    public Knight(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "N";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position aux = new Position(0, 0);

        aux.setPositionValues(position.getRow() + 2, position.getColumn() + 1);
        if (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
        }
        if (this.getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
        }

        aux.setPositionValues(position.getRow() + 2, position.getColumn() - 1);
        if (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
        }
        if (this.getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
        }

        aux.setPositionValues(position.getRow() - 2, position.getColumn() + 1);
        if (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
        }
        if (this.getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
        }

        aux.setPositionValues(position.getRow() - 2, position.getColumn() - 1);
        if (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
        }
        if (this.getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
        }

        aux.setPositionValues(position.getRow() + 1, position.getColumn() + 2);
        if (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
        }
        if (this.getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
        }

        aux.setPositionValues(position.getRow() - 1, position.getColumn() + 2);
        if (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
        }
        if (this.getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
        }

        aux.setPositionValues(position.getRow() + 1, position.getColumn() - 2);
        if (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
        }
        if (this.getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
        }

        aux.setPositionValues(position.getRow() - 1, position.getColumn() - 2);
        if (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
        }
        if (this.getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
        }

        return matrix;
    }
}
