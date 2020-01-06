
package chatserver;
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

public class ChatServer {
    ServerSocket myServerSocket = null;
    public static void main(String[] args) throws IOException {
      new ChatServer(); 
    }
    public ChatServer() throws IOException{
        myServerSocket = new ServerSocket(5008);
        //waiting on socket
        while(true){
            Socket socket = myServerSocket.accept();
            //create socket and send it to chathandler
            new ChatHandler(socket);
        }
        
    }
  
    
}