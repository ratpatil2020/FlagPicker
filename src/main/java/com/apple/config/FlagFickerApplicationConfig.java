package com.apple.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class FlagFickerApplicationConfig {

	@Bean
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}	
}
