package Dao;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import model.productModel;
import view.droneStringView;

public class ProductDao {

	// Insert product to datavase
	public static int insertProduct(productModel product) {
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(droneStringView.INSERT_PRODUCT_SQL)) {
			preparedStatement.setString(1, product.getProductName());
			preparedStatement.setString(2, product.getProductDesc());
			preparedStatement.setDouble(3, product.getMrp());
			preparedStatement.setDouble(4, product.getDiscountPrice());
			preparedStatement.setInt(5, product.getStock());
			preparedStatement.setString(6, product.getImageUrl());
			preparedStatement.setString(7, product.getCategory());

			int result = preparedStatement.executeUpdate();

			// Check if the update was successful (i.e., at least one row affected)
			if (result > 0) {
				return 1; // Added successful
			} else {
				return 0; // Not Added (no rows affected)
			}

		} catch (ClassNotFoundException | SQLException ex) {
			// Print the stack trace for debugging purposes
			ex.printStackTrace();
			return -1; // Internal error
		}
	}

	// delete
	public static boolean deleteProduct(int productId) {
		boolean deleted = false;
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(droneStringView.DELETE_PRODUCT_SQL)) {
			preparedStatement.setInt(1, productId);
			int rowsAffected = preparedStatement.executeUpdate();
			deleted = rowsAffected > 0;
		} catch (ClassNotFoundException | SQLException ex) {
			// Print the stack trace for debugging purposes
			ex.printStackTrace();
		}
		return deleted;
	}

	// update
	public static boolean updateProduct(productModel product) {
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(droneStringView.UPDATE_PRODUCT_SQL)) {
			preparedStatement.setString(1, product.getProductName());
			preparedStatement.setString(2, product.getProductDesc());
			preparedStatement.setString(3, product.getCategory());
			preparedStatement.setDouble(4, product.getMrp());
			preparedStatement.setDouble(5, product.getDiscountPrice());
			preparedStatement.setInt(6, product.getStock());
			preparedStatement.setString(7, product.getImageUrl());

			preparedStatement.setInt(8, product.getProductId());

			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected > 0;
		} catch (ClassNotFoundException | SQLException ex) {
			// Print the stack trace for debugging purposes
			ex.printStackTrace();
			return false; // Internal error
		}
	}

	// Fetch all the products
	public List<productModel> getAllProducts() {
		List<productModel> list = new ArrayList<>();
		try {

			Connection connection = DBConnection.getConnection();
			PreparedStatement p = connection.prepareStatement("SELECT * FROM product");
			ResultSet set = p.executeQuery();

			while (set.next()) {

				int productId = set.getInt("productId");
				String productName = set.getString("productName");
				String productDesc = set.getString("productDesc");
				int mrp = set.getInt("mrp");
				int discountPrice = set.getInt("discountPrice");
				int stock = set.getInt("stock");
				String imageUrl = set.getString("imageUrl");
				String category = set.getString("category");
				productModel product = new productModel(productId, productName, productDesc, mrp, discountPrice, stock,
						imageUrl, category);
				list.add(product);
			}

		} catch (ClassNotFoundException | SQLException ex) {
			
			ex.printStackTrace();
		}

		return list;

	}

	// Fetch all the products from category
	public List<productModel> getProductsByCategory(String category) {
		List<productModel> list = new ArrayList<>();
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement p = connection.prepareStatement("SELECT * FROM product WHERE category=?");
			p.setString(1, category);
			ResultSet set = p.executeQuery();

			while (set.next()) {

				int productId = set.getInt("productId");
				String productName = set.getString("productName");
				String productDesc = set.getString("productDesc");
				int mrp = set.getInt("mrp");
				int discountPrice = set.getInt("discountPrice");
				int stock = set.getInt("stock");
				String imageUrl = set.getString("imageUrl");
				productModel product = new productModel(productId, productName, productDesc, mrp, discountPrice, stock,
						imageUrl, category);
				list.add(product);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return list;

	}

	public List<productModel> getProductsBySearch(String search) {
		List<productModel> list = new ArrayList<>();
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement p = connection.prepareStatement("SELECT * FROM product WHERE productName LIKE ?");
			p.setString(1, "%" + search + "%");
			ResultSet set = p.executeQuery();

			while (set.next()) {

				int productId = set.getInt("productId");
				String productName = set.getString("productName");
				String productDesc = set.getString("productDesc");
				int mrp = set.getInt("mrp");
				int discountPrice = set.getInt("discountPrice");
				int stock = set.getInt("stock");
				String imageUrl = set.getString("imageUrl");
				String category = set.getString("category");
				productModel product = new productModel(productId, productName, productDesc, mrp, discountPrice, stock,
						imageUrl, category);
				list.add(product);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return list;
	}

	public static productModel getProductById(int productId) {
		productModel product = null;
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement p = connection.prepareStatement("SELECT * FROM product WHERE productId = ?");
			p.setInt(1, productId);
			ResultSet set = p.executeQuery();

			if (set.next()) {
				String productName = set.getString("productName");
				String productDesc = set.getString("productDesc");
				int mrp = set.getInt("mrp");
				int discountPrice = set.getInt("discountPrice");
				int stock = set.getInt("stock");
				String imageUrl = set.getString("imageUrl");
				String category = set.getString("category");
				product = new productModel(productId, productName, productDesc, mrp, discountPrice, stock, imageUrl,
						category);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

}
