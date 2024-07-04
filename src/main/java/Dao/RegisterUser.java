package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.LoginModel;
import model.droneModel;
import view.droneStringView;

public class RegisterUser {

	public int registeruser(droneModel drone) {

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(droneStringView.INSERT_USER)) {

			// PreparedStatement preparedStatement = getConnection()
			// .prepareStatement(droneStringView.INSERT_USER);

			// Set the student information in the prepared statement

			preparedStatement.setString(1, drone.getFullName());
			preparedStatement.setString(2, droneModel.getEmail());
			preparedStatement.setString(3, droneModel.getPassword());
			// preparedStatement.setString(3,
			// PasswordEncryption.encrypt(droneModel.getUserName(),
			// droneModel.getPassword()));
			preparedStatement.setString(4, drone.getGender());
			preparedStatement.setString(5, droneModel.getPhone());
			preparedStatement.setString(6, drone.getProvince());
			preparedStatement.setString(7, drone.getCity());
			preparedStatement.setString(8, droneModel.getUserName());
			preparedStatement.setString(9, drone.getStreet());

			// preparedStatement.setString(10,
			// PasswordEncryption.encrypt(droneModel.getEmail(),droneModel.getPassword()));

			// preparedStatement.setString(9, PasswordEncryption.encrypt(
			// student.getUsername(), student.getPassword()));
			// preparedStatement.setString(10, student.getImageUrlFromPart());

			// Execute the update statement and store the number of affected rows
			int result = preparedStatement.executeUpdate();

			// Check if the update was successful (i.e., at least one row affected)
			if (result > 0) {
				return 1; // Registration successful
			} else {
				return 0; // Registration failed (no rows affected)
			}

		} catch (ClassNotFoundException | SQLException ex) {
			// Print the stack trace for debugging purposes
			ex.printStackTrace();
			return -1; // Internal error
		}
	}

	/**
	 * This method attempts to validate a student login by checking the username and
	 * password against a database.
	 * 
	 * @param username The username provided by the user attempting to log in.
	 * @param password The password provided by the user attempting to log in.
	 * @return An integer value indicating the login status: - 1: Login successful -
	 *         0: Username or password mismatch - -1: Username not found in the
	 *         database - -2: Internal error (e.g., SQL or ClassNotFound exceptions)
	 * @throws SQLException           if a database access error occurs.
	 * @throws ClassNotFoundException if the JDBC driver class is not found.
	 */

	public int getUserLoginInfo(LoginModel loginModel) {
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(droneStringView.QUERY_LOGIN_USER_CHECK)) {

			// Set the username in the first parameter of the prepared statement
			preparedStatement.setString(1, loginModel.getuserName()); 
			preparedStatement.setString(2, loginModel.getPassword());

			// Execute the query and store the result set
			ResultSet result = preparedStatement.executeQuery();

			// Check if there's a record returned from the query
			if (result.next()) {
				// Get the username from the database
				String userNameDb = result.getString(droneStringView.userName);

				// Get the password from the database
				String passwordDb = result.getString(droneStringView.PASSWORD);

				// Check if the username and password match the credentials from the database
				if (userNameDb.equals(loginModel.getuserName()) && passwordDb.equals(loginModel.getPassword())) {
					// Login successful, return 1
					return 1;
				} else {
					// Username or password mismatch, return 0
					return 0;
				}
			} else {
				// Username not found in the database, return -1
				return -1;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			// Print the stack trace for debugging purposes
			ex.printStackTrace();
			// Return -2 to indicate an internal error
			return -2;
		}
	}

	
	

	public static ArrayList<droneModel> getAllUserInfo() {
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(droneStringView.QUERY_GET_ALL_USER)) {

			ResultSet result = preparedStatement.executeQuery();

			ArrayList<droneModel> students = new ArrayList<>();

			while (result.next()) {
				droneModel user = new droneModel();
				user.setFullName(result.getString("fullName"));
				user.setFullName(result.getString(droneStringView.FUllName));
																				
				droneModel.setEmail(result.getString(droneStringView.EMAIL));
				user.setGender(result.getString(droneStringView.GENDER));
				droneModel.setPhone(result.getString(droneStringView.PHONE_NUMBER));
																						
				user.setUserName(result.getString(droneStringView.userName)); 
																				
				user.setProvince(result.getString(droneStringView.PROVINCE));
				user.setCity(result.getString(droneStringView.CITY));
				user.setStreet(result.getString(droneStringView.STREET));

				students.add(user);
			}
			return students;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public int createInitialCartTable(int userId) {
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(droneStringView.CREATE_CART_TABLE)) {
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, 0);
			preparedStatement.setInt(3, 0);
			int result = preparedStatement.executeUpdate();

			// Check if the update was successful
			if (result > 0) {
				return 1; // Registration successful
			} else {
				return 0; // Registration failed (no rows affected)
			}

		} catch (ClassNotFoundException | SQLException ex) {
			// Print the stack trace for debugging purposes
			ex.printStackTrace();
			return -1; // Internal error
		}
	}

	public static int getUserIdFromUserName(String userName) {
		int userId = -1;
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(droneStringView.GET_USER_ID_FROM_USERNAME)) {
			preparedStatement.setString(1, userName);
			ResultSet result = preparedStatement.executeQuery();
			if (result.next()) {
				userId = result.getInt("userId");
			} else {
				// Username not found in the database, return -1
				return -1;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			// Print the stack trace for debugging purposes
			ex.printStackTrace();
			// Return -2 to indicate an internal error
			return -2;
		}
		return userId;
	}

}
