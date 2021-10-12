package com.smartcar.api;

import com.smartcar.api.client.GmApi;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Bean
	public GmApi gmApi(@Value("${com.smartcar.gm.client.baseurl}") String baseUrl) {
		return Feign.builder()
				.encoder(new JacksonEncoder())
				.decoder(new JacksonDecoder())
				.target(GmApi.class, baseUrl);
	}

}
