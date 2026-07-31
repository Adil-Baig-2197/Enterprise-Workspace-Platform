package com.ewp.user_service.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequestDTO {
    @NotBlank(message = "Name is required")
    @Size(max = 100,message = "Name cannot exceed 100 characters")
    private String name;
    @NotBlank(message = "Email is Required")
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank(message = "Role is required")
    private String role;
}