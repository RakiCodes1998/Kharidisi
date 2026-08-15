package com.kharidisi.kharidisibackend.repository;

import com.kharidisi.kharidisibackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
