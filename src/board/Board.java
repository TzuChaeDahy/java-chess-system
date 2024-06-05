package src.board;

public class Board {
    private int rows;
    private int columns;

    private Piece[][] pieces;

    public Board(int rows, int columns) {
        if (rows < 1 || columns < 1) {
            throw new BoardException("Board error: there must be at least 1 row and 1 column");
        }

        this.rows = rows;
        this.columns = columns;

        this.pieces = new Piece[rows][columns];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Piece piece(int row, int column) {
        if (!positionExists(row, column)){
            throw new BoardException("Board error: position not in the board");
        }

        return this.pieces[row][column];
    }

    public Piece piece(Position position) {
        if (!positionExists(position)){
            throw new BoardException("Board error: position not in the board");
        }
        return this.pieces[position.getRow()][position.getColumn()];
    }

    public void placePiece(Piece piece, Position position) {
        if (thereIsAPiece(position)) {
            throw new BoardException(String.format("Board error: there is already a piece in position: (%d, %d)", position.getRow(), position.getColumn()));
        }
        piece.position = position;
        pieces[position.getRow()][position.getColumn()] = piece;
    }

    public boolean thereIsAPiece(Position position) {
        if (!positionExists(position)) {
            throw new BoardException("Board error: position not in the board");
        }
        return piece(position) != null;
    }

    public boolean positionExists(int row, int column) {
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }

    public boolean positionExists(Position position) {
        return positionExists(position.getRow(), position.getColumn());
    }
}
