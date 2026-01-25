package com.sample.couponcodecollector.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.couponcodecollector.DTO.request.CreateUserRequest;
import com.sample.couponcodecollector.DTO.request.LoginRequest;
import com.sample.couponcodecollector.DTO.response.LoginResponse;
import com.sample.couponcodecollector.DTO.response.UserResponse;
import com.sample.couponcodecollector.Service.AuthService;

@RestController
@RequestMapping("/api/users")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody CreateUserRequest userRequestDTO) {
        UserResponse userResponse = authService.registerUser(userRequestDTO);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequestDTO) {
        LoginResponse loginResponse = authService.loginUser(loginRequestDTO);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }
}
