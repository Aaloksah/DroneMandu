package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.productModel;
import view.droneStringView;
import Dao.ProductDao;

/**
 * Servlet implementation class AddProductServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AddProductServlet" })
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddProductServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		System.out.println("servlet called");

		// Get value from input fields
		String productName = request.getParameter("productName");
		String productDesc = request.getParameter("productDesc");
		String category = request.getParameter("productCategory");
		int mrp = Integer.parseInt(request.getParameter("mrp"));
		int discountPrice = Integer.parseInt(request.getParameter("discount"));
		int stock = Integer.parseInt(request.getParameter("productStock"));
		String imageUrl = request.getParameter("productImageUrl");

//		Creating Object of product
		productModel product = new productModel(productName, productDesc, mrp, discountPrice, stock, imageUrl,category);

		// Register the user using the droneDBController
		int result = ProductDao.insertProduct(product);
		
		//To be changed
		if (result == 1) {
			// String successRegisterMessage = "Sucessfully Registered";
			request.setAttribute(droneStringView.MESSAGE_SUCCESS, droneStringView.MESSAGE_SUCCESS_REGISTER);
			response.sendRedirect(request.getContextPath() + droneStringView.PAGE_URL_ADMIN);
		} else if (result == 0) {
			request.setAttribute(droneStringView.MESSAGE_ERROR, droneStringView.MESSAGE_ERROR_REGISTER);
			request.getRequestDispatcher(droneStringView.PAGE_URL_REGISTER).forward(request, response);

		} else {
			// String errorMessage = "An unexpected server error occured.";
			request.setAttribute(droneStringView.MESSAGE_ERROR, droneStringView.MESSAGE_ERROR_SERVER);
			request.getRequestDispatcher(droneStringView.PAGE_URL_REGISTER).forward(request, response);
		}
	}
}
