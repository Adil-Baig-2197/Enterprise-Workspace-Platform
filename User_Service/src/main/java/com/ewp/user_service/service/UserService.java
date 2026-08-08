package com.ewp.user_service.service;

import com.ewp.user_service.dto.UserRequestDTO;
import com.ewp.user_service.dto.UserResponseDTO;
import com.ewp.user_service.exception.EmailAlreadyExistsException;
import com.ewp.user_service.exception.InvalidRoleException;
import com.ewp.user_service.exception.UserNotFoundException;
import com.ewp.user_service.mapper.UserMapper;
import com.ewp.user_service.model.Role;
import com.ewp.user_service.model.Users;
import com.ewp.user_service.repository.RoleRepository;
import com.ewp.user_service.repository.UsersRepository;
import org.springframework.boot.data.autoconfigure.web.DataWebProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private UsersRepository usersRepository;
    private RoleRepository roleRepository;
    public UserService(UsersRepository usersRepository,RoleRepository roleRepository){
        this.usersRepository = usersRepository;
        this.roleRepository = roleRepository;
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO){
//        if(userRequestDTO.getRole().equals("Admin")){
//            //later to add auth
//        }

        if(usersRepository.existsByEmail(userRequestDTO.getEmail())){
            throw new EmailAlreadyExistsException("A user with this Email already exists " + userRequestDTO.getEmail());
        }

        Users newUser = UserMapper.toUser(userRequestDTO);
        Role role = roleRepository.findByName(userRequestDTO.getRole())
                .orElseThrow(() -> new InvalidRoleException("Invalid Role entered"));

        newUser.setRole(role);
        usersRepository.save(newUser);
        return UserMapper.toDTO(newUser);
    }

    public List<UserResponseDTO> getUsers(Pageable pageable,String search){
        List<Users> userList = null;
        if(search==null){
            userList = usersRepository.findAll(pageable).getContent();
        }else {
            userList = usersRepository.findByName(search,pageable).getContent();
        }
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
    }

}