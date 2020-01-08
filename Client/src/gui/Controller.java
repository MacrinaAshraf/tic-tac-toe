package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    FlowPane fPane;
    Button[] inviteBtns;
    FlowPane[] fPaneChildren = new FlowPane[5];

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        inviteBtns = new Button[5];

        for (int i = 0; i < 5; i++) {
        	inviteBtns[i] = new Button("Invite");
            fPaneChildren[i] = new FlowPane();
            fPaneChildren[i].setPrefWidth(100);
            fPaneChildren[i].getChildren().add(new Label("User"));
            fPaneChildren[i].setHgap(5);
            fPaneChildren[i].getChildren().add(inviteBtns[i]);
            fPane.getChildren().add(fPaneChildren[i]);
        }
    }
    
    public void setActionHandler(Stage stage) {
    	for (int i = 0; i < 5; i++) {
            
            inviteBtns[i].addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                	Alert alert = new Alert(AlertType.CONFIRMATION);
                	alert.setHeaderText("Do you want to accept the invitation?");
                	alert.setContentText(null);
                	Optional<ButtonType> btnType = alert.showAndWait();
                	if(btnType.get() == ButtonType.OK) {
                		Scene scene = new Scene(new Label("Hello"), 300, 275);
                    	stage.setScene(scene);
                    }
                }
            });
        }
    }
}
