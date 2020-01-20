/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONException;

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

public class GameController implements Initializable {

    Alert alert4, alert5, alert6;
    public ArrayList<Integer> checkBoardArr = new ArrayList<>(9);
    public static final Random RANDOM = new Random();
    private Stage stage;
    private TestBoard testBoard = new TestBoard();
    private String player = "";
    private Point position = new Point();
    private Boolean turnFlag = true;
    Client client;
    static public String thePlayer = "X";
    @FXML
    private Label usernameTwo;
    @FXML
    private Label usernameOne;
    public int xCount;
    public int oCount;

   
    static private Boolean turn;

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
    @FXML
    private Label scoreplayerX;
    

    @FXML
    private Label scoreplayerO;
    private Alert gameOverAlert;
    ButtonType yesbtn = new ButtonType("Yes");
    ButtonType nobtn = new ButtonType("No");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public static String getThePlayer() {
        return thePlayer;
    }

    public static void setThePlayer(String thePlayer) {
        GameController.thePlayer = thePlayer;
    }

    public static Boolean getTurn() {
        return turn;
    }
     public int getxCount() {
        return xCount;
    }

    public void setxCount(int xCount) {
        this.xCount = xCount;
    }

    public int getoCount() {
        return oCount;
    }

    public void setoCount(int oCount) {
        this.oCount = oCount;
    }
    public static void setTurn(Boolean turn) {
        GameController.turn = turn;
    }

    public Label getUsernameOne() {
        return usernameOne;
    }

    public void setUsernameOne(String usernameOne1) {
        usernameOne.setText(usernameOne1);
    }

    public Label getUsernameTwo() {
        return usernameTwo;
    }

    public void setUsernameTwo(String username) {
        usernameTwo.setText(username);
    }

    public GameController() {

        alert4 = new Alert(Alert.AlertType.INFORMATION);
        alert5 = new Alert(Alert.AlertType.INFORMATION);
        alert6 = new Alert(Alert.AlertType.INFORMATION);
        for (int i = 0; i < 9; i++) {
            checkBoardArr.add(i, 1);
        }
        System.out.println("----------" + Main.client.getPlayer().getScore());

    }

    public Label getScoreplayerX() {
        return scoreplayerX;
    }

    public void setScoreplayerX(String scoreplayerX) {
        this.scoreplayerX.setText(scoreplayerX);
    }

    public Label getScoreplayerO() {
        return scoreplayerO;
    }

    public void setScoreplayerO(String scoreplayerO) {
        this.scoreplayerO.setText(scoreplayerO);
    }

    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public void reset() {
        Button arr2[] = {button1, button2, button3, button4, button5, button6, button7, button8, button9};
        for (int i = 0; i < arr2.length; i++) {
            arr2[i].setText("");
            arr2[i].setStyle("-fx-background-color: d7c682;");
        }

        EnableButton();
    }

    public void EnableButton() {
        Button arr2[] = {button1, button2, button3, button4, button5, button6, button7, button8, button9};
        for (int i = 0; i < arr2.length; i++) {
            arr2[i].setDisable(false);

        }

    }

    ////////////// multiplayerfunctions//////////////////////////////////////////
    public void score(String player) {
        if ("X".equals(player)) {
            xCount += 20;
            Main.client.getPlayer().setScore(xCount);
        } else {
            System.out.println("oooooooooooooooooooooooooooooooooooooo");
             System.out.println(oCount);
            oCount += 20;
            System.out.println(oCount);
            Main.client.getPlayer().setScore(oCount);
        }

        scoreplayerX.setText(String.valueOf(xCount));
        scoreplayerO.setText(String.valueOf(oCount));
        System.out.println("-------" + xCount);
        System.out.println("-------" + oCount);
    }

    public boolean draw() {

        for (int i = 0; i < checkBoardArr.size(); i++) {
            System.out.println(checkBoardArr.get(i));
        }
        System.out.println("in draw check and the board contains 1 = " + (checkBoardArr.contains(1)));
        if (!(checkBoardArr.contains(1))) {
            return true;
        }
        return false;
    }

    public void setValues(int index) {
        checkBoardArr.set(index - 1, 0);
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

            System.out.println("in x wins");
            Main.client.win();

            score("X");
            try {
                Main.client.endOfGame();
            } catch (JSONException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
            alert4.setTitle("TicTacToe");
            alert4.setHeaderText(null);
            alert4.setContentText("player X wins");
            if (alert4.showAndWait().get() == ButtonType.OK) {

                reset();

            }
            System.out.println("x win");
        } else if (arr[0].equals("O") && arr[1].equals("O") && arr[2].equals("O")
                || arr[3].equals("O") && arr[4].equals("O") && arr[5].equals("O")
                || arr[6].equals("O") && arr[7].equals("O") && arr[8].equals("O")
                || arr[0].equals("O") && arr[3].equals("O") && arr[6].equals("O")
                || arr[1].equals("O") && arr[4].equals("O") && arr[7].equals("O")
                || arr[2].equals("O") && arr[5].equals("O") && arr[8].equals("O")
                || arr[0].equals("O") && arr[4].equals("O") && arr[8].equals("O")
                || arr[2].equals("O") && arr[4].equals("O") && arr[6].equals("O")) {
            Main.client.win();

            System.out.println("in o wins");
            score("O");
            try {
                Main.client.endOfGame();
            } catch (JSONException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
            alert5.setTitle("TicTacToe");
            alert5.setHeaderText(null);
            alert5.setContentText("player O wins");

            if (alert5.showAndWait().get() == ButtonType.OK) {
                reset();
            }
            System.out.println("o win");

        } else if (draw()) {
            System.out.println("in draw");
            alert6.setTitle("TicTacToe");
            alert6.setHeaderText(null);
            alert6.setContentText("No one wins");
            if (alert6.showAndWait().get() == ButtonType.OK) {
                reset();
            }

        }
    }
    ////////////////////////////////////////// Ai Player/////////////////////////////////

    public void assignNumber() {
        testBoard.printBoard();
        player = TestBoard.PLAYER_O;
    }

    public void playAgainstComputer() {
        testBoard.printBoard();

        if (turnFlag == false && !testBoard.isGameOver()) {
            position = testBoard.getComputerMove(0, TestBoard.PLAYER_X);
            testBoard.placeMove(position, TestBoard.PLAYER_X);
            placeMoveOnGrid(position.x, position.y);
            turnFlag = true;
        }
        if (testBoard.isGameOver()) {

            gameOverAlert();

        }
    }

    public void placeMoveOnGrid(int row, int column) {

        if (GridPane.getRowIndex(button1) == row && GridPane.getColumnIndex(button1) == column) {
            if (HomePageController.vsComputer) {
                button1.setText("X");
            } else {
                if (thePlayer == "X") {
                    button1.setText("O");
                } else {
                    button1.setText("X");
                }
                setValues(1);
            }
            button1.setDisable(true);
        } else if (GridPane.getRowIndex(button2) == row && GridPane.getColumnIndex(button2) == column) {
            if (HomePageController.vsComputer) {
                button2.setText("X");
            } else {
                if (thePlayer == "X") {
                    button2.setText("O");
                } else {
                    button2.setText("X");
                }
                setValues(2);
            }
            button2.setDisable(true);
        } else if (GridPane.getRowIndex(button3) == row && GridPane.getColumnIndex(button3) == column) {
            if (HomePageController.vsComputer) {
                button3.setText("X");
            } else {
                if (thePlayer == "X") {
                    button3.setText("O");
                } else {
                    button3.setText("X");
                }
                setValues(3);
            }
            button3.setDisable(true);
        } else if (GridPane.getRowIndex(button4) == row && GridPane.getColumnIndex(button4) == column) {
            if (HomePageController.vsComputer) {
                button4.setText("X");
            } else {
                if (thePlayer == "X") {
                    button4.setText("O");
                } else {
                    button4.setText("X");
                }
                setValues(4);
            }
            button4.setDisable(true);
        } else if (GridPane.getRowIndex(button5) == row && GridPane.getColumnIndex(button5) == column) {
            if (HomePageController.vsComputer) {
                button5.setText("X");
            } else {
                if (thePlayer == "X") {
                    button5.setText("O");
                } else {
                    button5.setText("X");
                }
                setValues(5);
            }

            button5.setDisable(true);
        } else if (GridPane.getRowIndex(button6) == row && GridPane.getColumnIndex(button6) == column) {
            if (HomePageController.vsComputer) {
                button6.setText("X");
            } else {
                if (thePlayer == "X") {
                    button6.setText("O");
                } else {
                    button6.setText("X");
                }
                setValues(6);
            }
            button6.setDisable(true);
        } else if (GridPane.getRowIndex(button7) == row && GridPane.getColumnIndex(button7) == column) {
            if (HomePageController.vsComputer) {
                button7.setText("X");
            } else {
                if (thePlayer == "X") {
                    button7.setText("O");
                } else {
                    button7.setText("X");
                }
                setValues(7);
            }
            button7.setDisable(true);
        } else if (GridPane.getRowIndex(button8) == row && GridPane.getColumnIndex(button8) == column) {
            if (HomePageController.vsComputer) {
                button8.setText("X");
            } else {
                if (thePlayer == "X") {
                    button8.setText("O");
                } else {
                    button8.setText("X");
                }
                setValues(8);
            }
            button8.setDisable(true);
        } else if (GridPane.getRowIndex(button9) == row && GridPane.getColumnIndex(button9) == column) {
            if (HomePageController.vsComputer) {
                button9.setText("X");
            } else {
                if (thePlayer == "X") {
                    button9.setText("O");
                } else {
                    button9.setText("X");
                }
                setValues(9);
            }
            button9.setDisable(true);
        }

    }

    private void gameOverAlert() {

        if (TestBoard.checkWinner.equals("X")) {
            gameOverAlert = new Alert(AlertType.CONFIRMATION);
            gameOverAlert.setTitle("null");
            gameOverAlert.setHeaderText("player x won");
            gameOverAlert.setContentText("Do You Want Play Agian ?");
            gameOverAlert.getButtonTypes().setAll(yesbtn, nobtn);
            Optional<ButtonType> result = gameOverAlert.showAndWait();

            if (result.get() == yesbtn) {
                playerXwin();

                score("X");
                testBoard.resetBoard();

            } else if (result.get() == nobtn) {
                gotoHomePage();
            }

        } else if (TestBoard.checkWinner.equals("O")) {
            gameOverAlert = new Alert(AlertType.CONFIRMATION);
            gameOverAlert.setTitle("null");
            gameOverAlert.setHeaderText("player o won");
            gameOverAlert.setContentText("Do You Want Play Agian ?");

            gameOverAlert.getButtonTypes().setAll(yesbtn, nobtn);
            Optional<ButtonType> result = gameOverAlert.showAndWait();

            if (result.get() == yesbtn) {
                playerOwin();
                testBoard.resetBoard();
                xCount++;
                score("O");
            } else if (result.get() == nobtn) {
                gotoHomePage();
            }
        } else if (TestBoard.checkWinner.equals("noWinner")) {
            gameOverAlert = new Alert(AlertType.CONFIRMATION);
            gameOverAlert.setTitle("null");
            gameOverAlert.setHeaderText("Draw!");
            gameOverAlert.setContentText("Do You Want Play Agian ?");
            gameOverAlert.getButtonTypes().setAll(yesbtn, nobtn);
            Optional<ButtonType> result = gameOverAlert.showAndWait();
            if (result.get() == yesbtn) {
                DrawXWithO();
                testBoard.resetBoard();
            } else if (result.get() == nobtn) {
                gotoHomePage();
            }
        }
        EnableButton();

    }

    public void playerXwin() {
        gameOverAlert.setHeaderText("The Player X has won!");

        gameOverAlert.setContentText(null);
        reset();
    }

    public void playerOwin() {
        gameOverAlert.setHeaderText("The Player O has won!");
        gameOverAlert.setContentText(null);
        reset();
    }

    public void DrawXWithO() {
        gameOverAlert.setHeaderText("Draw!");
        gameOverAlert.setContentText(null);
        reset();
    }

    public void gotoHomePage() {
        Parent homePageUI = null;
        FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        try {
            homePageUI = homePageLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        HomePageController homePageControl = (HomePageController) homePageLoader.getController();
        stage.setScene(new Scene(homePageUI));
        homePageControl.setActionHandler(stage);
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
            button1.setDisable(true);
            if (testBoard.isGameOver()) {
                gameOverAlert();
            } else {
                playAgainstComputer();
            }

        } else if (HomePageController.vsComputer == false && turn == true) {

            button1.setText(thePlayer);
            button1.setDisable(true);
            if (thePlayer.equalsIgnoreCase("X")) {
                button1.setStyle("-fx-text-fill: red;");
            } else {
                button1.setStyle("-fx-text-fill: blue;");
            }
            int row = GridPane.getRowIndex(button1);
            int col = GridPane.getColumnIndex(button1);
            Main.client.inGame(row, col);
            //choosePlayer();
            turn = false;
            setValues(1);
            winning();
        }

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
            button2.setDisable(true);
            if (testBoard.isGameOver()) {
                gameOverAlert();
            } else {
                playAgainstComputer();
            }
        } else if (HomePageController.vsComputer == false && turn == true) {
            button2.setText(thePlayer);
            if (thePlayer.equalsIgnoreCase("X")) {
                button2.setStyle("-fx-text-fill: red;");
            } else {
                button2.setStyle("-fx-text-fill: blue;");
            }

            button2.setDisable(true);
            int row = GridPane.getRowIndex(button2);
            int col = GridPane.getColumnIndex(button2);
            Main.client.inGame(row, col);
            //     choosePlayer();
            turn = false;
            setValues(2);
            winning();
        }
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
            button3.setDisable(true);
            if (testBoard.isGameOver()) {
                gameOverAlert();
            } else {
                playAgainstComputer();
            }
        } else if (HomePageController.vsComputer == false && turn == true) {

            button3.setText(thePlayer);
            if (thePlayer.equalsIgnoreCase("X")) {

                button3.setStyle("-fx-text-fill: red;");
            } else {
                button3.setStyle("-fx-text-fill: blue;");
            }
            int row = GridPane.getRowIndex(button3);
            int col = GridPane.getColumnIndex(button3);
            Main.client.inGame(row, col);
            button3.setDisable(true);
            //choosePlayer();
            winning();
            turn = false;
            button3.setDisable(true);
            setValues(3);
            winning();
        }
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
            button4.setDisable(true);
            if (testBoard.isGameOver()) {
                gameOverAlert();
            } else {
                playAgainstComputer();
            }

        } else if (HomePageController.vsComputer == false && turn == true) {

            button4.setText(thePlayer);
            if (thePlayer.equalsIgnoreCase("X")) {

                button4.setStyle("-fx-text-fill: red;");
            } else {
                button4.setStyle("-fx-text-fill: blue;");
            }
            button4.setDisable(true);
            int row = GridPane.getRowIndex(button4);
            int col = GridPane.getColumnIndex(button4);
            Main.client.inGame(row, col);
            // choosePlayer();
            turn = false;
            setValues(4);
            winning();
        }

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
            button5.setDisable(true);
            if (testBoard.isGameOver()) {
                gameOverAlert();
            } else {
                playAgainstComputer();
            }
        } else if (HomePageController.vsComputer == false && turn == true) {
            button5.setText(thePlayer);
            if (thePlayer.equalsIgnoreCase("X")) {
                button5.setStyle("-fx-text-fill: red;");
            } else {
                button5.setStyle("-fx-text-fill: blue;");
            }
            button5.setDisable(true);

            int row = GridPane.getRowIndex(button5);
            int col = GridPane.getColumnIndex(button5);
            Main.client.inGame(row, col);
            //    choosePlayer();
            turn = false;
            setValues(5);
            winning();
        }

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
            button6.setDisable(true);
            if (testBoard.isGameOver()) {
                gameOverAlert();
            } else {
                playAgainstComputer();
            }
        } else if (HomePageController.vsComputer == false && turn == true) {

            button6.setText(thePlayer);
            if (thePlayer.equalsIgnoreCase("X")) {

                button6.setStyle("-fx-text-fill: red;");
            } else {
                button6.setStyle("-fx-text-fill: blue;");
            }
            button6.setDisable(true);
            int row = GridPane.getRowIndex(button6);
            int col = GridPane.getColumnIndex(button6);
            Main.client.inGame(row, col);
            //  choosePlayer();
            turn = false;
            setValues(6);
            winning();
        }
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
            button7.setDisable(true);
            if (testBoard.isGameOver()) {
                gameOverAlert();
            } else {
                playAgainstComputer();
            }

        } else if (HomePageController.vsComputer == false && turn == true) {

            button7.setText(thePlayer);
            if (thePlayer.equalsIgnoreCase("X")) {
                button7.setStyle("-fx-text-fill: red;");
            } else {
                button7.setStyle("-fx-text-fill: blue;");
            }
            button7.setDisable(true);

            int row = GridPane.getRowIndex(button7);
            int col = GridPane.getColumnIndex(button7);
            Main.client.inGame(row, col);
            // choosePlayer();
            turn = false;

            setValues(7);
            winning();
        }

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
            button8.setDisable(true);
            if (testBoard.isGameOver()) {
                gameOverAlert();
            } else {
                playAgainstComputer();
            }

            //button8.setOnAction(null);
        } else if (HomePageController.vsComputer == false && turn == true) {

            button8.setText(thePlayer);
            if (thePlayer.equalsIgnoreCase("X")) {
                button8.setStyle("-fx-text-fill: red;");
            } else {
                button8.setStyle("-fx-text-fill: blue;");
            }
            button8.setDisable(true);
            int row = GridPane.getRowIndex(button8);
            int col = GridPane.getColumnIndex(button8);
            Main.client.inGame(row, col);
            //  choosePlayer();
            turn = false;
            setValues(8);
            winning();
        }

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
            button9.setDisable(true);
            if (testBoard.isGameOver()) {
                gameOverAlert();
            } else {
                playAgainstComputer();
            }
            //button9.setOnAction(null);
        } else if (HomePageController.vsComputer == false && turn == true) {

            button9.setText(thePlayer);
            if (thePlayer.equalsIgnoreCase("X")) {

                button9.setStyle("-fx-text-fill: red;");
            } else {
                button9.setStyle("-fx-text-fill: blue;");
            }
            button9.setDisable(true);
            int row = GridPane.getRowIndex(button9);
            int col = GridPane.getColumnIndex(button9);
            Main.client.inGame(row, col);
            turn = false;

            setValues(9);
            winning();
        }

    }

    @FXML
    private void backToHome(ActionEvent event) {
        FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        Parent homePageUI = null;
        HomePageController homePageControl = null;
        Main.client.endOfBattle();
        try {
            homePageUI = homePageLoader.load();
            homePageControl = (HomePageController) homePageLoader.getController();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        stage.setScene(new Scene(homePageUI));
        homePageControl.setActionHandler(stage);
    }
}
