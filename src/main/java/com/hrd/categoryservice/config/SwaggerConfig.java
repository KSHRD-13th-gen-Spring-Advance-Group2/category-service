package com.hrd.categoryservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Category Service",
                version = "1.0.0",
                description = "REST API",
                contact = @Contact(
                        name = "GitHub",
                        url = "https://github.com/KSHRD-13th-gen-Spring-Advance-Group2/category-service"
                )
        ),
        servers = {
                @Server(url = "http://localhost:8082", description = "Local Server")
        }
)
public class SwaggerConfig {
}
