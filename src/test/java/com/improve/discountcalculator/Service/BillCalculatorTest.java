package com.improve.discountcalculator.Service;

import com.improve.discountcalculator.Domain.Model.*;
import com.improve.discountcalculator.fakedata.TestCartItems;
import com.improve.discountcalculator.fakedata.TestDiscount;
import com.improve.discountcalculator.fakedata.TestPrepItems;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BillCalculatorTest {
    @Mock
    private DiscountService _discountService;
    private BillCalculator underTest;

    private TestCartItems _fakecartItems;
    private TestUser _fakeUser;
    private TestDiscount _fakeDiscount;
    private TestPrepItems _fakePrepItems;
    Invoice invoice;

    @BeforeEach
    void setUp(){
        underTest = new BillCalculator(_discountService);
        _fakecartItems = new TestCartItems();
        _fakeUser = new TestUser();
        _fakeDiscount = new TestDiscount();
        _fakePrepItems = new TestPrepItems();

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

        //when(underTest.GetMemberDuration(testUser.getMembershipDate())).thenReturn(37);

        when(underTest.PrepItems(testCart.getCartItem())).thenReturn(_fakePrepItems.getItems());

        if(memDuration >= 24){
            var userRule = (Discounts) when(testDiscount.stream().filter(c -> c.getDiscountType().getName().toLowerCase(Locale.ROOT).equals("percentage")).findFirst().orElse(null));
            invoice = (Invoice) when(underTest.GetPercentageDiscountBill(_fakePrepItems.getItems(), userRule, testUser));
        }else{
            var userRule = (Discounts) when(testDiscount.stream().filter(c -> c.getDiscountType().getName().toLowerCase(Locale.ROOT).equals("cash")).findFirst().orElse(null));
            invoice = (Invoice) when(underTest.GetCashDiscountBill(_fakePrepItems.getItems(), userRule, testUser));
        }

        var userRule = (Discounts) when(testDiscount.stream().findFirst().orElse(null));

        switch (userRule.getDiscountType().getName().toLowerCase(Locale.ROOT)){
            case "percentage" :
                invoice = (Invoice) when(underTest.GetPercentageDiscountBill(_fakePrepItems.getItems(), userRule, testUser));
                break;
            case "cash" :
                invoice = (Invoice) when(underTest.GetCashDiscountBill(_fakePrepItems.getItems(), userRule, testUser));
                break;
        }

        Mockito.verify(_discountService.getCustomerById(testCart.getUser()));
        Mockito.verify(_discountService.getDiscountRulesByUserType(testUser.getUserType().getId()));
        Mockito.verify(underTest.GetMemberDuration(testUser.getMembershipDate()));
        Mockito.verify(underTest.GetMemberDuration(testUser.getMembershipDate()));
        Mockito.verify(_discountService.getCustomerById(testCart.getUser()));
    }
}