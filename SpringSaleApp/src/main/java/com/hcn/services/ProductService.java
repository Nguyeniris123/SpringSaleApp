/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hcn.services;

import com.hcn.pojo.Product;
import java.util.List;
import java.util.Map;

/**
 *
 * @author nguyenho
 */
public interface ProductService {
    List<Product> getProducts(Map<String, String> params);
    Product getProductById(int id);
    Product addOrUpdateProduct(Product p);
    void deleteProduct(int id);
}
