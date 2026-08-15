package com.kharidisi.kharidisibackend.service.impl;

import com.kharidisi.kharidisibackend.entity.Cart;
import com.kharidisi.kharidisibackend.repository.CartRepository;
import com.kharidisi.kharidisibackend.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart getCartById(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    @Override
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }
}

