package com.mycompany.frs_maven.model.exceptions;

public class RecordNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RecordNotFoundException (String s) {
		super(s);	
	}
}
