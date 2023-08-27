
package com.jsp.onlinePharmacy.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.onlinePharmacy.dao.AddressDao;
import com.jsp.onlinePharmacy.dao.AdminDao;
import com.jsp.onlinePharmacy.dao.MedicalStoreDao;
import com.jsp.onlinePharmacy.dto.AddressDto;
import com.jsp.onlinePharmacy.dto.AdminDto;
import com.jsp.onlinePharmacy.dto.MedicalStoreDto;
import com.jsp.onlinePharmacy.entity.Address;
import com.jsp.onlinePharmacy.entity.Admin;
import com.jsp.onlinePharmacy.entity.MedicalStore;
import com.jsp.onlinePharmacy.exception.AddressIdNotFoundException;
import com.jsp.onlinePharmacy.exception.AdminIdNotFoundException;
import com.jsp.onlinePharmacy.exception.MedicalStoreIdNotFoundException;
import com.jsp.onlinePharmacy.util.ResponseStructure;

@Service
public class MedicalStoreService {

	@Autowired
	private MedicalStoreDao medicalStoreDao;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private AddressDao addressDao;

	public ResponseEntity<ResponseStructure<MedicalStoreDto>> saveMedicalStore(int adminId, int addressId,
			MedicalStoreDto medicalStoreDto) {

		// we are taking dto from front end now useing mapper we are converting into
		// entity

		MedicalStore medicalStore = this.modelMapper.map(medicalStoreDto, MedicalStore.class);

		// this medicalstore is not having admin and address yet
		// so first i need to get that admin by useing autowired

		Admin dbadmin = adminDao.findAdmin(adminId);
		// now we check whether in this particular admin id have admin or not

		if (dbadmin != null) {
			// now we store this admin into medical store

			medicalStore.setAdmin(dbadmin);
			Address dbAddress = addressDao.findAddressById(addressId);
			if (dbAddress != null) {
				medicalStore.setAddress(dbAddress);

				// now we saving this medicalstore into db
				MedicalStore dbmedicalStore = medicalStoreDao.saveMedicalStore(medicalStore);
				Address dbMeddicalAddress = dbmedicalStore.getAddress();

				// address Dto class
				AddressDto addressDto = this.modelMapper.map(dbMeddicalAddress, AddressDto.class);
				Admin dbMedicalStoreAdmin = dbmedicalStore.getAdmin();

				// now we saving mappinmg class as dto

				MedicalStoreDto dto = this.modelMapper.map(dbmedicalStore, MedicalStoreDto.class);

				// we are saving first address dto
				dto.setAddressDto(addressDto);

				// secound way to store admin
				dto.setAdminDto(this.modelMapper.map(dbMedicalStoreAdmin, AdminDto.class));

				ResponseStructure<MedicalStoreDto> structure = new ResponseStructure<MedicalStoreDto>();
				structure.setMessage("MedicalStore added successfully");
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setData(dto);
				return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure, HttpStatus.CREATED);

			} else {
				throw new AddressIdNotFoundException("Sorry failed to add medicalstore");
			}

		} else {
			throw new AdminIdNotFoundException("Sorry failed to add medicalstore");
		}
	}

	public ResponseEntity<ResponseStructure<MedicalStoreDto>> updateMedicalStore(int storeId,
			MedicalStore medicalStore) {
		MedicalStore dbMedicalStore = medicalStoreDao.updateMedicalStore(storeId, medicalStore);
		if (dbMedicalStore != null) {
			MedicalStoreDto storeDto = this.modelMapper.map(dbMedicalStore, MedicalStoreDto.class);
			storeDto.setAddressDto(this.modelMapper.map(dbMedicalStore.getAddress(), AddressDto.class));
			storeDto.setAdminDto(this.modelMapper.map(dbMedicalStore.getAdmin(), AdminDto.class));

			ResponseStructure<MedicalStoreDto> structure = new ResponseStructure<MedicalStoreDto>();
			structure.setMessage("MedicalStoreUpdated successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(storeDto);
			return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure, HttpStatus.OK);
		} else {
			// when that id is not present
			throw new MedicalStoreIdNotFoundException("Sorry failed to update medicalstore id is not present ");
		}
	}

	public ResponseEntity<ResponseStructure<MedicalStoreDto>> getMedicalStoreById(int storeId) {
		MedicalStore dbMedicalStore = medicalStoreDao.getMedicalStoreById(storeId);
		if (dbMedicalStore != null) {
			MedicalStoreDto storeDto = this.modelMapper.map(dbMedicalStore, MedicalStoreDto.class);
			storeDto.setAddressDto(this.modelMapper.map(dbMedicalStore.getAddress(), AddressDto.class));
			storeDto.setAdminDto(this.modelMapper.map(dbMedicalStore.getAdmin(), AdminDto.class));
			ResponseStructure<MedicalStoreDto> structure = new ResponseStructure<MedicalStoreDto>();
			structure.setMessage("MedicalStore fetched successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(storeDto);
			return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure, HttpStatus.FOUND);
		} else {
//			when that id is not present
			throw new MedicalStoreIdNotFoundException("Sorry failed to fetch medicalstore");
		}
	}

	public ResponseEntity<ResponseStructure<MedicalStoreDto>> deleteMedicalstoreById(int storeId) {
		MedicalStore dbMedicalStore=medicalStoreDao.deleteMedicalstoreById(storeId);
	 	if(dbMedicalStore!=null) {
			MedicalStoreDto storeDto=this.modelMapper.map(dbMedicalStore, MedicalStoreDto.class);
			storeDto.setAddressDto(this.modelMapper.map(dbMedicalStore.getAddress(), AddressDto.class));
			storeDto.setAdminDto(this.modelMapper.map(dbMedicalStore.getAdmin(), AdminDto.class));
			
			ResponseStructure<MedicalStoreDto> structure=new ResponseStructure<MedicalStoreDto>();
			structure.setMessage("MedicalStore deleted successfully");
			structure.setStatus(HttpStatus.GONE.value());
			structure.setData(storeDto);
			return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure,HttpStatus.GONE);
		}else {
//			when that id is not present
			throw new MedicalStoreIdNotFoundException("Sorry failed to delete medicalstore");
		}
	}
	

}