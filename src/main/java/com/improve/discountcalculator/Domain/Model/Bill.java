package com.improve.discountcalculator.Domain.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.UUID;

public class Bill {
    @JsonProperty("Invoice ID")
    private final UUID billId;
    private final User user;
    @JsonProperty("Discount Rule")
    private final Discounts discounts;
    private final BigDecimal totalAmount;
    private final BigDecimal discountableAmount;
    private final BigDecimal discountAmount;
    private final BigDecimal amountPayable;

    public UUID getBillId() {
        return billId;
    }

    public User getUser() {
        return user;
    }

    public Discounts getDiscounts() {
        return discounts;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public BigDecimal getDiscountableAmount() {
        return discountableAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public BigDecimal getAmountPayable() {
        return amountPayable;
    }

    public Bill(User user, Discounts discounts, BigDecimal totalAmount, BigDecimal discountableAmount, BigDecimal discountAmount, BigDecimal amountPayable) {
        this.billId = UUID.randomUUID();
        this.user = user;
        this.discounts = discounts;
        this.totalAmount = totalAmount;
        this.discountableAmount = discountableAmount;
        this.discountAmount = discountAmount;
        this.amountPayable = amountPayable;
    }
}
