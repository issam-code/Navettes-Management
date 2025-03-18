package com.societe.navettes.Entities;

import java.util.List;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Autocar {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private boolean air_con;
	private int n_seat;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Subscription> subscriptions;
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isAir_con() {
		return air_con;
	}
	public void setAir_con(boolean air_con) {
		this.air_con = air_con;
	}
	public int getN_seat() {
		return n_seat;
	}
	public void setN_seat(int n_seat) {
		this.n_seat = n_seat;
	}
	
	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}
	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}
	
	
	
	@Override
	public String toString() {
		return "Autocar [id=" + id + ", air_con=" + air_con + ", n_seat=" + n_seat + "]";
	}

	
}
