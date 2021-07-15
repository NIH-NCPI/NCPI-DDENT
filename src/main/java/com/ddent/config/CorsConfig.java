package com.ddent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Method to Enable end points to be reaches in case of CORS issues
 * 
 * @author ikeoguan
 *
 */


@Configuration
public class CorsConfig {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry){
				registry.addMapping("/**")
				.allowedMethods("POST")
				.allowedHeaders("*")
				.allowedOrigins("http://localhost:4200");
				};
			};
		
	}
}