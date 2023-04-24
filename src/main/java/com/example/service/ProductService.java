package com.example.service;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    private List<Product> products;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public ProductService() {
        this.products = new ArrayList<>();
    }

    public Product createProduct(Long id, String title, String category, BigDecimal price, String serialNumber) {
        Product product = new Product(title, category, price, serialNumber);
        products.add(product);
        return product;
    }

    public Product getProductById(String id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }
    public Product getProductBySerialNumber(String productSerialNumber) {
        for (Product product : products) {
            if (product.getSerialNumber().equals(productSerialNumber)) {
                return product;
            }
        }
        return null;
    }

    public List<Product> getProductsByCategory(String category) {
        List<Product> matchingProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory().equals(category)) {
                matchingProducts.add(product);
            }
        }
        return matchingProducts;
    }
}
