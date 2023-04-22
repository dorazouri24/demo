package com.example.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class CampaignRequest {

    private String name;
    private LocalDate startDate;
    private BigDecimal bid;
    private List<Long> productIds;

    // constructors, getters, and setters omitted for brevity

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    public CampaignRequest(String name, LocalDate startDate, BigDecimal bid, List<Long> productIds) {
        this.name = name;
        this.startDate = startDate;
        this.bid = bid;
        this.productIds = productIds;
    }
}
