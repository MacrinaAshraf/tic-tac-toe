package signup;
import java.sql.*;
import java.sql.Connection;
public class DataBaseManagement {
    Connection con;  
    public DataBaseManagement(){
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Player","root","R12!dff2svF0");
            System.out.println("Running");
        }catch(Exception e){ System.out.println(e);}
    }
    public void update(String FirstName,String LastName,String Email,String Password){
        String query = "INSERT INTO Players (FirstName,LastName,Email,Password) VALUES (?,?,?,?);";
        try{
            //System.out.println("notInserted");
            PreparedStatement pst;
            pst = con.prepareStatement(query);
            pst.setString(1,FirstName);
            pst.setString(2,LastName);
            pst.setString(3,Email);
            pst.setString(4,Password);
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
