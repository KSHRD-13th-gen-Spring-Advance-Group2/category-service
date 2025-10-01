package com.hrd.categoryservice.client;

import com.hrd.categoryservice.model.dto.response.ApiResponse;
import com.hrd.categoryservice.model.entity.User;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-service", path = "/api/v1/users")
public interface UserClient {

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    ResponseEntity<ApiResponse<User>> getUser(@RequestHeader("Authorization") String header);
}
