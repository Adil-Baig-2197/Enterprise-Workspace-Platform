package com.ewp.user_service.controller;

import com.ewp.user_service.dto.UserRequestDTO;
import com.ewp.user_service.dto.UserResponseDTO;
import com.ewp.user_service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/User")
@Tag(name = "Users",description = "User Management APIs")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService)   {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "Get Users", description = "Returns Users in pages that provides all pagination functionality")
    public ResponseEntity<List<UserResponseDTO>> getUsers(
            @RequestParam(required = false,defaultValue = "1") int pageNo,
            @RequestParam(required = false,defaultValue = "2") int pageSize,
            @RequestParam(required = false,defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDir,
            @RequestParam(required = false) String search){
        Sort sort;
        if(sortDir.equalsIgnoreCase("ASC")){
            sort = Sort.by(sortBy).ascending();
        }else{
            sort = Sort.by(sortBy).descending();
        }
        List<UserResponseDTO> lis = userService.getUsers(PageRequest.of(pageNo-1,pageSize, sort),search);
        return ResponseEntity.ok().body(lis);
    }

    @PostMapping
    @Operation(summary = "Creates a new user")
    public ResponseEntity<UserResponseDTO> createUser(@Validated({Default.class}) @RequestBody UserRequestDTO userRequestDTO){
        UserResponseDTO userResponseDTO = userService.createUser(userRequestDTO);
        return ResponseEntity.ok().body(userResponseDTO);
    }

    @PutMapping("/{userId}")
    @Operation(summary = "Updates a user")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable UUID userId
            ,@Validated({Default.class})@RequestBody UserRequestDTO userRequestDTO){
        UserResponseDTO userResponseDTO = userService.updateUser(userId,userRequestDTO);
        return ResponseEntity.ok().body(userResponseDTO);
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Deletes a user")
    public ResponseEntity<UserResponseDTO> deleteUser(@PathVariable UUID userId){
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}