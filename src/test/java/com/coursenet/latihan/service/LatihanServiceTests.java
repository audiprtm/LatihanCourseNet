package com.coursenet.latihan.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.coursenet.latihan.controller.ShipperContoller;
import com.coursenet.latihan.dto.ShipperRequestDTO;
import com.coursenet.latihan.dto.ShipperResponseDTO;
import com.coursenet.latihan.entity.Shipper;
import com.coursenet.latihan.enums.ShipperStatus;
import com.coursenet.latihan.repository.ShipperRepository;
import com.coursenet.latihan.service.ShipperService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
class LatihanServiceTests {
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@InjectMocks
	private ShipperService shipperService;
	
	@Mock 
	ShipperRepository shipperRepository;
	
	@Test
	void testGetShipperServiceSuccess() throws Exception {
		//Arrange
		//Deklarasi Variabel dan Deklarasi Mocking
		Shipper mockShipper = new Shipper();
		mockShipper.setId(1L);
		mockShipper.setName("Gojek");
		mockShipper.setStatus(ShipperStatus.ACTIVE);
		
		
		when(shipperRepository.findById(1L)).thenReturn(Optional.ofNullable(mockShipper));
		
		//Act
		//Menjalankan Component yang akan ditest
		ResponseEntity<List<ShipperResponseDTO>> actualResponse = shipperService.getShippers(1L);
		
		//Assert
		//Membandingkan hasil setelah dijalankan
		assertEquals(actualResponse.getBody().get(0).getName(), mockShipper.getName());
	}
	
	@Test
	void testGetShipperServiceNotFoundShipper() throws Exception {
		//Arrange
		//Deklarasi Variabel dan Deklarasi Mocking
		
		when(shipperRepository.findById(1L)).thenReturn(Optional.ofNullable(null));
		
		//Act
		//Menjalankan Component yang akan ditest
		ResponseEntity<List<ShipperResponseDTO>> actualResponse = shipperService.getShippers(1L);
		
		//Assert
		//Membandingkan hasil setelah dijalankan
		assertEquals(actualResponse.getStatusCode(), HttpStatus.NOT_FOUND);
	}
	
	@Test
	void testGetShipperActiveServiceSuccess() throws Exception {
		//Arrange
		//Deklarasi Variabel dan Deklarasi Mocking
		Shipper mockShipper = new Shipper();
		mockShipper.setId(1L);
		mockShipper.setName("Gojek");
		mockShipper.setStatus(ShipperStatus.ACTIVE);
		
		List<Shipper> mockListShipper = new ArrayList<>();
		mockListShipper.add(mockShipper);
		
		when(shipperRepository.getAllShipperActive()).thenReturn(mockListShipper);
		
		//Act
		//Menjalankan Component yang akan ditest
		ResponseEntity<List<ShipperResponseDTO>> actualResponse = shipperService.getShippersActive();
		
		//Assert
		//Membandingkan hasil setelah dijalankan
		assertEquals(actualResponse.getStatusCode(), HttpStatus.OK);
	}


}
