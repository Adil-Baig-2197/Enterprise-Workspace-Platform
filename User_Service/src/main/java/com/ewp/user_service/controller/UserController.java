package com.ewp.user_service.controller;

import com.ewp.user_service.dto.UserRequestDTO;
import com.ewp.user_service.dto.UserResponseDTO;
import com.ewp.user_service.service.UserService;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("User")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getUsers(){
        List<UserResponseDTO> lis = userService.getUsers();
        return ResponseEntity.ok().body(lis);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Validated({Default.class}) @RequestBody UserRequestDTO userRequestDTO){
        UserResponseDTO userResponseDTO = userService.createUser(userRequestDTO);
        return ResponseEntity.ok().body(userResponseDTO);
    }

}