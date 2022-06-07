package com.coursenet.latihan.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Paket {
	//id, namaBarang, createAt,updatedAt
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String namaBarang;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	//fitur 2
	@UpdateTimestamp
	private LocalDateTime updatedAt;
}
