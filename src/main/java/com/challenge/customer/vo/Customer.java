package com.challenge.customer.vo;

import com.fasterxml.jackson.annotation.JsonGetter;

import lombok.Getter;

@Getter
public class Customer {

	private Long userId;
	private String name;
	private Double latitude;
	private Double longitude;
	
	@JsonGetter(value = "user_id")
	public Long getUserId() {
		return userId;
	}
	
}
