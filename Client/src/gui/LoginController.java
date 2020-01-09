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

/**
 *
 * @author Ismail_khadr
 */
public class LoginController implements Initializable {

    @FXML
    private Label label;

    private void handleButtonAction(ActionEvent event) {
        

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void LonginAction(ActionEvent event) {
        System.out.println("hi login");
    }

}
