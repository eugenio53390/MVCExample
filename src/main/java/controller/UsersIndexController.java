package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.jdom2.JDOMException;

import dao.UserDao;
import model.User;

/**
 * Servlet implementation class UsersIndex
 */
@WebServlet("/UsersIndex")
public class UsersIndexController extends AuthorizedController {
	private static final long serialVersionUID = 1L;
	
	private UserDao usersDao = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersIndexController() {
        super();

    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
			super.init(config);

			usersDao = new UserDao(getServletContext().getRealPath("/") + config.getServletContext().getInitParameter("config"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = null;
		int id = 0;
		User user = null;
		
		
		try {
			super.doGet(request, response);
			
			action = request.getParameter("action");
			
			if(request.getParameter("id") != null && !request.getParameter("id").isEmpty() ) {
				id = Integer.parseInt(request.getParameter("id"));
			}

			
			if(this.isAuthorized) {

				
				if(ACTION_DELETE.equals(action)) {
					usersDao.deleteUser(id);
					
					response.sendRedirect("UsersIndex");
				}else {
					List<User> listUsers = usersDao.getAll();
					
					request.setAttribute("users", listUsers);
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("view/user/index.jsp");
			        dispatcher.forward(request, response);
				}

			}else {
				response.sendRedirect("login");
			}
			

		}catch(Exception e) {
			e.printStackTrace();

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		super.doPost(request, response);
	}

}
