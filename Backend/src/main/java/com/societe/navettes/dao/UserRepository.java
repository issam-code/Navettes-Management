package com.societe.navettes.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.societe.navettes.Entities.User;


public interface UserRepository extends JpaRepository<User, Long> {
	User save(User user);
	User findById(long id);
	User findByUsername(String username);
}
