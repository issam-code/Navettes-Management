package com.societe.navettes.dto;

public class BookingDTO {
	private String name;
	private String telephone;
	private int  nb_places;
	private Long id_subcription;
	private Long id_user;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public int getNb_places() {
		return nb_places;
	}
	public void setNb_places(int nb_places) {
		this.nb_places = nb_places;
	}
	public Long getId_subcription() {
		return id_subcription;
	}
	public void setId_subcription(Long id_subcription) {
		this.id_subcription = id_subcription;
	}
	public Long getId_user() {
		return id_user;
	}
	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}
	
	
}
