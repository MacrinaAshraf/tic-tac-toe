/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import org.json.JSONException;


public class PlayersMenuController implements Initializable {

    @FXML
    private MenuItem logOutBtn;
    @FXML
    private MenuItem helpBtn;
    @FXML
    ListView lview;
    Button[] inviteBtns;
    FlowPane[] fPane;
    FlowPane headerPane;
    Stage stage;
    int size;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setSize(10);
        inviteBtns = new Button[size];
        fPane = new FlowPane[size];
        headerPane = new FlowPane();
        //System.out.print();
        headerPane.setHgap(390 / 4);
        headerPane.getChildren().addAll(new Label("Name"),new Label("Score"),new Label("Rank"));
        lview.getItems().add(headerPane);
        
        
        
        
        
        

        for (int i = 0; i < size; i++) {
            inviteBtns[i] = new Button("Invite");
            fPane[i] = new FlowPane ();
            fPane[i].setHgap(400 / 4);
            fPane[i].getChildren().addAll(new Label("Noura"), new Label("100") , new Label("Gold"),  inviteBtns[i]);
            lview.getItems().add(fPane[i]);

           
        }  
    }
    public void setSize (int s)
    {
        size = s;
    }
    
     public void setStage(Stage primaryStage) {
    	stage = primaryStage;
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
                		Scene scene = new Scene(new Label("Hello"), 400, 500);
                    	stage.setScene(scene);
                    }
                }
            });
        }
          logOutBtn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
                            try {
                                // TODO Auto-generated method stub

                                Main.client.logout();
                            } catch (JSONException ex) {
                                Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                
				stage.setScene(new Scene(HomePageController.loginUI));
				HomePageController.loginControl.setActionHandler(stage);
			}
    		
    	});
    }
    @FXML
    private void ReturnHomeAction(ActionEvent event) {
    }

    @FXML
    private void MenuAction(ActionEvent event) {
    }
    
}
