package com.jsp.onlinePharmacy.exception;

import lombok.Getter;

@Getter
public class MedicineIdNotFoundException extends RuntimeException {

	
		private String message ;

		public MedicineIdNotFoundException(String message) {
			super();
			this.message = message;
		}
		
}
