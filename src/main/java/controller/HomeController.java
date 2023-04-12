package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/home")
public class HomeController extends AuthorizedController {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see AuthorizedController#AuthorizedController()
     */
    public HomeController() {
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

		super.doGet(request, response);
		
		if(this.isAuthorized) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("view/home/home.jsp");
	        dispatcher.forward(request, response);
		}else {
			response.sendRedirect("login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		if(this.isAuthorized) {
			
		}else {
			response.sendRedirect("login");
		}
	}

}
