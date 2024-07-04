package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.UserDao;
import model.droneModel;
import view.droneStringView;


@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	System.out.println("Hitted");
        // Retrieve parameters from the request
        String userName = request.getParameter("userName"); // Retrieve username
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String province = request.getParameter("province");
        String phone = request.getParameter("phone");
        String city = request.getParameter("city");
        String street = request.getParameter("street");
        System.out.println(userName);
        System.out.println(fullName);
        System.out.println(gender);
        System.out.println(phone);
        System.out.println(userName);
        
        

        // Create a new user object with the updated information
        droneModel user = new droneModel(fullName, email, null, gender, phone, province, city, userName, street);

        // Update the user in the database
        boolean updated = UserDao.updateUser(userName, user);
        response.sendRedirect(request.getContextPath() + droneStringView.PAGE_URL_HOME);

        // Redirect to appropriate page based on the update result
        
    }
}