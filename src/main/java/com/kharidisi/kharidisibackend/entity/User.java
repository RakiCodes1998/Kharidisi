package com.kharidisi.kharidisibackend.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import  com.fasterxml.jackson.annotation.JsonIgnore;
import  jakarta.persistence.EnumType;
import  jakarta.persistence.Enumerated;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

    @Column(name ="first_name", nullable = false)
    private String firstName;
    @Column(name ="last_name", nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @JsonIgnore
    @Column(nullable = false)
    private String password;
    @Column(name ="phone_no")
    private String phoneNo;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(nullable = false)
    private String status;
    @Column(name ="created_at")
    private LocalDateTime createdAt;
    @Column(name ="updated_at")
    private LocalDateTime updatedAt;
    


@PrePersist
public void onCreate() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
}
@PreUpdate
public void onUpdate() {
    this.updatedAt = LocalDateTime.now();
}
}
