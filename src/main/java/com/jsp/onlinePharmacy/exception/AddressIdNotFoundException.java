package com.jsp.onlinePharmacy.exception;

public class AddressIdNotFoundException extends RuntimeException{
	
	
	
	private String message ;
	
	public AddressIdNotFoundException(String message) {
		super();
		this.message = message;
	}

	
	
	
}
