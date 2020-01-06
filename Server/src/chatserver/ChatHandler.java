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
import org.json.JSONException;
import org.json.JSONObject;

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
        JSONObject message;
    	try {
		   //first input is the username
		   str = dis.readLine(); 
                   message=new JSONObject(str);
                   String type =(String)message.get("type");
                   if(type=="invite")
                       invite(message);
                   else if(type=="responsetoinvite")
                       respondToInvite(message);
                  
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
	   } catch (JSONException ex) {
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
        public void invite(JSONObject msg) throws JSONException{
          //get the stream of the one to ask to play via the json Object toPlayWith key
            PrintStream playerToPlayWithStream=(PrintStream) streams.get( msg.get("toPlayWith"));
	  //write in the player's stream and his/her opponent's stream
            JSONObject inviteMessage=new JSONObject();
           //setting mesage type key and the username of the asking player
            inviteMessage.put("type", "invite");
            inviteMessage.put("username", msg.get("askingplayername"));
            //sendint to the player the invitation object
            playerToPlayWithStream.println(inviteMessage.toString());
        }
        public void respondToInvite(JSONObject msg) throws JSONException{
            //getting the stream of the player to respond to
            PrintStream opponentPS=(PrintStream) streams.get( msg.get("toPlayWith"));
            JSONObject responseToInviteMessage=new JSONObject();
            //setting the type of the JSON Object and username of the player who accepted/rejected the invitation
            responseToInviteMessage.put("type", "responsetoinvite");
            responseToInviteMessage.put("username", msg.get("username"));
            if(msg.get("response")=="accept"){ 
                //setting connections
                String username=(String)msg.get("username");
                String toPlayWith=(String)msg.get("toPlayWith");
                for(int i=0;i<clientsVector.size();i++){
                    if(clientsVector.elementAt(i).getUserName()== msg.get("toPlayWith")){
                        clientsVector.elementAt(i).setPlayingWith(username);
                    }
                    else if(clientsVector.elementAt(i).getUserName()== msg.get("username")){
                        clientsVector.elementAt(i).setPlayingWith(toPlayWith);
                    }
                }
                responseToInviteMessage.put("response", "accept");
                
            }
            else {
                responseToInviteMessage.put("response", "rejected");
                
            }
            //sending the response JSON object to the askingPlayer
            opponentPS.println(responseToInviteMessage.toString());
        }
        
    
}