package controller.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.DBConnection;
import Dao.RegisterUser;
import Dao.CartDao;
import model.CartItem;
import view.droneStringView;

@WebServlet(asyncSupported = true, urlPatterns = { "/AddToCart" })
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddToCart() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName");
		int productId = Integer.parseInt(request.getParameter("productId"));
		int productQuantity = Integer.parseInt(request.getParameter("quantity"));
		int productPrice = Integer.parseInt(request.getParameter("price"));
		int cartId = getCartIdFromUserName(userName);
		int userId = RegisterUser.getUserIdFromUserName(userName);

		// Insert into cartItem table
		int insertToCartItem = insertToCartItem(cartId, productId, productQuantity);

		// Update cart table
		int updateCartTable = updateCartTable(cartId, productPrice, productQuantity);

		// Retrieve cart items
		List<CartItem> cartItems = CartDao.getAllCartItems();
		request.setAttribute("cartItems", cartItems);
		response.sendRedirect(request.getContextPath() +(droneStringView.PAGE_URL_HOME));
	}

	public int getCartIdFromUserName(String userName) {
		int cartId = -1;
		int userId = RegisterUser.getUserIdFromUserName(userName);
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement cartIdFromUserId = connection
						.prepareStatement("SELECT cartId FROM cart WHERE userId = ?")) {
			cartIdFromUserId.setInt(1, userId);
			ResultSet result = cartIdFromUserId.executeQuery();
			if (result.next()) {
				cartId = result.getInt("cartId");
			} else {
				cartId = 0;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return cartId;
	}

	public int insertToCartItem(int cartId, int productId, int quantity) {
		int result = -1;
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("INSERT INTO cartitem (cartId, productId, quantity) VALUES (?, ?, ?)")) {
			preparedStatement.setInt(1, cartId);
			preparedStatement.setInt(2, productId);
			preparedStatement.setInt(3, quantity);

			result = preparedStatement.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateCartTable(int cartId, int price, int quantity) {
		int result = -1;
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(
						"UPDATE cart SET totalCartAmount = totalCartAmount + ?, totalCartQuantity = totalCartQuantity + ? WHERE cartId = ?")) {
			preparedStatement.setInt(1, price * quantity); 
			preparedStatement.setInt(2, quantity); 
			preparedStatement.setInt(3, cartId); 

			result = preparedStatement.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
}
