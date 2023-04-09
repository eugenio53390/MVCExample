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
	
	private static final String SQL_INSERT = "INSERT INTO user (username, first_name, last_name, email, cellphone, birth_date, date_insert, password, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE_DATE_ACCESS = "UPDATE user SET date_access=? WHERE id=?";
	private static final String SQL_UPDATE = "UPDATE user SET username=?, first_name=?, last_name=?, email=?, cellphone=?, birth_date=?, password=? WHERE id=?";
	private static final String SQL_DELETE = "DELETE FROM user WHERE id=?";
	private static final String SQL_GET_ALL = "SELECT * FROM user";
	private static final String SQL_GET_BY_ID = "SELECT * FROM user where id=?";
	private static final String SQL_GET_BY_USER_AND_PASSWORD = "SELECT * FROM user where username=? and password=?";
	
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
	
	public User getByUserAndPassword(String username, String password) throws Exception {
		User user = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			this.conn = this.getConnection();
			
			st = this.conn.prepareStatement(SQL_GET_BY_USER_AND_PASSWORD);
			
			st.setString(1, username);
			st.setString(2, password);
			
			rs = st.executeQuery();
			
			while (rs.next()) {
				user = new User();
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
				user.setRole(rs.getString("role"));
			}
		}catch (Exception e) {
			user = null;
			
			throw new Exception(e);
		}finally {
			this.conn.close();
		}
		
		return user;
	}
	
	public User getById(int id) throws Exception {
		User user = new User();
		
		try {
			this.conn = this.getConnection();
			
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
				user.setRole(rs.getString("role"));
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
				temp.setRole(rs.getString("role"));
				
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
			preparedStatement.setString(9, user.getRole());
			
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

			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getFirst_name());
			preparedStatement.setString(3, user.getLast_name());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setString(5, user.getCellphone());
			preparedStatement.setDate(6, new java.sql.Date(user.getBirth_date().getTime()));
			preparedStatement.setString(7, user.getPassword());
			preparedStatement.setInt(8, user.getId());


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
	
	public void updateUser4DateAccess(User user) throws SQLException, ClassNotFoundException, JDOMException, IOException {
		this.conn = this.getConnection();
		
		//Preparo la query
		try (PreparedStatement preparedStatement = this.conn.prepareStatement(SQL_UPDATE_DATE_ACCESS)) {

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
