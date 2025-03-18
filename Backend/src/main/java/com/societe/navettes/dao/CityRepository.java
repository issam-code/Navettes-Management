package com.societe.navettes.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.societe.navettes.Entities.City;

public interface CityRepository extends JpaRepository<City,Long> {

}
