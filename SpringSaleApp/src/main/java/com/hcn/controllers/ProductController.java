/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcn.controllers;

import com.hcn.pojo.Product;
import com.hcn.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author nguyenho
 */
@Controller
public class ProductController {

    @Autowired
    private ProductService prodService;

    @GetMapping("/products")
    public String addView(Model model) {
        model.addAttribute("product", new Product());
        return "products";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute(value = "product") Product p) {
        this.prodService.addOrUpdateProduct(p);
        return "redirect:/";
    }

    @GetMapping("/products/{productId}")
    public String updateView(Model model, @PathVariable(value = "productId") int id) {
        model.addAttribute("product", this.prodService.getProductById(id));
        return "products";
    }
}
