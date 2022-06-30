package com.coursenet.latihan.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.coursenet.latihan.dto.UserLoginResponseDTO;
import com.coursenet.latihan.dto.UserRequestDTO;
import com.coursenet.latihan.dto.UserResponseDTO;
import com.coursenet.latihan.entity.Shipper;
import com.coursenet.latihan.entity.Users;
import com.coursenet.latihan.enums.TokenType;
import com.coursenet.latihan.repository.UserRepository;
import com.coursenet.latihan.security.JWTUtil;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Value("${salt.hash.password}")
	private String salt;
	
	@Value("${static.token}")
	private String token;
	
	@Autowired
	private JWTUtil jwtUtil;
	
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
			//If found return jwt token	
			UserLoginResponseDTO loginResponseDTO= new UserLoginResponseDTO();
			String access_token = jwtUtil.generateJWTToken(user.get().getUserName(), TokenType.ACCESS);
			String refresh_token = jwtUtil.generateJWTToken(user.get().getUserName(), TokenType.REFRESH);
			
			loginResponseDTO.setAccess_token(access_token);
			loginResponseDTO.setRefresh_token(refresh_token);
			
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

	public ResponseEntity<UserLoginResponseDTO> refreshToken(String token) {
		UserLoginResponseDTO loginResponseDTO= new UserLoginResponseDTO();
		
		try {
			DecodedJWT decodedJWT = jwtUtil.decodeJWTToken(token);
			
			if (decodedJWT.getClaim("type").asString().equalsIgnoreCase(TokenType.REFRESH.toString())) {
				String access_token = jwtUtil.generateJWTToken(decodedJWT.getSubject(), TokenType.ACCESS);
				String refresh_token = token.replace("Bearer ", "");
				
				loginResponseDTO.setAccess_token(access_token);
				loginResponseDTO.setRefresh_token(refresh_token);
				
				return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
			}
		}catch (Exception e) {
			
		}
		
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}
}
