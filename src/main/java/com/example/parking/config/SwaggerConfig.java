package com.example.parking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI parkingLotOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("🚗 Parking Lot Reservation API")
                        .description("API documentation for Parking Lot Reservation System")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Sruthi T")
                                .email("sruthithayyil2002@gmail.com")
                                .url("https://github.com/sruthi") // your GitHub/LinkedIn
                        )
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")
                        )
                );
    }
}
