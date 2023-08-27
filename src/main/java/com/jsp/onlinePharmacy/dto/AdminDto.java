package com.jsp.onlinePharmacy.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDto {
	
	private int adminId;
	private String adminName;
	private String adminEmail;
	private String password;
	private long phoneNumber;

}
