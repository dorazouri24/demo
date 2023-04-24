package com.example.model;

import jakarta.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Id is required")
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    @NotNull(message = "Start date is required")
    private LocalDate startDate;
    @NotNull(message = "Bid is required")
    private BigDecimal bid;

    @NotNull(message = "Product list is required")
    @Valid
    @ManyToMany
    private List<Product> products;

    // constructors, getters, and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Campaign() {}

    public Campaign(String name, LocalDate startDate, BigDecimal bid, List<Product> products) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.bid = bid;
        this.products = products;
    }

}