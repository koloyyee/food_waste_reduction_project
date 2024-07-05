package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * TODO: singleton connection 
 * read from properties file.
 * */
public class DBConnection {
	private Connection connection;
	private static DBConnection instance;
	private String user = "";
	private String pass= "";
	private String url= "";

	
	public void getConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, pass);
			
		} catch (SQLException | ClassNotFoundException e ) {
			
			// log 
			System.err.println(e.getLocalizedMessage());
		}
	}
	
	public static DBConnection getInstance() {
		
		
		if ( instance == null ) {
			synchronized (DBConnection.class) {
				if ( instance == null ) {
					instance = new DBConnection();
				}
			}
		}
		return instance;
	}

}
