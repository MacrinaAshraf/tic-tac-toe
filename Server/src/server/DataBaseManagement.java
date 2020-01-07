package server;
import java.sql.*;
import java.sql.Connection;
public class DataBaseManagement {
    Connection con;  
    public DataBaseManagement(){
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tictactoe_db","root","root");
            System.out.println("Running");
        }catch(ClassNotFoundException | SQLException e){ System.out.println(e);}
    }
    public void update(String FullName,String Password,String Email){
        String query = "INSERT INTO Player (name,Password,email) VALUES (?,?,?);";
        try{
            //System.out.println("notInserted");
            PreparedStatement pst;
            pst = con.prepareStatement(query);
            pst.setString(1,FullName);
            pst.setString(2,Password);
            pst.setString(3,Email);
            
            pst.executeUpdate();
            System.out.println("Inserted");
        }catch(SQLException ex){
            System.err.println("Error in update: "+ ex);
        }
    }
    public static void main(String[] args) {
        DataBaseManagement db = new DataBaseManagement();
    }
}
