package com.improve.discountcalculator.fakedata;

import com.improve.discountcalculator.Domain.Model.Discounts;
import com.improve.discountcalculator.Domain.Model.DiscountsType;
import com.improve.discountcalculator.Domain.Model.RulesAppliesTo;
import com.improve.discountcalculator.Domain.Model.UserTypes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestDiscount {
    private List<Discounts> testDiscounts() {
        List<Discounts> discountsList = new ArrayList<>();

        discountsList.add(new Discounts(UUID.randomUUID(), new UserTypes(3, "Customer", null),
                new DiscountsType(1, "Cash"), new BigDecimal(10.00), new RulesAppliesTo(1, "Grocery")));
        discountsList.add(new Discounts(UUID.randomUUID(), new UserTypes(3, "Customer", null),
                new DiscountsType(1, "Percentage"), new BigDecimal(15.00), new RulesAppliesTo(1, "Grocery")));
        discountsList.add(new Discounts(UUID.randomUUID(), new UserTypes(1, "Employee", null),
                new DiscountsType(1, "Percentage"), new BigDecimal(20.00), new RulesAppliesTo(2, "Electronics")));
        discountsList.add(new Discounts(UUID.randomUUID(), new UserTypes(2, "Affiliate", null),
                new DiscountsType(1, "Percentage"), new BigDecimal(25.00), new RulesAppliesTo(2, "Electronics")));


        return discountsList;
    }

    public List<Discounts> getTestDiscount(int userTypeId){
        return testDiscounts().stream().filter(d -> d.getUserType().getId() == userTypeId).toList();
    }
}