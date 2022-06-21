package com.coursenet.latihan.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.coursenet.latihan.dto.ShipperRequestDTO;
import com.coursenet.latihan.enums.ShipperStatus;

@Entity
public class Shipper {
	public Shipper(ShipperRequestDTO requestDTO) {
		super();
		this.name = requestDTO.getName();
		this.status = requestDTO.getStatus();
	}
	
	public Shipper() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@CreationTimestamp
	private LocalDateTime createdAt;

	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	private String name;
	
	@Enumerated(EnumType.STRING)
	private ShipperStatus status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public ShipperStatus getStatus() {
		return status;
	}

	public void setStatus(ShipperStatus status) {
		this.status = status;
	}
}
