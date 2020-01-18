/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.JSONFunctions;
import org.json.JSONObject;

/**
 *
 * @author Ismail_khadr
 */
public class SignUpController implements Initializable {
    
	@FXML
	private Button signUpBtn;
	

	private Parent homePageUI;
	private HomePageController homePageControl;
    @FXML
    private TextField username;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

	public void setActionHandler(Stage stage) {
		// TODO Auto-generated method stub
		FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
    	FXMLLoader signUpLoader = new FXMLLoader(getClass().getResource("SignUp.fxml"));

    		try {
				homePageUI = homePageLoader.load();
				homePageControl = (HomePageController)homePageLoader.getController();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	signUpBtn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				stage.setScene(new Scene(homePageUI));
				homePageControl.setActionHandler(stage);
			}
    		
    	});
	}
    
}
