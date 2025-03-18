package com.societe.navettes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.societe.navettes.Entities.City;
import com.societe.navettes.service.CityService;


@RestController
@RequestMapping("/city")
public class CityController {
	
	@Autowired
	CityService city_service;

	@GetMapping("/")
	public ResponseEntity<List<City>> getAllCitiesr(){
		List<City> cities = city_service.getCities();
		return new ResponseEntity<List<City>>(cities,HttpStatus.OK);
	}
}
