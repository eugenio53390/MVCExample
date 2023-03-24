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

import model.User;

/**
 * @author stefa
 *
 */
public class UserDao extends DAO {
	
	private static final String SQL_INSERT = "INSERT INTO user (username, first_name, last_name, email, cellphone, birth_date, date_insert, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE user SET date_access=? WHERE id=?";
	private static final String SQL_DELETE = "DELETE user WHERE id=?";
	private static final String SQL_GET_ALL = "SELECT * FROM user";
	private static final String SQL_GET_BY_ID = "SELECT * FROM user where id=?";

	/**
	 * @param xml
	 * @throws ClassNotFoundException
	 * @throws JDOMException
	 * @throws IOException
	 * @throws SQLException
	 */
	public UserDao(String xml) throws ClassNotFoundException, JDOMException, IOException, SQLException {
		super(xml);

	}
	
	public User getById(int id) throws Exception {
		User user = new User();
		
		this.conn = this.getConnection();
		
		try {
			PreparedStatement st = this.conn.prepareStatement(SQL_GET_BY_ID);
			
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {

				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setFirst_name(rs.getString("first_name"));
				user.setLast_name(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setCellphone(rs.getString("cellphone"));
				user.setBirth_date(rs.getDate("birth_date"));
				user.setDate_access(rs.getDate("date_access"));
				user.setDate_insert(rs.getDate("date_insert"));
				user.setPassword(rs.getString("password"));
			}
		}catch (Exception e) {
			throw new Exception(e);
		}finally {
			this.conn.close();
		}
		
		return user;
	}
	
	public List<User> getAll() throws Exception {
		List<User> listUsers = new ArrayList<User>();
		
		this.conn = this.getConnection();
		
		try {
			Statement st = this.conn.createStatement();
			
			ResultSet rs = st.executeQuery(SQL_GET_ALL);
			
			while (rs.next()) {
				User temp = new User();
				temp.setId(rs.getInt("id"));
				temp.setUsername(rs.getString("username"));
				temp.setFirst_name(rs.getString("first_name"));
				temp.setLast_name(rs.getString("last_name"));
				temp.setEmail(rs.getString("email"));
				temp.setCellphone(rs.getString("cellphone"));
				temp.setBirth_date(rs.getDate("birth_date"));
				temp.setDate_access(rs.getDate("date_access"));
				temp.setDate_insert(rs.getDate("date_insert"));
				temp.setPassword(rs.getString("password"));
				
				listUsers.add(temp);
			}
		}catch (Exception e) {
			throw new Exception(e);
		}

		
		return listUsers;
	}

	public void insertUser(User user) throws SQLException, ClassNotFoundException, JDOMException, IOException 
	{
		this.conn = this.getConnection();
		//Preparo la query
		try (PreparedStatement preparedStatement = this.conn.prepareStatement(SQL_INSERT)) {

			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getFirst_name());
			preparedStatement.setString(3, user.getLast_name());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setString(5, user.getCellphone());
			preparedStatement.setDate(6, new java.sql.Date(user.getBirth_date().getTime()));
			preparedStatement.setDate(7, java.sql.Date.valueOf(LocalDate.now()));
			preparedStatement.setString(8, user.getPassword());

			System.out.println(preparedStatement);
			
			// Eseguo la query
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// processo la sql exception
			printSQLException(e);
			
			throw new SQLException(e);
		}finally {
			if(this.conn != null) {
				this.closeConnection();
			}
		}

	}
	
	public void deleteUser(int user_id) throws SQLException, ClassNotFoundException, JDOMException, IOException {
		
		this.conn = this.getConnection();
		
		//Preparo la query
		try (PreparedStatement preparedStatement = this.conn.prepareStatement(SQL_DELETE)) {

			preparedStatement.setInt(1, user_id);

			System.out.println(preparedStatement);
			

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// processo la sql exception
			printSQLException(e);
			
			throw new SQLException(e);
		}finally {
			this.closeConnection();
		}
	}
	
	public void updateUser(User user) throws SQLException, ClassNotFoundException, JDOMException, IOException {
		this.conn = this.getConnection();
		
		//Preparo la query
		try (PreparedStatement preparedStatement = this.conn.prepareStatement(SQL_UPDATE)) {

			preparedStatement.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
			preparedStatement.setInt(2, user.getId());


			System.out.println(preparedStatement);
			

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// processo la sql exception
			printSQLException(e);
			
			throw new SQLException(e);
		}finally {
			this.closeConnection();
		}
	}
}
