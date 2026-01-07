package com.copilot.tictactoe;

import java.util.Scanner;

public class TicTacToe {
    private final char[][] board = new char[3][3];
    private char currentPlayer = 'X';

    public TicTacToe() {
        initBoard();
    }

    private void initBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private void printBoard() {
        System.out.println("Current board:");
        for (int i = 0; i < 3; i++) {
            System.out.print(" ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) System.out.println("---+---+---");
        }
    }

    private boolean makeMove(int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2) return false;
        if (board[row][col] != ' ') return false;
        board[row][col] = currentPlayer;
        return true;
    }

    private boolean isWin() {
        // rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) return true;
        }
        // cols
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == currentPlayer && board[1][j] == currentPlayer && board[2][j] == currentPlayer) return true;
        }
        // diagonals
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) return true;
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) return true;
        return false;
    }

    private boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') return false;
            }
        }
        return true;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Tic-Tac-Toe (Console)");
        while (true) {
            printBoard();
            System.out.printf("Player %s, enter your move (row and column 1-3, separated by space): ", currentPlayer);
            int row, col;
            try {
                row = scanner.nextInt();
                col = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter two numbers between 1 and 3.");
                scanner.nextLine(); // clear
                continue;
            }
            row--; col--;
            if (!makeMove(row, col)) {
                System.out.println("Invalid move. Try again.");
                continue;
            }
            if (isWin()) {
                printBoard();
                System.out.printf("Player %s wins!\n", currentPlayer);
                break;
            }
            if (isFull()) {
                printBoard();
                System.out.println("It's a draw!");
                break;
            }
            switchPlayer();
        }
        scanner.close();
    }

    public static void main(String[] args) {
        new TicTacToe().play();
    }
}
