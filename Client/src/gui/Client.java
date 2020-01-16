package gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import org.json.JSONException;
import org.json.JSONObject;

public class Client {

	static Player player; 
	Socket clientSocket;
	BufferedReader dis;
	PrintStream ps;
	JSONObject message;
	Boolean keepRunning;
	JSONObject sendJson;
	JSONObject recieveJson;

	public Client() {
		player = new Player();
		sendJson = new JSONObject();
		try {
			keepRunning = true;
			clientSocket = new Socket("127.0.0.1", 5008);
			dis = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			ps = new PrintStream(clientSocket.getOutputStream());

			Thread th = new Thread(new Runnable() {

				@Override
				public void run() {
					while (keepRunning) {

						try {
							System.out.println("client !");
							String msg = dis.readLine(); 
							recieveJson = new JSONObject(msg);
							String type = (String) recieveJson.get("type");
							switch(type) {
							case "login":
								handleLogin();
								break;
							}
						} catch (IOException ex) {

							keepRunning = false;
							System.out.print("server has closed");
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}

			});
			th.start();
		} catch (IOException e) {
			System.out.print("hi");
			e.printStackTrace();
			keepRunning = false;
			stopConnection();
		}

	}
	
	public void handleLogin(){
		String result = null;
		try {
			result = (String) recieveJson.get("res");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		if(result.equals("Successfully")) {
			setPlayer();
		}
		else {
			player.setId(-1);
		}
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer() {
		// TODO Auto-generated method stub
		try {
			player.setName((String) recieveJson.get("name"));
			player.setId(recieveJson.getInt("id"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void sendStopSignalToServer() {
		try {
			message.put("type", "stop");
			System.out.print(message);
			ps.print(message);
			System.out.print("closed");
		} catch (JSONException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void stopConnection() {
		try {
			clientSocket.close();
		} catch (IOException ex) {
			System.out.print("hi"); //
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
		} catch (NullPointerException e) {
			System.out.print("closed");
		}
	}

	public void login(String userName, String password) throws JSONException {
		sendJson.put("type", "login");
		sendJson.put("username", userName);
		sendJson.put("password", password);

	}

	public void invite(String userName1, String userName2) throws JSONException {
		sendJson.put("type", "invite");
		sendJson.put("askingplayername", userName1);
		sendJson.put("toPlayWith", userName2);
	}

	public void responsetoinvite(String userName1, String userName2, boolean inviteStatus) throws JSONException {
		sendJson.put("type", "responsetoinvite");
		sendJson.put("toPlayWith", userName1);
		sendJson.put("username", userName2);
		sendJson.put("response", inviteStatus);
	}

	public void register(String userName, String password, String email) throws JSONException {
		sendJson.put("type", "register");
		sendJson.put("username", userName);
		sendJson.put("password", password);
		sendJson.put("email", email);

	}

	public void stop(String userName) throws JSONException {
		sendJson.put("type", "stop");
		sendJson.put("username", userName);
	}

	public void logout(String userName) throws JSONException {
		sendJson.put("type", "logout");
		sendJson.put("username", userName);
	}

	public void endOfGame(String userName, int score, String userName2, int score2) throws JSONException {
		sendJson.put("type", "endofgame");
		sendJson.put("score1", score);
		sendJson.put("username", userName);

	}
}
