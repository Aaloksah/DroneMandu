package controller.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import view.droneStringView;


public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();

        if (uri.endsWith(".css") || uri.endsWith(".png") || uri.endsWith(".jpg")) {
            chain.doFilter(request, response);
            return;
        }

        if (uri.endsWith(droneStringView.PAGE_URL_LOGIN) || uri.endsWith(droneStringView.PAGE_URL_HOME)) {
        	chain.doFilter(request, response);
            //req.getRequestDispatcher(droneStringView.SERVLET_URL_HOME).forward(request, response);
            return;
        }

        boolean isLogin = uri.endsWith(droneStringView.PAGE_URL_LOGIN);
        boolean isLoginServlet = uri.endsWith(droneStringView.SERVLET_URL_LOGIN);
        boolean isLogoutServlet = uri.endsWith(droneStringView.SERVLET_URL_LOGOUT);
        boolean isHomeServlet = uri.endsWith(droneStringView.SERVLET_URL_HOME);

        boolean isRegisterPage = uri.endsWith(droneStringView.PAGE_URL_REGISTER);
        boolean isRegisterServlet = uri.endsWith(droneStringView.SERVLET_URL_REGISTER);

        HttpSession session = req.getSession(false);
        boolean isLoggedIn = session != null && session.getAttribute(droneStringView.USER) != null;
        System.out.println("Not loggedIN");
        System.out.println(!isLoggedIn && !isHomeServlet && !(isLogin || isLoginServlet || isRegisterPage || isRegisterServlet));
        System.out.println("Not loggedIN");
        System.out.println(isLoggedIn && !isHomeServlet && !(!isLogin || isLogoutServlet));
        if (!isLoggedIn && !isHomeServlet && !(isLogin || isLoginServlet || isRegisterPage || isRegisterServlet)) {
            res.sendRedirect(req.getContextPath() + droneStringView.PAGE_URL_LOGIN);
        } else if (isLoggedIn && !isHomeServlet && !(!isLogin || isLogoutServlet)) {
            res.sendRedirect(req.getContextPath() + droneStringView.PAGE_URL_LOGIN);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // Clean-up code if needed
    }
}
