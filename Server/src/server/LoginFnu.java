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

/**
 *
 * @author Ismail_khadr
 */
public class LoginFnu {
    Connection con;
     Statement stmt;
     ResultSet rs ;
public LoginFnu(){
    try{
            Class.forName("com.mysql.jdbc.Driver");  
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tictactoe_db","root","root");
            System.out.println("Running");
        }catch(ClassNotFoundException | SQLException e){ System.out.println(e);}
    
}
public void Check(String username ,String password)
{
    
        try {
            
            System.out.println("Connected to database !");
            stmt = con.createStatement();
            String sql = "select * from player";
             rs = stmt.executeQuery(sql);
             boolean flag =true;
             while(rs.next()){
                 
                 if(username.equals(rs.getString(2))&&password.equals(rs.getString(3))){
                 
                     System.err.println("you are welcome");
                     flag=false;
                     break;
                 
                 }
              if(flag){System.err.println("not amember");}
             
             }
        } catch (SQLException ex) {
            Logger.getLogger(LoginFnu.class.getName()).log(Level.SEVERE, null, ex);
        }



}

public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
