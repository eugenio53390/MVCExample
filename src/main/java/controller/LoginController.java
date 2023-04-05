package controller;

import java.io.IOException;
import java.sql.SQLException;

import model.*;
import utils.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jdom2.JDOMException;

import dao.UserDao;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class LoginController extends SessionController {
	private static final long serialVersionUID = 1L;
	
	private UserDao usersDao = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();

    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
		try {
	    	super.init(config);
			
			usersDao = new UserDao(getServletContext().getRealPath("/") + config.getServletContext().getInitParameter("config"));
		} catch (ClassNotFoundException | JDOMException | IOException | SQLException e) {

			e.printStackTrace();
		}

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher("view/login/login.jsp");
			dispatcher.forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String password = StringUtils.encrypt(request.getParameter("password"));
			String username = request.getParameter("username");
			
			User user = usersDao.getByUserAndPassword(username, password);
			
			if(user != null) {
				HttpSession session = request.getSession();

				session.setAttribute("session_user", user);
				
				usersDao.updateUser4DateAccess(user);
				
				response.sendRedirect("home");
			}else {
				
				response.sendRedirect("login");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
