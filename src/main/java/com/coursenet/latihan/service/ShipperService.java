package com.coursenet.latihan.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.coursenet.latihan.dto.ShipperRequestDTO;
import com.coursenet.latihan.dto.ShipperResponseDTO;
import com.coursenet.latihan.entity.Shipper;
import com.coursenet.latihan.repository.ShipperRepository;

@Service
public class ShipperService {
	@Autowired
	private ShipperRepository shipperRepository;
	
	
	public ResponseEntity<ShipperResponseDTO> createShipper(ShipperRequestDTO shipperRequestDTO) {
		ShipperResponseDTO shipperResponseDTO = new ShipperResponseDTO();
		
		if(shipperRequestDTO.getName()==null || shipperRequestDTO.getName().isEmpty()) {
			shipperResponseDTO.setErrorMessages("Param name is empty");
			return new ResponseEntity<>(shipperResponseDTO, HttpStatus.BAD_REQUEST);
		}
		
		Shipper shipper = new Shipper();
		shipper.setName(shipperRequestDTO.getName());
		
		shipperRepository.save(shipper);
		
		//Untuk build JSON response ke client
		shipperResponseDTO.setName(shipper.getName());
		shipperResponseDTO.setId(shipper.getId());
		shipperResponseDTO.setCreatedAt(shipper.getCreatedAt());
		shipperResponseDTO.setUpdatedAt(shipper.getUpdatedAt());
		
		return new ResponseEntity<>(shipperResponseDTO, HttpStatus.CREATED);
	}


	public ResponseEntity<ShipperResponseDTO> getShippers(Long id) {
		ShipperResponseDTO shipperResponseDTO = new ShipperResponseDTO();
		if(id==null) {
			shipperResponseDTO.setErrorMessages("Param id is empty");
			return new ResponseEntity<>(shipperResponseDTO, HttpStatus.BAD_REQUEST);
		}
		
		
		Optional<Shipper> shipper = shipperRepository.findById(id);
		if(!shipper.isPresent()) {
			shipperResponseDTO.setErrorMessages("Shipper not found");
			return new ResponseEntity<>(shipperResponseDTO, HttpStatus.NOT_FOUND);
		}
		
		
		shipperResponseDTO.setName(shipper.get().getName());
		shipperResponseDTO.setId(shipper.get().getId());
		shipperResponseDTO.setCreatedAt(shipper.get().getCreatedAt());
		shipperResponseDTO.setUpdatedAt(shipper.get().getUpdatedAt());
		
		return new ResponseEntity<>(shipperResponseDTO, HttpStatus.OK);
	}

}
