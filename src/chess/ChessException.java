package src.chess;

import src.board.BoardException;

public class ChessException extends BoardException {
    public ChessException(String message) {
        super(message);
    }
}
