package com.improve.discountcalculator.Domain.Model;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public class Discounts {
    private final UUID id;
    @NotNull
    private final UserTypes userType;
    @NotNull
    private final DiscountsType discountType;
    @NotNull
    private final BigDecimal discountValue;
    @NotNull
    private final RulesAppliesTo ruleAppliesTo;

    public UUID getId() {
        return id;
    }

    public UserTypes getUserType() {
        return userType;
    }

    public DiscountsType getDiscountType() {
        return discountType;
    }

    public BigDecimal getDiscountValue() {
        return discountValue;
    }

    public RulesAppliesTo getRuleAppliesTo() {
        return ruleAppliesTo;
    }

    public Discounts(UUID id, UserTypes userType, DiscountsType discountType, BigDecimal discountValue, RulesAppliesTo ruleAppliesTo) {
        this.id = id;
        this.userType = userType;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.ruleAppliesTo = ruleAppliesTo;
    }
}
