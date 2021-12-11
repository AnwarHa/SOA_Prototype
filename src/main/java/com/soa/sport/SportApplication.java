package com.soa.sport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@RestController
public class SportApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportApplication.class, args);
	}

	@Bean
	public WebClient soccerAPI() {
		return WebClient.create("http://127.0.0.1:8002");
	}

	@Bean
	public WebClient cyclistAPI() {
		return WebClient.create("http://127.0.0.1:8000");
	}

	@Bean
	public WebClient running_racesAPI() {
		return WebClient.create("http://127.0.0.1:8001");
	}

    @Bean
    public WebClient playerAPI() {
        return WebClient.create("http://127.0.0.1:8003");
    }

}
