package model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Scot Ismael
 */

public class Log implements Serializable {
	
	private int id, id_user;
	private Date creation_log;
	private String actions, note;
	
	public Log() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public Date getCreation_log() {
		return creation_log;
	}

	public void setCreation_log(Date creation_log) {
		this.creation_log = creation_log;
	}

	public String getActions() {
		return actions;
	}

	public void setActions(String actions) {
		this.actions = actions;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
}
