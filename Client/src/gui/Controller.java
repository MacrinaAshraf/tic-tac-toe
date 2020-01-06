package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    FlowPane fPane;

    FlowPane[] fPaneChildren = new FlowPane[5];

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Button[] btns = new Button[5];
        for (int i = 0; i < 5; i++) {
            btns[i] = new Button("Invite");
            btns[i].addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setHeaderText("Hello");
                    a.setContentText(null);
                    a.showAndWait();
                }
            });
        }

        for (int i = 0; i < 5; i++) {
            fPaneChildren[i] = new FlowPane();
            fPaneChildren[i].setPrefWidth(100);
            fPaneChildren[i].getChildren().add(new Label("User"));
            fPaneChildren[i].setHgap(5);
            fPaneChildren[i].getChildren().add(btns[i]);
            fPane.getChildren().add(fPaneChildren[i]);
        }
    }
}
