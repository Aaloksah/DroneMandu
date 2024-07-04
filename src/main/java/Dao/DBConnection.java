package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import view.droneStringView;

public class DBConnection {
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		// Load the JDBC driver class specified by the DRIVER_NAME constant
		Class.forName(droneStringView.DRIVER_NAME);

		// Create a connection to the database using the provided credentials
		return DriverManager.getConnection(droneStringView.LOCALHOST_URL, droneStringView.LOCALHOST_USERNAME,
				droneStringView.LOCALHOST_PASSWORD);
	}
}
