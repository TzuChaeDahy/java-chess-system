package src.chess.pieces;

import src.board.Board;
import src.board.Position;
import src.chess.ChessMatch;
import src.chess.ChessPiece;
import src.chess.Color;

public class King extends ChessPiece {
    private final ChessMatch chessMatch;

    public King(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public String toString() {
        return "K";
    }

    private boolean canMove(Position position) {
        ChessPiece chessPiece = (ChessPiece) this.getBoard().piece(position);
        return chessPiece == null || chessPiece.getColor() != this.getColor();
    }

    private boolean testRookCastling(Position position) {
        ChessPiece piece = (ChessPiece) this.getBoard().piece(position);
        return piece != null && piece.getColor() == this.getColor() && piece.getMoveCount() == 0 && piece instanceof Rook;
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position aux = new Position(0, 0);

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

        if (getMoveCount() == 0 && !chessMatch.getCheck()) {
            Position rightRook = new Position(position.getRow(), position.getColumn() + 3);
            if (testRookCastling(rightRook)) {
                boolean isRookCastlePossible = true;
                for (int i = 1; i < 3; i++) {
                    ChessPiece piece = (ChessPiece) this.getBoard().piece(this.position.getRow(), this.position.getColumn() - i);

                    if (piece != null) {
                        isRookCastlePossible = false;
                        break;
                    }
                }
                matrix[rightRook.getRow()][rightRook.getColumn() - 1] = isRookCastlePossible;
            }

            Position leftRook = new Position(position.getRow(), position.getColumn() - 4);
            if (testRookCastling(leftRook)) {
                boolean isRookCastlePossible = true;
                for (int i = 1; i < 4; i++) {
                    ChessPiece piece = (ChessPiece) this.getBoard().piece(this.position.getRow(), this.position.getColumn() - i);

                    if (piece != null) {
                        isRookCastlePossible = false;
                        break;
                    }
                }
                matrix[leftRook.getRow()][leftRook.getColumn() + 2] = isRookCastlePossible;
            }
        }

        return matrix;
    }
}
