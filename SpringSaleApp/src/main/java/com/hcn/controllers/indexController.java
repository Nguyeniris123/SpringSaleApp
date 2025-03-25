    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hcn.controllers;


import com.hcn.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author admin
 */

@Controller
public class indexController {
    @Autowired
    private CategoryService CategoryService;
     
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("categories", this.CategoryService.getCates());
        return "index";
    }
}
