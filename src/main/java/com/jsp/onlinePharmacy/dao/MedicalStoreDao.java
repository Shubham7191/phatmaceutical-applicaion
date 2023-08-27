package com.jsp.onlinePharmacy.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.onlinePharmacy.dto.MedicalStoreDto;
import com.jsp.onlinePharmacy.entity.MedicalStore;
import com.jsp.onlinePharmacy.repository.MedicalStoreRepo;

@Repository
public class MedicalStoreDao {
	
	@Autowired
		private MedicalStoreRepo repo;

		public MedicalStore saveMedicalStore(MedicalStore medicalStore) {
			
			
			return repo.save(medicalStore);
		}

		public MedicalStore updateMedicalStore(int storeId, MedicalStore medicalStore) {
			
			Optional<MedicalStore> optional = repo.findById(storeId);
			if (optional.isPresent()) {
				// now we having name , managername
				MedicalStore oldMedicalStore = optional.get();
				// now we are storing mapping side 
				// 
				medicalStore.setStoreId(storeId);
				medicalStore.setAdmin(oldMedicalStore.getAdmin());
				medicalStore.setAddress(oldMedicalStore.getAddress());
				// now id , name , managername , phone , admin , address
				
				return repo.save(medicalStore);
			}
			return null;
		}

		public MedicalStore getMedicalStoreById(int storeId) {
			Optional<MedicalStore> optional = repo. findById(storeId );
			if (optional.isPresent()) {
				return optional.get();	
			}
			
			return null;
		}
		
		
		public MedicalStore deleteMedicalstoreById(int storeId) {
			Optional<MedicalStore> optional=repo.findById(storeId);
			if(optional.isPresent()){
				 MedicalStore medicalStore=optional.get();
				 repo.delete(medicalStore);
				 return medicalStore;
			}
			return null;
		}

}
