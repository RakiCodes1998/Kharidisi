package com.kharidisi.kharidisibackend.repository;
import com.kharidisi.kharidisibackend.entity.Order;
import  org.springframework.data.jpa.repository.JpaRepository;
public interface OrderRepository extends JpaRepository<Order, Long>{
}
