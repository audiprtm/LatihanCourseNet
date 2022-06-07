package com.coursenet.latihan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirstConfig {
	
	@Bean
	public AlgorithmConfig apaAja() {
		return new AlgorithmConfig();
	}
}
