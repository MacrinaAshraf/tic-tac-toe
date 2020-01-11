package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class GameHandler extends Thread {
    Client client;
    BufferedReader dis;
    Socket clientSocket;
    PrintStream ps;
    int placeInVector;
    boolean keepRunning;
    AllPlayers players;
    //dictionary {value : username, key: its printstream}
    static Dictionary streams = new Hashtable();
   
    public GameHandler(Socket socket) throws IOException, SQLException {
        clientSocket=socket;
        
       
        try {
            //making streams on socket, create client object and send to it the streams
            dis = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ps = new PrintStream(socket.getOutputStream());
            System.out.print(ps);
            System.out.println(dis);
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            String str;
            JSONObject message;
            keepRunning = true;
            String inputLine;
            ps.flush();            
            while ((inputLine = dis.readLine()) != null) {               
                try {                   
                    message = new JSONObject(inputLine);
                    String type = (String) message.get("type");                    
                    switch (type) {
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
                            register(message);
                            break;
                        case "chat":
                            sendMessage(message.toString());
                            break;
                        case "endofgame":
                            endOfGame(message);
                            break;
                        case "stop":
                            System.out.println("stop");
                            stopClient();                            
                            break;                           
                    }
                } catch (IOException | JSONException ex) {                    
                    Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
        } catch (IOException ex) {         
           
            GameServer.clientsVector.elementAt(placeInVector).setStatus("offline");
            GameServer.clientsVector.elementAt(placeInVector).setIsPlaying(false);
            
        } catch(NullPointerException e){                     
            e.printStackTrace();
                          
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
        System.out.print(streams.get(msg.get("toPlayWith")));
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
        if (msg.get("response").equals("accept") ){
            //setting connections
            String username = (String) msg.get("username");
            String toPlayWith = (String) msg.get("toPlayWith");
            for (int i = 0; i < GameServer.clientsVector.size(); i++) {
                if (GameServer.clientsVector.elementAt(i).getUserName().equals(msg.get("toPlayWith")) ){
                    GameServer.clientsVector.elementAt(i).setPlayingWith(username);
                    GameServer.clientsVector.elementAt(i).setIsPlaying(true);
                } else if (GameServer.clientsVector.elementAt(i).getUserName().equals( msg.get("username"))) {
                    GameServer.clientsVector.elementAt(i).setPlayingWith(toPlayWith);
                    GameServer.clientsVector.elementAt(i).setIsPlaying(true);
                }
            }
            responseToInviteMessage.put("response", "accept");
            playersJSON();

        } else {
            responseToInviteMessage.put("response", "rejected");

        }
        //sending the response JSON object to the askingPlayer
        opponentPS.println(responseToInviteMessage.toString());
    }
    public void login(JSONObject data) throws JSONException {
        LoginController login = new LoginController();
        login.Check((String) data.get("username"), (String) data.get("password"));
        ps.println(login.getResult());
        if (login.getResult().get("res") == "Successfully") {
            for (Client c : GameServer.clientsVector) {
                if (c.getUserName().equals(data.get("username")) ) {
                    c.setStatus("online");
                    c.setDataInputStream(dis);
                    c.setPrintStream(ps);
                    placeInVector=GameServer.clientsVector.indexOf(c);              
                    playersJSON();
                    streams.put(c.getUserName(),c.getPrintStream());
                }
            }
        }

    }
    public void register(JSONObject data) throws JSONException, IOException {
        SignUpController signup = new SignUpController();
        signup.update((String) data.get("username"), (String) data.get("password"), (String) data.get("email"));
        if (signup.getResult().get("res").equals("Successfully")) {
            Client temp = new Client();
            temp.setUserName((String) data.get("username"));
            GameServer.clientsVector.add(temp);
            placeInVector=GameServer.clientsVector.size()-1;
            playersJSON();
        }
    }
    public static void playersJSON() throws JSONException {
        JSONObject player;
        //players tiers (Gold, Silver, Bronze)
        JSONArray playersJSONArrayGold = new JSONArray();
        JSONArray playersJSONArraySilver = new JSONArray();
        JSONArray playersJSONArrayBronze = new JSONArray();
        JSONObject playersJSONObject = new JSONObject();
        
        for (Client c : GameServer.clientsVector) {
            try {
                player = new JSONObject();
                player.put("username", c.getUserName());
                player.put("score", c.getScore());
                player.put("status", c.getStatus());

                if (c.getScore() >= 300) {
                    playersJSONArrayGold.put(player);
                } else if (c.getScore() >= 200) {
                    playersJSONArraySilver.put(player);
                } else {
                    playersJSONArrayBronze.put(player);
                }

            } catch (JSONException ex) {
                Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        playersJSONObject.put("Gold", playersJSONArrayGold);
        playersJSONObject.put("Silver", playersJSONArraySilver);
        playersJSONObject.put("bronze", playersJSONArrayBronze);
        playersJSONObject.put("type", "playerlist");
        for (Client c : GameServer.clientsVector) {
            if(c.getPrintStream()!=null)
              c.getPrintStream().println(playersJSONObject);        
        }
       
       
    }
    public void endOfGame(JSONObject data){
        for (Client client : GameServer.clientsVector) {
            try {
                if(client.getUserName().equals(data.get("username"))){
                    client.setIsPlaying(false);
                    client.setScore((int)data.get("score"));
                }
               
            } catch (JSONException ex) {
                Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            playersJSON();
        } catch (JSONException ex) {
            Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void stopClient(){
        try {
            System.out.print(GameServer.clientsVector.size());
            System.out.print(placeInVector);
            GameServer.clientsVector.elementAt(placeInVector).setStatus("offline");
            GameServer.clientsVector.elementAt(placeInVector).setIsPlaying(false);
            GameServer.clientsVector.elementAt(placeInVector).setPlayingWith(null);
            System.out.print(GameServer.clientsVector.elementAt(placeInVector).getStatus());          
            ps.close();
            dis.close();
            clientSocket.close();
            keepRunning=false;           
            stop();
        } catch (IOException ex) {
            Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}
