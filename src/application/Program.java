package src.application;

import src.chess.ChessException;
import src.chess.ChessMatch;
import src.chess.ChessPiece;
import src.chess.ChessPosition;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        List<ChessPiece> capturedPieces = new ArrayList<>();

        while (true) {
            try {
                UI.clearScreen();
                UI.printMatch(chessMatch, capturedPieces);

                System.out.println();
                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(scanner);

                boolean[][] possibleMoves = chessMatch.possibleMoves(source);

                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces(), possibleMoves);

                System.out.println();
                System.out.print("Target: ");
                ChessPosition target = UI.readChessPosition(scanner);

                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
                if (capturedPiece != null) {
                    capturedPieces.add(capturedPiece);
                }

            } catch (ChessException | InputMismatchException e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
                scanner.nextLine();
            }
        }
    }
}
