package com.societe.navettes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.societe.navettes.Entities.User;
import com.societe.navettes.dao.UserRepository;

@Component
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository user_repository;
	
	@Autowired
    AuthenticationManager authManager;
	
	@Autowired
    private jwtService jwtService;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = user_repository.findByUsername(username);
		if (user == null) {
			System.out.println("user not found!"); 
			throw new UsernameNotFoundException("username not found");
		}
		return user;
	}

	
	public List<User> getUsers(){
		return user_repository.findAll();
	}
	public User add(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		User saved_user= user_repository.save(user);
		return saved_user;
	}

	 public String verify(User user) {
	        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
	        if (authentication.isAuthenticated()) {
	            return jwtService.generateToken(user.getUsername());
	        	//return "success";
	        } else {
	            return "fail";
	        }
	    }
}
