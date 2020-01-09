package gui;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {
	//HomePageController cl;

    @Override
    public void start(Stage primaryStage) throws Exception{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\xml\\Signup.fxml"));
        Parent root = loader.load();
    	primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        //cl = (HomePageController)loader.getController();
        //cl.setStage(primaryStage);
        //cl.setActionHandler();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
