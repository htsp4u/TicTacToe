
/**
 * Write a description of class TicTacToe here.
 * 
 * @author Cory 
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.Scanner;
import java.io.*;

//class TicTacToe
public class TicTacToe {
    private static final String HUMAN = "X";
    private static final String COMPUTER = "O";
	private static int turn = 0;


    private static final String EMPTY = "·";
    private static String winner = "·";

    // Let us assume that the Computer will move with 'O' and human with 'X'
    //private static char COMPUTERMOVE = 'O';
    //private static char HUMANMOVE = 'X';

    private static String[][] board;

    //function to create board of given size

    public TicTacToe(int boardSize) 
    {
        board = new String[boardSize][boardSize];
        for (int x = 0; x < boardSize; x++)
        {
            for (int y = 0; y < boardSize; y++) 
            {
                board[x][y] = EMPTY;
            }
        }
        showInstructions();
        play();
    }

    // A function to show the current board status

    static public void printBoard()
    {
        for (int x = 0; x < board.length; x++)
        {
            for (int y = 0; y < board[x].length; y++)
            {
                if(y == 0)
                {
                    System.out.print("  ");
                }
                System.out.print(board[x][y]);
                if(y < 2)
                {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if(x < 2)
            {
                System.out.println(" ---|---|---");
            }

        }
    }

    // A function to show the instructions
    public static void showInstructions()
    {
        System.out.print("\t\t\t Tic-Tac-Toe\n\n");
        System.out.println("Choose a cell numbered from 1 to 9 as below and play\n\n");

        System.out.println("\t\t\t 1 | 2 | 3 \n");
        System.out.println("\t\t\t--------------\n");
        System.out.println("\t\t\t 4 | 5 | 6 \n");
        System.out.println("\t\t\t--------------\n");
        System.out.println("\t\t\t 7 | 8 | 9 \n\n");

        System.out.println("-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n");
    }

    static public void play()
    {
        Scanner scan = new Scanner(System.in);
        Random generator = new Random();

        int move;
        boolean win;

        printBoard();
        System.out.println();        

        do {

            if(turn%2 == 0)
            {
                System.out.println("Player 1, place your X");
                System.out.print("Make a Move: ");
                move = scan.nextInt();
            }
            else
            {
                System.out.print("Computer, place your O: ");
                move = generator.nextInt(10);
                System.out.println(move);
            }

            if(move == 0 || move > (board.length*board.length)){
                System.out.println("That is out of bounds, please try again");
                play();
            }
            move--;
            getMove(move);
            System.out.println();
            printBoard();
            System.out.println();
            win = winner();
        } while(!win && turn < 9);

        if (win)
        {
            if((turn-1)%2 == 0){
                winner = "Human";
            }
            else{
                winner = "Computer";
            }
            System.out.println(winner + " WINS!!");

        }
        else
        {
            System.out.println("The Game is a DRAW!");
        }

    }

    /*static public void playHuman()
    {
        Scanner scan = new Scanner(System.in);
        printBoard();
        System.out.println();        

        do {

            if(turn%2 == 0)
            {
                System.out.println("Player 1, place your X");
            }
            else
            {
                System.out.println("Player 2, place your O");
            }

            System.out.print("Make a Move: ");
            int move = scan.nextInt();
            getMove(move);
            System.out.println();
            printBoard();
            System.out.println();            
        } while(winner().equals(EMPTY) && turn < 9);

        if (winner().equals(EMPTY))
        {
            System.out.println("The Game is a DRAW!");
        }
        else
        {
            System.out.println("PLAYER " + ((turn - 1) % 2 + 1) + " WINS!!");
        }

    }*/

    static public void getMove(int move)
    {
        int row = move/board.length;
        int col = move%board[row].length;

        if (board[row][col].equals(EMPTY)) 
        {
            if(turn%2 == 0)
            {
                board[row][col] = (HUMAN);
            }
            else
            {
                board[row][col] = (COMPUTER);
            }
             // ? PLAYER1 : PLAYER2);
            turn++;
        }
        else
        {
            System.out.println("FIELD is already OCCUPIED!");
            play();
        }
    }

    static public boolean winner()
    {
        for (int i = 0; i < board.length; i++)
        {   /* check horizontally */

            int n=board.length;

            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n-2; k++) {
                    if (board[j][k].equals(board[j][k+1]) && board[k][j].equals(board[j][k+2])) {
                        if (board[j][k].equals(EMPTY))
                        {
                            return false; //has to return bool, same for next 2 errors
                        }
                        return true;
                    }
                }
            }

            /* check vertically */

            for (int k = 0; k < n; k++) {
                for (int j = 0; j < n-2; j++) {
                    if (board[j][k].equals(board[j+1][k]) && board[j][k].equals(board[j+2][k])) {
                        if (board[j][k].equals(EMPTY))
                        {
                            return false; //has to return bool, same for next 2 errors
                        }
                        return true;
                    }
                }
            }

            /* check diagonally */

            for (int j = 0; j < n-2; j++) {
                for (int k = 0; k < n-2; k++) {

                    if (board[j][k].equals(board[j+1][k+1]) && board[j][k].equals(board[j+2][k+2]))
                    {
                        if (board[j][k].equals(EMPTY))
                        {
                            return false; //has to return bool, same for next 2 errors
                        }
                        return true;
                    }

                    if (board[j][k+2].equals(board[j+1][k+1]) && board[j][k+2].equals(board[j+2][k]))
                    {
                        if (board[j][k+2].equals(EMPTY))
                        {
                            return false;
                        }
                        return true;
                    }

                    //return false;
                }

            }
        }
        return false;
    }
}

