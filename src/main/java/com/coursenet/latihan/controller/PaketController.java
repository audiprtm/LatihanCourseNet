package com.coursenet.latihan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coursenet.latihan.dto.HelloRequestDTO;
import com.coursenet.latihan.dto.PaketRequestDTO;
import com.coursenet.latihan.dto.PaketResponseDTO;
import com.coursenet.latihan.service.PaketService;

@RestController
public class PaketController {
	
	@Autowired
	private PaketService paketService;
	
	@PostMapping("/hello")
	public String sayHello(@RequestBody HelloRequestDTO helloRequest) {
		return "Halo "+ helloRequest.getNama();
	}
	
	@PostMapping("/paket")
	public PaketResponseDTO createPaket(@RequestBody PaketRequestDTO paketRequestDTO) {
		return paketService.createPaket(paketRequestDTO);
	}
}
