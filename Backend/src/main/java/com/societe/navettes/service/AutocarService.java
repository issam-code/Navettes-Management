package com.societe.navettes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.societe.navettes.Entities.Autocar;
import com.societe.navettes.dao.AutocarRepository;



@Component
public class AutocarService {

	@Autowired
	private AutocarRepository autocar_rep;
	
	public List<Autocar> getAutocars(){
		return autocar_rep.findAll();
	}
	public Autocar add(Autocar autocar) {
		return autocar_rep.save(autocar);
	}
}
