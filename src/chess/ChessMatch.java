package src.chess;

import src.board.Board;
import src.board.Piece;
import src.board.Position;
import src.chess.pieces.King;
import src.chess.pieces.Rook;

public class ChessMatch {
    private Board board;

    public ChessMatch() {
        board = new Board(8,8);

        initialSetup();
    }

    public ChessPiece[][] getPieces() {
        ChessPiece[][] matrix = new ChessPiece[board.getRows()][board.getColumns()];

        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                matrix[i][j] = (ChessPiece) board.piece(i, j);
            }
        }

        return matrix;
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();

        validateSourcePosition(source);
        validateTargetPosition(source, target);

        Piece capturedPiece = makeMove(source, target);

        return (ChessPiece) capturedPiece;
    }

    public Piece makeMove(Position source, Position target) {
        Piece sourcePiece = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);

        board.placePiece(sourcePiece, target);

        return capturedPiece;
    }

    public void validateTargetPosition(Position source, Position target) {
        ChessPiece sourcePiece = (ChessPiece) this.board.piece(source);
        boolean possibleMove = sourcePiece.possibleMove(target);

        if (!possibleMove) {
            throw new ChessException("Chess error: target position is not one possible move");
        }
    }

    public void validateSourcePosition(Position sourcePosition) {
        if (!board.thereIsAPiece(sourcePosition)) {
            throw new ChessException("Chess error: there is not a piece in source position");
        }

        if(!board.piece(sourcePosition).isThereAnyPossibleMove()) {
            throw new ChessException("Chess error: there are no possible moves for this piece");
        }
    }

    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(row, column).toPosition());
    }

    public void initialSetup() {
        placeNewPiece('b', 6, new Rook(board, Color.WHITE));
        placeNewPiece('b', 7, new King(board, Color.BLACK));
    }
}
