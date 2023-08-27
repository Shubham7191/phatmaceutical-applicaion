package com.jsp.onlinePharmacy.dto;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StaffDto {

	private int staffId;
	private String staffName;
	private String email;
	private String password;
	private long phoneNumber;
	
	
	@ManyToOne
	private AdminDto adminDto;
	@OneToOne
	private MedicalStoreDto medicalStoreDto;
	

}
