package com.sample.couponcodecollector.Service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sample.couponcodecollector.Configuration.JwtUtil;
import com.sample.couponcodecollector.DTO.request.CreateUserRequest;
import com.sample.couponcodecollector.DTO.request.LoginRequest;
import com.sample.couponcodecollector.DTO.response.LoginResponse;
import com.sample.couponcodecollector.DTO.response.UserResponse;
import com.sample.couponcodecollector.Entity.User;
import com.sample.couponcodecollector.Mapper.UserMapper;
import com.sample.couponcodecollector.Repository.UserRepository;

@Service
public class AuthService {
    private JwtUtil jwtUtil;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public AuthService(JwtUtil jwtUtil, UserMapper userMapper, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse registerUser(CreateUserRequest userRequest){
        User user = userMapper.toEntity(userRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User registerdUser = userRepository.save(user);
        return userMapper.toDTO(registerdUser);
    }

    public LoginResponse  loginUser(LoginRequest loginRequest){
        User user = userRepository
        .findByEmail(loginRequest.getEmail())
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if(passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            String token = jwtUtil.generateToken(user.getEmail());
            LoginResponse loginResponse = userMapper.toLoginResponse(token);
            return loginResponse;
        } else{
            throw new BadCredentialsException("Invalid Password");
        }
    }
}
