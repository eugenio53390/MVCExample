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
import javax.servlet.http.HttpSession;

import org.jdom2.JDOMException;

import dao.BillDao;
import model.Bill;

/**
 * Servlet implementation class BILLIndex
 */
@WebServlet("/BILLIndex")
public class BILLIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
    BillDao billDao= null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BILLIndex() {
        super();
        
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			billDao =new BillDao(getServletContext().getRealPath("/") + config.getServletContext().getInitParameter("config"), false);
		} catch (ClassNotFoundException | JDOMException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			List<Bill> list=billDao.getAll();
			request.setAttribute("bills", list);
			RequestDispatcher dispatcher = request.getRequestDispatcher("view/bill/billTable.jsp");
	        dispatcher.forward(request, response);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
