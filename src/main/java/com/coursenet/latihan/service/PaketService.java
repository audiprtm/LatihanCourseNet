package com.coursenet.latihan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coursenet.latihan.dto.PaketRequestDTO;
import com.coursenet.latihan.dto.PaketResponseDTO;
import com.coursenet.latihan.entity.Paket;
import com.coursenet.latihan.repository.PaketRepository;

@Service
public class PaketService {
	@Autowired
	private PaketRepository paketRepository;
	
	public PaketResponseDTO createPaket(PaketRequestDTO paketRequestDTO) {
		
		//Buat nge save ke database
		Paket paket = new Paket();
		paket.setNamaBarang(paketRequestDTO.getNamaPaket());
		
		paketRepository.save(paket);
		
		
		//Untuk build JSON response ke client
		PaketResponseDTO paketResponseDTO = new PaketResponseDTO();
		paketResponseDTO.setNamaBarang(paket.getNamaBarang());
		paketResponseDTO.setId(paket.getId());
		
		return paketResponseDTO;
	}
	
}
