/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.sql.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoadController {

    private DatabaseConnection con = new DatabaseConnection();
    JSONObject result;

    public void load(String player1, String player2) throws IOException, JSONException, SQLException {
        PreparedStatement stmt = con.getCon().prepareStatement("SELECT * From game where playerX = ? and playerO = ?");
        stmt.setString(1, player1);
        stmt.setString(2, player2);
        ResultSet rs = stmt.executeQuery();
        
        String cells = "";
        cells+=rs.getString("cell11");
        cells+=rs.getString("cell12");
        cells+=rs.getString("cell13");
        cells+=rs.getString("cell21");
        cells+=rs.getString("cell22");
        cells+=rs.getString("cell23");
        cells+=rs.getString("cell31");
        cells+=rs.getString("cell32");
        cells+=rs.getString("cell33");
        
        result.put("type", "load");
        result.put("res", "Successfuly");
        result.put("playerX", rs.getString("playerX"));
        result.put("playerO", rs.getString("playerO"));
        result.put("turn", rs.getString("turn"));
        result.put("cells", cells);
        
    }

    public JSONObject getResult() {
        return result;
    }

    public static Boolean checkGameExist(String playerX, String playerO) throws SQLException {
        //check if game exist where playerX is the first and playerO is the second
        //swap to check the other way around
        DatabaseConnection con = new DatabaseConnection();
        PreparedStatement stmtPlayerId = con.getCon().prepareStatement("SELECT id From player where name = ?");
        stmtPlayerId.setString(1,playerX);
        ResultSet rs = stmtPlayerId.executeQuery();
        int playerXId = Integer.parseInt(rs.getString("id"));
        stmtPlayerId.setString(1,playerO);
        rs = stmtPlayerId.executeQuery();
        int playerOId = Integer.parseInt(rs.getString("id"));
        
        PreparedStatement stmt = con.getCon().prepareStatement("SELECT * From game where playerX = ? and playerO = ?");
        stmt.setInt(1, playerXId);
        stmt.setInt(2, playerOId);
        rs = stmt.executeQuery();
        return rs.next();
    }
}
