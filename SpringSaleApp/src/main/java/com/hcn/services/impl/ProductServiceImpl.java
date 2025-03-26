/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcn.services.impl;

import com.hcn.pojo.Product;
import com.hcn.repositories.ProductRepository;
import com.hcn.services.ProductService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nguyenho
 */

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository proRepo;
    
    @Override
    public List<Product> getProducts(Map<String, String> params) {
        return this.proRepo.getProducts(params);
    }

    @Override
    public Product getProductById(int id) {
        return this.proRepo.getProductById(id);
    }

    @Override
    public Product addOrUpdateProduct(Product p) {
        return this.proRepo.addOrUpdateProduct(p);
    }

    @Override
    public void deleteProduct(int id) {
        this.proRepo.deleteProduct(id);
    }
    
}
