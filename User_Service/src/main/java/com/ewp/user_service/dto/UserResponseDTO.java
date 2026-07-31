package com.ewp.user_service.dto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserResponseDTO {
    private String userId;
    private String name;
    private String email;
    private String role;
}