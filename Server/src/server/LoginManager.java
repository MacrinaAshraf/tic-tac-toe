/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.PreparedStatement;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Ismail_khadr
 */
public class LoginManager {

    private DatabaseConnection con = new DatabaseConnection();
    PreparedStatement stmt;
    ResultSet rs;
    JSONObject result;

    public void Check(String username, String password) throws JSONException {
        result = new JSONObject();
        try {
            System.out.println("Connected to database!");
            stmt = con.getCon().prepareStatement("SELECT id, name, score From player WHERE name = ? AND password = ?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            boolean flag = true;

            if (rs.next()) {
                System.out.println("you are welcome");
                result.put("res", "Successfully");
                result.put("type", "login");
                result.put("name", rs.getString("name"));
                result.put("id", rs.getInt("id"));
                result.put("score", rs.getInt("score"));
                flag = false;
            }

            if (flag) {
                result.put("type", "login");
                result.put("res", "failed");
                System.err.println("Not a member");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public JSONObject getResult() {
        return result;
    }
}
