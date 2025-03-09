/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.hcn.hibernatedemov1;

import com.hcn.repositories.impl.CategoryRepositoryImpl;
import com.hcn.repositories.impl.ProductRepositoryImpl;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author nguyenho
 */
public class HibernateDemoV1 {

    public static void main(String[] args) {
        CategoryRepositoryImpl c = new CategoryRepositoryImpl();
        c.getCates().forEach(ca -> System.out.println(ca.getName()));
        
        
        ProductRepositoryImpl pro = new ProductRepositoryImpl();
        
        Map<String, String> params = new HashMap<>();
//        params.put("kw", "galaxy");
//        params.put("fromPrice", "18600000.0");
//        params.put("categoryId", "2");
        params.put("page", "2");
        pro.getProducts(params).forEach(p -> System.out.printf("%d - %s - %.1f\n", p.getId(), p.getName(), p.getPrice()));
    }
}
