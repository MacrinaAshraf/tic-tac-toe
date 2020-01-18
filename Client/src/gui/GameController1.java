/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameController1 implements Initializable {

    Alert alert, alert2, alert3, alert4, alert5, alert6;
    public ArrayList<Integer> intarr = new ArrayList<>(9);
    public boolean flag = false;
    public static final Random RANDOM = new Random();
    private Stage stage;
    private TestBoard testBoard = new TestBoard();
    private String player = "";
    private Point position = new Point();
    private Boolean turnFlag = true;
    Client client;
    public String theFirstPlayer = "X";
    public int xCount = 0;
    public int oCount = 0;

    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button button9;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println(GridPane.getRowIndex(button1));
    }

    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }
//EventHandler<ActionEvent> reset = new EventHandler<ActionEvent>(){
//    
//};
    public void reset() {
        Button arr2[] = {button1, button2, button3, button4, button5, button6, button7, button8, button9};
        for (int i = 0; i < arr2.length ; i++) {
            arr2[i].setText("");
            
        }
        

    }
    public GameController1() {
        //vsComputerFlag = HomePageController.vsComputer;
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert2 = new Alert(Alert.AlertType.INFORMATION);
        alert3 = new Alert(Alert.AlertType.INFORMATION, "DRAW!", ButtonType.OK);
        alert4 = new Alert(Alert.AlertType.INFORMATION);
        alert5 = new Alert(Alert.AlertType.INFORMATION);
        alert6 = new Alert(Alert.AlertType.INFORMATION, "DRAW!", ButtonType.OK);
        for (int i = 0; i < 9; i++) {
            intarr.add(1);
        }

    }
    ////////////// multiplayerfunctions//////////////////////////////////////////

    public void choosePlayer() {
        if (theFirstPlayer.equalsIgnoreCase("X")) {
            theFirstPlayer = "O";

        } else {
            theFirstPlayer = "X";
        }
    }

    public void draw() {

        alert6.setTitle("TicTacToe");
        alert6.setHeaderText(null);
        alert6.setContentText("No one wins");
       if(alert6.showAndWait().get() == ButtonType.OK){
             reset(); 
       choosePlayer();}
        //alert6.show();
   
     //alert6.show();
      //reset(); 
      

    }

    public void setValues(int index) {
        intarr.set(index - 1, 0);
        if (!(intarr.contains(1)) && flag == false) {
            draw();

        }
    }

    public void winning() {
        String arr[] = {button1.getText(), button2.getText(), button3.getText(), button4.getText(), button5.getText(),
            button6.getText(), button7.getText(), button8.getText(), button9.getText()};
        if (arr[0].equals("X") && arr[1].equals("X") && arr[2].equals("X")
                || arr[3].equals("X") && arr[4].equals("X") && arr[5].equals("X")
                || arr[6].equals("X") && arr[7].equals("X") && arr[8].equals("X")
                || arr[0].equals("X") && arr[3].equals("X") && arr[6].equals("X")
                || arr[1].equals("X") && arr[4].equals("X") && arr[7].equals("X")
                || arr[2].equals("X") && arr[5].equals("X") && arr[8].equals("X")
                || arr[0].equals("X") && arr[4].equals("X") && arr[8].equals("X")
                || arr[2].equals("X") && arr[4].equals("X") && arr[6].equals("X")) {
            alert4.setTitle("TicTacToe");
            alert4.setHeaderText(null);
            alert4.setContentText("player X wins");
           if(alert4.showAndWait().get() == ButtonType.OK){
             reset(); 
           choosePlayer();}
            flag = true;
            
            //alert4.show();
            //reset();
            
        } else if (arr[0].equals("O") && arr[1].equals("O") && arr[2].equals("O")
                || arr[3].equals("O") && arr[4].equals("O") && arr[5].equals("O")
                || arr[6].equals("O") && arr[7].equals("O") && arr[8].equals("O")
                || arr[0].equals("O") && arr[3].equals("O") && arr[6].equals("O")
                || arr[1].equals("O") && arr[4].equals("O") && arr[7].equals("O")
                || arr[2].equals("O") && arr[5].equals("O") && arr[8].equals("O")
                || arr[0].equals("O") && arr[4].equals("O") && arr[8].equals("O")
                || arr[2].equals("O") && arr[4].equals("O") && arr[6].equals("O")) {
            alert5.setTitle("TicTacToe");
            alert5.setHeaderText(null);
            alert5.setContentText("player O wins");
           
//             Optional<ButtonType> result = alert.showAndWait();
//        if(!result.isPresent())
    // alert is exited, no button has been pressed.
         if(alert5.showAndWait().get() == ButtonType.OK){
             reset(); 
         choosePlayer();}
            flag = true;
            
           // alert5.show();
            //reset();
            
            
        }

    }
    ////////////////////////////////////////// Ai Player
    ////////////////////////////////////////// ///////////////////////////////////////////////

    public void assignNumber() {
        testBoard.printBoard();
        player = TestBoard.PLAYER_O;
    }

    public void playAgainstComputer() {
        testBoard.printBoard();

        if (turnFlag == false && !testBoard.isGameOver()) {
            position = testBoard.getComputerMove(0, TestBoard.PLAYER_X);
            testBoard.placeMove(position, TestBoard.PLAYER_X);
            placeComputerMove(position.x, position.y);
            turnFlag = true;
        }
        if (testBoard.isGameOver()) {
            gameOverAlert();
        }
    }

    public void placeComputerMove(int row, int column) {
        if (GridPane.getRowIndex(button1) == row && GridPane.getColumnIndex(button1) == column) {
            button1.setText("X");
            button1.setOnAction(null);
        } else if (GridPane.getRowIndex(button2) == row && GridPane.getColumnIndex(button2) == column) {
            button2.setText("X");
            button2.setOnAction(null);
        } else if (GridPane.getRowIndex(button3) == row && GridPane.getColumnIndex(button3) == column) {
            button3.setText("X");
            button3.setOnAction(null);
        } else if (GridPane.getRowIndex(button4) == row && GridPane.getColumnIndex(button4) == column) {
            button4.setText("X");
            button4.setOnAction(null);
        } else if (GridPane.getRowIndex(button5) == row && GridPane.getColumnIndex(button5) == column) {
            button5.setText("X");
            button5.setOnAction(null);
        } else if (GridPane.getRowIndex(button6) == row && GridPane.getColumnIndex(button6) == column) {
            button6.setText("X");
            button6.setOnAction(null);
        } else if (GridPane.getRowIndex(button7) == row && GridPane.getColumnIndex(button7) == column) {
            button7.setText("X");
            button7.setOnAction(null);
        } else if (GridPane.getRowIndex(button8) == row && GridPane.getColumnIndex(button8) == column) {
            button8.setText("X");
            button8.setOnAction(null);
        } else if (GridPane.getRowIndex(button9) == row && GridPane.getColumnIndex(button9) == column) {
            button9.setText("X");
            button9.setOnAction(null);
        }

    }

    private void gameOverAlert() {
        Parent homePageUI = null;
        Alert gameOverAlert ;
        if(TestBoard.checkWinner.equals("X")){
          gameOverAlert=  new Alert(AlertType.INFORMATION);
        gameOverAlert.setHeaderText("The Player X has won!");
        gameOverAlert.setContentText(null);
        gameOverAlert.showAndWait();
        reset();
        }
        else if(TestBoard.checkWinner.equals("O")){
        gameOverAlert=  new Alert(AlertType.INFORMATION);
        gameOverAlert.setHeaderText("The Player O has won!");
        gameOverAlert.setContentText(null);
        gameOverAlert.showAndWait();
        reset();}
        else if(TestBoard.checkWinner.equals("noWinner")){
        gameOverAlert=  new Alert(AlertType.INFORMATION);
        gameOverAlert.setHeaderText("Draw!");
        gameOverAlert.setContentText(null);
        gameOverAlert.showAndWait();
        reset();}
        // if()
        FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        try {
            homePageUI = homePageLoader.load();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        HomePageController homePageControl = (HomePageController) homePageLoader.getController();
        stage.setScene(new Scene(homePageUI));
        homePageControl.setActionHandler(stage, client);
    }
    //////////////////////////////// buttonAction intwo
    //////////////////////////////// cases///////////////////////////////////////

    @FXML
    private void btn1(ActionEvent event) {
        if (HomePageController.vsComputer == true) {
            if (player.equals(TestBoard.PLAYER_X)) {
                button1.setText(TestBoard.PLAYER_X);
            } else if (player.equals(TestBoard.PLAYER_O)) {
                button1.setText(TestBoard.PLAYER_O);
            }
            testBoard.board[0][0] = player;
            position.x = 0;
            position.y = 0;
            turnFlag = false;
            if (testBoard.isGameOver()) {
                gameOverAlert();
            } else {
                playAgainstComputer();
            }
        } else if (HomePageController.vsComputer == false) {

            button1.setText(theFirstPlayer);
            if (theFirstPlayer.equalsIgnoreCase("X")) {
                button1.setStyle("-fx-text-fill: red;");
            } else {
                button1.setStyle("-fx-text-fill: blue;");
            }
            choosePlayer();
            winning();
            setValues(1);
        }
        //button1.setOnAction(null);
    }

    @FXML
    private void btn2(ActionEvent event) {
        if (HomePageController.vsComputer == true) {
            if (player.equals(TestBoard.PLAYER_X)) {
                button2.setText(TestBoard.PLAYER_X);
            } else if (player.equals(TestBoard.PLAYER_O)) {
                button2.setText(TestBoard.PLAYER_O);
            }
            testBoard.board[0][1] = player;
            position.x = 0;
            position.y = 1;
            turnFlag = false;
            if (testBoard.isGameOver()) {
                gameOverAlert();
            } else {
                playAgainstComputer();
            }
            //button2.setOnAction(null);
        } else if (HomePageController.vsComputer == false) {
            button2.setText(theFirstPlayer);
            if (theFirstPlayer.equalsIgnoreCase("X")) {
                button2.setStyle("-fx-text-fill: red;");
            } else {
                button2.setStyle("-fx-text-fill: blue;");
            }
            choosePlayer();
            winning();
            setValues(2);
            //button2.setOnAction(null);
        }
         ////button2.setOnAction(null);
    }

    @FXML
    private void btn3(ActionEvent event) {
        if (HomePageController.vsComputer == true) {
            if (player.equals(TestBoard.PLAYER_X)) {
                button3.setText(TestBoard.PLAYER_X);
            } else if (player.equals(TestBoard.PLAYER_O)) {
                button3.setText(TestBoard.PLAYER_O);
            }
            testBoard.board[0][2] = player;
            position.x = 0;
            position.y = 2;
            turnFlag = false;
            if (testBoard.isGameOver()) {
                gameOverAlert();
            } else {
                playAgainstComputer();
            }
            //button3.setOnAction(null);
        } else if (HomePageController.vsComputer == false) {

            button3.setText(theFirstPlayer);
            if (theFirstPlayer.equalsIgnoreCase("X")) {
                button3.setStyle("-fx-text-fill: red;");
            } else {
                button3.setStyle("-fx-text-fill: blue;");
            }
            choosePlayer();
            winning();
            setValues(3);
        }
        //button3.setOnAction(null);
    }

    @FXML
    private void btn4(ActionEvent event) {
        if (HomePageController.vsComputer == true) {
            if (player.equals(TestBoard.PLAYER_X)) {
                button4.setText(TestBoard.PLAYER_X);
            } else if (player.equals(TestBoard.PLAYER_O)) {
                button4.setText(TestBoard.PLAYER_O);
            }
            testBoard.board[1][0] = player;
            position.x = 1;
            position.y = 0;
            turnFlag = false;
            if (testBoard.isGameOver()) {
                gameOverAlert();
            } else {
                playAgainstComputer();
            }
            //button4.setOnAction(null);
        } else if (HomePageController.vsComputer == false) {
            button4.setText(theFirstPlayer);
            if (theFirstPlayer.equalsIgnoreCase("X")) {
                button4.setStyle("-fx-text-fill: red;");
            } else {
                button4.setStyle("-fx-text-fill: blue;");
            }
            choosePlayer();
            winning();
            setValues(4);
        }
         //button4.setOnAction(null);
    }

    @FXML
    private void btn5(ActionEvent event) {
        if (HomePageController.vsComputer == true) {
            if (player.equals(TestBoard.PLAYER_X)) {
                button5.setText(TestBoard.PLAYER_X);
            } else if (player.equals(TestBoard.PLAYER_O)) {
                button5.setText(TestBoard.PLAYER_O);
            }
            testBoard.board[1][1] = player;
            position.x = 1;
            position.y = 1;
            turnFlag = false;
            if (testBoard.isGameOver()) {
                gameOverAlert();
            } else {
                playAgainstComputer();
            }
            //button5.setOnAction(null);
        } else if (HomePageController.vsComputer == false) {
            button5.setText(theFirstPlayer);
            if (theFirstPlayer.equalsIgnoreCase("X")) {
                button5.setStyle("-fx-text-fill: red;");
            } else {
                button5.setStyle("-fx-text-fill: blue;");
            }
            choosePlayer();
            winning();
            setValues(5);
        }
         //button5.setOnAction(null);
    }

    @FXML
    private void btn6(ActionEvent event) {
        if (HomePageController.vsComputer == true) {
            if (player.equals(TestBoard.PLAYER_X)) {
                button6.setText(TestBoard.PLAYER_X);
            } else if (player.equals(TestBoard.PLAYER_O)) {
                button6.setText(TestBoard.PLAYER_O);
            }
            testBoard.board[1][2] = player;
            position.x = 1;
            position.y = 2;
            turnFlag = false;
            if (testBoard.isGameOver()) {
                gameOverAlert();
            } else {
                playAgainstComputer();
            }
            //button6.setOnAction(null);
        } else if (HomePageController.vsComputer == false) {
            button6.setText(theFirstPlayer);
            if (theFirstPlayer.equalsIgnoreCase("X")) {
                button6.setStyle("-fx-text-fill: red;");
            } else {
                button6.setStyle("-fx-text-fill: blue;");
            }
            choosePlayer();
            winning();
            setValues(6);
        }
         //button6.setOnAction(null);
    }

    @FXML
    private void btn7(ActionEvent event) {
        if (HomePageController.vsComputer == true) {
            if (player.equals(TestBoard.PLAYER_X)) {
                button7.setText(TestBoard.PLAYER_X);
            } else if (player.equals(TestBoard.PLAYER_O)) {
                button7.setText(TestBoard.PLAYER_O);
            }
            testBoard.board[2][0] = player;
            position.x = 2;
            position.y = 0;
            turnFlag = false;
            if (testBoard.isGameOver()) {
                gameOverAlert();
            } else {
                playAgainstComputer();
            }
            //button7.setOnAction(null);
        } else if (HomePageController.vsComputer == false) {

            button7.setText(theFirstPlayer);
            if (theFirstPlayer.equalsIgnoreCase("X")) {
                button7.setStyle("-fx-text-fill: red;");
            } else {
                button7.setStyle("-fx-text-fill: blue;");
            }
            choosePlayer();
            winning();
            setValues(7);

        }
        //button7.setOnAction(null);
    }

    @FXML
    private void btn8(ActionEvent event) {
        if (HomePageController.vsComputer == true) {
            if (player.equals(TestBoard.PLAYER_X)) {
                button8.setText(TestBoard.PLAYER_X);
            } else if (player.equals(TestBoard.PLAYER_O)) {
                button8.setText(TestBoard.PLAYER_O);
            }
            testBoard.board[2][1] = player;
            position.x = 2;
            position.y = 1;
            turnFlag = false;
            if (testBoard.isGameOver()) {
                gameOverAlert();
            } else {
                playAgainstComputer();
            }
            //button8.setOnAction(null);
        } else if (HomePageController.vsComputer == false) {

            button8.setText(theFirstPlayer);
            if (theFirstPlayer.equalsIgnoreCase("X")) {
                button8.setStyle("-fx-text-fill: red;");
            } else {
                button8.setStyle("-fx-text-fill: blue;");
            }
            choosePlayer();
            winning();

            setValues(8);
        }
        //button8.setOnAction(null);
    }

    @FXML
    private void btn9(ActionEvent event) {
        if (HomePageController.vsComputer == true) {
            if (player.equals(TestBoard.PLAYER_X)) {
                button9.setText(TestBoard.PLAYER_X);
            } else if (player.equals(TestBoard.PLAYER_O)) {
                button9.setText(TestBoard.PLAYER_O);
            }
            testBoard.board[2][2] = player;
            position.x = 2;
            position.y = 1;
            turnFlag = false;
            if (testBoard.isGameOver()) {
                gameOverAlert();
            } else {
                playAgainstComputer();
            }
            //button9.setOnAction(null);
        } else if (HomePageController.vsComputer == false) {

            button9.setText(theFirstPlayer);
            if (theFirstPlayer.equalsIgnoreCase("X")) {
                button9.setStyle("-fx-text-fill: red;");
            } else {
                button9.setStyle("-fx-text-fill: blue;");
            }
            choosePlayer();
            winning();
            setValues(9);
        }
        //button9.setOnAction(null);
    }

}
