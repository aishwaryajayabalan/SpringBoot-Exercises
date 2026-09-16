package com.example.product.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.product.model.Product;
import com.example.product.service.ProductService;

@RestController
public class ProductController {
	

    @Autowired
    private ProductService productService;
    
    @PostMapping("/products")
    public Product addProduct(@Valid @RequestBody Product product) {

        return productService.addProduct(product);
    }
    
    @PutMapping("/products/{productId}")
    public Product updateProduct(@PathVariable int productId,
                                 @Valid @RequestBody Product product) {

        return productService.updateProduct(productId, product);
    }
    
    @GetMapping("/products")
    public List<Product> getAllProducts() {

        return productService.getAllProducts();
    }
    
    @GetMapping("/products/{productId}")
    public Product getProductById(@PathVariable int productId) {

        return productService.getProductById(productId);
    }


}
