package com.improve.discountcalculator.Domain.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public class Invoice extends Bill {
    @JsonProperty("Cart Items")
    private final List<PrepItems> prepItems;

    public List<PrepItems> getPrepItems() {
        return prepItems;
    }

    public Invoice(List<PrepItems> items, User user, Discounts discounts,
                   BigDecimal totalAmount,BigDecimal discountableAmount,
                   BigDecimal discountAmount,BigDecimal amountPayable){
        super(user, discounts, totalAmount, discountableAmount, discountAmount, amountPayable);
        this.prepItems = items;
    }
}
