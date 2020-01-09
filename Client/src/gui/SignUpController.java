/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import jdk.nashorn.internal.runtime.JSONFunctions;
import org.json.JSONObject;

/**
 *
 * @author Ismail_khadr
 */
public class SignUpController implements Initializable {
    
    @FXML
    private Label label;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void signuphandleButtonAction(ActionEvent event) {
        System.out.println("Sign Up");
        JSONObject ss=new JSONObject();
        
        
    }
    
}
