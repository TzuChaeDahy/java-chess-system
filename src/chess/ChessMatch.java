package src.chess;

import src.board.Board;
import src.board.Piece;
import src.board.Position;
import src.chess.pieces.King;
import src.chess.pieces.Rook;

import java.util.ArrayList;
import java.util.List;

public class ChessMatch {
    private final Board board;
    private int turn;
    private Color currentPlayer;

    private final List<ChessPiece> piecesOnTheBoard = new ArrayList<>();
    private final List<ChessPiece> capturedPieces = new ArrayList<>();

    public ChessMatch() {
        board = new Board(8,8);
        turn = 1;
        currentPlayer = Color.WHITE;

        initialSetup();
    }

    public int getTurn() {
        return this.turn;
    }

    public Color getCurrentPlayer() {
        return this.currentPlayer;
    }

    public void nextTurn() {
        this.turn++;
        this.currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
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

    public boolean[][] possibleMoves(ChessPosition source) {
        validateSourcePosition(source.toPosition());
        return board.piece(source.toPosition()).possibleMoves();
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();

        validateSourcePosition(source);
        validateTargetPosition(source, target);

        Piece capturedPiece = makeMove(source, target);

        nextTurn();
        return (ChessPiece) capturedPiece;
    }

    public Piece makeMove(Position source, Position target) {
        Piece sourcePiece = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);

        board.placePiece(sourcePiece, target);

        if (capturedPiece != null) {
            piecesOnTheBoard.remove((ChessPiece) capturedPiece);
            capturedPieces.add((ChessPiece) capturedPiece);
        }

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

        ChessPiece piece = (ChessPiece) this.board.piece(sourcePosition);
        if ((piece.getColor() != currentPlayer)) {
            throw new ChessException("Chess error: The piece selected is not from the current player");
        }

        if(!board.piece(sourcePosition).isThereAnyPossibleMove()) {
            throw new ChessException("Chess error: there are no possible moves for this piece");
        }
    }

    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(row, column).toPosition());
        piecesOnTheBoard.add(piece);
    }

    public void initialSetup() {
        placeNewPiece('a', 2, new Rook(board, Color.WHITE));
        placeNewPiece('b', 8, new Rook(board, Color.BLACK));
        placeNewPiece('b', 7, new Rook(board, Color.BLACK));
        placeNewPiece('a', 7, new Rook(board, Color.BLACK));
        placeNewPiece('a', 8, new King(board, Color.BLACK));
    }
}
