package com.javaweb.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModolMapperconfig {
@Bean
public ModelMapper modolMapper () {
	return new ModelMapper();
}
}
