package com.sample.couponcodecollector.Mapper;

import org.springframework.stereotype.Component;

import com.sample.couponcodecollector.DTO.request.CreateUserRequest;
import com.sample.couponcodecollector.DTO.response.LoginResponse;
import com.sample.couponcodecollector.DTO.response.UserResponse;
import com.sample.couponcodecollector.Entity.User;

@Component
public class UserMapper {
    public User toEntity(CreateUserRequest dto){
        if(dto == null){
            return null;
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    public UserResponse toDTO(User user){
        if(user == null){
            return null;
        }
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        return response;
    }

    public LoginResponse toLoginResponse(String token){
        LoginResponse loginResponse = new LoginResponse(token);
        return loginResponse;
    }
}
