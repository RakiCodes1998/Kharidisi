package com.kharidisi.kharidisibackend.service;
import com.kharidisi.kharidisibackend.dto.ProductRequest;
import com.kharidisi.kharidisibackend.entity.Product;
import java.util.List;
import com.kharidisi.kharidisibackend.dto.ProductRequest;
public interface ProductService {
    Product createProduct(ProductRequest request);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product updateProduct(Long id,Product product);
    void  deleteProduct(Long id);
}
