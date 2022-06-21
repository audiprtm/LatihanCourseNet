package com.coursenet.latihan.dto;

import javax.validation.constraints.NotNull;

import com.coursenet.latihan.enums.ShipperStatus;

public class ShipperRequestDTO {
	private long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private ShipperStatus status;
	
	
	public ShipperStatus getStatus() {
		return status;
	}
	public void setStatus(ShipperStatus status) {
		this.status = status;
	}
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
