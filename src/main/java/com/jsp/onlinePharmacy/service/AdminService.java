package com.jsp.onlinePharmacy.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.onlinePharmacy.dao.AdminDao;
import com.jsp.onlinePharmacy.dto.AdminDto;
import com.jsp.onlinePharmacy.entity.Admin;
import com.jsp.onlinePharmacy.util.ResponseStructure;

@Service
public class AdminService {
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<ResponseStructure<AdminDto>> saveAdmin(Admin admin) {
	   Admin dbAdmin = adminDao.saveAdmin(admin);
	 AdminDto adminDto =  	this.modelMapper.map(dbAdmin, AdminDto.class);
	 
	 ResponseStructure<AdminDto> responseStructure = new ResponseStructure<AdminDto>();
	 responseStructure.setMessage("admin saved sucessfully");
	 responseStructure.setStatus(HttpStatus.CREATED.value());
	 responseStructure.setData(adminDto);
	
		return new ResponseEntity<ResponseStructure<AdminDto>>(responseStructure,HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<AdminDto>> updateAdmin(int adminId, Admin admin) {
	Admin dbAdmin = adminDao.updateAdmin(adminId,admin);
	if (dbAdmin != null ) {
		AdminDto adminDto = this.modelMapper.map(dbAdmin, AdminDto.class);
		ResponseStructure<AdminDto> structure = new ResponseStructure<>();
		structure.setMessage("Admin updated successfuly");
		structure.setStatus(HttpStatus.UPGRADE_REQUIRED.value());
		structure.setData(adminDto);
		
		return new ResponseEntity<ResponseStructure<AdminDto>> (structure,HttpStatus.UPGRADE_REQUIRED);
	}
	// raise exception
		return null;
	}

	public ResponseEntity<ResponseStructure<AdminDto>> findAdmin(int adminId) {
		 Admin dbadmin= adminDao.findAdmin(adminId);
		 
		 if (dbadmin != null) {
		AdminDto adminDto	= this.modelMapper.map(dbadmin, AdminDto.class);
			
		ResponseStructure<AdminDto> structure = new ResponseStructure<>();
		structure.setMessage("Admin updated successfuly");
		structure.setStatus(HttpStatus.FOUND.value());
		structure.setData(adminDto);
		
		return new ResponseEntity<ResponseStructure<AdminDto>> (structure,HttpStatus.FOUND);
	}
	// raise exception
		return null;
	}

	public ResponseEntity<ResponseStructure<AdminDto>> deleteAdmin(int adminId) {
		
		Admin dbadmin = adminDao.deleteAdmin(adminId);
		if (dbadmin != null ){
			
			
			ResponseStructure<AdminDto> structure = new ResponseStructure<>();
			structure.setMessage("Admin deleted  successfuly");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dbadmin);
			
			return new ResponseEntity<ResponseStructure<AdminDto>> (structure,HttpStatus.OK);
		}
		return null;
		}
		
        
	

}
