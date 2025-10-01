package com.hrd.categoryservice.client;

import com.hrd.categoryservice.model.dto.response.ApiResponse;
import com.hrd.categoryservice.model.entity.User;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "auth-service", path = "/api/v1/users")
public interface UserClient {

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    @CircuitBreaker(name = "category-service", fallbackMethod = "userFallback")
    ResponseEntity<ApiResponse<User>> getUser();

    default ResponseEntity<ApiResponse<User>> userFallback(Throwable throwable) {
        System.out.println("Error: " + throwable.getMessage());
        ApiResponse<User> response = ApiResponse.<User>builder()
                .payload(null)
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
