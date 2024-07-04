package controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.RegisterUser;
import model.droneModel;
import view.ValidationView;
import view.droneStringView;

/**
 * Servlet implementation class registerServlet
 */
@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final RegisterUser RegisterUser;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public registerServlet() {
		this.RegisterUser = new RegisterUser();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter printOut = response.getWriter();
		response.setContentType("text/html");
		printOut.print("Drones");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// doGet(request,response);
		// Retrieve parameters from the request
		String fullName = request.getParameter("fullName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phoneNumber");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String userName = request.getParameter("userName");
		String street = request.getParameter("street");
		String retypePassword = request.getParameter("retypePassword");

		// Create a droneModel object with the retrieved parameters
		droneModel drone = new droneModel(fullName, email, password, gender, phone, province, city, userName, street);
		System.out.println(!ValidationView.isTextOnly(fullName));
		System.out.println(!ValidationView.isTextOnly(province));
		System.out.println(!ValidationView.isTextOnly(city));
		System.out.println(!ValidationView.isEmail(email));
		System.out.println(!ValidationView.isNumbersOnly(phone));
		System.out.println(!ValidationView.isTextOnly(street));
		System.out.println(!ValidationView.isGenderMatches(gender));
		if (!ValidationView.isTextOnly(fullName)
				|| !ValidationView.isTextOnly(province) || !ValidationView.isTextOnly(city)
				|| !ValidationView.isEmail(email) || !ValidationView.isNumbersOnly(phone)
				|| !ValidationView.isTextOnly(street) || !ValidationView.isGenderMatches(gender)) {
			request.setAttribute(droneStringView.MESSAGE_ERROR, droneStringView.MESSAGE_ERROR_INCORRECT_DATA);
			request.getRequestDispatcher(droneStringView.PAGE_URL_REGISTER).forward(request, response);
			return; // Added return to stop
		}
		// Register the user using the droneDBController
		int result = RegisterUser.registeruser(drone);
		System.out.println(result);
		
		// Redirect based on the result
		if (password.equals(retypePassword)) {
			switch (result) {
			case 1:
				request.setAttribute(droneStringView.MESSAGE_SUCCESS, droneStringView.MESSAGE_SUCCESS_REGISTER);
				int ID = Dao.RegisterUser.getUserIdFromUserName(userName);
				int cart = RegisterUser.createInitialCartTable(ID);
				response.sendRedirect(request.getContextPath() + droneStringView.PAGE_URL_LOGIN);
				return; // Added return to stop further processing
			case 0:
				request.setAttribute(droneStringView.MESSAGE_ERROR, droneStringView.MESSAGE_ERROR_EMAIL);
				response.sendRedirect(request.getContextPath() + droneStringView.PAGE_URL_REGISTER);
				break;
			case -1:
				
				request.setAttribute(droneStringView.MESSAGE_ERROR, droneStringView.MESSAGE_ERROR_SERVER);
				response.sendRedirect(request.getContextPath() + droneStringView.PAGE_URL_REGISTER);
				break;
			case -2:
				request.setAttribute(droneStringView.MESSAGE_ERROR, droneStringView.MESSAGE_ERROR_PHONE_NUMBER);
				response.sendRedirect(request.getContextPath() + droneStringView.PAGE_URL_REGISTER);
				break;
			case -3:
				request.setAttribute(droneStringView.MESSAGE_ERROR, droneStringView.MESSAGE_ERROR_USERNAME);
				response.sendRedirect(request.getContextPath() + droneStringView.PAGE_URL_REGISTER);
				break;
			default:
				request.setAttribute(droneStringView.MESSAGE_ERROR, droneStringView.MESSAGE_ERROR_SERVER);
				response.sendRedirect(request.getContextPath() + droneStringView.PAGE_URL_REGISTER);
				break;
			}
		} else {
			request.setAttribute(droneStringView.MESSAGE_ERROR, droneStringView.MESSAGE_ERROR_PASSWORD_UNMATCHED);
		}

		// Redirect to the registration page
		response.sendRedirect(request.getContextPath() + droneStringView.PAGE_URL_REGISTER);

	}
}