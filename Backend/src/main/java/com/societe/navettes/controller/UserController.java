package com.societe.navettes.controller;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.societe.navettes.Entities.User;
import com.societe.navettes.dao.UserRepository;
import com.societe.navettes.dto.UserDto;
import com.societe.navettes.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService user_service;
	@Autowired
	UserRepository user_repository;
	
	@GetMapping("/")
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> users = user_service.getUsers();
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<UserDto> getUser(@PathVariable("username") String username){
		User user= user_repository.findByUsername(username);
		UserDto myuser =new UserDto();
		myuser.setId(user.getId());
		myuser.setUsername(user.getUsername());
		myuser.setRole(user.getRole());
		return new ResponseEntity<UserDto>(myuser,HttpStatus.OK);
	}
	
	 @PostMapping("login")
	    public String login(@RequestBody User user) {

	        return user_service.verify(user);
	  }
	
	@PostMapping("register")
	public ResponseEntity<String> addUser(@RequestBody User user){
		//if(user_repository.findByUsername(user.getUsername()) != null) throw new RuntimeException("Username Already Exist!");
		if(user_repository.findByUsername(user.getUsername()) != null) return new ResponseEntity<String>("Username Already Exist!",HttpStatus.CONFLICT); 
		if(user.getPassword() == null || user.getUsername() == null || user.getRole() == null) return new ResponseEntity<String>("Enter Data!",HttpStatus.ACCEPTED); 
		User saved = user_service.add(user);
		if(saved != null) {
			return new ResponseEntity<String>("User Added Successefully",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Problem with Adding User",HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user){
		User old_user= user_repository.findById(id).get();
		old_user.setUsername(user.getUsername());
		old_user.setPassword(user.getPassword());
		//old_user.setType(user.getType());
		User saved = user_service.add(old_user);
		return new ResponseEntity<User>(saved,HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id){
		user_repository.deleteById(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
