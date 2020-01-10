/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playscreen;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author islam salah
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Button pos_1;
    @FXML
    private Button pos_2;
    private Button pos_3;
    private Button pos_4;
    @FXML
    private Button pos_5;
    private Button pos_6;
    private Button pos_7;

    private Button pos_8;

    private Button pos_9;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Pso_1_Handeler(ActionEvent event) {

        pos_1.setText("O");
        System.out.println("Pos_1");

    }

    private void Pso_4_Handeler(ActionEvent event) {
        pos_4.setText("O");
        System.out.println("Pos_4");

    }

    @FXML
    private void Pso_2_Handeler(ActionEvent event) {
        pos_2.setText("X");
        System.out.println("Pos_2");

    }

    @FXML
    private void Pso_5_Handeler(ActionEvent event) {
        pos_5.setText("X");
        System.out.println("Pos_5");
    }

    private void Pso_3_Handeler(ActionEvent event) {
        pos_3.setText("X");
        System.out.println("Pos_3");
    }

    private void Pso_7_Handeler(ActionEvent event) {
        pos_7.setText("X");
        System.out.println("Pos_7");
    }

    private void Pso_8_Handeler(ActionEvent event) {
        pos_8.setText("X");
        System.out.println("Pos_8");
    }

    private void Pso_6_Handeler(ActionEvent event) {
        pos_6.setText("X");
        System.out.println("Pos_6");
    }

    private void Pso_9_Handeler(ActionEvent event) {
        pos_9.setText("X");
        System.out.println("Pos_9");
    }

}
