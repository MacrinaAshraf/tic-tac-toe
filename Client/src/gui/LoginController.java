/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Ismail_khadr
 */
public class LoginController implements Initializable {

	@FXML
	private Button loginBtn;
	@FXML
	private Hyperlink signUpLink;

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	private Parent homePageUI;
	private HomePageController homePageControl;
	private Parent signUpUI;
	private SignUpController signUpControl;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

	public void setActionHandler(Stage stage) {
		FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
		FXMLLoader signUpLoader = new FXMLLoader(getClass().getResource("SignUp.fxml"));
		try {
			homePageUI = homePageLoader.load();
			homePageControl = (HomePageController) homePageLoader.getController();

			signUpUI = signUpLoader.load();
			signUpControl = (SignUpController) signUpLoader.getController();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		loginBtn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub

				try {
					sendPlayerData();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				while (Main.client.getPlayer().getId() == 0) {
					System.out.println(Main.client.getPlayer().getId());
					if (Main.client.getPlayer().getId() == -1) {
						System.out.println("you can't login.");
						Main.client.getPlayer().setId(0);
						break;
					} else if (Main.client.getPlayer().getId() > 0) {
						stage.setScene(new Scene(homePageUI));
						homePageControl.setActionHandler(stage);
						break;
					} else {

					}
				}
			}

		});

		signUpLink.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				stage.setScene(new Scene(signUpUI));
				signUpControl.setActionHandler(stage);
			}

		});

	}

	public void sendPlayerData() throws JSONException {
		Main.client.login(username.getText(), password.getText());
	}

}
