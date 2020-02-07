/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.sql.*;
import org.json.JSONException;
import org.json.JSONObject;

public class DeleteController {

    private DatabaseConnection con = new DatabaseConnection();
    JSONObject result;

    public void delete(String playerX, String playerO) throws SQLException, JSONException {
        try {
            String query = "delete from game where playerX = (select id from player where name = ?) and playerO = (select id from player where name = ?)";
            PreparedStatement preparedStmt = con.getCon().prepareStatement(query);
            preparedStmt.setString(1, playerX);
            preparedStmt.setString(2, playerO);
            preparedStmt.execute();
            result.put("type", "delete");
            result.put("res", "Successfuly");
            
        }catch (SQLException ex){
            result.put("type", "delete");
            result.put("res", "failed");
        }

    }
    public JSONObject getResult(){
        return result;
    }
}
