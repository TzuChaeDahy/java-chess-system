package src.chess.pieces;

import src.board.Board;
import src.board.Position;
import src.chess.ChessPiece;
import src.chess.Color;

public class Pawn extends ChessPiece {

    public Pawn(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "P";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position aux = new Position(0, 0);

        if (getColor() == Color.BLACK) {
            aux.setPositionValues(position.getRow() + 1, position.getColumn());
            if (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
                matrix[aux.getRow()][aux.getColumn()] = true;
            }

            if (this.getMoveCount() == 0 && !this.getBoard().thereIsAPiece(aux)) {
                aux.setPositionValues(position.getRow() + 2, position.getColumn());
                if (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
                    matrix[aux.getRow()][aux.getColumn()] = true;
                }
            }

            aux.setPositionValues(position.getRow() + 1, position.getColumn() - 1);
            if (getBoard().positionExists(aux) && getBoard().thereIsAPiece(aux)) {
                ChessPiece piece = (ChessPiece) getBoard().piece(aux);
                if (piece != null && piece.getColor() != Color.BLACK){
                    matrix[aux.getRow()][aux.getColumn()] = true;
                }
            }

            aux.setPositionValues(position.getRow() + 1, position.getColumn() + 1);
            if (getBoard().positionExists(aux) && getBoard().thereIsAPiece(aux)) {
                ChessPiece piece = (ChessPiece) getBoard().piece(aux);
                if (piece != null && piece.getColor() != Color.BLACK){
                    matrix[aux.getRow()][aux.getColumn()] = true;
                }
            }
        } else {
            aux.setPositionValues(position.getRow() - 1, position.getColumn());
            if (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
                matrix[aux.getRow()][aux.getColumn()] = true;
            }

            if (this.getMoveCount() == 0 && !this.getBoard().thereIsAPiece(aux)) {
                aux.setPositionValues(aux.getRow() - 1, aux.getColumn());
                if (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
                    matrix[aux.getRow()][aux.getColumn()] = true;
                }
            }

            aux.setPositionValues(position.getRow() - 1, position.getColumn() - 1);
            if (getBoard().positionExists(aux) && getBoard().thereIsAPiece(aux)) {
                ChessPiece piece = (ChessPiece) getBoard().piece(aux);
                if (piece != null && piece.getColor() != Color.WHITE){
                    matrix[aux.getRow()][aux.getColumn()] = true;
                }
            }

            aux.setPositionValues(position.getRow() - 1, position.getColumn() + 1);
            if (getBoard().positionExists(aux) && getBoard().thereIsAPiece(aux)) {
                ChessPiece piece = (ChessPiece) getBoard().piece(aux);
                if (piece != null && piece.getColor() != Color.WHITE){
                    matrix[aux.getRow()][aux.getColumn()] = true;
                }
            }
        }

        return matrix;
    }
}
