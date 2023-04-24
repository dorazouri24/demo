package com.example.service;

import com.example.model.Campaign;
import com.example.model.Product;
import com.example.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private ProductService productService;

    public Campaign createCampaign(String name, LocalDate startDate, List<String> productIds, BigDecimal bid) {
        // Validate input parameters
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Campaign name cannot be empty");
        }
        if (startDate == null || startDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Start date cannot be empty or in the past");
        }
        if (productIds == null || productIds.isEmpty()) {
            throw new IllegalArgumentException("Product ids cannot be empty");
        }
        if (bid == null || bid.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Bid amount must be greater than zero");
        }

        // Retrieve products by ids
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Product product = productService.getProductById(productId);
            if (product == null) {
                throw new IllegalArgumentException("Invalid product id: " + productId);
            }
            products.add(product);
        }

        // Create campaign and save to repository
        Campaign campaign = new Campaign(name, startDate, bid, products);
        campaign = campaignRepository.save(campaign);
        return campaign;
    }
}

