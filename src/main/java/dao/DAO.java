package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.jdom2.JDOMException;

import utils.Config;



/**
 * Classe DAO di accesso ai dati
 * 
 * @author ste
 *
 */
public class DAO {

	protected Connection conn;
	protected Config cfg;

	/**
	 * Costruttore della classe di accesso ai dati
	 * 
	 * @throws ClassNotFoundException
	 * @throws JDOMException
	 * @throws IOException
	 * @throws SQLException
	 */
	public DAO(String xml) throws ClassNotFoundException, JDOMException, IOException, SQLException {
		this.cfg = new Config(xml);


	}
	
	/**
	 * metodo creato per utilizzare la stessa connessione passata da input
	 * @param conn
	 */
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	protected void closeConnection() throws SQLException {
		if(this.conn != null && !this.conn.isClosed()) {
			this.conn.close();
		}
	}

	/**
	 * Legge dal file di configurazione la stringa di connessione e imposta la
	 * connessione ai dati
	 * 
	 * @return la connessione al DB
	 * @throws JDOMException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	protected Connection getConnection()
			throws JDOMException, IOException, ClassNotFoundException, SQLException {

		this.cfg.loadConfig();

		Class.forName(this.cfg.getDriver());// leggo driver

		System.out.println("Driver Load: " + this.cfg.getDriver());

		System.out.println(cfg.getDbUrl() + " " + this.cfg.getUser() + " " + this.cfg.getPassword());

		return DriverManager.getConnection(this.cfg.getDbUrl(), this.cfg.getUser(), this.cfg.getPassword());
	}
	
	/**
	 * Gestisco l�eccezione sql stampando l'errore a console
	 * @param ex l'eccezione sollevata dal metodo
	 */
	protected void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
