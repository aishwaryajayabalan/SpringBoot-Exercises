package com.example.product.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.example.product.exception.DuplicateProductException;
import com.example.product.exception.ProductNotFoundException;
import com.example.product.model.Product;

@Service
public class ProductService {
	
	private List<Product> products = new ArrayList<>();
	
	public Product addProduct(Product product) {

	    for (Product existingProduct : products) {

	        if (existingProduct.getProductId() == product.getProductId()) {
	            throw new DuplicateProductException(
	                "Product already exists with id: " + product.getProductId()
	            );
	        }
	    }

	    products.add(product);

	    return product;
	}
	
	public Product updateProduct(int productId, Product product) {

	    for (Product existingProduct : products) {

	        if (existingProduct.getProductId() == productId) {

	            existingProduct.setProductName(product.getProductName());
	            existingProduct.setCategory(product.getCategory());
	            existingProduct.setStock(product.getStock());
	            existingProduct.setPrice(product.getPrice());

	            return existingProduct;
	        }
	    }

	    throw new ProductNotFoundException(
	        "Product not found with id: " + productId
	    );
	}
	
	public List<Product> getAllProducts() {
	    return products;
	}
	
	public Product getProductById(int productId) {

	    for (Product product : products) {

	        if (product.getProductId() == productId) {
	            return product;
	        }
	    }

	    throw new ProductNotFoundException(
	            "Product not found with id: " + productId);
	}

}
