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

/**
 *
 * @author Ismail_khadr
 */
public class LoginController {
	DatabaseConnection con;
	PreparedStatement stmt;
	ResultSet rs ;
	
	public void Check(String username, String password) {
	    try {
	    	System.out.println("Connected to database!");
	        stmt = con.getCon().prepareStatement("SELECT * From player WHERE name = ? AND password = ?");
	        stmt.setString(1, username);
	        stmt.setString(2, password);
	        rs = stmt.executeQuery();
	        boolean flag = true;
	        
	        if(rs.next()) {
	        	System.out.println("you are welcome");
	            flag = false;
	        }
	        
	        if(flag) {
	        	System.err.println("Not a member");
	        }
	    } catch (SQLException ex) {
	            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
}