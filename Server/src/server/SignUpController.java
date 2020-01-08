package server;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.*;
import java.sql.Connection;
import org.json.JSONException;
import org.json.JSONObject;

public class SignUpController {
    private DatabaseConnection con = new DatabaseConnection(); 
    Socket mySocket;
    DataInputStream dis ;
    PrintStream ps ;   
    JSONObject result;

    
    public void update(String FullName, String Password, String Email) throws IOException, JSONException{
        result=new JSONObject();
    	String query = "INSERT INTO Player (name,Password,email) VALUES (?,?,?);";
        
    	try{
            
    	    PreparedStatement pst = con.getCon().prepareStatement(query);
            pst.setString(1,FullName);
            pst.setString(2,Password);
            pst.setString(3,Email);
            
            pst.executeUpdate();
            result.put("res", "Successfuly");
            System.out.println("Inserted");
        }catch(SQLException ex){
            result.put("res", "Failed");
            System.err.println("Error in update: " + ex);
        }
       
    }
    public JSONObject getResult(){
        return result;
    }
}
