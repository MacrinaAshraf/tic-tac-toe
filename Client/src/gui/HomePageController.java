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
	
	static Boolean vsComputer = false;
	
	Parent loginUI, playersMenuUI, gameScreenUI;
	PlayersMenuController playersMenuControl;
	LoginController loginControl;
	private GameController gameControl;
    public boolean playerChoose=true;
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	
        
    }
    
    public void setActionHandler(Stage stage, Client client) {
    	FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
    	FXMLLoader playersMenuLoader = new FXMLLoader(getClass().getResource("PlayersMenu.fxml"));
    	FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("GameScreen.fxml"));
        try {
			loginUI = loginLoader.load();
			playersMenuUI = playersMenuLoader.load();
			gameScreenUI = gameLoader.load();
			
			playersMenuControl = (PlayersMenuController) playersMenuLoader.getController();
			loginControl = (LoginController) loginLoader.getController();
			gameControl = (GameController) gameLoader.getController();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        logOutBtn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				stage.setScene(new Scene(loginUI));
				loginControl.setActionHandler(stage, client);
			}
    		
    	});
    	
    	multiPlayer.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                                             
                      
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//stage.setScene(new Scene(playersMenuUI));
				//playersMenuControl.setActionHandler(stage);
				gameControl.setStage(stage);
				stage.setScene(new Scene(gameScreenUI));
				gameControl.assignNumber();
			}
                        
    		
    	});
    	
    	singlePlayer.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				vsComputer = true;
				gameControl.setStage(stage);
				stage.setScene(new Scene(gameScreenUI));
				gameControl.assignNumber();
			}
    		
    	});
    }
    
}
