package src.chess;

import src.board.Position;

public class ChessPosition {
    private int row;
    private char column;

    public ChessPosition(int row, char column) {
        if (row < 1 || row > 8 || column < 'a' || column > 'h') {
            throw new ChessException("Chess error: position must be within the rang of a1 to h8");
        }

        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return this.row;
    }

    public char getColumn() {
        return this.column;
    }

    protected Position toPosition() {
        int convertedRow = 8 - this.row;
        int convertedColumn = this.column - 'a';

        return new Position(convertedRow, convertedColumn);
    }

    // 3 2

    protected static ChessPosition fromPosition(Position position) {
        int convertedRow = 8 - position.getRow();
        char convertedColumn = (char) ('a' - position.getColumn());

        return new ChessPosition(convertedRow, convertedColumn);
    }

    @Override
    public String toString() {
        return String.format("%s%d", this.column, this.row);
    }
}
