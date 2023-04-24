package com.example.controller;

import com.example.model.Campaign;
import com.example.model.Product;
import com.example.repository.CampaignRepository;
import com.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/ads")
    public Product serveAd(@RequestParam("category") String category) {
        List<Campaign> activeCampaigns = campaignRepository.findByStartDateAfterAndStartDateBefore(LocalDate.now(), LocalDate.now().plusDays(10));
        List<Product> promotedProducts = new ArrayList<>();

        for (Campaign campaign : activeCampaigns) {
            for (Product product : campaign.getProducts()) {
                if (product.getCategory().equals(category)) {
                    promotedProducts.add(product);
                }
            }
        }

        if (promotedProducts.isEmpty()) {
            List<Product> products = productRepository.findByCategory(category);
            return Collections.max(products, Comparator.comparing(Product::getPrice));
        } else {
            return Collections.max(promotedProducts, Comparator.comparing(Product::getPrice));
        }
    }
}