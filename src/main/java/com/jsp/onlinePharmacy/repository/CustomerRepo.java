package com.jsp.onlinePharmacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.onlinePharmacy.entity.Customer;

public interface  CustomerRepo extends JpaRepository<Customer, Integer> {

}
