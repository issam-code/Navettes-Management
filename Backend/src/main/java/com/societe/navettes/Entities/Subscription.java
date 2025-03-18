package com.societe.navettes.Entities;

import java.util.List;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Subscription {
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String date_depart;
	private String date_arriv;
	private String time_depart;
	private String time_arriv;
	private String city_depart;
	private String city_arriv;
	private int n_wanted;
	private int n_reached;
	private String type;
	private float price;
	
	// les association ------
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Autocar autocar;

	@OneToMany(fetch = FetchType.LAZY)
	private List<Booking> booking;
	
	public List<Booking> getBooking() {
		return booking;
	}
	public void setBooking(List<Booking> booking) {
		this.booking = booking;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDate_depart() {
		return date_depart;
	}

	public void setDate_depart(String date_depart) {
		this.date_depart = date_depart;
	}

	public String getDate_arriv() {
		return date_arriv;
	}

	public void setDate_arriv(String date_arriv) {
		this.date_arriv = date_arriv;
	}

	public String getTime_depart() {
		return time_depart;
	}

	public void setTime_depart(String time_depart) {
		this.time_depart = time_depart;
	}

	public String getTime_arriv() {
		return time_arriv;
	}

	public void setTime_arriv(String time_arriv) {
		this.time_arriv = time_arriv;
	}

	public int getN_wanted() {
		return n_wanted;
	}

	public void setN_wanted(int n_wanted) {
		this.n_wanted = n_wanted;
	}

	public int getN_reached() {
		return n_reached;
	}

	public void setN_reached(int n_reached) {
		this.n_reached = n_reached;
	}

	public String getType() {
		return type;
	}
	

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCity_depart() {
		return city_depart;
	}

	public void setCity_depart(String city_depart) {
		this.city_depart = city_depart;
	}

	public String getCity_arriv() {
		return city_arriv;
	}

	public void setCity_arriv(String city_arriv) {
		this.city_arriv = city_arriv;
	}

	public Autocar getAutocar() {
		return autocar;
	}

	public void setAutocar(Autocar autocar) {
		this.autocar = autocar;
	}

	@Override
	public String toString() {
		return "Subscription [id=" + id + ", date_depart=" + date_depart + ", date_arriv=" + date_arriv
				+ ", time_depart=" + time_depart + ", time_arriv=" + time_arriv + ", city_depart=" + city_depart
				+ ", city_arriv=" + city_arriv + ", n_wanted=" + n_wanted + ", n_reached=" + n_reached + ", type="
				+ type + ", user=" + user + ", autocar=" + autocar + "]";
	}

	
	
	
}
