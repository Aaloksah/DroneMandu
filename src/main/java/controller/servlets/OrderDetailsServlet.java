package controller.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.OrderDao;
import Dao.ProductDao;
import model.OrderModel;
import view.droneStringView;

@WebServlet("/OrderDetailsServlet")
public class OrderDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
        
 int orderId = Integer.parseInt(request.getParameter("orderId"));
        
        // Delete the product using the ProductDao
        boolean updated = OrderDao.markAsDelivered(orderId);

    
        // Write the response based on the deletion result
        if (updated) {
           
            request.setAttribute(droneStringView.MESSAGE_SUCCESS, droneStringView.MESSAGE_SUCCESS_DELETE);
			response.sendRedirect(request.getContextPath() + droneStringView.PAGE_URL_ADMIN);
        } else {
        	
        	request.setAttribute(droneStringView.MESSAGE_SUCCESS, droneStringView.MESSAGE_ERROR_DELETE);
        }
    }
    }