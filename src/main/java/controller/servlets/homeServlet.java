package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.RegisterUser;
import model.LoginModel;
import model.droneModel;
import view.droneStringView;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/homeServlet")
public class homeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RegisterUser Dao = new RegisterUser();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    ArrayList<droneModel> userList = Dao.getAllUserInfo(); // Corrected method name and variable name
	    request.setAttribute(droneStringView.USER_LISTS, userList); // Corrected variable name
	    request.getRequestDispatcher(droneStringView.PAGE_URL_HOME).forward(request, response);
	}

}