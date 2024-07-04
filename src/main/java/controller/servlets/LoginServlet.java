package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.RegisterUser;
import model.LoginModel;
import view.droneStringView;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RegisterUser dbController = new RegisterUser();

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter(droneStringView.userName);
		String password = request.getParameter(droneStringView.PASSWORD);
		System.out.println(userName);
		System.out.println(password);

		if ("admin123".equals(userName) && "admin".equals(password)) {
			System.out.println("Admin Page");
			HttpSession userSession = request.getSession();
			userSession.setAttribute("userName", userName);
			userSession.setMaxInactiveInterval(30 * 60);
			Cookie userCookie = new Cookie("userName", userName);
			userCookie.setMaxAge(30 * 60);
			response.addCookie(userCookie);
			response.sendRedirect(request.getContextPath() + droneStringView.PAGE_URL_ADMIN);
		} else {
			
			LoginModel loginModel = new LoginModel(userName, password);	// Call DBController to validate login credentials
			int loginResult = dbController.getUserLoginInfo(loginModel);
			System.out.println(loginResult);// Handle login results with appropriate messages and redirects
			if (loginResult == 1) {
				// Login successful
				HttpSession userSession = request.getSession();
				userSession.setAttribute("userName", userName);
				userSession.setMaxInactiveInterval(30 * 60);

				Cookie userCookie = new Cookie("userName", userName);
				userCookie.setMaxAge(30 * 60);
				response.addCookie(userCookie);
				
				// Redirect to the home page
				response.sendRedirect(request.getContextPath() + droneStringView.PAGE_URL_HOME);
			} else if (loginResult == 0) {
				// Username or password mismatch
				request.setAttribute(droneStringView.MESSAGE_ERROR, droneStringView.MESSAGE_ERROR_LOGIN);
				response.sendRedirect(request.getContextPath() + droneStringView.PAGE_URL_LOGIN);
//				request.getRequestDispatcher(droneStringView.PAGE_URL_LOGIN).forward(request, response);
			} else if (loginResult == -1) {
				// Username not found
				request.setAttribute(droneStringView.MESSAGE_ERROR, droneStringView.MESSAGE_ERROR_REGISTER);
				response.sendRedirect(request.getContextPath() + droneStringView.PAGE_URL_LOGIN);
//				request.getRequestDispatcher(droneStringView.PAGE_URL_REGISTER).forward(request, response);
			} else {
				// Internal server error
				request.setAttribute(droneStringView.MESSAGE_ERROR, droneStringView.MESSAGE_ERROR_SERVER);
				request.getRequestDispatcher(droneStringView.PAGE_URL_LOGIN).forward(request, response);
			}
		}

	}
}
