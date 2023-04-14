package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LogDao;
import model.Log;

/**
 * Servlet implementation class LogsIndexController
 */
@WebServlet("/LogsIndexController")
public class LogsIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	LogDao logDao = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogsIndexController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try {
			super.init(config);
			logDao = new LogDao(getServletContext().getRealPath("/") + config.getServletContext().getInitParameter("config"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			List<Log> logs = logDao.getAll();
			
			request.setAttribute("logs", logs);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("view/log/index.jsp");
	        dispatcher.forward(request, response);
			
		}catch(Exception e) {
			e.printStackTrace();
			
			response.sendRedirect("index.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
