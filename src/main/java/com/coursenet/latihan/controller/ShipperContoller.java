package com.coursenet.latihan.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coursenet.latihan.dto.ShipperResponseDTO;
import com.coursenet.latihan.service.ShipperService;
import com.coursenet.latihan.dto.ShipperRequestDTO;

@RestController
@RequestMapping("/v1")
public class ShipperContoller {
	@Autowired
	private ShipperService shipperService;
	
	//POST
	@PostMapping("/shippers")
	public ResponseEntity<ShipperResponseDTO> createShipper(@RequestBody ShipperRequestDTO shipperRequestDTO) {
		return shipperService.createShipper(shipperRequestDTO);
	}
		
	//GET
	@GetMapping("/shippers")
	public ResponseEntity<ShipperResponseDTO> getShipper(@RequestParam (value = "id") Long id) {
		return shipperService.getShippers(id);
	}
	
	//PUT
	
	//PATCH
	
	//DELETE
}
