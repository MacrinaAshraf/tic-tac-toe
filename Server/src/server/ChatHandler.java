package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

class ChatHandler extends Thread {

    Client client;
    DataInputStream dis;
    PrintWriter ps;
    boolean keepRunning;

    //dictionary {value : username, key: its printstream}
    static Dictionary streams = new Hashtable();

    //vectors with the clients objects
    static Vector<Client> clientsVector = new Vector<Client>();

    public ChatHandler(Socket socket) throws IOException {
        try {
            //making streams on socket, create client object and send to it the streams
            dis = new DataInputStream(socket.getInputStream());
            ps = new PrintWriter(socket.getOutputStream(),true);
           // client = new Client(dis, ps);    
          //  clientsVector.add(client);
            start();           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        String str;
        JSONObject message;
      ps.print("hiii");
       while(true){
        
            try {             
                str = dis.readLine();
                message = new JSONObject(str);
                String type = (String) message.get("type");
                switch(type){
                    case "invite":
                        invite(message);
                        break;
                    case "responsetoinvite":
                         respondToInvite(message);
                         break;
                    case "login":
                         login(message);
                         break;
                    case "register":
                        System.out.print("hello");
                        register(message);
                        break;
                    case "chat":
                        sendMessage(message.toString());
                }
            } catch (IOException ex) {
                Logger.getLogger(ChatHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JSONException ex) {
                Logger.getLogger(ChatHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }     

        
    }

    public void stopSending() {
        keepRunning = false;
    }
    public void sendMessage(String msg) {
        //get the stream of the player's opponent from the dictionary with getPLayingWith
        PrintStream opponentPS = (PrintStream) streams.get(client.getPlayingWith());
        //write in the player's stream and his/her opponent's stream
        opponentPS.println(msg);
        client.getPrintStream().println(msg);
    }
    public void invite(JSONObject msg) throws JSONException {
        //get the stream of the one to ask to play via the json Object toPlayWith key
        PrintStream playerToPlayWithStream = (PrintStream) streams.get(msg.get("toPlayWith"));
        //write in the player's stream and his/her opponent's stream
        JSONObject inviteMessage = new JSONObject();
        //setting mesage type key and the username of the asking player
        inviteMessage.put("type", "invite");
        inviteMessage.put("username", msg.get("askingplayername"));
        //sendint to the player the invitation object
        playerToPlayWithStream.println(inviteMessage.toString());
    }
    public void respondToInvite(JSONObject msg) throws JSONException {
        //getting the stream of the player to respond to
        PrintStream opponentPS = (PrintStream) streams.get(msg.get("toPlayWith"));
        JSONObject responseToInviteMessage = new JSONObject();
        //setting the type of the JSON Object and username of the player who accepted/rejected the invitation
        responseToInviteMessage.put("type", "responsetoinvite");
        responseToInviteMessage.put("username", msg.get("username"));
        if (msg.get("response") == "accept") {
            //setting connections
            String username = (String) msg.get("username");
            String toPlayWith = (String) msg.get("toPlayWith");
            for (int i = 0; i < clientsVector.size(); i++) {
                if (clientsVector.elementAt(i).getUserName() == msg.get("toPlayWith")) {
                    clientsVector.elementAt(i).setPlayingWith(username);
                } else if (clientsVector.elementAt(i).getUserName() == msg.get("username")) {
                    clientsVector.elementAt(i).setPlayingWith(toPlayWith);
                }
            }
            responseToInviteMessage.put("response", "accept");

        } else {
            responseToInviteMessage.put("response", "rejected");

        }
        //sending the response JSON object to the askingPlayer
        opponentPS.println(responseToInviteMessage.toString());
    }
    public void login(JSONObject data) throws JSONException{
        LoginController login= new LoginController();
        login.Check((String)data.get("username"), (String)data.get("password"));
        ps.flush();
        ps.print(login.getResult());
        ps.flush();
    }
    public void register(JSONObject data) throws JSONException, IOException{
    
      SignUpController signup= new SignUpController();
      signup.update((String)data.get("username"), (String)data.get("password"), (String)data.get("email"));
      ps.flush();
      ps.print(signup.getResult());
    }

}
