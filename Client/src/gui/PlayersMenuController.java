/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class PlayersMenuController implements Initializable {

    @FXML
    private MenuItem logOutBtn;
    @FXML
    private MenuItem helpBtn;
    @FXML
    AnchorPane aPane;
    Button[] inviteBtns;
    Button[] chatBtns;
    AnchorPane[] aPaneChildren;
    AnchorPane[] btnPane;
    Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        aPaneChildren = new AnchorPane[5];
    	chatBtns = new Button[5];
        inviteBtns = new Button[5];
        btnPane = new AnchorPane[5];
        

        for (int i = 0; i < 5; i++) {
            chatBtns[i] = new Button("Chat");
            inviteBtns[i] = new Button("Invite");
            aPaneChildren[i] = new AnchorPane();
            btnPane[i] = new AnchorPane();
            btnPane[i].setPrefWidth(100);
            chatBtns[i].setLayoutX(60);
            inviteBtns[i].setLayoutX(100);
            btnPane[i].getChildren().addAll(inviteBtns[i], chatBtns[i]);
            aPaneChildren[i].getChildren().add(new Label("User"));
            aPaneChildren[i].setLayoutY((i * 5) + 50);
            aPaneChildren[i].getChildren().add(btnPane[i]);
            aPane.getChildren().add(aPaneChildren[i]);
        }
    }    
 
     public void setStage(Stage primaryStage) {
    	stage = primaryStage;
    }
    
    public void setActionHandler(final Stage stage) {
    	for (int i = 0; i < 5; i++) {
            
            inviteBtns[i].addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                	Alert alert = new Alert(AlertType.CONFIRMATION);
                	alert.setHeaderText("Do you want to accept the invitation?");
                	alert.setContentText(null);
                	Optional<ButtonType> btnType = alert.showAndWait();
                	if(btnType.get() == ButtonType.OK) {
                		Scene scene = new Scene(new Label("Hello"), 400, 500);
                    	stage.setScene(scene);
                    }
                }
            });
        }
    }
    @FXML
    private void ReturnHomeAction(ActionEvent event) {
    }

    @FXML
    private void MenuAction(ActionEvent event) {
    }
    
}
