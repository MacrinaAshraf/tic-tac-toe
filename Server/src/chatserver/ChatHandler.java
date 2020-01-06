package chatserver;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

class ChatHandler extends Thread{
	Client client;
	DataInputStream dis;
	PrintStream ps;
    
	//dictionary {value : username, key: its printstream}
    static Dictionary streams = new Hashtable();

    //vectors with the clients objects
    static Vector<Client> clientsVector = new Vector<Client>();
    
    public ChatHandler(Socket socket) throws IOException{
    	try {
    	//making streams on socket, create client object and send to it the streams
    		dis = new DataInputStream(socket.getInputStream());
    		ps = new PrintStream(socket.getOutputStream());
    		client = new Client(dis,ps);              
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
		   client.setUserName(str);
		   //set the username as key and its stream as value in dictionary
		   streams.put(client.getUserName(), client.getPrintStream());                    
		   clientsVector.add(client);                   
		   //the second input is the name of the opponent
		   str = dis.readLine();                 
		   client.setPlayingWith(str);               
	   } catch (IOException ex) {
		   Logger.getLogger(ChatHandler.class.getName()).log(Level.SEVERE, null, ex);
	   }
    	//listen from the third input to the end 
    	while (true) {
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
		PrintStream opponentPS=(PrintStream) streams.get(client.getPlayingWith());
		//write in the player's stream and his/her opponent's stream
		opponentPS.println(msg);
		client.getPrintStream().println(msg);
	}
    
}