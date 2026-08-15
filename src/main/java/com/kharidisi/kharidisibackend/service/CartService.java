package com.kharidisi.kharidisibackend.service;
import com.kharidisi.kharidisibackend.entity.Cart;

import java.util.List;

public interface CartService {

Cart createCart(Cart cart);
List<Cart> getAllCarts();
Cart getCartById(Long id);
void  deleteCart(Long id);
}

