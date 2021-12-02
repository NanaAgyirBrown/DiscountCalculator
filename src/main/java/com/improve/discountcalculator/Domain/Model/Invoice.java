package com.improve.discountcalculator.Domain.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public class Invoice extends Bill {
    @JsonProperty("Cart Items")
    public final List<PrepItems> PrepItems;

    public Invoice(List<PrepItems> items, User user, Discounts discounts,
                   BigDecimal totalAmount,BigDecimal discountableAmount,
                   BigDecimal discountAmount,BigDecimal amountPayable){
        super(user, discounts, totalAmount, discountableAmount, discountAmount, amountPayable);
        this.PrepItems = items;
    }
}
