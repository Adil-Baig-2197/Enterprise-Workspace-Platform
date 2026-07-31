package com.ewp.user_service.service;

import com.ewp.user_service.dto.UserRequestDTO;
import com.ewp.user_service.dto.UserResponseDTO;
import com.ewp.user_service.exception.EmailAlreadyExistsException;
import com.ewp.user_service.exception.InvalidRoleException;
import com.ewp.user_service.exception.UserNotFoundException;
import com.ewp.user_service.mapper.UserMapper;
import com.ewp.user_service.model.Users;
import com.ewp.user_service.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO){
        if(userRequestDTO.getRole().equals("Organization Administrator")){
            //later to add auth
        }else if(!userRequestDTO.getRole().equals("Developer") && !userRequestDTO.getRole().equals("Guest")){
            throw new InvalidRoleException("Invalid role entered " + userRequestDTO.getRole());
        }

        if(usersRepository.existsByEmail(userRequestDTO.getEmail())){
            throw new EmailAlreadyExistsException("A user with this Email already exists " + userRequestDTO.getEmail());
        }

        Users newUser = UserMapper.toUser(userRequestDTO);
        usersRepository.save(newUser);
        return UserMapper.toDTO(newUser);
    }

    public List<UserResponseDTO> getUsers(){
        List<Users> userList = usersRepository.findAll();

        return userList.stream()
                .map(UserMapper::toDTO).toList();
    }

    public UserResponseDTO updateUser(UUID userId, UserRequestDTO userRequestDTO){

        Users user = usersRepository.findById(userId).orElseThrow(()->new UserNotFoundException(
                "User Not Found with id "+userId));

        if(usersRepository.existsByEmailAndUserIdNot(userRequestDTO.getEmail(),userId)){
            throw new EmailAlreadyExistsException("A User with this Email already exists"
                    + userRequestDTO.getEmail());
        }
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());

        Users updatedUser = usersRepository.save(user);
        return UserMapper.toDTO(user);
    }

    public void deleteUser(UUID userId){
        usersRepository.deleteById(userId);
        return;
    }

}