/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Feeshar
 */
public class ServerGUI extends Application {
    GameServer gameServer;
    ServerRunning runServer;
    @Override
    public void start(Stage primaryStage) {
        Button start = new Button();
        Button stop = new Button();
        start.setText("run Server");
        stop.setText("stop Server");
        start.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                try {
                    if(runServer == null){
                        runServer = new ServerRunning();
                        runServer.start();
                    }
//                    Thread.getAllStackTraces().keySet().forEach((t) -> System.out.println(t.getName() + "\nIs Daemon " + t.isDaemon() + "\nIs Alive " + t.isAlive()));
                } catch (Exception ex) {
                    Logger.getLogger(ServerGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        stop.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                    if(gameServer != null){
                        gameServer.stop();
                        gameServer = null;
                        runServer.stop();
                        runServer = null;
                        System.out.println("Server Closing");
                    }
            }
        });
        
        BorderPane root = new BorderPane();
        root.setRight(start);
        root.setLeft(stop);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    class ServerRunning extends Thread {
        public void run() {
            try {
                gameServer = new GameServer();
                gameServer.start();
                System.out.println("Server Running");
                
            } catch (IOException ex) {
                Logger.getLogger(ServerGUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ServerGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            // the server failed
            System.out.println("Server crashed\n");
            gameServer.stop();
            gameServer = null;
        }
    }

}
