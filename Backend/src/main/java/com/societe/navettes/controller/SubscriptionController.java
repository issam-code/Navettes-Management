package com.societe.navettes.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.societe.navettes.Entities.Autocar;
import com.societe.navettes.Entities.Subscription;
import com.societe.navettes.Entities.User;
import com.societe.navettes.dao.SubscriptionRepository;
import com.societe.navettes.dto.SubscriptionDto;
import com.societe.navettes.service.SubscriptionService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

	@Autowired
	SubscriptionRepository sub_rep;
	@Autowired
	SubscriptionService sub_service;
	 
	@GetMapping("/size")
	public int getAllsubscriptions(){
		List<Subscription> subscriptions = sub_service.getSubscriptions();
		return subscriptions.size();
	}
	@PostMapping("/suggest")
	ResponseEntity<String> suggest_sub(@RequestBody SubscriptionDto subscriptiondto){
		Specification<Subscription> spec = Specification.where(null);
		Subscription subscription = new Subscription();
		
		if(subscriptiondto.getDate_depart() == null /*|| subscriptiondto.getDate_arriv() == null*/ || subscriptiondto.getTime_depart() == null || subscriptiondto.getTime_arriv() == null
				|| subscriptiondto.getCity_depart() == null || subscriptiondto.getCity_arriv() == null || subscriptiondto.getN_wanted() == 0
				|| subscriptiondto.getType() == null || subscriptiondto.getId_autocar() == 0 || subscriptiondto.getId_user() == 0 ) {
			return new ResponseEntity<String>("Enter Data!",HttpStatus.ACCEPTED); 
		}
           
		spec = spec.and((root, query, cb) -> cb.equal(root.get("city_depart"), subscriptiondto.getCity_depart()));
		spec = spec.and((root, query, cb) -> cb.equal(root.get("type"), subscriptiondto.getType()));
          
		spec = spec.and((root, query, cb) -> cb.equal(root.get("city_arriv"), subscriptiondto.getCity_arriv()));
  
		spec = spec.and((root, query, cb) -> cb.equal(root.get("date_depart"), subscriptiondto.getDate_depart()));

		List<Subscription> subscriptions = sub_service.findUsersByQueryParams(spec);
		int size= subscriptions.size();
		if(size > 0 ) {
			subscription = sub_rep.findById(subscriptions.get(0).getId());
			subscription.setN_wanted(subscription.getN_wanted() + 1);
		}else {
			subscription.setN_wanted(subscriptiondto.getN_wanted());
		}
			subscription.setDate_depart(subscriptiondto.getDate_depart());
			subscription.setDate_arriv(subscriptiondto.getDate_arriv());
			subscription.setTime_depart(subscriptiondto.getTime_depart());
			subscription.setTime_arriv(subscriptiondto.getTime_arriv());
			subscription.setCity_depart(subscriptiondto.getCity_depart());
			subscription.setCity_arriv(subscriptiondto.getCity_arriv());
			
			subscription.setN_reached(subscriptiondto.getN_reached());
			subscription.setType(subscriptiondto.getType());
			subscription.setPrice(subscriptiondto.getPrice());
			
			Autocar autocar = new Autocar();
			autocar.setId(subscriptiondto.getId_autocar());
			subscription.setAutocar(autocar);
			
			User user = new User();
			user.setId(subscriptiondto.getId_user());
			subscription.setUser(user);
			Subscription saved = sub_service.add(subscription);
			
			if(saved != null) {
				return new ResponseEntity<String>("Route Requested Successefully",HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Problem with Requesting Route",HttpStatus.NOT_FOUND);
			}
			
	}
	@GetMapping("/all")
	public ResponseEntity<ArrayList<Object>> searchSubscription(@RequestParam(required = false) String from,@RequestParam(required = false) String to,@RequestParam(required = false) String depart,@RequestParam(required = false) int page,@RequestParam(required = false) int limit,@RequestParam(required = false) String type, @RequestParam(required = false) Long id){
		Specification<Subscription> spec = Specification.where(null);
		if (id != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("user").get("id"), id));
        }
		if (type != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("type"), type));
        }
		if (from != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("city_depart"), from));
        }
		if (to != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("city_arriv"), to));
        }
		if (depart != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("date_depart"), depart));
        }
		Page<Subscription> subscriptions = sub_service.searchSubscriptions(spec,page,limit);
		List<Subscription> subscriptions1 = sub_service.findUsersByQueryParams(spec);
		int size= subscriptions1.size();
		ArrayList<Object> result = new ArrayList<>();
		result.add(subscriptions);
		result.add(size);
		return new ResponseEntity<ArrayList<Object>>(result,HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Subscription> getSubscription(@PathVariable("id") Long id){
		Subscription subscription= sub_rep.findById(id).get();
		return new ResponseEntity<Subscription>(subscription,HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<String> addSubscription(@RequestBody SubscriptionDto subscriptiondto){
		if(subscriptiondto.getDate_depart() == null /*|| subscriptiondto.getDate_arriv() == null*/ || subscriptiondto.getTime_depart() == null || subscriptiondto.getTime_arriv() == null
				|| subscriptiondto.getCity_depart() == null || subscriptiondto.getCity_arriv() == null || subscriptiondto.getN_wanted() == 0
				|| subscriptiondto.getType() == null || subscriptiondto.getId_autocar() == 0 || subscriptiondto.getId_user() == 0 ) {
			return new ResponseEntity<String>("Enter Data!",HttpStatus.ACCEPTED); 
		}
		Subscription subscription = new Subscription();
		
		subscription.setDate_depart(subscriptiondto.getDate_depart());
		subscription.setDate_arriv(subscriptiondto.getDate_arriv());
		subscription.setTime_depart(subscriptiondto.getTime_depart());
		subscription.setTime_arriv(subscriptiondto.getTime_arriv());
		subscription.setCity_depart(subscriptiondto.getCity_depart());
		subscription.setCity_arriv(subscriptiondto.getCity_arriv());
		subscription.setN_wanted(subscriptiondto.getN_wanted());
		subscription.setN_reached(subscriptiondto.getN_reached());
		subscription.setType(subscriptiondto.getType());
		subscription.setPrice(subscriptiondto.getPrice());
		
		Autocar autocar = new Autocar();
		autocar.setId(subscriptiondto.getId_autocar());
		subscription.setAutocar(autocar);
		
		User user = new User();
		user.setId(subscriptiondto.getId_user());
		subscription.setUser(user);
		
		Subscription saved = sub_service.add(subscription);
		if(saved != null) {
			return new ResponseEntity<String>("Subscription Added Successefully",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Problem with Adding Subscription",HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateSubscription(@PathVariable("id") Long id, @RequestBody SubscriptionDto subscription){
		Subscription old_sub= sub_rep.findById(id).get();
		old_sub.setDate_depart(subscription.getDate_depart());
		//old_sub.setDate_arriv(subscription.getDate_arriv());
		old_sub.setTime_depart(subscription.getTime_depart());
		old_sub.setTime_arriv(subscription.getTime_arriv());
		old_sub.setCity_depart(subscription.getCity_depart());
		old_sub.setCity_arriv(subscription.getCity_arriv());
		old_sub.setN_wanted(subscription.getN_wanted());
		old_sub.setPrice(subscription.getPrice());
		old_sub.setType(subscription.getType());
		
		Autocar autocar = new Autocar();
		autocar.setId(subscription.getId_autocar());
		old_sub.setAutocar(autocar);
		
		User user = new User();
		user.setId(subscription.getId_user());
		old_sub.setUser(user);
		
		Subscription saved = sub_service.add(old_sub);

		if(saved != null) {
			return new ResponseEntity<String>("Subscription Updated Successefully",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Problem with Updating Subscription",HttpStatus.NOT_FOUND);
		}

		
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteSubscription(@PathVariable("id") Long id){
		sub_rep.deleteById(id);
		return new ResponseEntity<String>("Subscription deleted Successefully",HttpStatus.OK);
	}
}
