package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bill implements Serializable {
	
	private Integer id;
	private String description;
	private Date date_insert;
	private User user;
	
	private List<BillRow> listRows;


	public Bill() {
		user = new User();
		setListRows(new ArrayList<BillRow>());
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getDate_insert() {
		return date_insert;
	}


	public void setDate_insert(Date date_insert) {
		this.date_insert = date_insert;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public List<BillRow> getListRows() {
		return listRows;
	}


	public void setListRows(List<BillRow> listRows) {
		this.listRows = listRows;
	}

}
