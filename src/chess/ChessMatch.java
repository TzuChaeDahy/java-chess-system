package src.chess;

import src.board.Board;
import src.board.Piece;
import src.board.Position;
import src.chess.pieces.*;

import java.util.ArrayList;
import java.util.List;

public class ChessMatch {
    private final Board board;
    private int turn;
    private Color currentPlayer;
    private boolean check;
    private boolean checkMate;
    private ChessPiece promoted;

    private final List<Piece> piecesOnTheBoard = new ArrayList<>();

    public ChessMatch() {
        board = new Board(8, 8);
        turn = 1;
        currentPlayer = Color.WHITE;
        check = false;

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

    public boolean getCheck() {
        return this.check;
    }

    public boolean isCheckMate() {
        return this.checkMate;
    }

    public ChessPiece getPromoted() {
        return promoted;
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

        if (testCheck(currentPlayer)) {
            undoMove(source, target, capturedPiece);

            throw new ChessException("Chess error: you cannot put yourself in check");
        }

        ChessPiece movedPiece = (ChessPiece) board.piece(target);
        promoted = null;

        if (movedPiece instanceof Pawn) {
            if (movedPiece.getColor() == Color.WHITE && target.getRow() == 0 || movedPiece.getColor() == Color.BLACK && target.getRow() == 7) {
                promoted = (ChessPiece) board.piece(target);

                promoted = replacePromotedPiece("Q");
            }
        }

        check = testCheck(opponent(currentPlayer));

        if (testCheckMate(opponent(currentPlayer))) {
            checkMate = true;
        } else {
            nextTurn();
        }

        return (ChessPiece) capturedPiece;
    }

    public ChessPiece replacePromotedPiece(String type) {
        if (promoted == null) {
            throw new IllegalStateException("There is no piece to be promoted");
        }

        if (!type.equals("B") && !type.equals("N") && !type.equals("Q") && !type.equals("R")) {
            return promoted;
        }

        Position promotedPosition = promoted.getChessPosition().toPosition();
        Piece promotedPiece = board.removePiece(promotedPosition);
        piecesOnTheBoard.remove(promotedPiece);

        ChessPiece newPiece = newPiece(type, promoted.getColor());
        board.placePiece(newPiece, promotedPosition);
        piecesOnTheBoard.add(newPiece);

        return newPiece;
    }

    public ChessPiece newPiece(String type, Color color) {
        if (type.equals("B")) return new Bishop(board, color);
        if (type.equals("N")) return new Knight(board, color);
        if (type.equals("Q")) return new Queen(board, color);

        return new Rook(board, color);
    }

    public void undoMove(Position source, Position target, Piece capturedPiece) {
        Piece piece = board.removePiece(target);
        ((ChessPiece) piece).decreaseMoveCount();
        board.placePiece(piece, source);

        if (capturedPiece != null) {
            board.placePiece(capturedPiece, target);

            piecesOnTheBoard.add(capturedPiece);
        }

        if (piece instanceof King && target.getColumn() == source.getColumn() + 2) {
            Position rookPosition = new Position(source.getRow(), source.getColumn() + 3);
            Position rookTarget = new Position(source.getRow(), source.getColumn() + 1);

            ChessPiece rook = (ChessPiece) board.removePiece(rookTarget);
            board.placePiece(rook, rookPosition);

            rook.decreaseMoveCount();
        }

        if (piece instanceof King && target.getColumn() == source.getColumn() - 2) {
            Position rookPosition = new Position(source.getRow(), source.getColumn() - 4);
            Position rookTarget = new Position(source.getRow(), source.getColumn() - 1);

            ChessPiece rook = (ChessPiece) board.removePiece(rookTarget);
            board.placePiece(rook, rookPosition);

            rook.decreaseMoveCount();
        }
    }

    public Piece makeMove(Position source, Position target) {
        Piece sourcePiece = board.removePiece(source);
        ((ChessPiece) sourcePiece).increaseMoveCount();

        Piece capturedPiece = board.removePiece(target);

        board.placePiece(sourcePiece, target);

        if (capturedPiece != null) {
            piecesOnTheBoard.remove(capturedPiece);
        }

        if (sourcePiece instanceof King && target.getColumn() == source.getColumn() + 2) {
            Position rookPosition = new Position(source.getRow(), source.getColumn() + 3);
            Position rookTarget = new Position(source.getRow(), source.getColumn() + 1);

            ChessPiece rook = (ChessPiece) board.removePiece(rookPosition);
            board.placePiece(rook, rookTarget);

            rook.increaseMoveCount();
        }

        if (sourcePiece instanceof King && target.getColumn() == source.getColumn() - 2) {
            Position rookPosition = new Position(source.getRow(), source.getColumn() - 4);
            Position rookTarget = new Position(source.getRow(), source.getColumn() - 1);

            ChessPiece rook = (ChessPiece) board.removePiece(rookPosition);
            board.placePiece(rook, rookTarget);

            rook.increaseMoveCount();
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

        if (!board.piece(sourcePosition).isThereAnyPossibleMove()) {
            throw new ChessException("Chess error: there are no possible moves for this piece");
        }
    }

    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(row, column).toPosition());
        piecesOnTheBoard.add(piece);
    }

    private Color opponent(Color color) {
        return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private ChessPiece king(Color color) {
        List<Piece> pieces = piecesOnTheBoard.stream().filter(piece -> ((ChessPiece) piece).getColor() == color).toList();

        for (Piece piece : pieces) {
            if (piece instanceof King) {
                return (ChessPiece) piece;
            }
        }

        throw new IllegalStateException("There is no king in the board");
    }

    private boolean testCheck(Color color) {
        Position kingPosition = king(color).getChessPosition().toPosition();

        List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(piece -> ((ChessPiece) piece).getColor() == opponent(color)).toList();

        for (Piece opponentPiece : opponentPieces) {
            if (opponentPiece.possibleMove(kingPosition)) {
                return true;
            }
        }

        return false;
    }

    private boolean testCheckMate(Color color) {
        if (!testCheck(color)) {
            return false;
        }

        List<Piece> pieces = piecesOnTheBoard.stream().filter(piece -> ((ChessPiece) piece).getColor() == color).toList();
        for (Piece piece : pieces) {
            boolean[][] matrix = piece.possibleMoves();

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j]) {
                        Position source = ((ChessPiece) piece).getChessPosition().toPosition();
                        Position target = new Position(i, j);

                        Piece capturedPiece = makeMove(source, target);
                        boolean testCheck = testCheck(color);
                        undoMove(source, target, capturedPiece);

                        if (!testCheck) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    public void initialSetup() {
        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('e', 6, new Pawn(board, Color.WHITE));
        placeNewPiece('b', 8, new Knight(board, Color.BLACK));
        placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('d', 5, new Queen(board, Color.BLACK));
        placeNewPiece('e', 8, new King(board, Color.BLACK, this));
        placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('g', 8, new Knight(board, Color.BLACK));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('b', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('c', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('d', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('e', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('f', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('g', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('h', 7, new Pawn(board, Color.BLACK));

        placeNewPiece('a', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('b', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('c', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('d', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('e', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('f', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('g', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('h', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('a', 1, new Rook(board, Color.WHITE));
        placeNewPiece('b', 1, new Knight(board, Color.WHITE));
        placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('d', 1, new Queen(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE, this));
        placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('g', 1, new Knight(board, Color.WHITE));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));
    }
}
