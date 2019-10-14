package com.codegym.service;

import com.codegym.model.Products;

public interface ProductsService {

    void addProduct(Products products);

    void deleteProduct(Long id);

    Products findById(Long id);

    Iterable<Products> findAll();
}
