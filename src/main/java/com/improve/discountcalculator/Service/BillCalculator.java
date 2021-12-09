package com.improve.discountcalculator.Service;

import com.improve.discountcalculator.Domain.Model.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class BillCalculator {
    private final DiscountService _discountService;

    public BillCalculator(DiscountService discountService) {
        _discountService = discountService;
    }

    public Invoice GetBill(Cart cart){
        Invoice invoice = null;
        // search for user
        var user = _discountService.getCustomerById(cart.getUser()).orElse(null);

        if(user == null){
            return invoice;
        }

        // Retrieve User discount type and rule
        var userDiscountRule = _discountService.getDiscountRulesByUserType(user.getUserType().getId());
        if(userDiscountRule == null || userDiscountRule.isEmpty()){
            return invoice;
        }

        if(userDiscountRule.size() > 1){
            int memDuration = (user.getUserType().getUserType().toLowerCase(Locale.ROOT) == "customer") ? GetMemberDuration(user.getMembershipDate()) : 0;

            if(memDuration >= 24){
                var userRule = userDiscountRule.stream().filter(c -> c.getDiscountType().getName().toLowerCase(Locale.ROOT).equals("percentage")).findFirst().orElse(null);
                invoice = GetPercentageDiscountBill(PrepItems(cart.getCartItem()), userRule, user);
            }else {
                var userRule = userDiscountRule.stream().filter(c -> c.getDiscountType().getName().toLowerCase(Locale.ROOT).equals("cash")).findFirst().orElse(null);
                invoice = GetCashDiscountBill(PrepItems(cart.getCartItem()), userRule, user);
            }

            return invoice;
        }

        var ruleType = userDiscountRule.stream().findFirst().orElse(null);

        if (ruleType == null)
            return invoice;

        switch (ruleType.getDiscountType().getName().toLowerCase(Locale.ROOT)){
            case "percentage" :
                invoice = GetPercentageDiscountBill(PrepItems(cart.getCartItem()), ruleType, user);
                break;
            case "cash" :
                invoice = GetCashDiscountBill(PrepItems(cart.getCartItem()), ruleType, user);
                break;
        }
        return invoice;

    }

    private Invoice GetPercentageDiscountBill(List<PrepItems> items, Discounts discountRule, User user){
        BigDecimal totalAmount  = new BigDecimal(items.stream().filter(i -> i.getTotalCost() > 0).mapToDouble(t -> t.getTotalCost()).sum()).setScale(2, RoundingMode.CEILING);
        BigDecimal dicountableAmount = new BigDecimal(items.stream().filter(i -> i.getCategoryId() == discountRule.getRuleAppliesTo().getId()).mapToDouble(c -> c.getTotalCost()).sum()).setScale(2, RoundingMode.CEILING);

        BigDecimal discountAmount = dicountableAmount.multiply((discountRule.getDiscountValue()).divide(BigDecimal.valueOf(100))).setScale(2, RoundingMode.CEILING);
        BigDecimal amountPayable = totalAmount.subtract(discountAmount);

        return new Invoice
                (items, user, discountRule, totalAmount,
                        dicountableAmount, discountAmount, amountPayable);
    }

    private Invoice GetCashDiscountBill(List<PrepItems> items, Discounts discountRule, User user){
        BigDecimal totalAmount = new BigDecimal(items.stream().filter(i -> i.getTotalCost() > 0).mapToDouble(t -> t.getTotalCost()).sum()).setScale(2, RoundingMode.CEILING);
        BigDecimal dicountableAmount = new BigDecimal(totalAmount.doubleValue() - (totalAmount.doubleValue() % 100)).setScale(2, RoundingMode.CEILING);

        BigDecimal discountAmount = (dicountableAmount.divide( new BigDecimal(100))).multiply(discountRule.getDiscountValue());
        BigDecimal amountPayable = totalAmount.subtract(discountAmount).setScale(2, RoundingMode.CEILING);

        return new Invoice
                (items, user, discountRule, totalAmount.setScale(2, RoundingMode.CEILING),
                        dicountableAmount, discountAmount, amountPayable);
    }

    private int GetMemberDuration(Date membershipDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(membershipDate);

        int yearDuration = LocalDate.now().getYear() - calendar.get(Calendar.YEAR);

        int monthDuration = LocalDate.now().getMonthValue() - calendar.get(Calendar.MONTH);

        return (yearDuration * 12) + monthDuration;
    }

    private List<PrepItems> PrepItems(List<CartItem> cartItem){
        List<PrepItems> preppedItems = new ArrayList<>();

        if (cartItem == null || cartItem.size() == 0)
            return preppedItems;

        for (CartItem item : cartItem) {
            if (item.getItem() == 0)
                return null;

            var newItem = _discountService.getAllShopItems().stream().filter(i -> i.getId() == item.getItem()).findAny().orElse(null);

            if (newItem != null) {
                preppedItems.add(
                        new PrepItems(item.getQuantity(), (item.getQuantity() * newItem.getUnitPrice()), newItem.getId(), newItem.getItemName(),
                                newItem.getCategoryId(), newItem.getUnitPrice()));
            }
        }

        return preppedItems;
    }
}
