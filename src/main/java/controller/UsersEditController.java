package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;

import org.jdom2.JDOMException;

import dao.UserDao;
import model.User;
import utils.StringUtils;

/**
 * Servlet implementation class UsersEdit
 */
@WebServlet("/UsersEdit")
public class UsersEditController extends AuthorizedController {
	private static final long serialVersionUID = 1L;
	
	private UserDao usersDao = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersEditController() {
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
				}else if(ACTION_EDIT.equals(action)) {
					user = usersDao.getById(id);
					
					request.setAttribute("user", user);
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("view/user/edit.jsp");
			        dispatcher.forward(request, response);
				}else if(ACTION_INSERT.equals(action)) {
					user = new User();
					user.setCellphone("");
					user.setEmail("");
					user.setFirst_name("");
					user.setLast_name("");
					user.setPassword("");
					user.setUsername("");
					user.setId(0);
					
					request.setAttribute("user", user);
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("view/user/edit.jsp");
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
		User user = new User();
		
		try {
			super.doPost(request, response);
			
			if(this.isAuthorized) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);


				Date date = formatter.parse(request.getParameter("birth_date"));
				
				String username = request.getParameter("username");
				String first_name = request.getParameter("first_name");
				String last_name = request.getParameter("last_name");
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				String cellphone = request.getParameter("cellphone");
				Date birth_date = date;
				

				user.setUsername(username);
				user.setFirst_name(first_name);
				user.setLast_name(last_name);
				user.setEmail(email);

				user.setCellphone(cellphone);
				user.setBirth_date(birth_date);
				
				if(request.getParameter("id") == null || request.getParameter("id").isEmpty() || Integer.valueOf(request.getParameter("id")) == 0) {
					user.setPassword(StringUtils.encrypt(password) );
					usersDao.insertUser(user);
				}else {
					//check password
					User temp = usersDao.getById(Integer.valueOf(request.getParameter("id")));
					
					if(temp.getPassword().equals(password)) {
						user.setPassword(password );
					}else {
						user.setPassword(StringUtils.encrypt(password) );
					}
					
					user.setId(Integer.valueOf(request.getParameter("id")));
					usersDao.updateUser(user);
				}

				
				response.sendRedirect("UsersIndex");
			}else {
				response.sendRedirect("login");
			}

		}catch(Exception e) {
			e.printStackTrace();

		}
		

	}

}
