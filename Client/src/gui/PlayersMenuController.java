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
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;

public class PlayersMenuController implements Initializable {

	@FXML
	private MenuItem logOutBtn;
	@FXML
	private MenuItem helpBtn;
	@FXML
	private ListView<FlowPane> lview;
	private Button[] inviteBtns;
	private FlowPane[] fPane;
	private Label[] usernames;
	private Label[] score;
	private FlowPane headerPane;
	private Stage stage;
	private int size;
	static Parent helpUI;
	static HelpController helpControl;

	ObservableList<JSONObject> players;
	ObservableList<JSONObject> silver;
	ObservableList<JSONObject> bronze;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	public void init() {
		players = FXCollections.observableArrayList(Main.client.getGoldPlayers());
		players.addAll(Main.client.getSilverPlayers());
		players.addAll(Main.client.getBronzePlayers());

		setSize(players.size());
		inviteBtns = new Button[size];
		fPane = new FlowPane[size];
		usernames = new Label[size];
		score = new Label[size];
		headerPane = new FlowPane();
		headerPane.setHgap(390 / 4);
		headerPane.getChildren().addAll(new Label("Name"), new Label("Score"), new Label("Rank"));
		lview.getItems().add(headerPane);

		for (int i = 0; i < size; i++) {
			inviteBtns[i] = new Button("Invite");
			fPane[i] = new FlowPane();
			fPane[i].setHgap(400 / 4);
			try {
				if (players.get(i).get("status").toString().equals("offline"))
					inviteBtns[i].setDisable(true);
				usernames[i] = new Label(players.get(i).get("username").toString());
				score[i] = new Label(players.get(i).get("score").toString());
				fPane[i].getChildren().add(usernames[i]);
				fPane[i].getChildren().add(score[i]);
				fPane[i].getChildren().addAll(new Label("Bronze"), inviteBtns[i]);
				lview.getItems().add(fPane[i]);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		setActionHandler();
	}

	public ListView<FlowPane> getList() {
		return lview;
	}

	public void setSize(int s) {
		size = s;
	}

	public void setStage(Stage primaryStage) {
		stage = primaryStage;
	}

	public void setActionHandler() {
		FXMLLoader helpLoader = new FXMLLoader(getClass().getResource("Help.fxml"));

		for (int i = 0; i < size; i++) {
			inviteBtns[i].addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setHeaderText("Do you want to accept the invitation?");
					alert.setContentText(null);
					Optional<ButtonType> btnType = alert.showAndWait();
					if (btnType.get() == ButtonType.OK) {
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
}
