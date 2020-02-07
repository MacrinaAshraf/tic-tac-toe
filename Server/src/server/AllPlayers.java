/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONObject;

/**
 *
 * @author COMPUTER SHOP
 */
public class AllPlayers {
    private DatabaseConnection con = new DatabaseConnection();
	PreparedStatement stmt;
	ResultSet rs ;
	JSONObject result;
        
   public void getAllPlayers() throws SQLException{
        stmt = con.getCon().prepareStatement("SELECT name, score From player");        
        rs = stmt.executeQuery();
        Client tmp;
        while(rs.next()){
               tmp = new Client();
               tmp.setUserName(rs.getString("name"));
               tmp.setScore(rs.getInt("score"));          
               GameServer.clientsVector.add(tmp);
           }
   }
}
