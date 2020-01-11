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
import static javafx.application.Application.launch;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    ObservableList list;
    TableView<Client> table;
    BorderPane root;

    @Override
    public void start(Stage primaryStage) {
        Button start = new Button();
        Button stop = new Button();
        Button displayPlayers = new Button();
        start.setText("run Server");
        stop.setText("stop Server");
        displayPlayers.setText("Show all players");
        start.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    if (runServer == null) {
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
                if (gameServer != null) {
                    gameServer.stop();
                    gameServer = null;
                    runServer.stop();
                    runServer = null;
                    System.out.println("Server Closing");
                }
            }
        });
        displayPlayers.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                list = FXCollections.observableList(GameServer.clientsVector);
                TableColumn<Client, String> usernameColumn = new TableColumn<>("username");
                usernameColumn.setMinWidth(200);
                usernameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
                TableColumn<Client, String> statusColumn = new TableColumn<>("status");
                statusColumn.setMinWidth(200);
                statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
                TableColumn<Client, Integer> scoreColumn = new TableColumn<>("score");
                scoreColumn.setMinWidth(200);
                scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
                table = new TableView<>();
                table.setItems(list);
                table.getColumns().addAll(usernameColumn, statusColumn, scoreColumn);
                root.setCenter(table);
                System.out.print(table);
            }
        });

        root = new BorderPane(table);
        root.setLeft(start);
        root.setCenter(table);
        root.setBottom(stop);
        root.setRight(displayPlayers);

        Scene scene = new Scene(root, 800, 550);

        primaryStage.setTitle("Server");
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
