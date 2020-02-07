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
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Feeshar
 */
public class ServerGUI extends Application {

    GameServer gameServer;
    ServerRunning runServer;
    static ObservableList list;
    static TableView<Client> table;
    HBox box;
    BorderPane root;
    BorderPane bottomPane;
    FlowPane boxPane;

    @Override
    public void start(Stage primaryStage) {
        Button start = new Button();
        Button stop = new Button();
        box = new HBox();
        boxPane = new FlowPane();
        primaryStage.setOnCloseRequest(e -> {
            System.exit(0);
        });
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
              if(table!=null)  table.setVisible(false);
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
                TableColumn<Client, String> playingWithColum = new TableColumn<>("playingWith");
                playingWithColum.setMinWidth(200);
                playingWithColum.setCellValueFactory(new PropertyValueFactory<>("playingWith"));
                table = new TableView<>();
                table.setItems(list);
                table.getColumns().addAll(usernameColumn, statusColumn, scoreColumn,playingWithColum);
                root.setCenter(table);
                table.setVisible(true);

            }
        });
        boxPane.setHgap(100);
        boxPane.setPadding(new Insets(10, 10, 10, 60));
        boxPane.getChildren().addAll(start, stop, displayPlayers);
        root = new BorderPane();
        bottomPane = new BorderPane();
        bottomPane.setCenter(boxPane);
        root.setCenter(table);
        root.setBottom(bottomPane);

        Scene scene = new Scene(root, 820, 550);

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
