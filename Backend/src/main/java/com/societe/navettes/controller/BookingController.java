package com.societe.navettes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.societe.navettes.Entities.Booking;
import com.societe.navettes.Entities.Subscription;
import com.societe.navettes.Entities.User;
import com.societe.navettes.dao.SubscriptionRepository;
import com.societe.navettes.dto.BookingDTO;
import com.societe.navettes.service.BookingService;
import com.societe.navettes.service.SubscriptionService;



@RestController
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
	BookingService book_service;
	
	@Autowired
	SubscriptionService sub_service;
	@Autowired
	SubscriptionRepository sub_rep;

	@GetMapping("/all")
	public ResponseEntity<List<Booking>> getAllAutocar(){
		List<Booking> book = book_service.getBookings();
		return new ResponseEntity<List<Booking>>(book,HttpStatus.OK);
	}
	
	@PostMapping("/book")
	public ResponseEntity<String> Book(@RequestBody BookingDTO book){
		
		if(book.getName() == null || book.getNb_places()==0 || book.getTelephone() == null || book.getId_user()== null || book.getId_subcription()== null) {
			return new ResponseEntity<String>("Enter Data!",HttpStatus.ACCEPTED); 
		}
		Booking  book_save = new Booking();
		
		book_save.setName(book.getName());
		book_save.setNb_places(book.getNb_places());
		book_save.setTelephone(book.getTelephone());

		Subscription sub = sub_rep.findById(book.getId_subcription()).get();
		int p = sub.getN_wanted() - sub.getN_reached();
		if(p < book.getNb_places()) {
			return new ResponseEntity<String>("Problem with Booking still "+ p +" Seats",HttpStatus.NOT_FOUND);
		}
		sub.setN_reached(sub.getN_reached() + book.getNb_places());
		//sub.setN_wanted(sub.getN_wanted() - book.getNb_places());
		book_save.setSubscription(sub_service.add(sub));
		
		User user = new User();
		user.setId(book.getId_user());
		book_save.setUser(user);
		
		Booking saved = book_service.add(book_save);
		
		if(saved != null) {
			return new ResponseEntity<String>("Booking Added Successefully",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Problem with Booking",HttpStatus.NOT_FOUND);
		}
		
	}
	

}
