package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.OrderModel;

public class OrderDao {
    
    // SQL Queries
  
	 public static final String SELECT_ALL_ORDERS = "SELECT * FROM orders";
    // Method to retrieve all orders from the database
    public  List<OrderModel> getAllOrders() {
        List<OrderModel> orders = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ORDERS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int orderId = resultSet.getInt("orderId");
                int userId = resultSet.getInt("userId");
                double totalAmount = resultSet.getDouble("totalAmount");
                String status = resultSet.getString("status");
               
                Date orderDate = resultSet.getDate("orderDate");
                
                // Create an OrderModel object with retrieved data
                OrderModel order = new OrderModel(orderId, userId, totalAmount, status, orderDate);
                
                // Add the order to the list
                orders.add(order);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return orders;
    }
    
public static final String UPDATE_ORDER_STATUS = "UPDATE orders SET status = 'Delivered' WHERE orderId = ?";
    
    // Method to update the status of an order to Delivered
    public static boolean markAsDelivered(int orderId) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_STATUS)) {
            preparedStatement.setInt(1, orderId);
            
            // Execute the update statement
            int rowsAffected = preparedStatement.executeUpdate();
            
            // Check if the update was successful (i.e., if at least one row was affected)
            return rowsAffected > 0;
            
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return false; // Internal error
        }
    }
}