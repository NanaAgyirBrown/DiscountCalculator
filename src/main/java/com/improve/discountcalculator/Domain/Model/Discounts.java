package com.improve.discountcalculator.Domain.Model;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public class Discounts {
    public final UUID Id;
    @NotNull
    public final UserTypes UserType;
    @NotNull
    public final DiscountsType DiscountType;
    @NotNull
    public final BigDecimal DiscountValue;
    @NotNull
    public final RulesAppliesTo RuleAppliesTo;


    public Discounts(UUID id, UserTypes userType, DiscountsType discountType, BigDecimal discountValue, RulesAppliesTo ruleAppliesTo) {
        Id = id;
        UserType = userType;
        DiscountType = discountType;
        DiscountValue = discountValue;
        RuleAppliesTo = ruleAppliesTo;
    }
}
