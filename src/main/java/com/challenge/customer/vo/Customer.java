package com.challenge.customer.vo;

import com.fasterxml.jackson.annotation.JsonGetter;

public class Customer {

	public Customer() {}
	
	private Long userId;
	private String name;
	private Double latitude;
	private Double longitude;
	
	@JsonGetter(value = "user_id")
	public Long getUserId() {
		return userId;
	}
	
	public String getName() {
		return name;
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
}
