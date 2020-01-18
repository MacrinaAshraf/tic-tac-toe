package gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javafx.application.Platform;

public class Client {

	static Player player;
	String errorMessage;
	Socket clientSocket;
	BufferedReader dis;
	PrintStream ps;
	Boolean keepRunning;
	JSONObject sendJson;
	JSONObject recieveJson;

	private Vector<JSONObject> goldPlayers = new Vector<JSONObject>();
	private Vector<JSONObject> silverPlayers = new Vector<JSONObject>();
	private Vector<JSONObject> bronzePlayers = new Vector<JSONObject>();

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
							System.out.println(msg);
							recieveJson = new JSONObject(msg);
							String type = (String) recieveJson.get("type");
							switch (type) {
							case "login":
								handleLogin();
								break;
							case "playerlist":
								fillPlayersVectors();
								break;
							case "invite":
								getInviterInfo();
								break;
							case "register":
								handleRegister();
								break;
							case "responsetoinvite":
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

	protected void getInviterInfo() {
		// TODO Auto-generated method stub

	}

	public Vector<JSONObject> getGoldPlayers() {
		return goldPlayers;
	}

	public Vector<JSONObject> getSilverPlayers() {
		return silverPlayers;
	}

	public Vector<JSONObject> getBronzePlayers() {
		return bronzePlayers;
	}

	public void fillPlayersVectors() {
		try {
			JSONArray goldList = recieveJson.getJSONArray("Gold");
			JSONArray silverList = recieveJson.getJSONArray("Silver");
			JSONArray bronzeList = recieveJson.getJSONArray("bronze");

			Vector<JSONObject> gold = new Vector<JSONObject>();
			Vector<JSONObject> silver = new Vector<JSONObject>();
			Vector<JSONObject> bronze = new Vector<JSONObject>();

			for (int i = 0; i < goldList.length(); i++) {
				gold.add(goldList.getJSONObject(i));
			}
			for (int i = 0; i < silverList.length(); i++) {
				silver.add(silverList.getJSONObject(i));
			}
			for (int i = 0; i < bronzeList.length(); i++) {
				bronze.add(bronzeList.getJSONObject(i));
			}

			goldPlayers = gold;
			silverPlayers = silver;
			bronzePlayers = bronze;

			System.out.println("values " + bronzePlayers.size());

			System.out.println(HomePageController.playersMenuControl == null);

			if (HomePageController.playersMenuControl != null
					&& HomePageController.playersMenuControl.getList() != null)
				HomePageController.playersMenuControl.getList().refresh();

		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	public void handleLogin() {
		String result = null;
		try {
			result = (String) recieveJson.get("res");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		if (result.equals("Successfully")) {
			setPlayer();
		} else {
			player.setId(-1);
		}
	}

	public void handleRegister() {
		String result = null;
		try {
			result = (String) recieveJson.get("res");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		if (result.equals("Successfully")) {
			setPlayer();
		} else {
			errorMessage = "Either username or email is duplicate";
			player.setId(-1);
		}
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer() {
		try {
			player.setName((String) recieveJson.get("name"));
			player.setScore((int) recieveJson.get("score"));
			player.setId(recieveJson.getInt("id"));
		} catch (JSONException e) {
			e.printStackTrace();
			// System.out.println("");
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
		sendToServer();

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
		sendToServer();

	}

	public void stop() throws JSONException {
		sendJson.put("type", "stop");
		sendToServer();
	}

	public void logout() throws JSONException {
		System.out.println("logout");
		sendJson.put("type", "logout");
		sendToServer();

	}

	public void endOfGame(String userName, int score, String userName2, int score2) throws JSONException {
		sendJson.put("type", "endofgame");
		sendJson.put("score", score);
		sendToServer();
	}

	public void sendToServer() {
		ps.println(sendJson);
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
