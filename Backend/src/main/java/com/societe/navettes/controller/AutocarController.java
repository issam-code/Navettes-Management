package com.societe.navettes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.societe.navettes.Entities.Autocar;
import com.societe.navettes.dao.AutocarRepository;
import com.societe.navettes.service.AutocarService;


@RestController
@RequestMapping("/autocar")
public class AutocarController {

	@Autowired
	AutocarRepository autocar_rep;
	@Autowired
	AutocarService sub_service;
	
	@GetMapping("/")
	public ResponseEntity<List<Autocar>> getAllAutocar(){
		List<Autocar> autocars = sub_service.getAutocars();
		return new ResponseEntity<List<Autocar>>(autocars,HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Autocar> getAutocar(@PathVariable("id") Long id){
		Autocar autocar= autocar_rep.findById(id).get();
		return new ResponseEntity<Autocar>(autocar,HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<String> addAutocar(@RequestBody Autocar autocar){
		if(autocar.getN_seat() == 0) return new ResponseEntity<String>("Enter N - Seat!",HttpStatus.ACCEPTED); 
		Autocar saved = sub_service.add(autocar);
		if(saved != null) {
			return new ResponseEntity<String>("Autocar Added Successefully",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Problem with Adding Autocar",HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Autocar> updateAutocar(@PathVariable("id") Long id, @RequestBody Autocar autocar){
		Autocar old_autocar= autocar_rep.findById(id).get();
		old_autocar.setAir_con(autocar.isAir_con());
		old_autocar.setN_seat(autocar.getN_seat());
		old_autocar.setName(autocar.getName());

		Autocar saved = sub_service.add(old_autocar);
		
		return new ResponseEntity<Autocar>(saved,HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteAutocar(@PathVariable("id") Long id){
		autocar_rep.deleteById(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
