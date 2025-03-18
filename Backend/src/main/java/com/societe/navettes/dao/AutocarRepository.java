package com.societe.navettes.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.societe.navettes.Entities.Autocar;


public interface AutocarRepository extends JpaRepository<Autocar, Long> {

	Autocar save(Autocar autocar);
	Autocar findById(long id);
	
	
}
