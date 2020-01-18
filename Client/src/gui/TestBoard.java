package gui;


import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Ismail_khadr
 */
public class TestBoard {

    public static  String No_player = "-";
    public static  String PLAYER_X = "X";
    public static  String PLAYER_O = "O";
    public  String value="" ;
 
    public String[][] board = new String[3][3];
    public static String[][] board2 = new String[3][3];
    public Point computerMove;
    
    public TestBoard() {
    	for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
            	board[i][j] = "-";
            }
        }
    }
    
    public void resetBoard() {
    	for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
            	board[i][j] = "-";
            }
        }
    }
    
    public boolean isGameOver() {
        return hasPlayerWon(PLAYER_X) || hasPlayerWon(PLAYER_O) || getAvailableCells().isEmpty();
    }
    
    public boolean hasPlayerWon(String player) {
        //check for diagonal
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == player)
                || ((board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == player))) {
            return true;
        }
        //check for rows and column
        for (int i = 0; i < 3; i++) {

            if ((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == player)
                    || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == player)) {
                return true;
            }

        }
        return false;
    }

    public List<Point> getAvailableCells() {
        List<Point> availableCells = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (board[i][j] == No_player) {
                    availableCells.add(new Point(i, j));
                }

            }

        }

        return availableCells;
    }

    public boolean placeMove(Point point, String playerX) {
        //if the user enter nuber not 1 or 2
        if (board[point.x][point.y] != No_player) {
            return false;
        }
        board[point.x][point.y] = playerX;
        return true;
    }

    public void displayBoard() {
        System.out.println();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                 value = "?";
                if (board[i][j] == PLAYER_X) {
                    value = "x";
                } else if (board[i][j] == PLAYER_O) {
                    value = "O";
                }

               System.out.print(value + " ");
               board2[i][j] = value;

            }
            System.out.println();

        }
        System.out.println();

    }

    public int minimax(int depth, String turn) {
        if (hasPlayerWon(PLAYER_X)) {
            return 1;
        }
        if (hasPlayerWon(PLAYER_O)) {
            return -1;
        }
        List<Point> availableCells = getAvailableCells();
        if (availableCells.isEmpty()) {
            //System.out.println(availableCells.size() +"available size");
            return 0;
        }
        int min = Integer.MAX_VALUE;
        //System.out.println( min+ "   min = Integer.MAX_VALUE");
        int max = Integer.MIN_VALUE;
        //System.out.println(max +" max= Integer.min_VALUE");
        for (int i = 0; i < availableCells.size(); i++) {
            Point point = availableCells.get(i);
            if (turn == PLAYER_X) {
                placeMove(point, PLAYER_X);
                int currentScore = minimax(depth + 1, PLAYER_O);
                max = Math.max(currentScore, max);
                if (depth == 0) {
                    System.out.println("Computer Score for position " + point + " = " + currentScore);
                }
                if (currentScore >= 0) {
                    if (depth == 0) {
                        computerMove = point;
                    }
                }
                
                if (currentScore == 1) {
                    board[point.x][point.y] = No_player;
                    break;
                }
                if(i == availableCells.size() - 1 && max < 0)
                    if(depth == 0)
                        computerMove = point;
                
            } 
            else if (turn == PLAYER_O) {
                placeMove(point, PLAYER_O);
                int currentScore = minimax(depth+1, PLAYER_X);
                min = Math.min(currentScore, min);
                if(min == -1){
                 board[point.x][point.y]=No_player;
                 break;
                }
                
            }
             board [point.x][point.y]=No_player;
        }

        return turn == PLAYER_X ? max : min ;
    }
    
    public Point getComputerMove(int depth, String turn) {
    	minimax(depth, turn);
    	return computerMove;
    }
    
    public void printBoard() {
    	for(int i = 0; i < 3; i++) {
    		for(int j = 0; j < 3; j++) {
    			System.out.println(i + " " + j + " " + board[i][j]);
    		}
    	}
    }

//    private Point Point(int i, int j) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

}
