package gui;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.JSONException;



public class Main extends Application {
	LoginController loginControl;
	static Client client = new Client();
	//Controller cl;
    @Override
    public void start(Stage primaryStage) throws Exception{
    	FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("PlayersMenu.fxml"));
        Parent loginUI = loginLoader.load();
    	primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(new Scene(loginUI));
        primaryStage.setOnCloseRequest(e -> {
                try {
                    client.stop();
                } catch (JSONException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            client.stopConnection();
            Platform.exit();});
        primaryStage.show();
        primaryStage.setMinWidth(200);
        primaryStage.setMinHeight(120);

        //loginControl = (LoginController)loginLoader.getController();
        //loginControl.setActionHandler(primaryStage, client);

        loginControl = (LoginController)loginLoader.getController();
        loginControl.setActionHandler(primaryStage);
        
        
        /*cl = (Controller)loginLoader.getController();
        cl.setActionHandler(primaryStage);*/
    }


    public static void main(String[] args) {
        Main.launch(args);
    }
}
