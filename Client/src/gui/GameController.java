/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.json.JSONException;

/**
 * FXML Controller class
 *
 * @author Ismail_khadr
 */
public class GameController implements Initializable {
	public static final Random RANDOM = new Random();
	private Stage stage;
	private TestBoard testBoard = new TestBoard();
	private String player = "";
	private Point position = new Point();
	private Boolean turnFlag = true; // true if its user player, false if its computer player
	Client client;
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
        static Parent helpUI;
        static HelpController helpControl;
        @FXML
        private MenuItem logOutBtn;
        @FXML
        private MenuItem helpBtn;

	/**
	 * Initializes the controller class.
	 */
        

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		System.out.println(GridPane.getRowIndex(button1));
                
	}

	public void setStage(Stage primaryStage) {
		stage = primaryStage;
	}
        public void setActionHandler(Stage stage) throws IOException {
                FXMLLoader helpLoader = new FXMLLoader(getClass().getResource("Help.fxml"));
                helpUI = helpLoader.load();
                helpControl = (HelpController) helpLoader.getController();
                
                logOutBtn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					// TODO Auto-generated method stub

					Main.client.logout();
				} catch (JSONException ex) {
					Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
				}

				stage.setScene(new Scene(HomePageController.loginUI));
				HomePageController.loginControl.setActionHandler(stage);
			}
        });
                    helpBtn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
                            @Override
                            public void handle(ActionEvent event) {
                            stage.setScene(new Scene(helpUI));
                            helpControl.setActionHandler(stage);
                }
            
        });
                
                
                        }

	@FXML
	private void btn1(ActionEvent event) {
		if (player.equals(TestBoard.PLAYER_X)) {
			button1.setText(TestBoard.PLAYER_X);
		} else if (player.equals(TestBoard.PLAYER_O)) {
			button1.setText(TestBoard.PLAYER_O);
		}
		testBoard.board[0][0] = player;
		position.x = 0;
		position.y = 0;
		turnFlag = false;
		if (HomePageController.vsComputer) {
			if (testBoard.isGameOver())
				gameOverAlert();
			else
				playAgainstComputer();
		}
		button1.setOnAction(null);
	}

	@FXML
	private void btn2(ActionEvent event) {
		if (player.equals(TestBoard.PLAYER_X)) {
			button2.setText(TestBoard.PLAYER_X);
		} else if (player.equals(TestBoard.PLAYER_O)) {
			button2.setText(TestBoard.PLAYER_O);
		}
		testBoard.board[0][1] = player;
		position.x = 0;
		position.y = 1;
		turnFlag = false;
		if (HomePageController.vsComputer) {
			if (testBoard.isGameOver())
				gameOverAlert();
			else
				playAgainstComputer();
		}
		button2.setOnAction(null);
	}

	@FXML
	private void btn3(ActionEvent event) {
		if (player.equals(TestBoard.PLAYER_X)) {
			button3.setText(TestBoard.PLAYER_X);
		} else if (player.equals(TestBoard.PLAYER_O)) {
			button3.setText(TestBoard.PLAYER_O);
		}
		testBoard.board[0][2] = player;
		position.x = 0;
		position.y = 2;
		turnFlag = false;
		if (HomePageController.vsComputer) {
			if (testBoard.isGameOver())
				gameOverAlert();
			else
				playAgainstComputer();
		}
		button3.setOnAction(null);
	}

	@FXML
	private void btn4(ActionEvent event) {
		if (player.equals(TestBoard.PLAYER_X)) {
			button4.setText(TestBoard.PLAYER_X);
		} else if (player.equals(TestBoard.PLAYER_O)) {
			button4.setText(TestBoard.PLAYER_O);
		}
		testBoard.board[1][0] = player;
		position.x = 1;
		position.y = 0;
		turnFlag = false;
		if (HomePageController.vsComputer) {
			if (testBoard.isGameOver())
				gameOverAlert();
			else
				playAgainstComputer();
		}
		button4.setOnAction(null);
	}

	@FXML
	private void btn5(ActionEvent event) {
		if (player.equals(TestBoard.PLAYER_X)) {
			button5.setText(TestBoard.PLAYER_X);
		} else if (player.equals(TestBoard.PLAYER_O)) {
			button5.setText(TestBoard.PLAYER_O);
		}
		testBoard.board[1][1] = player;
		position.x = 1;
		position.y = 1;
		turnFlag = false;
		if (HomePageController.vsComputer) {
			if (testBoard.isGameOver())
				gameOverAlert();
			else
				playAgainstComputer();
		}
		button5.setOnAction(null);
	}

	@FXML
	private void btn6(ActionEvent event) {
		if (player.equals(TestBoard.PLAYER_X)) {
			button6.setText(TestBoard.PLAYER_X);
		} else if (player.equals(TestBoard.PLAYER_O)) {
			button6.setText(TestBoard.PLAYER_O);
		}
		testBoard.board[1][2] = player;
		position.x = 1;
		position.y = 2;
		turnFlag = false;
		if (HomePageController.vsComputer) {
			if (testBoard.isGameOver())
				gameOverAlert();
			else
				playAgainstComputer();
		}
		button6.setOnAction(null);
	}

	@FXML
	private void btn7(ActionEvent event) {
		if (player.equals(TestBoard.PLAYER_X)) {
			button7.setText(TestBoard.PLAYER_X);
		} else if (player.equals(TestBoard.PLAYER_O)) {
			button7.setText(TestBoard.PLAYER_O);
		}
		testBoard.board[2][0] = player;
		position.x = 2;
		position.y = 0;
		turnFlag = false;
		if (HomePageController.vsComputer) {
			if (testBoard.isGameOver())
				gameOverAlert();
			else
				playAgainstComputer();
		}
		button7.setOnAction(null);
	}

	@FXML
	private void btn8(ActionEvent event) {
		if (player.equals(TestBoard.PLAYER_X)) {
			button8.setText(TestBoard.PLAYER_X);
		} else if (player.equals(TestBoard.PLAYER_O)) {
			button8.setText(TestBoard.PLAYER_O);
		}
		testBoard.board[2][1] = player;
		position.x = 2;
		position.y = 1;
		turnFlag = false;
		if (HomePageController.vsComputer) {
			if (testBoard.isGameOver())
				gameOverAlert();
			else
				playAgainstComputer();
		}
		button8.setOnAction(null);
	}

	@FXML
	private void btn9(ActionEvent event) {
		if (player.equals(TestBoard.PLAYER_X)) {
			button9.setText(TestBoard.PLAYER_X);
		} else if (player.equals(TestBoard.PLAYER_O)) {
			button9.setText(TestBoard.PLAYER_O);
		}
		testBoard.board[2][2] = player;
		position.x = 2;
		position.y = 1;
		turnFlag = false;
		if (HomePageController.vsComputer) {
			if (testBoard.isGameOver())
				gameOverAlert();
			else
				playAgainstComputer();
		}
		button9.setOnAction(null);

	}

	private void LogOutAction(ActionEvent event) {
             try {
				Main.client.logout();
				} catch (JSONException ex) {
					Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
				}

				stage.setScene(new Scene(HomePageController.loginUI));
				HomePageController.loginControl.setActionHandler(stage);
	}

	private void HelpButtonAction(ActionEvent event) {
           stage.setScene(new Scene(helpUI));
           helpControl.setActionHandler(stage);
			}


	@FXML
	private void Reset(ActionEvent event) {
		testBoard.resetBoard();
		button1.setText(null);
		button2.setText(null);
		button3.setText(null);
		button4.setText(null);
		button5.setText(null);
		button6.setText(null);
		button7.setText(null);
		button8.setText(null);
		button9.setText(null);
	}

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
		if (testBoard.isGameOver())
			gameOverAlert();
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
		Alert gameOverAlert = new Alert(AlertType.INFORMATION);
		gameOverAlert.setHeaderText("The Game is Over!");
		gameOverAlert.setContentText(null);
		gameOverAlert.showAndWait();
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
		homePageControl.setActionHandler(stage);
	}

}
