/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.*;
import java.sql.Connection;
import org.json.JSONException;
import org.json.JSONObject;

public class SaveController {
    private DatabaseConnection con = new DatabaseConnection();    
    JSONObject result;
    public void insertIntoDatabase(String cells, char turn, String playerX, String playerO) throws IOException, JSONException, SQLException{
        result = new JSONObject();
        PreparedStatement stmtPlayerId = con.getCon().prepareStatement("SELECT id From player where name = ?");
        stmtPlayerId.setString(1,playerX);
        ResultSet rs = stmtPlayerId.executeQuery();
        int playerXId = Integer.parseInt(rs.getString("id"));
        stmtPlayerId.setString(1,playerO);
        rs = stmtPlayerId.executeQuery();
        int playerOId = Integer.parseInt(rs.getString("id"));
    	String query = "INSERT INTO Player (playerX, playerO, cell11, cell12, cell13, cell21, cell22, cell23, cell13, cell23, cell33, turn) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
    	try{
            char[] cellsArr = cells.toCharArray();
    	    PreparedStatement pst = con.getCon().prepareStatement(query);
            pst.setInt(1,playerXId);
            pst.setInt(2,playerOId);
            pst.setString(3,String.valueOf(cellsArr[0]));
            pst.setString(4,String.valueOf(cellsArr[1]));
            pst.setString(5,String.valueOf(cellsArr[2]));
            pst.setString(6,String.valueOf(cellsArr[3]));
            pst.setString(7,String.valueOf(cellsArr[4]));
            pst.setString(8,String.valueOf(cellsArr[5]));
            pst.setString(9,String.valueOf(cellsArr[6]));
            pst.setString(10,String.valueOf(cellsArr[7]));
            pst.setString(11,String.valueOf(cellsArr[8]));
            pst.setString(12,String.valueOf(cellsArr[9]));
            pst.setString(11,String.valueOf(turn));
            pst.executeUpdate();
            result.put("type","save");
            result.put("res", "Successfuly");
            System.out.println("Game Inserted");
            
        }catch(SQLException ex){
            result.put("type","save");
            result.put("res", "failed");
            System.err.println("Error in insertion: " + ex.getMessage());
        }
    }
    public JSONObject getResult(){
        return result;
    }
}
