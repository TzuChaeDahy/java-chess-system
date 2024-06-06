package src.application;

import src.chess.ChessException;
import src.chess.ChessMatch;
import src.chess.ChessPiece;
import src.chess.ChessPosition;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();

        while (true) {
            try {
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces());

                System.out.println();

                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(scanner);

                System.out.println();

                System.out.print("Target: ");
                ChessPosition target = UI.readChessPosition(scanner);

                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);

                System.out.println(capturedPiece);

            } catch (ChessException | InputMismatchException e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
                scanner.nextLine();
            }
        }
    }
}
