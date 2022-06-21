package com.coursenet.latihan.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.coursenet.latihan.controller.ShipperContoller;
import com.coursenet.latihan.dto.ShipperRequestDTO;
import com.coursenet.latihan.dto.ShipperResponseDTO;
import com.coursenet.latihan.enums.ShipperStatus;
import com.coursenet.latihan.service.ShipperService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ShipperContoller.class)
class LatihanServiceTests {
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@MockBean
	private ShipperService shipperService;
	
	@Test
	void testPostShipperController() throws Exception {
		//Arrange
		//Deklarasi Variabel dan Deklarasi Mocking
		ShipperRequestDTO shipperRequestDTO = new ShipperRequestDTO();
		shipperRequestDTO.setName("Gojek");
		shipperRequestDTO.setStatus(ShipperStatus.ACTIVE);
		
		
		//Act
		//Menjalankan Component yang akan ditest
		mockMvc.perform(
				post("/v1/shippers")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(shipperRequestDTO)))
		//Assert
		//Membandingkan hasil setelah dijalankan
		.andDo(print())
		.andExpect(status().is2xxSuccessful())
		.andReturn();
	}

}
