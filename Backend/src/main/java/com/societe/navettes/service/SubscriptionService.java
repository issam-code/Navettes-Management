package com.societe.navettes.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.societe.navettes.Entities.Subscription;
import com.societe.navettes.dao.SubscriptionRepository;

@Component
public class SubscriptionService {

	@Autowired
	SubscriptionRepository sub_rep;
	
	public List<Subscription> getSubscriptions(){
		return sub_rep.findAll();
	}
	public Page<Subscription> searchSubscriptions(Specification<Subscription> spec, int page , int limit){
		return sub_rep.findAll(spec, PageRequest.of(page, limit));
	}
	
	public Page<Subscription> getSubscriptionsbyPagination(int page , int limit){
		return sub_rep.findAll(PageRequest.of(page, limit));
	}
	
	public Subscription add(Subscription subscription) {
		return sub_rep.save(subscription);
	}
	public List<Subscription> findUsersByQueryParams(Specification<Subscription> spec){
        return sub_rep.findAll(spec);
    }
}
