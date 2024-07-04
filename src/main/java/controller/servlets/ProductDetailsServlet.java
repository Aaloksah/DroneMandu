package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import view.droneStringView;

/**
 * Servlet implementation class ProductDetailsServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ProductDetailsServlet" })
public class ProductDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProductDetailsServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int productId = Integer.parseInt(request.getParameter("productId"));
		response.sendRedirect(request.getContextPath() + "/pages/productDetails.jsp?productId="+ productId);
	}

}