package com.jsp.onlinePharmacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.onlinePharmacy.entity.Booking;

public interface BookingRepo extends JpaRepository< Booking, Integer> {

}
