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

import dao.BillDao;
import model.Bill;

/**
 * Servlet implementation class UsersIndex
 */
@WebServlet("/Bills Edit")
public class BillsEditController extends AuthorizedController {
	private static final long serialVersionUID = 1L;

	
	private BillDao billDao = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BillsEditController() {
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
			super.doGet(request, response);
			
			if(this.isAuthorized) {
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