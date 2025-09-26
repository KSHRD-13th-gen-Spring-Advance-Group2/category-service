package com.hrd.categoryservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@OpenAPIDefinition(
        info = @Info(
                title = "Category Service API",
                version = "1.0.0",
                description = "Endpoints for managing product categories owned by users. Supports create/read/update/delete operations, listing and searching categories, and exposing category metadata used by the product and order flows.",
                contact = @Contact(
                        name = "Contact KSGA",
                        url = "https://www.kshrd.com.kh/",
                        email = "info.kshrd@gmail.com"
                ),
                license = @License(
                        name = "KSGA 2.0",
                        url = "https://www.kshrd.com.kh/"
                )
        ),
        servers = {
                @Server(url = "/")
        }
)
@SpringBootApplication
public class CategoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CategoryServiceApplication.class, args);
    }

}
