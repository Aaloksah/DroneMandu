package controller.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.OrderItem;
import model.OrderModel;
import Dao.ProductDao;
import Dao.DBConnection;
import Dao.RegisterUser;
import view.droneStringView;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/MoveToOrderServlet")
public class MoveToOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("userName");
        int userId = RegisterUser.getUserIdFromUserName(userName);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Obtain database connection using DBConnection class
            conn = DBConnection.getConnection();

            // Retrieve data from cartItem and Cart tables for the current user
            stmt = conn.prepareStatement(
                    "SELECT * FROM cartItem WHERE cartId IN (SELECT cartId FROM cart WHERE userId = ?)");
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();
            List<OrderItem> orderItems = new ArrayList<>();
            while (rs.next()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrderId(rs.getInt("cartId"));
                orderItem.setProductId(rs.getInt("productId"));
                orderItem.setQuantity(rs.getInt("quantity"));
                orderItem.setUnitPrice(ProductDao.getProductById(orderItem.getProductId()).getDiscountPrice());
                orderItems.add(orderItem);
            }

            stmt = conn.prepareStatement("SELECT * FROM cart WHERE userId = ?");
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();
            List<OrderModel> orders = new ArrayList<>();
            while (rs.next()) {
                OrderModel order = new OrderModel();
                order.setUserId(rs.getInt("userId"));
                order.setTotalAmount(rs.getInt("totalCartAmount"));
                order.setStatus("Pending");
                orders.add(order);
            }

            // Move data to OrderItem table with new orderId for each order
            for (OrderModel order : orders) {
                // Inserting current timestamp into orderDate column
                Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

                // Insert the order into the order table to obtain the auto-generated orderId
                PreparedStatement orderStmt = conn.prepareStatement(
                        "INSERT INTO `orders` (userId, totalAmount, status, orderDate) VALUES (?, ?, ?, ?)",
                        PreparedStatement.RETURN_GENERATED_KEYS);
                orderStmt.setInt(1, order.getUserId());
                orderStmt.setDouble(2, order.getTotalAmount());
                orderStmt.setString(3, order.getStatus());
                orderStmt.setTimestamp(4, currentTimestamp);
                orderStmt.executeUpdate();

                
                ResultSet generatedKeys = orderStmt.getGeneratedKeys();
                int orderId = 0;
                if (generatedKeys.next()) {
                    orderId = generatedKeys.getInt(1);
                } 

                // Move data to OrderItem table with the new orderId
                for (OrderItem orderItem : orderItems) {
                    stmt = conn.prepareStatement(
                            "INSERT INTO orderitem (orderId, productId, quantity, unitPrice) VALUES (?, ?, ?, ?)");
                    stmt.setInt(1, orderId);
                    stmt.setInt(2, orderItem.getProductId());
                    stmt.setInt(3, orderItem.getQuantity());
                    stmt.setInt(4, orderItem.getUnitPrice());
                    stmt.executeUpdate();
                    
                 // Update stock in the product table
                    int orderedQuantity = orderItem.getQuantity();
                    int productId = orderItem.getProductId();
                    PreparedStatement updateStockStmt = conn.prepareStatement(
                            "UPDATE product SET stock = stock - ? WHERE productId = ?");
                    updateStockStmt.setInt(1, orderedQuantity);
                    updateStockStmt.setInt(2, productId);
                    updateStockStmt.executeUpdate();
                }
            }

            // Empty the cart tables for the current user
            stmt = conn.prepareStatement(
                    "DELETE FROM cartItem WHERE cartId IN (SELECT cartId FROM cart WHERE userId = ?)");
            stmt.setInt(1, userId);
            stmt.executeUpdate();

         // Empty the cart tables for the current user by setting totalCartAmount and totalCartQuantity to zero
            stmt = conn.prepareStatement("UPDATE cart SET totalCartAmount = 0, totalCartQuantity = 0 WHERE userId = ?");
            stmt.setInt(1, userId);
            stmt.executeUpdate();
            response.sendRedirect(request.getContextPath()+ droneStringView.PAGE_URL_ORDERSUCESS); // Redirect to success page
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Redirect to error page
        } finally {
            // Close JDBC resources
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
