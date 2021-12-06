package com.improve.discountcalculator.Service;

import com.improve.discountcalculator.Domain.Model.*;
import com.improve.discountcalculator.fakedata.TestCartItems;
import com.improve.discountcalculator.fakedata.TestDiscount;
import com.improve.discountcalculator.fakedata.TestUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Locale;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BillCalculatorTest {
    @Mock
    private DiscountService _discountService;

    private BillCalculator underTest;

    private TestCartItems _fakecartItems;
    private TestUser _fakeUser;
    private TestDiscount _fakeDiscount;
    Invoice invoice;

    @BeforeEach
    void setUp(){
        _fakecartItems = new TestCartItems();
        _fakeUser = new TestUser();
        _fakeDiscount = new TestDiscount();

        invoice = null;
        underTest = new BillCalculator(_discountService);
    }

    @Test
    void getBill() {
        int userID = 1;

        var testCart = _fakecartItems.getUserCart(userID);
        var testUser = _fakeUser.getTestUser(testCart.getUser());
        var testDiscount = _fakeDiscount.getTestDiscount(testUser.getUserType().getId());
        int memDuration = 0;

        when(_discountService.getCustomerById(testCart.getUser()))
                .thenReturn(java.util.Optional.of(testUser));

       when(_discountService.getDiscountRulesByUserType(testUser.getUserType().getId()))
                .thenReturn(testDiscount);

        when(underTest.GetMemberDuration(testUser.getMembershipDate()))
                .thenReturn(24);

        var prepItems = (List<PrepItems>) when(underTest.PrepItems(testCart.getCartItem()))
                .thenReturn(null);

        if(memDuration >= 24){
            var userRule = (Discounts) when(testDiscount.stream().filter(c -> c.getDiscountType().getName().toLowerCase(Locale.ROOT).equals("percentage")).findFirst().orElse(null));
            invoice = (Invoice) when(underTest.GetPercentageDiscountBill(prepItems, userRule, testUser));
        }else{
            var userRule = (Discounts) when(testDiscount.stream().filter(c -> c.getDiscountType().getName().toLowerCase(Locale.ROOT).equals("percentage")).findFirst().orElse(null));
            invoice = (Invoice) when(underTest.GetCashDiscountBill(prepItems, userRule, testUser));
        }

        var userRule = (Discounts) when(testDiscount.stream().findFirst().orElse(null));

        switch (userRule.getDiscountType().getName().toLowerCase(Locale.ROOT)){
            case "percentage" :
                invoice = (Invoice) when(underTest.GetPercentageDiscountBill(prepItems, userRule, testUser));
                break;
            case "cash" :
                invoice = (Invoice) when(underTest.GetCashDiscountBill(prepItems, userRule, testUser));
                break;
        }

        Mockito.verify(_discountService.getCustomerById(testCart.getUser()));
        Mockito.verify(_discountService.getDiscountRulesByUserType(testUser.getUserType().getId()));
        Mockito.verify(underTest.GetMemberDuration(testUser.getMembershipDate()));
        Mockito.verify(underTest.GetMemberDuration(testUser.getMembershipDate()));
        Mockito.verify(_discountService.getCustomerById(testCart.getUser()));
    }
}