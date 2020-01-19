package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;

public class PlayersMenuController implements Initializable {

    @FXML
    private MenuItem logOutBtn;
    @FXML
    private MenuItem helpBtn;
    private ListView<FlowPane> lview;
    private Button[] inviteBtns;
    private FlowPane[] fPane;
    private Label[] usernames;
    private Label[] score;
    private FlowPane headerPane;
    private Stage stage;
    private int size;
    private int i, place;
    static Parent helpUI;
    static HelpController helpControl;

    ObservableList<AllPlayers> players;
    
    @FXML
    private TableColumn<AllPlayers, String> playerName;
    @FXML
    private TableColumn<AllPlayers, String> status;
    @FXML
    private TableColumn<AllPlayers, String> rank;
    @FXML
    private TableColumn<?, ?> btnsCol;
    @FXML
     private TableView<AllPlayers>table;

    public  TableView<AllPlayers> getTable() {
        return table;
    }

    public  void setTable(TableView<AllPlayers> table1) {
        table = table1;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void init() {
       /* players = FXCollections.observableArrayList(Main.client.getGoldPlayers());
        players.addAll(Main.client.getSilverPlayers());
        players.addAll(Main.client.getBronzePlayers());*/

        setSize(players.size());
        inviteBtns = new Button[size];
        fPane = new FlowPane[size];
        usernames = new Label[size];
        score = new Label[size];
        /*headerPane = new FlowPane();
		headerPane.setHgap(390 / 4);
		headerPane.getChildren().addAll(new Label("Name"), new Label("Score"), new Label("Rank"));
		//lview.getItems().add(headerPane);*/

        for (int i = 0; i < size; i++) {
            inviteBtns[i] = new Button("Invite");

            fPane[i] = new FlowPane();
            fPane[i].setHgap(400 / 4);
            
            /*try {
               inviteBtns[i].setId(players.get(i).get("username").toString());
                if (players.get(i).get("status").toString().equals("offline")) {
                    inviteBtns[i].setDisable(true);
                }
                usernames[i] = new Label(players.get(i).get("username").toString());
                score[i] = new Label(players.get(i).get("score").toString());
                
                /*fPane[i].getChildren().add(usernames[i]);
                fPane[i].getChildren().add(score[i]);
                fPane[i].getChildren().addAll(new Label("Bronze"), inviteBtns[i]);
                lview.getItems().add(fPane[i]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }*/
        setActionHandler();
        }
        playerName.setCellValueFactory(new PropertyValueFactory <> ("username"));
        status.setCellValueFactory(new PropertyValueFactory <> ("status"));
        rank.setCellValueFactory(new PropertyValueFactory <> ("rank"));
        table.setItems(players);
    }

    public void fillPlayersList (){
        
        AllPlayers tempPlayer;
        for (int i=0 ; i < Main.client.getGoldPlayers().size(); i++){
            tempPlayer = new AllPlayers();
            try {
                tempPlayer.setUsername(Main.client.getGoldPlayers().get(i).get("username").toString());
                tempPlayer.setPlaying(Main.client.getGoldPlayers().get(i).getBoolean("playing"));
                tempPlayer.setRank("Gold");
                tempPlayer.setStatus(Main.client.getGoldPlayers().get(i).getString("status"));
                tempPlayer.setScore(Main.client.getGoldPlayers().get(i).getInt("score"));
                players.add(tempPlayer);
            } catch (JSONException ex) {
                Logger.getLogger(PlayersMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for (i=0 ; i< Main.client.getBronzePlayers().size(); i++){
            tempPlayer = new AllPlayers ();
            try {
                tempPlayer.setUsername(Main.client.getBronzePlayers().get(i).get("username").toString());
                tempPlayer.setPlaying(Main.client.getBronzePlayers().get(i).getBoolean("playing"));
                tempPlayer.setRank("Bronze");
                tempPlayer.setStatus(Main.client.getBronzePlayers().get(i).getString("status"));
                tempPlayer.setScore(Main.client.getBronzePlayers().get(i).getInt("score"));
                players.add(tempPlayer);
            } catch (JSONException ex) {
                Logger.getLogger(PlayersMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
                
        }
        for (i=0 ; i< Main.client.getSilverPlayers().size(); i++){
            tempPlayer = new AllPlayers ();
            try {
                tempPlayer.setUsername(Main.client.getSilverPlayers().get(i).get("username").toString());
                tempPlayer.setPlaying(Main.client.getSilverPlayers().get(i).getBoolean("playing"));
                tempPlayer.setRank("Silver");
                tempPlayer.setStatus(Main.client.getSilverPlayers().get(i).getString("status"));
                tempPlayer.setScore(Main.client.getSilverPlayers().get(i).getInt("score"));
                players.add(tempPlayer);
            } catch (JSONException ex) {
                Logger.getLogger(PlayersMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
    }

    public void setSize(int s) {
        size = s;
    }

    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public void setActionHandler() {
        FXMLLoader helpLoader = new FXMLLoader(getClass().getResource("Help.fxml"));

        for (i = 0; i < size; i++) {
            inviteBtns[i].addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    //					Alert alert = new Alert(AlertType.CONFIRMATION);
//					alert.setHeaderText("Do you want to accept the invitation?");
//					alert.setContentText(null);
//					Optional<ButtonType> btnType = alert.showAndWait();
//					if (btnType.get() == ButtonType.OK) {
//						Scene scene = new Scene(new Label("Hello"), 400, 500);
//						stage.setScene(scene);
//					
                    handleInvite((Button) event.getSource());
                    //  Main.client.invite(Main.client.getPlayer().getName(), usernames[i].getText());

                }
            });
        }
        logOutBtn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                try {
                    Main.client.logout();
                } catch (JSONException ex) {
                    Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Main.client.setPlayerToZero();
                stage.setScene(new Scene(HomePageController.loginUI));
                HomePageController.loginControl.setActionHandler(stage);
            }

        });

        helpBtn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    helpUI = helpLoader.load();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                helpControl = (HelpController) helpLoader.getController();
                stage.setScene(new Scene(helpUI));
                helpControl.setActionHandler(stage);
            }

        });

    }

    @FXML
    private void ReturnHomeAction(ActionEvent event) {
        FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        Parent homePageUI = null;
        HomePageController homePageControl = null;
        try {
            homePageUI = homePageLoader.load();
            homePageControl = (HomePageController) homePageLoader.getController();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        stage.setScene(new Scene(homePageUI));
        homePageControl.setActionHandler(stage);
    }

    private void handleInvite(Button c) {
        try {
            Main.client.invite(Main.client.getPlayer().getName(), c.getId());
        } catch (JSONException ex) {
            Logger.getLogger(PlayersMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
