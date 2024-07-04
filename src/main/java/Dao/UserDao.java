package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.droneModel;
import model.productModel;
import view.droneStringView;

public class UserDao {
	public static boolean updateUser(String userName, droneModel user) {
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(droneStringView.UPDATE_USER)) {
			preparedStatement.setString(1, droneModel.getFullName());
			preparedStatement.setString(2, droneModel.getEmail());
			preparedStatement.setString(3, droneModel.getGender());
			preparedStatement.setString(4, droneModel.getProvince());
			preparedStatement.setString(5, droneModel.getPhone());
			preparedStatement.setString(6, droneModel.getCity());
			preparedStatement.setString(7, droneModel.getStreet());
			preparedStatement.setString(8, userName);

			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected > 0;
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	// Fetch all the products
	public droneModel returnUser(String userName) {
		droneModel user = null;
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement p = connection.prepareStatement(droneStringView.SELECT_USER_BY_USERNAME);
			p.setString(1, userName); // Set the username parameter
			ResultSet resultSet = p.executeQuery();

			// Check if there is a result
			if (resultSet.next()) {
				// Populate the user object with data from the result set
				user = new droneModel(resultSet.getString("fullName"), resultSet.getString("email"),
						resultSet.getString("password"), resultSet.getString("gender"), resultSet.getString("phone"),
						resultSet.getString("province"), resultSet.getString("city"), resultSet.getString("userName"),
						resultSet.getString("street"));
			}

			// Close the result set, statement, and connection
			resultSet.close();
			p.close();
			connection.close();
		} catch (ClassNotFoundException | SQLException ex) {
			// Print the stack trace for debugging purposes
			ex.printStackTrace();
		}

		return user;
	}

}