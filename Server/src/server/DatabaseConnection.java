package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	Connection con;
	
	public DatabaseConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tictactoe_db","root","mohamedshafik");
            System.out.println("Running");
        }catch(ClassNotFoundException | SQLException e){ System.out.println(e);}
    }
	
	public Connection getCon() {
		return con;
	}

}
