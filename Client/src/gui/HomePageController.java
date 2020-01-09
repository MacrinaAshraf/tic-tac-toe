/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseDragEvent;

/**
 *
 * @author Noran
 */
public class HomePageController implements Initializable {
    
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
    private void SingleGameButtonAction(ActionEvent event) {
        System.out.println("hello signle game");
    }

    private void singlGame_mousedrage(MouseDragEvent event) {
        System.out.println("hello signle game mouse drage");
    }

    @FXML
    private void LogOutAction(ActionEvent event) {
        System.out.println("You loged out");
    }

    @FXML
    private void HelpButtonAction(ActionEvent event) {
        System.out.println("You Enterded help");
    }

    
}
