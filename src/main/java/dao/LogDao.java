package dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Statement;

import org.jdom2.JDOMException;

import model.Log;

public class LogDao extends DAO{
	
	//private static final String SQL_INSERT = "INSERT INTO products (id, name, description, price) VALUES (?, ?, ?, ?)";
	//private static final String SQL_UPDATE = "UPDATE products SET date_access=? WHERE id=?";
	//private static final String SQL_DELETE = "DELETE products WHERE id=?";
	private static final String SQL_GET_ALL = "SELECT * FROM logs";
	//private static final String SQL_GET_BY_ID = "SELECT * FROM products where id=?";
	

	public LogDao(String xml) throws ClassNotFoundException, JDOMException, IOException, SQLException {
		super(xml);
		// TODO Auto-generated constructor stub
	}

	public List<Log> getAll() throws Exception{
		List<Log> logs = new ArrayList<Log>();
		
		this.conn = this.getConnection();
		
		try {
			Statement st = this.conn.createStatement();
			
			ResultSet rs = st.executeQuery(SQL_GET_ALL);
			
			while (rs.next()) {
				Log temp = new Log();
				temp.setId(rs.getInt("id"));
				temp.setId_user(rs.getInt("id_user"));
				temp.setCreation_log(rs.getDate("creation_log"));
				temp.setActions(rs.getString("actions"));
				temp.setNote(rs.getString("note"));
			}
		}catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e);		
		}
		
		return logs;
		
	}
}
