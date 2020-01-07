package server;
import java.sql.*;
import java.sql.Connection;

public class SignUpController {
    DatabaseConnection con = new DatabaseConnection();  
    
    public void update(String FullName, String Password, String Email){
    	String query = "INSERT INTO Player (name,Password,email) VALUES (?,?,?);";
        
    	try{
            
    		PreparedStatement pst = con.getCon().prepareStatement(query);
            pst.setString(1,FullName);
            pst.setString(2,Password);
            pst.setString(3,Email);
            
            pst.executeUpdate();
            
            System.out.println("Inserted");
        }catch(SQLException ex){
            System.err.println("Error in update: " + ex);
        }
    }
    public static void main(String[] args) {
        SignUpController db = new SignUpController();
    }
}
