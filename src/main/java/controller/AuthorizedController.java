package controller;

import model.*;

import java.io.IOException;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class AuthorizedController
 */
@WebServlet("/AuthorizedController")
public class AuthorizedController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected static final String ACTION_DELETE = "DELETE";
	protected static final String ACTION_EDIT = "EDIT";
	protected static final String ACTION_INSERT = "INSERT";
    
	protected boolean isAuthorized = false;
	protected User session_user = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthorizedController() {
        super();

    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		if(session.getAttribute("session_user") != null ) {
			isAuthorized = true;
			
			session_user = (User) session.getAttribute("session_user");
		}else {
			isAuthorized = false;
			
			session_user = null;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		if(session.getAttribute("session_user") != null ) {
			isAuthorized = true;
			
			session_user = (User) session.getAttribute("session_user");
		}else {
			isAuthorized = false;
			
			session_user = null;
		}
		
	}

}
