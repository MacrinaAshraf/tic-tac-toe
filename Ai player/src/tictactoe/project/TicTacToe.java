package tictactoe.project;

import java.util.Random;
import java.util.Scanner;
import tictactoe.project.Board;

public class TicTacToe {

    public static final Random RANDOM = new Random();

    public static void main(String[] args) {
        Board b = new Board();

        Scanner scanner = new Scanner(System.in);
        b.displayBoard();
        System.out.println("Select turn:\n1. Computer (x) / 2.User(O):  ");
        int choice =scanner.nextInt();
        if(choice==Board.PLAYER_X)
        {
         //random point
           Point p= new Point(RANDOM.nextInt(3),RANDOM.nextInt(3));
           b.placeMove(p,Board.PLAYER_X);
           b.displayBoard();
        }
        while (!b.isGameOver()) {
         boolean moveOk=true;
          do{
              if(!moveOk){
                  System.out.println("Cell already filled this cell is choosed");
              }
              System.out.println("Your Move :");
              Point userMove= new Point(scanner.nextInt(), scanner.nextInt());
              moveOk=b.placeMove(userMove, Board.PLAYER_O);
              
            }while(!moveOk);
           b.displayBoard();
           if(b.isGameOver())
               break;
           b.minimax(0, Board.PLAYER_X);
            b.placeMove(b.computerMove, Board.PLAYER_X);
            b.displayBoard();
            
        }
        if(b.hasPlayerWon(Board.PLAYER_X))
            System.out.println("You Lost !");
        else if(b.hasPlayerWon(Board.PLAYER_O))
            System.out.println("You Win !");
        else 
            System.out.println("draw !");
    }
   
}
