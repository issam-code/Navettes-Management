package com.societe.navettes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.societe.navettes.Entities.City;
import com.societe.navettes.dao.CityRepository;


@Component
public class CityService {
	@Autowired
	private CityRepository city_rep;
	
	public List<City> getCities(){
		return city_rep.findAll(Sort.by(Sort.Direction.ASC,"name"));
	}

}
