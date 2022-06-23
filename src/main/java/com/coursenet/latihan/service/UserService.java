package com.coursenet.latihan.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.coursenet.latihan.dto.UserLoginResponseDTO;
import com.coursenet.latihan.dto.UserRequestDTO;
import com.coursenet.latihan.dto.UserResponseDTO;
import com.coursenet.latihan.entity.Shipper;
import com.coursenet.latihan.entity.Users;
import com.coursenet.latihan.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Value("${salt.hash.password}")
	private String salt;
	
	@Value("${static.token}")
	private String token;
	
	public ResponseEntity<UserResponseDTO> userRegistration(UserRequestDTO userRequestDTO) {
		//Save ke db
		Users user = new Users();
		user.setUserName(userRequestDTO.getUserName());
		
		//Hash Password
		user.setPassword(hashPassword(userRequestDTO.getPassword(), salt));
		
		userRepository.save(user);
		
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		userResponseDTO.setUserName(user.getUserName());
		
		return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);
	}

	public ResponseEntity<UserLoginResponseDTO> userLogin(@Valid UserRequestDTO userRequestDTO) {
		//Find by Username and Hash
		Optional<Users> user = userRepository.findByUserNameAndPassword(
				userRequestDTO.getUserName(),
				hashPassword(userRequestDTO.getPassword(), salt)
				);
		if(!user.isPresent()) {
			//Not found return HTTP 401
			return new ResponseEntity<>(new UserLoginResponseDTO(), HttpStatus.UNAUTHORIZED);
		}else {
			//If found return static token	
			UserLoginResponseDTO loginResponseDTO= new UserLoginResponseDTO();
			loginResponseDTO.setToken(token);
			
			return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
		}
	}
	
	private String hashPassword(String passwordToHash, String salt) {
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(salt.getBytes());
			byte[] bytes = md.digest(passwordToHash.getBytes());
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}
}
