package com.mycompany.frs_maven.model.domain;

import java.io.Serializable;

public class DTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Boolean status;
	private Object data;
	private String commandString;
	
	public Boolean getStatus() {
		return this.status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Object getData() {
		return this.data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getCommandString() {
		return this.commandString;
	}
	public void setCommandString(String commandString) {
		this.commandString = commandString;
	}
}