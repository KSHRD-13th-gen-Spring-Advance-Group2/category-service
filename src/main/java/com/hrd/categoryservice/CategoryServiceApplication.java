package com.hrd.categoryservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", in = SecuritySchemeIn.HEADER)
@EnableDiscoveryClient
@OpenAPIDefinition(
        info = @Info(
                title = "Category Service API",
                version = "1.0.0",
                description = "Endpoints for managing product categories owned by users. Supports create/read/update/delete operations, listing and searching categories, and exposing category metadata used by the product and order flows.",
                contact = @Contact(
                        name = "Contact KSGA Advance Course Group 2",
                        url = "https://github.com/KSHRD-13th-gen-Spring-Advance-Group2/category-service"
                ),
                license = @License(
                        name = "KSGA Student 2.0",
                        url = "https://github.com/KSHRD-13th-gen-Spring-Advance-Group2/category-service"
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
