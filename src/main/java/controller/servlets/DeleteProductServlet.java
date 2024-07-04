package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Dao.ProductDao;
import view.droneStringView;

@WebServlet("/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteProductServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Retrieve the product ID parameter from the request
		int productId = Integer.parseInt(request.getParameter("productId"));

		// Delete the product using the ProductDao
		boolean deleted = ProductDao.deleteProduct(productId);
		// Write the response based on the deletion result
		if (deleted) {
			request.setAttribute(droneStringView.MESSAGE_SUCCESS, droneStringView.UPDATE_PRODUCT_SQL);
			response.sendRedirect(request.getContextPath() + droneStringView.PAGE_URL_ADMIN);

		} else {

//        	request.setAttribute(droneStringView.MESSAGE_SUCCESS, droneStringView.MESSAGE_ERROR_DELETE);
		}
	}
}
