
package server;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Dictionary;
import java.util.Hashtable;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import org.json.JSONException;
import org.json.JSONObject;

public class GameServer {
    ServerSocket myServerSocket;
    static boolean keepRunning;
    GameHandler ch;
    AllPlayers players;
    static Vector<Client> clientsVector = new Vector<Client>();
    static int countStart=0;
    JSONObject message;
    public GameServer() throws IOException{
        
    }
    public void start() throws SQLException{
        try {
            keepRunning = true;
            //waiting on socket
            myServerSocket = new ServerSocket(5008);
            if(countStart==0){
             players = new AllPlayers();
             players.getAllPlayers();
            }
            countStart++;
     
             
            System.out.println("i am about to run");
            while(keepRunning){
                //create socket and send it to chathandler
                if(!keepRunning){
                    System.out.println("stopped");
                    break;
                }
              Socket socket = myServerSocket.accept();
              System.out.println("I am running");
              ch = new GameHandler(socket);
              System.out.println("new online user");
                
            }
        } catch (IOException ex) {
            Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            myServerSocket.close();
            ch.stopSending();
            System.out.println("Stopped");
            stop();
        }catch(IOException ex) {
           System.out.println(ex);
        }
        
    }
    public void stop(){
        message=new JSONObject();
        for(Client c: clientsVector){
            if(c.getStatus().equals("online")){
                try {
                    System.out.print(message);
                    message.put("type", "stop");
                    c.getPrintStream().println(message);
                    c.setStatus("offline");
                    c.setIsPlaying(false);
                    c.setPrintStream(null);
                    c.setDataInputStream(null);
                    
                } catch (JSONException ex) {
                    Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            keepRunning = false;
        }
        try {
            //new Socket("localhost", 5008);
            myServerSocket.close();
            System.out.println("Stopped");
            
        }catch(Exception ex){
            System.out.println(ex);
        }

    }
}