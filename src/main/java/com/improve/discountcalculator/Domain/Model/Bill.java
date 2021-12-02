package com.improve.discountcalculator.Domain.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.UUID;

public class Bill {
    @JsonProperty("Invoice ID")
    public final UUID BillId;
    public final User User;
    @JsonProperty("Discount Rule")
    public final Discounts Discounts;
    public final BigDecimal TotalAmount;
    public final BigDecimal DiscountableAmount;
    public final BigDecimal DiscountAmount;
    public final BigDecimal AmountPayable;


    public Bill(User user, Discounts discounts, BigDecimal totalAmount, BigDecimal discountableAmount, BigDecimal discountAmount, BigDecimal amountPayable) {
        BillId = UUID.randomUUID();
        User = user;
        Discounts = discounts;
        TotalAmount = totalAmount;
        DiscountableAmount = discountableAmount;
        DiscountAmount = discountAmount;
        AmountPayable = amountPayable;
    }
}
