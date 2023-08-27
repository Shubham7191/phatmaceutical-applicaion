package com.jsp.onlinePharmacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.onlinePharmacy.dto.StaffDto;
import com.jsp.onlinePharmacy.entity.Staff;
import com.jsp.onlinePharmacy.service.StaffService;
import com.jsp.onlinePharmacy.util.ResponseStructure;
@RestController
@RequestMapping("/staff")

public class StaffController {
	
	@Autowired
	private StaffService service ;
	
	
	public ResponseEntity<ResponseStructure<StaffDto>> addStaff(@RequestParam int adminId, @RequestParam int storeId , @RequestBody Staff staff){
		
		return service.addStaff(adminId , storeId ,staff);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<StaffDto>> updateStaff(@RequestParam int staffId,@RequestBody Staff staff){
		return service.updateStaff(staffId,staff);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<StaffDto>> findStaff(@RequestParam int staffId){
		return service.getStaffById(staffId);
	}
	@DeleteMapping
	public ResponseEntity<ResponseStructure<StaffDto>> deleteStaffById(@RequestParam int staffId){
		return service.deleteStaffById(staffId);
	}

}
