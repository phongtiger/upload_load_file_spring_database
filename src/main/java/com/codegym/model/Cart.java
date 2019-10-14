package com.codegym.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Products> carts = new ArrayList<>();
    public Cart() {
    }

    public void addProductIntoCart(Products products) {
        carts.add(products);
    }

    public void deleteProductIntoCart(int id) {
        carts.remove(id);
    }

    public Products findByIdInCart(int id) {
        return carts.get(id);
    }

    public List<Products> findAllInCart() {
        return carts;
    }
}
