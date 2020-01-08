package gui;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {
	Controller cl;

    @Override
    public void start(Stage primaryStage) throws Exception{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\xml\\PlayersMenu.fxml"));
        Parent root = loader.load();
    	primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        cl = (Controller)loader.getController();
        cl.setActionHandler(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
