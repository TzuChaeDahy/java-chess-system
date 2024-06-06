package src.application;

import src.chess.ChessPiece;
import src.chess.ChessPosition;
import src.chess.Color;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static ChessPosition readChessPosition(Scanner scanner) {
        try {
            String position = scanner.next();
            char column = position.charAt(0);
            int row = Integer.parseInt(position.substring(1));

            return new ChessPosition(row, column);
        } catch (RuntimeException e) {
            throw new InputMismatchException("Input error: input must be between a1 and h8");
        }
    }

    public static void printBoard(ChessPiece[][] pieces) {
        for (int i = 0; i < pieces.length; i++) {
            System.out.printf("%d ", 8 - i);
            for (int j = 0; j < pieces[i].length; j++) {
                printPiece(pieces[i][j], false);
            }
            System.out.println();
        }
        System.out.print("  a b c d e f g h");
    }

    public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
        for (int i = 0; i < pieces.length; i++) {
            System.out.printf("%d ", 8 - i);
            for (int j = 0; j < pieces[i].length; j++) {
                printPiece(pieces[i][j], possibleMoves[i][j]);
            }
            System.out.println();
        }
        System.out.print("  a b c d e f g h");
    }

    public static void printPiece(ChessPiece piece, boolean possibleMove) {
        if (possibleMove) {
            System.out.print(ANSI_BLUE_BACKGROUND);
        }
        if (piece == null) {
            System.out.print("-" + ANSI_RESET);
        } else if (piece.getColor() == Color.WHITE){
            System.out.print(ANSI_WHITE + piece + ANSI_RESET);
        } else {
            System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
        }

        System.out.print(" ");
    }
}
