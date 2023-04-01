package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom2.JDOMException;

import dao.BillDao;
import model.Bill;

/**
 * Servlet implementation class UsersIndex
 */
@WebServlet("/Bills Edit")
public class BillsEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ACTION_DELETE = "DELETE";
	private static final String ACTION_EDIT = "EDIT";
	private static final String ACTION_INSERT = "INSERT";

	
	private BillDao billDao = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BillsEdit() {
        super();

    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
			super.init(config);

			billDao = new BillDao(getServletContext().getRealPath("/") + config.getServletContext().getInitParameter("config"), true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Bill bill = null;
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String action = request.getParameter("action");
			
			if(ACTION_DELETE.equals(action)) {
				billDao.deleteBill(id);
				
				response.sendRedirect("billDao");
			}else if(ACTION_EDIT.equals(action)) {
				bill = billDao.getById(id);
				
				request.setAttribute("bill", bill);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("view/bill/edit.jsp");
		        dispatcher.forward(request, response);
			}else if(ACTION_INSERT.equals(action)) {
				bill = new Bill();
				
				request.setAttribute("bill", bill);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("view/bill/edit.jsp");
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

		doGet(request, response);
	}

}