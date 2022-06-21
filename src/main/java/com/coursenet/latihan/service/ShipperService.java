package com.coursenet.latihan.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.coursenet.latihan.dto.ShipperRequestDTO;
import com.coursenet.latihan.dto.ShipperResponseDTO;
import com.coursenet.latihan.entity.Shipper;
import com.coursenet.latihan.enums.ShipperStatus;
import com.coursenet.latihan.repository.ShipperRepository;

@Service
public class ShipperService {
	@Autowired
	private ShipperRepository shipperRepository;
	
	
	public ResponseEntity<ShipperResponseDTO> createShipper(ShipperRequestDTO shipperRequestDTO) {
		ShipperResponseDTO shipperResponseDTO;
		
		if(shipperRequestDTO.getName()==null || shipperRequestDTO.getName().isEmpty()) {
			shipperResponseDTO = new ShipperResponseDTO();
			shipperResponseDTO.setErrorMessages("Param name is empty");
			return new ResponseEntity<>(shipperResponseDTO, HttpStatus.BAD_REQUEST);
		}
		
		Shipper shipper = new Shipper();
		shipper.setName(shipperRequestDTO.getName());
		
		shipperRepository.save(shipper);
		
		//Untuk build JSON response ke client
		shipperResponseDTO = new ShipperResponseDTO(shipper);
		return new ResponseEntity<>(shipperResponseDTO, HttpStatus.CREATED);
	}


	public ResponseEntity<List<ShipperResponseDTO>> getShippers(Long id) {
		ShipperResponseDTO shipperResponseDTO = new ShipperResponseDTO();
		List<ShipperResponseDTO> responseDTOs = new ArrayList<>();
		
		//Get All
		if(id==null) {
			List<Shipper>listShipper = shipperRepository.findAll();
			ShipperResponseDTO responseDTO;
			
			for(int i=0;i<listShipper.size();i++) {
				responseDTO = new ShipperResponseDTO(listShipper.get(i));
				responseDTOs.add(responseDTO);
			}
			
			return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
		
		}else {
			
			//Get by Id
			Optional<Shipper> shipper = shipperRepository.findById(id);
			if(!shipper.isPresent()) {
				shipperResponseDTO.setErrorMessages("Shipper not found");
				return new ResponseEntity<>(responseDTOs, HttpStatus.NOT_FOUND);
			}
			
			
			shipperResponseDTO = new ShipperResponseDTO(shipper.get());
			responseDTOs.add(shipperResponseDTO);
			return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
		}
	}


	public ResponseEntity<ShipperResponseDTO> putShippers(Long id, ShipperRequestDTO shipperRequestDTO) {
		//1. Misalkan data tidak ada, create
		Optional<Shipper> shipper = shipperRepository.findById(id);
		if(!shipper.isPresent()) {
			Shipper newShipper = new Shipper(shipperRequestDTO);
			shipperRepository.save(newShipper);
			
			//Untuk build JSON response ke client
			ShipperResponseDTO shipperResponseDTO = new ShipperResponseDTO(newShipper);
			return new ResponseEntity<>(shipperResponseDTO, HttpStatus.CREATED);
		}else {
			//2. Tapi jika datanya ada, dia mengedit
			
			Shipper newShipper = new Shipper(shipperRequestDTO);
			newShipper.setId(id);
			shipperRepository.save(newShipper);
			
			//Untuk build JSON response ke client
			ShipperResponseDTO shipperResponseDTO = new ShipperResponseDTO(newShipper);
			return new ResponseEntity<>(shipperResponseDTO, HttpStatus.OK);
		}
	}


	public ResponseEntity<ShipperResponseDTO> editShipperStatus(Long id, ShipperStatus status) {
		//Get by Id
		Optional<Shipper> shipper = shipperRepository.findById(id);
		ShipperResponseDTO shipperResponseDTO;
		if(!shipper.isPresent()) {
			shipperResponseDTO= new ShipperResponseDTO();
			shipperResponseDTO.setErrorMessages("Shipper not found");
			return new ResponseEntity<>(shipperResponseDTO, HttpStatus.NOT_FOUND);
		}
		
		Shipper newShipper = shipper.get();
		newShipper.setStatus(status);
		shipperRepository.save(newShipper);
		
		shipperResponseDTO = new ShipperResponseDTO(newShipper);
		return new ResponseEntity<>(shipperResponseDTO, HttpStatus.OK);		
	}


	public ResponseEntity<ShipperResponseDTO> deleteShipper(Long id) {
		Optional<Shipper> shipper = shipperRepository.findById(id);
		ShipperResponseDTO shipperResponseDTO= new ShipperResponseDTO();;
		if(!shipper.isPresent()) {
			shipperResponseDTO= new ShipperResponseDTO();
			shipperResponseDTO.setErrorMessages("Shipper not found");
			return new ResponseEntity<>(shipperResponseDTO, HttpStatus.NOT_FOUND);
		}
		
		shipperRepository.deleteById(id);
		return new ResponseEntity<>(shipperResponseDTO, HttpStatus.NO_CONTENT);
	}

}
