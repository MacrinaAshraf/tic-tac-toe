
package server;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Dictionary;
import java.util.Hashtable;
import org.json.JSONObject;

public class ChatServer {
    ServerSocket myServerSocket;
    boolean keepRunning;
    ChatHandler ch;
    
    public ChatServer() throws IOException{
    }
    public void start(){
        try {
            keepRunning = true;
            //waiting on socket
            myServerSocket = new ServerSocket(5008);
            System.out.println("i am about to run");
            while(keepRunning){
                //create socket and send it to chathandler
                if(!keepRunning){
                    System.out.println("stopped");
                    break;
                }
              Socket socket = myServerSocket.accept();
              System.out.println("I am running");
              ch = new ChatHandler(socket);
                
                
            }
        } catch (IOException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            myServerSocket.close();
            ch.stopSending();
            System.out.println("Stopped");
        }catch(IOException ex) {
           System.out.println(ex);
        }
        
    }
    public void stop(){
        keepRunning = false;
        try {
            //new Socket("localhost", 5008);
            myServerSocket.close();
            System.out.println("Stoppeds");
        }catch(Exception ex){
            System.out.println(ex);
        }

    }
}