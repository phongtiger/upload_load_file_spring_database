package com.codegym.service;

import com.codegym.model.Products;

public interface ProductsService {

    void addImage(Products products);

    void deleteImage(Long id);

    Products findById(Long id);

    Iterable<Products> findAll();
}
