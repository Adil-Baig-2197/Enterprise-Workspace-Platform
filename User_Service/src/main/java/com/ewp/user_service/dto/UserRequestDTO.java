package com.ewp.user_service.dto;
import com.ewp.user_service.model.RoleName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequestDTO {
    @NotBlank(message = "Name is required")
    @Size(max = 100,message = "Name cannot exceed 100 characters")
    @Schema(description = "User's full name")
    private String name;
    @NotBlank(message = "Email is Required")
    @Email(message = "Email should be valid")
    @Schema(description = "User's email", example = "oscar81@gmail.com")
    private String email;
    @NotNull(message = "Role is required")
    private RoleName role;
}