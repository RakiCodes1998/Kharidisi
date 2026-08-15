package com.kharidisi.kharidisibackend.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import javax.crypto.Mac;

public class UserUpdateRequest {
    @NotBlank(message = "first name required")
    private String firstName;
    @NotBlank(message = "last name required")
    private String lastName;
    @NotBlank(message = "email is required")
    @Email(message = "provide a vaild email")
    private  String email;
    @Email(message = "phone no is required")
    private String phoneNo;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
