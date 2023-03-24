/**
 * 
 */
package model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author stefa
 *
 */
public class User implements Serializable {
	
	private String first_name, last_name, username, email, cellphone, password;
	private Integer id;
	private Date birth_date, date_access, date_insert;

	/**
	 * 
	 */
	public User() {

	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}

	public Date getDate_access() {
		return date_access;
	}

	public void setDate_access(Date date_access) {
		this.date_access = date_access;
	}

	public Date getDate_insert() {
		return date_insert;
	}

	public void setDate_insert(Date date_insert) {
		this.date_insert = date_insert;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
