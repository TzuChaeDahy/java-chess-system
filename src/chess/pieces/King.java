package src.chess.pieces;

import src.board.Board;
import src.board.Position;
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

    private boolean canMove(Position position) {
        ChessPiece chessPiece = (ChessPiece) this.getBoard().piece(position);
        return chessPiece == null || chessPiece.getColor() != this.getColor();
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position aux = new Position(0,0);

        aux.setPositionValues(this.position.getRow() + 1, this.position.getColumn());
        if (this.getBoard().positionExists(aux) && this.canMove(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
        }

        aux.setPositionValues(this.position.getRow() - 1, this.position.getColumn());
        if (this.getBoard().positionExists(aux) && this.canMove(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
        }

        aux.setPositionValues(this.position.getRow(), this.position.getColumn() + 1);
        if (this.getBoard().positionExists(aux) && this.canMove(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
        }

        aux.setPositionValues(this.position.getRow(), this.position.getColumn() - 1);
        if (this.getBoard().positionExists(aux) && this.canMove(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
        }

        aux.setPositionValues(this.position.getRow() + 1, this.position.getColumn() + 1);
        if (this.getBoard().positionExists(aux) && this.canMove(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
        }

        aux.setPositionValues(this.position.getRow() - 1, this.position.getColumn() + 1);
        if (this.getBoard().positionExists(aux) && this.canMove(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
        }

        aux.setPositionValues(this.position.getRow() + 1, this.position.getColumn() - 1);
        if (this.getBoard().positionExists(aux) && this.canMove(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
        }

        aux.setPositionValues(this.position.getRow() - 1, this.position.getColumn() - 1);
        if (this.getBoard().positionExists(aux) && this.canMove(aux)) {
            matrix[aux.getRow()][aux.getColumn()] = true;
        }

        return matrix;
    }
}
