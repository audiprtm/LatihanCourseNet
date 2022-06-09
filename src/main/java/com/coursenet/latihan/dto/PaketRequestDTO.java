package com.coursenet.latihan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class PaketRequestDTO {
	private String namaPaket;

	public String getNamaPaket() {
		return namaPaket;
	}

	public void setNamaPaket(String namaPaket) {
		this.namaPaket = namaPaket;
	}
	
}
