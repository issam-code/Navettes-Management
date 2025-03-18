package com.societe.navettes.dto;

import com.societe.navettes.Entities.Subscription;


public class SubscriptionDto extends Subscription{

	private long id_autocar;
	private long id_user;
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
	
	public long getId_autocar() {
		return id_autocar;
	}
	public void setId_autocar(long id_autocar) {
		this.id_autocar = id_autocar;
	}
	public long getId_user() {
		return id_user;
	}
	public void setId_user(long id_user) {
		this.id_user = id_user;
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
	public void setType(String type) {
		this.type = type;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	
	
	
	
}
