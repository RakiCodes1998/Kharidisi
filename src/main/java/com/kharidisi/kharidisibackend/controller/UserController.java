package com.kharidisi.kharidisibackend.controller;
import com.kharidisi.kharidisibackend.dto.*;
import com.kharidisi.kharidisibackend.service.UserService;
import  com.kharidisi.kharidisibackend.entity.User;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import  java.util.List;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private  final UserService userService;
    public  UserController(UserService userService){
        this.userService = userService;

    }
    @PostMapping("/register")
    public UserResponse registerUser(@Valid @RequestBody UserRequest request){
        return  userService.registerUser(request );
    }
    @GetMapping
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public  UserResponse getUserById(@PathVariable("id" +
            "") Long id){
        return userService.getUserById(id);
    }
    @PutMapping("/{id}")
    public  User updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest request){
        return  userService.updateUser(id,request);

    }
    @DeleteMapping("/{id}")
        public String deleteUser(@PathVariable Long id){
            userService.deleteUser(id);
            return "User deleted successfully";
        }


}
