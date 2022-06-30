package com.coursenet.latihan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coursenet.latihan.dto.ShipperResponseDTO;
import com.coursenet.latihan.enums.ShipperStatus;
import com.coursenet.latihan.service.ShipperService;
import com.coursenet.latihan.dto.ShipperRequestDTO;

@RestController
@RequestMapping("/v1")
public class ShipperContoller {
	@Autowired
	private ShipperService shipperService;

	// POST
	@PostMapping("/shippers")
	public ResponseEntity<ShipperResponseDTO> createShipper(
			@Valid @RequestBody ShipperRequestDTO shipperRequestDTO
			) {
		return shipperService.createShipper(shipperRequestDTO);
	}

	// GET
	@GetMapping("/shippers")
	public ResponseEntity<List<ShipperResponseDTO>> getShipper(@RequestParam(value = "id", required = false) Long id) {
		return shipperService.getShippers(id);
	}

	// GET ALL
	@GetMapping("/shippers-active")
	public ResponseEntity<List<ShipperResponseDTO>> getShipperActive() {
		return shipperService.getShippersActive();
	}

	// PUT
	@PutMapping("/shippers/{id}")
	public ResponseEntity<ShipperResponseDTO> putShipper(@PathVariable(value = "id") Long id,
			@Valid @RequestBody ShipperRequestDTO shipperRequestDTO) {
		return shipperService.putShippers(id, shipperRequestDTO);
	}

	// PATCH
	@PatchMapping("/shippers/{id}/{status}")
	public ResponseEntity<ShipperResponseDTO> editShipperStatus(@PathVariable(value = "id") Long id,
			@PathVariable(value = "status") ShipperStatus status) {
		return shipperService.editShipperStatus(id, status);
	}

	// DELETE
	@DeleteMapping("/shippers/{id}")
	public ResponseEntity<ShipperResponseDTO> deleteShipper(@PathVariable(value = "id") Long id) {
		return shipperService.deleteShipper(id);
	}
}
