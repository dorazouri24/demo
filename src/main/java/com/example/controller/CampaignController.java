package com.example.controller;

import com.example.dto.CampaignRequest;
import com.example.model.Campaign;
import com.example.model.Product;
import com.example.repository.CampaignRepository;
import com.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CampaignController {
    private final CampaignRepository campaignRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CampaignController(CampaignRepository campaignRepository, ProductRepository productRepository) {
        this.campaignRepository = campaignRepository;
        this.productRepository = productRepository;
    }

    @PostMapping("/campaigns")
    public Campaign createCampaign(@RequestBody CampaignRequest request) {
        List<Product> products = productRepository.findAllById(request.getProductIds());
        Campaign campaign = new Campaign(request.getName(), request.getStartDate(), request.getBid(), products);
        return campaignRepository.save(campaign);
    }

    @GetMapping("/ads")
    public Product serveAd(@RequestParam String category) {
        LocalDate now = LocalDate.now();
        List<Campaign> activeCampaigns = campaignRepository.findByStartDateAfterAndStartDateBefore(now.minusDays(1), now.plusDays(10));
        if (activeCampaigns.isEmpty()) {
            return productRepository.findFirstByCategoryOrderByPriceDesc(category);
        } else {
            List<Product> promotedProducts = activeCampaigns.stream().flatMap(campaign -> campaign.getProducts().stream()).collect(Collectors.toList());
            List<Product> categoryProducts = promotedProducts.stream().filter(product -> product.getCategory().equals(category)).collect(Collectors.toList());
            if (categoryProducts.isEmpty()) {
                return productRepository.findFirstByCategoryOrderByPriceDesc(category);
            } else {
                return Collections.max(categoryProducts, Comparator.comparing(Product::getPrice));
            }
        }
    }
}
