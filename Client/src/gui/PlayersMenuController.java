package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
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
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import org.json.JSONException;

public class PlayersMenuController implements Initializable {

	@FXML
	private MenuItem logOutBtn;
	@FXML
	private MenuItem helpBtn;
	
	private Stage stage;
	
	static Parent helpUI;
	static HelpController helpControl;
	Refresh refresh;
	Timer timer;

	@FXML
	private TableColumn<AllPlayers, String> playerName;
	@FXML
	private TableColumn<AllPlayers, String> status;
	@FXML
	private TableColumn<AllPlayers, String> rank;
	@FXML
	private TableColumn<AllPlayers, Void> btnsCol;
	@FXML
	private TableView<AllPlayers> table;

	ObservableList<AllPlayers> players;

	Vector<AllPlayers> allPlayers;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}
	
	public TableView<AllPlayers> getTable() {
		return table;
	}

	public void init() {
		allPlayers = new Vector<AllPlayers>();

		fillPlayersList();

		playerName.setCellValueFactory(new PropertyValueFactory<AllPlayers, String>("username"));
		status.setCellValueFactory(new PropertyValueFactory<AllPlayers, String>("status"));
		rank.setCellValueFactory(new PropertyValueFactory<AllPlayers, String>("rank"));
		
		addBtnsCol();

		table.setItems(players);
		
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
				FXMLLoader helpLoader = new FXMLLoader(getClass().getResource("Help.fxml"));

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
	
		refresh = new Refresh();
		timer = new Timer();
		
		timer.schedule(refresh, 0, 1000);
	}

	public void addBtnsCol() {
		TableColumn<AllPlayers, Void> colBtn = new TableColumn("Button Column");

        Callback<TableColumn<AllPlayers, Void>, TableCell<AllPlayers, Void>> cellFactory = new Callback<TableColumn<AllPlayers, Void>, TableCell<AllPlayers, Void>>() {
            @Override
            public TableCell<AllPlayers, Void> call(final TableColumn<AllPlayers, Void> param) {
                final TableCell<AllPlayers, Void> cell = new TableCell<AllPlayers, Void>() {

                    private final Button btn = new Button("Invite");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                        	try {
                    			Main.client.invite(Main.client.getPlayer().getName(), getTableView().getItems().get(getIndex()).getUsername());
                    		} catch (JSONException ex) {
                    			Logger.getLogger(PlayersMenuController.class.getName()).log(Level.SEVERE, null, ex);
                    		}
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        table.getColumns().add(colBtn);
	}
	
	public void fillPlayersList(){
		AllPlayers tempPlayer;

		for (int i = 0 ; i < Main.client.getGoldPlayers().size(); i++){
			tempPlayer = new AllPlayers();
			try {
				if(Main.client.getGoldPlayers().get(i).get("username").toString().equals(Client.player.getName())) 
					continue;
				tempPlayer.setUsername(Main.client.getGoldPlayers().get(i).get("username").toString());
				tempPlayer.setPlaying(Main.client.getGoldPlayers().get(i).getBoolean("playing"));
				tempPlayer.setRank("Gold");
				tempPlayer.setStatus(Main.client.getGoldPlayers().get(i).getString("status"));
				tempPlayer.setScore(Main.client.getGoldPlayers().get(i).getInt("score"));
				allPlayers.add(tempPlayer);
			} catch (JSONException ex) {
				Logger.getLogger(PlayersMenuController.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		for (int i = 0 ; i < Main.client.getBronzePlayers().size(); i++){
			tempPlayer = new AllPlayers ();
			try {
				if(Main.client.getBronzePlayers().get(i).get("username").toString().equals(Client.player.getName())) 
					continue;
				tempPlayer.setUsername(Main.client.getBronzePlayers().get(i).get("username").toString());
				tempPlayer.setPlaying(Main.client.getBronzePlayers().get(i).getBoolean("playing"));
				tempPlayer.setRank("Bronze");
				tempPlayer.setStatus(Main.client.getBronzePlayers().get(i).getString("status"));
				tempPlayer.setScore(Main.client.getBronzePlayers().get(i).getInt("score"));
				allPlayers.add(tempPlayer);
			} catch (JSONException ex) {
				Logger.getLogger(PlayersMenuController.class.getName()).log(Level.SEVERE, null, ex);
			}

		}
		for (int i = 0; i < Main.client.getSilverPlayers().size(); i++){
			tempPlayer = new AllPlayers ();
			try {
				if(Main.client.getSilverPlayers().get(i).get("username").toString().equals(Client.player.getName())) 
					continue;
				tempPlayer.setUsername(Main.client.getSilverPlayers().get(i).get("username").toString());
				tempPlayer.setPlaying(Main.client.getSilverPlayers().get(i).getBoolean("playing"));
				tempPlayer.setRank("Silver");
				tempPlayer.setStatus(Main.client.getSilverPlayers().get(i).getString("status"));
				tempPlayer.setScore(Main.client.getSilverPlayers().get(i).getInt("score"));
				allPlayers.add(tempPlayer);
			} catch (JSONException ex) {
				Logger.getLogger(PlayersMenuController.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		
		players = FXCollections.observableArrayList(allPlayers);
	}

	public void setStage(Stage primaryStage) {
		stage = primaryStage;
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
		
		refresh.cancel();
		refresh = null;
		timer.cancel();
		timer = null;
		
		stage.setScene(new Scene(homePageUI));
		homePageControl.setActionHandler(stage);
	}

	class Refresh extends TimerTask {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			fillPlayersList();
			table.setItems(players);
		}
		
	}
}
