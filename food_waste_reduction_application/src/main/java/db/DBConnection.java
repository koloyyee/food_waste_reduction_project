package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import utils.Logger;
import utils.LoggerFactory;
import utils.PropertiesLoader;

public class DBConnection {

	private Logger log = LoggerFactory.getLogger();

	private Connection connection;
	private static DBConnection instance;
	private String user = "";
	private String pass = "";
	private String url = "";
	private Properties properties;

	public static void main(String[] args) {
		Connection conn = DBConnection.getInstance().getConnection();

	}

	private DBConnection() {
		properties = PropertiesLoader.load();
		String db = properties.getProperty("db");
		String host = properties.getProperty("host");
		String port = properties.getProperty("port");
		String dbname = properties.getProperty("dbname");

		user = properties.getProperty("user");
		pass = properties.getProperty("pass");
		url = "jdbc:%s://%s:%s/%s".formatted(db, host, port, dbname);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, pass);

		} catch (SQLException | ClassNotFoundException e) {

			// log
			log.warn(e.getLocalizedMessage());
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public static DBConnection getInstance() {

		if (instance == null) {
			synchronized (DBConnection.class) {
				if (instance == null) {
					instance = new DBConnection();
				}
			}
		}
		return instance;
	}

}
