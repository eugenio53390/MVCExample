/**
 * 
 */
package dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.JDOMException;

import model.Bill;
import model.BillRow;
import model.User;

/**
 * @author stefa
 *
 */
public class BillDao extends DAO {
	
	private static final String SQL_INSERT = "INSERT INTO bill (description, user_id) VALUES (?, ?)";
	private static final String SQL_UPDATE = "UPDATE bill SET description=? WHERE id=?";
	private static final String SQL_DELETE = "DELETE bill WHERE id=?";
	private static final String SQL_GET_ALL = "SELECT * FROM bill";
	private static final String SQL_GET_BY_ID = "SELECT * FROM bill where id=?";
	
	private UserDao userDao;
	private BillRowDao rowsDao;
	
	private boolean loadEntity = false;

	/**
	 * @param xml
	 * @throws ClassNotFoundException
	 * @throws JDOMException
	 * @throws IOException
	 * @throws SQLException
	 */
	public BillDao(String xml, boolean loadEntity) throws ClassNotFoundException, JDOMException, IOException, SQLException {
		super(xml);
		
		userDao = new UserDao(xml);
		
		this.loadEntity = loadEntity;
		
		rowsDao = new BillRowDao(xml, this.loadEntity);

	}
	
	public List<Bill> getAll() throws Exception {
		List<Bill> listBills = new ArrayList<Bill>();
		Statement st = null;
		ResultSet rs = null;
		Integer user_id = null;
		

		
		try {
			this.conn = this.getConnection();
			
			userDao.setConnection(this.conn);
			rowsDao.setConnection(this.conn);
			
			st = this.conn.createStatement();
			
			rs = st.executeQuery(SQL_GET_ALL);
			
			while (rs.next()) {
				Bill temp = new Bill();
				temp.setId(rs.getInt("id"));
				temp.setDescription(rs.getString("description"));
				temp.setDate_insert(rs.getDate("date_insert"));
				user_id = rs.getInt("user_id");	
				
				temp.setUser(userDao.getById(user_id));
				
				temp.setListRows(rowsDao.getAllByBillID(rs.getInt("id")));
				
				listBills.add(temp);
			}

		}catch (Exception e) {
			throw new Exception(e);
		}finally {
			this.closeConnection();
		}
		

		
		
		
		return listBills;
	}

	public Bill getById(int id) throws Exception {
		Bill bill = new Bill();
		Integer user_id = null;
		
		this.conn = this.getConnection();
		
		try {
			userDao.setConnection(this.conn);
			rowsDao.setConnection(this.conn);
			
			PreparedStatement st = this.conn.prepareStatement(SQL_GET_BY_ID);
			
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {

				bill.setId(rs.getInt("id"));
				bill.setDescription(rs.getString("description"));
				bill.setDate_insert(rs.getDate("date_insert"));
				user_id = rs.getInt("user_id");	
				
				bill.setUser(userDao.getById(user_id));
				
				bill.setListRows(rowsDao.getAllByBillID(rs.getInt("id")));
			}
		}catch (Exception e) {
			throw new Exception(e);
		}finally {
			this.conn.close();
		}
		
		return bill;
	}
	
	public void insertBill(Bill bill) throws SQLException, ClassNotFoundException, JDOMException, IOException 
	{
		this.conn = this.getConnection();
		//Preparo la query
		try (PreparedStatement preparedStatement = this.conn.prepareStatement(SQL_INSERT)) {

			preparedStatement.setString(1, bill.getDescription());
			preparedStatement.setInt(2, bill.getUser().getId());


			System.out.println(preparedStatement);
			

			preparedStatement.executeUpdate();

		} catch (SQLException e) {

			printSQLException(e);
			
			throw new SQLException(e);
		}finally {
			this.closeConnection();
		}

	}
	
	/**
	 * Cancellazione massiva della fattura con tutti le sue righe
	 * @param id
	 * @throws Exception
	 */
	public void deleteBill(int id) throws Exception {
		
		this.conn = this.getConnection();
		
		Bill bill = null;
		
		//Preparo la query
		try {
			rowsDao.setConnection(this.conn);
			PreparedStatement preparedStatement = this.conn.prepareStatement(SQL_DELETE);
			
			this.conn.setAutoCommit(false);
			
			bill = this.getById(id);
			
			for (BillRow row : bill.getListRows()) {
				rowsDao.deleteRow(row.getId());
			}

			preparedStatement.setInt(1, id);

			System.out.println(preparedStatement);
			

			preparedStatement.executeUpdate();
			
			this.conn.commit();


		} catch (SQLException e) {
			this.conn.rollback();

			printSQLException(e);
			
			throw new SQLException(e);
		}finally {
			this.closeConnection();
		}
	}
	
	public void updateBill(Bill bill) throws SQLException, ClassNotFoundException, JDOMException, IOException {
		this.conn = this.getConnection();
		
		//Preparo la query
		try (PreparedStatement preparedStatement = this.conn.prepareStatement(SQL_UPDATE)) {

			preparedStatement.setString(1, bill.getDescription());


			System.out.println(preparedStatement);
			

			preparedStatement.executeUpdate();

		} catch (SQLException e) {

			printSQLException(e);
			
			throw new SQLException(e);
		}finally {
			this.closeConnection();
		}
	}
}
