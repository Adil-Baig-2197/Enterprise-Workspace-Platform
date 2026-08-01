package com.ewp.user_service.mapper;

import com.ewp.user_service.dto.UserRequestDTO;
import com.ewp.user_service.dto.UserResponseDTO;
import com.ewp.user_service.model.Role;
import com.ewp.user_service.model.Users;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserMapper {
    public static UserResponseDTO toDTO(Users user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUserId(user.getUserId().toString());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setRole(user.getRole().toString());

        return userResponseDTO;
    }

    static ObjectMapper objMapper = new ObjectMapper();

    public static Users toUser(UserRequestDTO userRequestDTO){
        Users user = new Users();
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        return user;
    }
}