/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.json.JSONException;

/**
 *
 * @author Noran
 */
public class HomePageController implements Initializable {
	@FXML
	MenuItem logOutBtn;
	@FXML
	MenuItem helpBtn;
	@FXML
	Button singlePlayer;
	@FXML
	Button multiPlayer;

	static Parent playersMenuUI, gameScreenUI, loginUI, helpUI;
	static PlayersMenuController playersMenuControl;
	static LoginController loginControl;
	private GameController1 gameControl;
	static HelpController helpControl;
	static Boolean vsComputer = false;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO

	}

	public void setActionHandler(Stage stage) {
		FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
		FXMLLoader playersMenuLoader = new FXMLLoader(getClass().getResource("PlayersMenu.fxml"));
		FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("GameScreen.fxml"));
		FXMLLoader helpLoader = new FXMLLoader(getClass().getResource("Help.fxml"));
		try {
			loginUI = loginLoader.load();
			playersMenuUI = playersMenuLoader.load();
			gameScreenUI = gameLoader.load();
			helpUI = helpLoader.load();

			playersMenuControl = (PlayersMenuController) playersMenuLoader.getController();
			loginControl = (LoginController) loginLoader.getController();

			helpControl = (HelpController) helpLoader.getController();
			gameControl = (GameController1) gameLoader.getController();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		helpBtn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				stage.setScene(new Scene(helpUI));
				helpControl.setActionHandler(stage);
			}

		});

		logOutBtn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					Main.client.logout();
				} catch (JSONException ex) {
					Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
				}

				stage.setScene(new Scene(loginUI));
				loginControl.setActionHandler(stage);
			}

		});

		multiPlayer.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub

				
				 playersMenuControl.setStage(stage);
                                 stage.setScene(new Scene(playersMenuUI));
				 playersMenuControl.init();
				
				 stage.setScene(new Scene(playersMenuUI));
				// playersMenuControl.setActionHandler(stage);
//				vsComputer = false;
//				gameControl.setStage(stage);
//				stage.setScene(new Scene(gameScreenUI));
//				gameControl.assignNumber();

			}

		});

		singlePlayer.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {

				vsComputer = true;
				gameControl.setStage(stage);
				stage.setScene(new Scene(gameScreenUI));
				gameControl.assignNumber();
			}

		});
	}

}
