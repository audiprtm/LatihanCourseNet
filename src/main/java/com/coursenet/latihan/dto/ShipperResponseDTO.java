package com.coursenet.latihan.dto;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.coursenet.latihan.entity.Shipper;
import com.coursenet.latihan.enums.ShipperStatus;

public class ShipperResponseDTO extends ResponseDTO{
	public ShipperResponseDTO(Shipper shipper) {
		this.id = shipper.getId();
		this.name = shipper.getName();
		this.status = shipper.getStatus();
		this.createdAt = shipper.getCreatedAt();
		this.updatedAt = shipper.getUpdatedAt();
	}
	
	public ShipperResponseDTO() {

	}
	
	private long id;
	

	private String name;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
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
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
