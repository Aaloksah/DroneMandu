package controller.servlets;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.ProductDao;
import model.productModel;
import view.droneStringView;

@WebServlet("/UpdateProductServlet")
public class UpdateProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve parameters from the request
        int productId = Integer.parseInt(request.getParameter("productId"));
        String productName = request.getParameter("productName");
        String productDesc = request.getParameter("productDesc");
        String productCategory = request.getParameter("productCategory");
        int mrp = Integer.parseInt(request.getParameter("mrp"));
        int discount = Integer.parseInt(request.getParameter("discount"));
        int stock = Integer.parseInt(request.getParameter("productStock"));
        String imageUrl = request.getParameter("productImageUrl");

        // Create a new product object with the updated information
        productModel product = new productModel( productId,productName, productDesc, mrp, discount, stock, imageUrl,productCategory);
      
       
        
        // Update the product in the database
        boolean updated = ProductDao.updateProduct(product);

        

        // Write the response based on the update result
        if (updated) {
        	request.setAttribute(droneStringView.MESSAGE_SUCCESS, droneStringView.UPDATE_PRODUCT_SQL);
			response.sendRedirect(request.getContextPath() + droneStringView.PAGE_URL_ADMIN);
        } else {
        	request.setAttribute(droneStringView.MESSAGE_SUCCESS, droneStringView.MESSAGE_ERROR_DELETE);
        }
    }
}
