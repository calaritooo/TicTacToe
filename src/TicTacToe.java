import java.util.Scanner;
import java.lang.String;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Tic-Tac-Toe!");

        boolean playAgain = true;

        while (playAgain) {
            char[][] board = new char[3][3];
            initializeBoard(board);
            char currentPlayer = 'X';

            while (true) {
                System.out.println(" ");
                printBoard(board);
                System.out.println(" ");
                System.out.println("Player " + currentPlayer + ", enter row and column (0 - 2): ");

                int[] position = getValidInput(scanner);
                int row = position[0];
                int col = position[1];

                if (placeMove(board, row, col, currentPlayer)) {
                    if (checkWinner(board, currentPlayer)) {
                        System.out.println("Player " + currentPlayer + " won!");
                        break;
                    }

                    if (isBoardFull(board)) {
                        System.out.println("The game is a draw!");
                        break;
                    }
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            }

            System.out.println("Would you like to play again? (y/n): ");
            String input = scanner.next();
            playAgain = input.equalsIgnoreCase("y");
        }

        System.out.println("Thanks for playing! Made by @calaritooo.");
    }

    public static void printBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
                if (j < board[i].length - 1) System.out.print(" | ");
            }
            System.out.println();
            if (i < board.length - 1) System.out.println("---------");
        }
    }

    public static boolean placeMove(char[][] board, int row, int col, char symbol) {
        if (row >= 0 && row < board.length && col >= 0 && col < board[row].length && board[row][col] == ' ') {
            board[row][col] = symbol;
            return true;
        }
        System.out.println("Invalid move. Try again!");
        return false;
    }

    public static boolean checkWinner(char[][] board, char symbol) {
        for (int row = 0; row < board.length; row++) {
            if (board[row][0] == symbol && board[row][1] == symbol && board[row][2] == symbol) {
                return true;
            }
        }
        for (int col = 0; col < board[0].length; col++) {
            if (board[0][col] == symbol && board[1][col] == symbol && board[2][col] == symbol) {
                return true;
            }
        }
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) {
            return true;
        }
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) {
            return true;
        }
        return false;
    }

    public static boolean isBoardFull(char[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void initializeBoard(char[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = ' ';
            }
        }
    }

    public static int[] getValidInput(Scanner scanner) {
        while (true) {
            String line = scanner.nextLine().trim();
            String[] numbers = line.split("[^0-9]+");
            if (numbers.length >= 2) {
                try {
                    int row = Integer.parseInt(numbers[0]);
                    int col = Integer.parseInt(numbers[1]);
                    if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
                        return new int[]{row, col};
                    } else {
                        System.out.println("Please enter a valid number between 0 and 2.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter integers for row and column.");
                }
            } else {
                System.out.println("Invalid input! Please enter two integers for row and column.");
            }
        }
    }
}