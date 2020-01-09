package gui;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Client;


public class Main extends Application {
	Controller cl;
	Client client = new Client();

    @Override
    public void start(Stage primaryStage) throws Exception{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayersMenu.fxml"));
        Parent root = loader.load();
    	primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 400, 500));
        primaryStage.show();
        cl = (Controller)loader.getController();
        cl.setStage(primaryStage);
        cl.setActionHandler();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
