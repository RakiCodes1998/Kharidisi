package com.kharidisi.kharidisibackend.service;
import com.kharidisi.kharidisibackend.dto.UserRequest;
import com.kharidisi.kharidisibackend.dto.UserResponse;
import com.kharidisi.kharidisibackend.dto.UserUpdateRequest;
import  com.kharidisi.kharidisibackend.entity.User;
import  com.kharidisi.kharidisibackend.dto.UserUpdateRequest;

import java.util.List;
import com.kharidisi.kharidisibackend.dto.UserRequest;

public interface UserService {
    UserResponse registerUser( UserRequest request);
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long id);
    User updateUser(Long id, UserUpdateRequest request);
void deleteUser(Long id);
}
