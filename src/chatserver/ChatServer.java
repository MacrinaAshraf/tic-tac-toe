
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
class Client{    
    String userName;
    String playingWith;
    int score;
    DataInputStream diss;
    PrintStream pss;
    
    Client(DataInputStream dis,PrintStream ps){
        diss=dis;
        pss=ps;      
       
    }
    public String getPlayingWith() {
        return playingWith;
    }
    public void setPlayingWith(String playingWith) {
        this.playingWith = playingWith;
    }
    public DataInputStream getDataInputStream() {
        return diss;
    }
    public void setDataInputStream(DataInputStream diss) {
        this.diss = diss;
    }
    public PrintStream getPrintStream() {
        return pss;
    }
    public void setPrintStream(PrintStream pss) {
        this.pss = pss;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    
}


public class ChatServer {
    ServerSocket myServerSocket=null;
    public static void main(String[] args) throws IOException {
      new ChatServer(); 
    }
    public ChatServer() throws IOException{
        myServerSocket=new ServerSocket(5005);
        //waiting on socket
        while(true){
            Socket socket=myServerSocket.accept();
            //create socket and send it to chathandler
            new ChatHandler(socket);
        }
        
    }
  
    
}
  class ChatHandler extends Thread{
      Client c;
      DataInputStream dis;
      PrintStream ps;
      //dictionary {value : username, key: its printstream}
      static Dictionary streams= new Hashtable();
      //vectors with the clients objects
      static Vector<Client> clientsVector=new Vector<Client>();
      public ChatHandler(Socket socket) throws IOException{
          try{
              //making streams on socket, create client object and send to it the streams
              dis=new DataInputStream(socket.getInputStream());
              ps=new PrintStream(socket.getOutputStream());
              c=new Client(dis,ps);              
              start();              
          }
          catch(IOException e){
              e.printStackTrace();
          }
      }
     public void run(){         
             String str;
             try {
                 //first input is the username
                 str = dis.readLine(); 
                 //set the username of the object
                 c.setUserName(str);
                 //set the username as key and its stream as value in dictionary
                 streams.put(c.getUserName(),c.getPrintStream());                    
                 clientsVector.add(c);                   
                 //the second input is the name of the opponent
                 str = dis.readLine();                 
                 c.setPlayingWith(str);               
             } catch (IOException ex) {
                 Logger.getLogger(ChatHandler.class.getName()).log(Level.SEVERE, null, ex);
             }
             //listen from the third input to the end 
             while (true){
                 try {
                     str = dis.readLine();
                      sendMessage(str);
                 } catch (IOException ex) {
                     Logger.getLogger(ChatHandler.class.getName()).log(Level.SEVERE, null, ex);
                 }
                
             }
                
     }
     public void sendMessage(String msg){    
        //get the stream of the player's opponent from the dictionary with getPLayingWith
        PrintStream opponentPS=(PrintStream) streams.get(c.getPlayingWith());
        //write in the player's stream and his/her opponent's stream
        opponentPS.println(msg);
        c.getPrintStream().println(msg);
       
     }
      
    }