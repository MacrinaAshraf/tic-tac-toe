/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playscreen;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Ismail_khadr
 */
public class FXMLDocumentController implements Initializable {
	
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
	
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btn1(ActionEvent event) {
    	button1.setText("X");
    }
    
    @FXML
    private void btn2(ActionEvent event) {
    	button2.setText("X");
    }

    @FXML
    private void btn3(ActionEvent event) {
    	button3.setText("X");
    }
    
    @FXML
    private void btn4(ActionEvent event) {
    	button4.setText("X");
    }

    @FXML
    private void btn5(ActionEvent event) {
    	button5.setText("X");
    }

    @FXML
    private void btn6(ActionEvent event) {
    	button6.setText("X");
    }

    @FXML
    private void btn7(ActionEvent event) {
    	button7.setText("X");
    }

    @FXML
    private void btn8(ActionEvent event) {
    	button8.setText("X");
    }

    @FXML
    private void btn9(ActionEvent event) {
    	button9.setText("X");
    }

    @FXML
    private void LogOutAction(ActionEvent event) {
    }

    @FXML
    private void HelpButtonAction(ActionEvent event) {
    }
    
    @FXML
    private void Reset(ActionEvent event) {
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
}
