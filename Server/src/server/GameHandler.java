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
    static Vector<Integer> placesInVector = new Vector<Integer>();

    public GameHandler(Socket socket) throws IOException, SQLException {
        clientSocket = socket;

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
            while ((inputLine = dis.readLine()) != null && GameServer.keepRunning == true) {
                if (!GameServer.keepRunning) {
                    break;
                }
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
                        case "logout":
                            stopClient();
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
            System.out.println("stopped11");
        } catch (IOException ex) {
            GameServer.clientsVector.elementAt(placeInVector).setStatus("offline");
            GameServer.clientsVector.elementAt(placeInVector).setIsPlaying(false);
            ServerGUI.table.refresh();
            stopClient();

        } catch (NullPointerException e) {
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
        if (msg.get("response").equals("accept")) {
            //setting connections
            String username = (String) msg.get("username");
            String toPlayWith = (String) msg.get("toPlayWith");
            for (int n : placesInVector) {
                if (GameServer.clientsVector.elementAt(n).getUserName().equals(msg.get("toPlayWith"))) {
                    GameServer.clientsVector.elementAt(n).setPlayingWith(username);
                    GameServer.clientsVector.elementAt(n).setIsPlaying(true);
                } else if (GameServer.clientsVector.elementAt(n).getUserName().equals(msg.get("username"))) {
                    GameServer.clientsVector.elementAt(n).setPlayingWith(toPlayWith);
                    GameServer.clientsVector.elementAt(n).setIsPlaying(true);
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
        LoginManager login = new LoginManager();
        login.Check((String) data.get("username"), (String) data.get("password"));
        ps.println(login.getResult());
        if (login.getResult().get("res") == "Successfully") {
            for (Client c : GameServer.clientsVector) {
                if (c.getUserName().equals(data.get("username"))) {
                    c.setStatus("online");
                    c.setDataInputStream(dis);
                    c.setPrintStream(ps);
                    placeInVector = GameServer.clientsVector.indexOf(c);
                    placesInVector.add(placeInVector);
                    streams.put(c.getUserName(), c.getPrintStream());
                    playersJSON();
                }
            }
        }

    }

    public void register(JSONObject data) throws JSONException, IOException {
        SignUpManager signup = new SignUpManager();
        signup.update((String) data.get("username"), (String) data.get("password"), (String) data.get("email"));
        System.out.println(signup.getResult().get("res"));
        if (signup.getResult().get("res").equals("Successfuly")) {
            Client temp = new Client();
            temp.setUserName((String) data.get("username"));
            temp.setPrintStream(ps);
            temp.setDataInputStream(dis);
            temp.setStatus("online");
            GameServer.clientsVector.add(temp);
            placeInVector = GameServer.clientsVector.indexOf(temp);
            placesInVector.add(placeInVector);
            streams.put(temp.getUserName(), temp.getPrintStream());
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
                player.put("playing", c.getIsPlaying());

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
        PrintStream playerStream;
        String name;
        for (int n : placesInVector) {
            name = GameServer.clientsVector.elementAt(n).getUserName();
            playerStream = (PrintStream) streams.get(name);
            playerStream.println(playersJSONObject);
        }
        if (ServerGUI.table != null) {
            ServerGUI.table.refresh();
        }

    }

    public void endOfGame(JSONObject data) {
        int score = 0;
        String s;
        try {
            s = (String) data.get("score");
            score = Integer.parseInt(s);
        } catch (JSONException ex) {
            Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        GameServer.clientsVector.elementAt(placeInVector).setPlayingWith(null);
        GameServer.clientsVector.elementAt(placeInVector).setIsPlaying(false);
        GameServer.clientsVector.elementAt(placeInVector).setScore(score);
        try {
            playersJSON();
        } catch (JSONException ex) {
            Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void stopClient() {
        try {

            GameServer.clientsVector.elementAt(placeInVector).setStatus("offline");
            GameServer.clientsVector.elementAt(placeInVector).setIsPlaying(false);
            GameServer.clientsVector.elementAt(placeInVector).setPlayingWith(null);
            try {
                playersJSON();
            } catch (JSONException ex) {
                Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("hi");
            ServerGUI.table.refresh();
            ps.close();
            dis.close();
            clientSocket.close();
            keepRunning = false;
            stop();
        } catch (IOException ex) {
            Logger.getLogger(GameHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
