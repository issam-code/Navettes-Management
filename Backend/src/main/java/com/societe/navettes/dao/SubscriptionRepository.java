package com.societe.navettes.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.societe.navettes.Entities.Subscription;


public interface SubscriptionRepository extends JpaRepository<Subscription, Long> , JpaSpecificationExecutor<Subscription> {
	Subscription save(Subscription subscription);
	Subscription findById(long id);
}
