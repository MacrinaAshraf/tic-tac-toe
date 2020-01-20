/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.json.JSONException;

/**
 * FXML Controller class
 *
 * @author Noran
 */
public class HelpController implements Initializable {

	@FXML
	private MenuItem logOutBtn;
	@FXML
	private MenuItem homeBtn;

	static Parent loginUI,HomePageUI;
	HomePageController HomePageControl;
	static LoginController loginControl;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

	public void setActionHandler(Stage stage) {
		FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
		FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
		try {
			loginUI = loginLoader.load();
			HomePageUI = homeLoader.load();

			HomePageControl = (HomePageController) homeLoader.getController();
			loginControl = (LoginController) loginLoader.getController();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                

		logOutBtn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent lOut) {
				try {
					Main.client.logout();
				} catch (JSONException ex) {
					Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
				}
				Main.client.setPlayerToZero();
				stage.setScene(new Scene(loginUI));
				loginControl.setActionHandler(stage);
			}

		});
		homeBtn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				stage.setScene(new Scene(HomePageUI));
				HomePageControl.setActionHandler(stage);

			}

		});
	}
}
