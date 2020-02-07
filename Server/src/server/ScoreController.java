/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Adham
 */
public class ScoreController {

    private DatabaseConnection con = new DatabaseConnection();
    PreparedStatement stmt;
    ResultSet rs;
    JSONObject result;
    public  ScoreController() {
        result=new JSONObject();
    }

    public void setScore(String userName,String score){
        String query = "UPDATE Player SET score= ? WHERE name=?;";
        
    	try{
            
    	    PreparedStatement pst = con.getCon().prepareStatement(query);
            pst.setString(1,score);
            pst.setString(2,userName);
            pst.executeUpdate();
            try {
                result.put("res", "Successfuly");
            } catch (JSONException ex) {
                Logger.getLogger(ScoreController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
         }
        catch(SQLException ex){
            try {
                result.put("res", "Failed");
                System.err.println("Error in setScore: " + ex);
            } catch (JSONException ex1) {
                Logger.getLogger(ScoreController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }

    }
}
