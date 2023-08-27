package com.jsp.onlinePharmacy.exception;

import lombok.Getter;

@Getter
public class StaffIdNOtFoundException extends RuntimeException {

	
	
	private String message ;

	public StaffIdNOtFoundException(String message) {
		super();
		this.message = message;
	}


	
	
}
