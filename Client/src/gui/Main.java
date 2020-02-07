package gui;

import org.json.JSONException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
	LoginController loginControl;
	static Client client = new Client();
	static Stage stg;

	@Override
	public void start(Stage primaryStage) throws Exception {
		stg = primaryStage;
		FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
		Parent loginUI = loginLoader.load();
		primaryStage.setTitle("TicTacToe");
		primaryStage.setScene(new Scene(loginUI));
		primaryStage.setOnCloseRequest(e -> {
			try {
				if (client.clientSocket != null)
					client.stop();
			} catch (JSONException ex) {
				// Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
				System.exit(0);
			}
			client.stopConnection();
			System.exit(0);
		});

		loginControl = (LoginController) loginLoader.getController();
		loginControl.setActionHandler(primaryStage);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Main.launch(args);
	}

}
