package com.societe.navettes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.societe.navettes.Entities.Booking;
import com.societe.navettes.dao.BookingRepository;


@Component
public class BookingService {

	@Autowired
	private BookingRepository book_rep;
	
	public List<Booking> getBookings(){
		return book_rep.findAll();
	}
	public Booking add(Booking book) {
		return book_rep.save(book);
	}
}
