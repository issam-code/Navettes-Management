package com.societe.navettes.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.societe.navettes.Entities.Booking;


public interface BookingRepository extends JpaRepository<Booking, Long>{
	Booking save(Booking autocar);
	Booking findById(long id);
}
