package model;

import java.io.Serializable;
import java.util.Date;

public class BillRow implements Serializable {
	
	private Integer id, number;
	private String description;
	private Bill bill;

	public BillRow() {
		setBill(new Bill());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

}
