package com.improve.discountcalculator.Service;

import com.improve.discountcalculator.Domain.Model.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        var user = _discountService.getCustomerById(cart.User).get();

        if(user == null){
            return invoice;
        }

        // Retrieve User discount type and rule
        var userDiscountRule = _discountService.getDiscountRulesByUserType(user.UserType.Id);
        if(userDiscountRule == null || userDiscountRule.isEmpty()){
            return invoice;
        }

        if(userDiscountRule.size() > 1){
            int memDuration = (user.UserType.UserType.toLowerCase(Locale.ROOT) == "customer") ? GetMemberDuration(user.MembershipDate) : 0;

            if(memDuration >= 24){
                var userRule = userDiscountRule.stream().filter(c -> c.DiscountType.Name.toLowerCase(Locale.ROOT).equals("percentage")).findFirst().orElse(null);
                invoice = GetPercentageDiscountBill(PrepItems(cart.cartItem), userRule, user);
            }else {
                var userRule = userDiscountRule.stream().filter(c -> c.DiscountType.Name.toLowerCase(Locale.ROOT).equals("cash")).findFirst().orElse(null);
                invoice = GetCashDiscountBill(PrepItems(cart.cartItem), userRule, user);
            }

            return invoice;
        }

        var ruleType = userDiscountRule.stream().findFirst().orElse(null);

        if (ruleType == null)
            return invoice;

        switch (ruleType.DiscountType.Name.toLowerCase(Locale.ROOT)){
            case "percentage" :
                invoice = GetPercentageDiscountBill(PrepItems(cart.cartItem), ruleType, user);
                break;
            case "cash" :
                invoice = GetCashDiscountBill(PrepItems(cart.cartItem), ruleType, user);
                break;
        }
        return invoice;

    }

    private Invoice GetPercentageDiscountBill(List<PrepItems> items, Discounts discountRule, User user){
        BigDecimal totalAmount  = new BigDecimal(items.stream().filter(i -> i.TotalCost > 0).mapToDouble(t -> t.TotalCost).sum());
        BigDecimal dicountableAmount = new BigDecimal(items.stream().filter(i -> i.CategoryId == discountRule.RuleAppliesTo.Id).mapToDouble(c -> c.TotalCost).sum());

        BigDecimal discountAmount = dicountableAmount.multiply((discountRule.DiscountValue).divide(BigDecimal.valueOf(100)));
        BigDecimal amountPayable = totalAmount.subtract(discountAmount);

        return new Invoice
                (items, user, discountRule, totalAmount,
                        dicountableAmount, discountAmount, amountPayable);
    }

    private Invoice GetCashDiscountBill(List<PrepItems> items, Discounts discountRule, User user){
        double totalAmount = items.stream().filter(i -> i.TotalCost > 0).mapToDouble(t -> t.TotalCost).sum();
        BigDecimal dicountableAmount = new BigDecimal(totalAmount - (totalAmount % 100));

        BigDecimal discountAmount = (dicountableAmount.divide( new BigDecimal(100))).multiply(discountRule.DiscountValue);
        BigDecimal amountPayable = new BigDecimal(totalAmount).subtract(discountAmount);

        return new Invoice
                (items, user, discountRule, new BigDecimal(totalAmount),
                        dicountableAmount, discountAmount, amountPayable);
    }

    private int GetMemberDuration(Date membershipDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(membershipDate);

        int yearDuration = LocalDate.now().getYear() - calendar.get(Calendar.YEAR);

        int monthDuration = LocalDate.now().getMonthValue() - calendar.get(Calendar.MONTH);

        return (yearDuration * 12) + monthDuration;
    }

    private List<PrepItems> PrepItems(CartItem[] cartItem){
        List<PrepItems> preppedItems = new ArrayList<>();

        if (cartItem == null || cartItem.length == 0)
            return preppedItems;

        for (CartItem item : cartItem) {
            if(item.Item == 0)
                return null;

            var newItem = _discountService.getAllShopItems().stream().filter(i -> i.Id == item.Item).findAny().orElse(null);

            preppedItems.add(
                    new PrepItems(item.Quantity, (item.Quantity * newItem.UnitPrice), newItem.Id, newItem.ItemName,
                    newItem.CategoryId, newItem.UnitPrice));
        }

        return preppedItems;
    }
}
