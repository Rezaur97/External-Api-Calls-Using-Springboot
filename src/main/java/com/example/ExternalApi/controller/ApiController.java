package com.example.ExternalApi.controller;


import com.example.ExternalApi.io.ProductClientResponse;
import com.example.ExternalApi.io.ProductResponse;
import com.example.ExternalApi.services.ApiServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ApiController {

    @Autowired
    private ApiServices apiServices;


    @GetMapping("/")
    public String getAllProducts(Model model){
        List<ProductResponse> allProducts = apiServices.getAllProducts();
        System.out.println("All Products 1st method -- "+allProducts);
        model.addAttribute("products1",allProducts);
        return "index";
    }

    @GetMapping("/products")
    public String getAllProductsByFeign(Model model){
        List<ProductClientResponse> allProducts = apiServices.getAllProductByFeign();
        System.out.println("All Products 2nd method -- "+allProducts);
        model.addAttribute("products2",allProducts);
        return "products/products";
    }
}
