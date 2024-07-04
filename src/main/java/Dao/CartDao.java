// CartDao.java
package Dao;

import model.CartItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.productModel;
import model.CartItem;

public class CartDao {

	public static List<CartItem> getAllCartItems() {
		List<CartItem> cartItems = new ArrayList<>();
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM cartitem");
				ResultSet resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				int cartId = resultSet.getInt("cartId");
				int productId = resultSet.getInt("productId");
				int quantity = resultSet.getInt("quantity");

				// Create a CartItem object with retrieved data
				CartItem cartItem = new CartItem(cartId, productId, quantity);

				// Add the cart item to the list
				cartItems.add(cartItem);
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return cartItems;

	}

	public static productModel getproductnamefromId(int productId) {
		productModel product = null;
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT productName, mrp FROM product WHERE productId = ?");) {
			preparedStatement.setInt(1, productId);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					String productName = resultSet.getString("productName");
					int mrp = resultSet.getInt("mrp");
					product = new productModel(productId, productName, "", mrp, 0, 0, "", ""); 
				}
			}
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return product;
	}
}