package com.coursenet.latihan.dto;

import org.springframework.lang.NonNull;

public class ShipperRequestDTO {
	private long id;
	
	@NonNull
	private String name;
	
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
}
