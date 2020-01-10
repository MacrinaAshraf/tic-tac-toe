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
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseDragEvent;
import javafx.stage.Stage;

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
	
	Parent loginUI, playersMenuUI;
	Controller cl;
	LoginController loginControl;
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	
        
    }
    
    public void setActionHandler(Stage stage) {
    	FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
    	FXMLLoader playersMenuLoader = new FXMLLoader(getClass().getResource("PlayersMenu.fxml"));
    	
        try {
			loginUI = loginLoader.load();
			playersMenuUI = playersMenuLoader.load();
			
			cl = (Controller) playersMenuLoader.getController();
			loginControl = (LoginController) loginLoader.getController();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        logOutBtn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				stage.setScene(new Scene(loginUI));
				loginControl.setActionHandler(stage);
			}
    		
    	});
    	
    	multiPlayer.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				stage.setScene(new Scene(playersMenuUI));
				cl.setActionHandler(stage);
			}
    		
    	});
    }
    

    
}
