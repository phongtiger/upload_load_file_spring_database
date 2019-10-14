package com.codegym.service.impl;

import com.codegym.model.Products;
import com.codegym.repository.ProductsRepository;
import com.codegym.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public void addProduct(Products products) {
        productsRepository.save(products);
    }

    @Override
    public void deleteProduct(Long id) {
        productsRepository.delete(id);
    }

    @Override
    public Products findById(Long id) {
        return productsRepository.findOne(id);
    }

    @Override
    public Iterable<Products> findAll() {
        return productsRepository.findAll();
    }
}
