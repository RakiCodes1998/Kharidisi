package com.kharidisi.kharidisibackend.repository;
import com.kharidisi.kharidisibackend.entity.Cart;
import  org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
