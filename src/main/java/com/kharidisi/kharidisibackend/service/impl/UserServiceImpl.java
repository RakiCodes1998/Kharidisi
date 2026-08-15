package com.kharidisi.kharidisibackend.service.impl;
import com.kharidisi.kharidisibackend.dto.UserUpdateRequest;

import com.kharidisi.kharidisibackend.dto.UserRequest;
import com.kharidisi.kharidisibackend.dto.UserResponse;
import com.kharidisi.kharidisibackend.dto.UserUpdateRequest;
import com.kharidisi.kharidisibackend.entity.Role;
import com.kharidisi.kharidisibackend.entity.User;
import com.kharidisi.kharidisibackend.repository.UserRepository;
import com.kharidisi.kharidisibackend.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.kharidisi.kharidisibackend.entity.Role;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 1. REGISTER USER
    @Override
    public UserResponse registerUser(UserRequest request) {

        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());

        // Encode password before saving
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setPhoneNo(request.getPhoneNo());
        user.setRole(Role.USER);
        user.setStatus("ACTIVE");

        User savedUser = userRepository.save(user);

        // Convert User -> UserResponse
        UserResponse response = new UserResponse();

        response.setId(savedUser.getId());
        response.setFirstName(savedUser.getFirstName());
        response.setLastName(savedUser.getLastName());
        response.setEmail(savedUser.getEmail());
        response.setPhoneNo(savedUser.getPhoneNo());
        response.setRole(savedUser.getRole().name());
        response.setStatus(savedUser.getStatus());

        return response;
    }


    // 2. GET ALL USERS
    @Override
    public List<UserResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(user -> {

                    UserResponse response = new UserResponse();

                    response.setId(user.getId());
                    response.setFirstName(user.getFirstName());
                    response.setLastName(user.getLastName());
                    response.setEmail(user.getEmail());
                    response.setPhoneNo(user.getPhoneNo());
                    response.setRole(user.getRole().name());
                    response.setStatus(user.getStatus());

                    return response;

                })
                .collect(Collectors.toList());
    }


    // 3. GET USER BY ID
    @Override
    public UserResponse getUserById(Long id) {
User user = userRepository.findById(id).orElseThrow(()->
        new RuntimeException("User not found with id: " + id));
UserResponse response = new UserResponse();
response.setId(user.getId());
response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhoneNo(user.getPhoneNo());
        response.setRole(user.getRole().name());
        response.setStatus(user.getStatus());
return response;
    }


    // 4. UPDATE USER
    @Override
    public User updateUser(Long id, UserUpdateRequest request) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found with id: " + id)
                );

        existingUser.setFirstName(request.getFirstName());
        existingUser.setLastName(request.getLastName());
        existingUser.setEmail(request.getEmail());
        existingUser.setPhoneNo(request.getPhoneNo());

        return userRepository.save(existingUser);
    }


    // 5. DELETE USER
    @Override
    public void deleteUser(Long id) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found with id: " + id)
                );

        userRepository.delete(existingUser);
    }
}
