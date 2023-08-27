package com.jsp.onlinePharmacy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.onlinePharmacy.dao.AddressDao;
import com.jsp.onlinePharmacy.dto.AddressDto;
import com.jsp.onlinePharmacy.entity.Address;
import com.jsp.onlinePharmacy.util.ResponseStructure;

@Service
public class AddressService {

	@Autowired
	private AddressDao addressDao;
	

	public ResponseEntity<ResponseStructure<AddressDto>> saveAddress(Address address) {

		Address dbAddress = addressDao.saveAddress(address);

		ResponseStructure<AddressDto> structure = new ResponseStructure<AddressDto>();
		structure.setMessage("Address saved sucessfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dbAddress);
		return new ResponseEntity<ResponseStructure<AddressDto>>(structure, HttpStatus.CREATED);
	}

	
	
	public ResponseEntity<ResponseStructure<AddressDto>> findAddressById(int addressId) {
		Address dbAddress = addressDao.findAddressById(addressId);
		if (dbAddress != null) {

			ResponseStructure<AddressDto> structure = new ResponseStructure<AddressDto>();
			structure.setMessage("AddressData fetched  successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dbAddress);
			return new ResponseEntity<ResponseStructure<AddressDto>>(structure, HttpStatus.FOUND);
		} else {

			// raise exception address is null
			return null;
		}
	}
	
	

	public ResponseEntity<ResponseStructure<AddressDto>> updateAddress(int addressId, Address address) {
		
		
		Address dbAddress= addressDao.updateAddress(addressId , address);
		if(dbAddress != null )
		{
		AddressDto dto=new AddressDto();
		dto.setAddressId(dbAddress.getAddressId());
		dto.setCity(dbAddress.getCity());
		dto.setPincode(dbAddress.getPincode());
		dto.setState(dbAddress.getState());
		dto.setStreetName(dbAddress.getStreetName());
		
		ResponseStructure<AddressDto> structure=new ResponseStructure<AddressDto>();
		structure.setMessage("Address updated successfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(dto);
		return new ResponseEntity<ResponseStructure<AddressDto>>(structure,HttpStatus.OK);
	}else {
		return null;
		// raise the exception addressIdnotfoundexception
	}
		
	}

	public ResponseEntity<ResponseStructure<AddressDto>> deleteAddress(int addressId) {
	 Address dbaddress	= addressDao.deleteAddress(addressId);
	 
	    if(dbaddress != null )
	   {
	   ResponseStructure<AddressDto> structure = new ResponseStructure<>();
	   structure.setMessage("address is deleted sucessfully");
	   structure.setStatus(HttpStatus.GONE.value());
	   structure.setData(dbaddress);
		return new ResponseEntity<ResponseStructure<AddressDto>> (structure,HttpStatus.GONE);
       }
	    else {
	    	// raise exception 
	    	return null ;
	    }
	}

}
