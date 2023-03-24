package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom2.JDOMException;

import dao.UserDao;
import model.User;

/**
 * Servlet implementation class UsersEdit
 */
@WebServlet("/UsersEdit")
public class UsersEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ACTION_DELETE = "DELETE";
	private static final String ACTION_EDIT = "EDIT";
	private static final String ACTION_INSERT = "INSERT";
	
	private UserDao usersDao = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersEdit() {
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
		User user = null;
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String action = request.getParameter("action");
			
			if(ACTION_DELETE.equals(action)) {
				usersDao.deleteUser(id);
				
				response.sendRedirect("UsersEdit");
			}else if(ACTION_EDIT.equals(action)) {
				user = usersDao.getById(id);
				
				request.setAttribute("user", user);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("view/user/edit.jsp");
		        dispatcher.forward(request, response);
			}else if(ACTION_INSERT.equals(action)) {
				user = new User();
				
				request.setAttribute("user", user);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("view/user/edit.jsp");
		        dispatcher.forward(request, response);
			}
			

		}catch(Exception e) {
			e.printStackTrace();
			
			response.sendRedirect("index.html");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		
		try {
			String username = request.getParameter("username");
			String first_name = request.getParameter("first_name");
			String last_name = request.getParameter("last_name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String cellphone = request.getParameter("cellphone");
			Date birth_date = new Date( request.getParameter("birth_date"));
			

			user.setUsername(username);
			user.setFirst_name(first_name);
			user.setLast_name(last_name);
			user.setEmail(email);
			user.setPassword(password);
			user.setCellphone(cellphone);
			user.setBirth_date(birth_date);
			
			usersDao.insertUser(user);
			
			response.sendRedirect("index.html");
		}catch(Exception e) {
			e.printStackTrace();
			
			request.setAttribute("user", user);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("view/user/edit.jsp");
	        dispatcher.forward(request, response);
		}
		

	}

}
