package dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.JDOMException;

import model.Bill;
import model.BillRow;

public class BillRowDao extends DAO{
	private static final String SQL_INSERT = "INSERT INTO bill_row (bill_id, description, number) VALUES (?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE bill_row SET description=? AND number=? WHERE id=?";
	private static final String SQL_DELETE = "DELETE bill_row WHERE id=?";
	private static final String SQL_GET_ALL_BY_BILL_ID = "SELECT * FROM bill_row WHERE bill_id=?";
	private static final String SQL_GET_BY_ID = "SELECT * FROM bill_row where id=?";
	
	private BillDao billDao;
	private boolean loadEntity = false;

	public BillRowDao(String xml, boolean loadEntity) throws ClassNotFoundException, JDOMException, IOException, SQLException {
		super(xml);
		
		this.loadEntity = loadEntity;
		
		if(this.loadEntity) {
			billDao = new BillDao(xml, this.loadEntity);
		}



	}
	
	public List<BillRow> getAllByBillID(int bill_ID) throws Exception {
		List<BillRow> listBillRows = new ArrayList<BillRow>();
		PreparedStatement st = null;
		ResultSet rs = null;
		Integer user_id = null;
		
		this.conn = this.getConnection();
		
		try {
			if(this.loadEntity) {
				billDao.setConnection(this.conn);

			}

			
			st = this.conn.prepareStatement(SQL_GET_ALL_BY_BILL_ID);
			
			st.setInt(1, bill_ID);
			
			rs = st.executeQuery();
			
			while (rs.next()) {
				BillRow temp = new BillRow();
				temp.setId(rs.getInt("id"));
				temp.setDescription(rs.getString("description"));
				temp.setNumber(rs.getInt("number"));

				if(this.loadEntity) {
					temp.setBill(billDao.getById(bill_ID));
				}

				
				listBillRows.add(temp);
			}

		}catch (Exception e) {
			throw new Exception(e);
		}finally {
			this.closeConnection();
		}
		

		
		
		
		return listBillRows;
	}

	public BillRow getById(int id) throws Exception {
		BillRow row = new BillRow();
		Integer bill_id = null;

		
		try {
			this.conn = this.getConnection();
			
			if(this.loadEntity) {
				billDao.setConnection(this.conn);

			}
			
			PreparedStatement st = this.conn.prepareStatement(SQL_GET_BY_ID);
			
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {

				row.setId(rs.getInt("id"));
				row.setDescription(rs.getString("description"));
				row.setNumber(rs.getInt("number"));
				bill_id = rs.getInt("bill_id");	
				
				if(this.loadEntity) {
					row.setBill(this.billDao.getById(bill_id));
				}

			}
		}catch (Exception e) {
			throw new Exception(e);
		}finally {
			this.conn.close();
		}
		
		return row;
	}
	
	public void insertRow(BillRow row) throws SQLException, ClassNotFoundException, JDOMException, IOException 
	{
		this.conn = this.getConnection();
		//Preparo la query
		try (PreparedStatement preparedStatement = this.conn.prepareStatement(SQL_INSERT)) {

			preparedStatement.setInt(1, row.getBill().getId());
			preparedStatement.setString(2, row.getDescription());
			preparedStatement.setInt(3, row.getNumber());


			System.out.println(preparedStatement);
			

			preparedStatement.executeUpdate();

		} catch (SQLException e) {

			printSQLException(e);
			
			throw new SQLException(e);
		}finally {
			this.closeConnection();
		}

	}
	
	public void deleteRow(int id) throws SQLException, ClassNotFoundException, JDOMException, IOException {
		
		this.conn = this.getConnection();
		
		//Preparo la query
		try (PreparedStatement preparedStatement = this.conn.prepareStatement(SQL_DELETE)) {

			preparedStatement.setInt(1, id);

			System.out.println(preparedStatement);
			

			preparedStatement.executeUpdate();

		} catch (SQLException e) {

			printSQLException(e);
			
			throw new SQLException(e);
		}finally {
			this.closeConnection();
		}
	}
	
	public void updateRow(BillRow row) throws SQLException, ClassNotFoundException, JDOMException, IOException {
		this.conn = this.getConnection();
		
		//Preparo la query
		try (PreparedStatement preparedStatement = this.conn.prepareStatement(SQL_UPDATE)) {

			preparedStatement.setString(1, row.getDescription());
			preparedStatement.setInt(2, row.getNumber());
			preparedStatement.setInt(3, row.getId());

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
