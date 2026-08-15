package com.kharidisi.kharidisibackend.dto;
import jakarta.validation.constraints.Email;
import  jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import  jakarta.validation.constraints.Size;

public class UserRequest {
    @NotBlank(message = "First name is mandatory buddy")
    private  String firstName;
    @NotBlank(message = "hope you are not pushpa, enter last name also")
    private  String lastName;
    @NotBlank(message = "email id is basic buddy")
    @Email(message = "you also know its not a proper email")
    private  String email;
    @NotBlank(message = "200% mandatory")
    @Size(min = 6, message = "6 letters so no one can steal")
    private  String password;
    @NotBlank(message = "i am not a chapri , you can enter your No")
    @Pattern(regexp = "^[0-9]{10}$") 
    private  String phoneNo;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
