package com.coursenet.latihan.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coursenet.latihan.dto.UserLoginResponseDTO;
import com.coursenet.latihan.dto.UserRequestDTO;
import com.coursenet.latihan.dto.UserResponseDTO;
import com.coursenet.latihan.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	// POST Registration
	@PostMapping("/users/registration")
	public ResponseEntity<UserResponseDTO> userRegistration(@Valid @RequestBody UserRequestDTO userRequestDTO) {
		return userService.userRegistration(userRequestDTO);
	}
	
	@PostMapping("/users/login")
	public ResponseEntity<UserLoginResponseDTO> userLogin(@Valid @RequestBody UserRequestDTO userRequestDTO) {
		return userService.userLogin(userRequestDTO);
	}
}
