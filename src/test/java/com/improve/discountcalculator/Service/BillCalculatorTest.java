package com.improve.discountcalculator.Service;

import com.improve.discountcalculator.Domain.Model.*;
import com.improve.discountcalculator.fakedata.TestCartItems;
import com.improve.discountcalculator.fakedata.TestDiscount;
import com.improve.discountcalculator.fakedata.TestPrepItems;
import com.improve.discountcalculator.fakedata.TestUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BillCalculatorTest {
    @Mock
    private DiscountService _discountService;
    private BillCalculator underTest;

    @Mock
    private TestCartItems _fakecartItems;
    @Mock
    private TestUser _fakeUser;
    @Mock
    private TestDiscount _fakeDiscount;
    @Mock
    private TestPrepItems _fakePrepItems;
    @Mock
    private int userID;
    @Mock
    private Invoice invoice;

    @BeforeEach
    void setUp(){
        when(_discountService.getCustomerById(anyInt()))
                .thenReturn(_fakeUser.getTestUser(anyInt()));

        when(_discountService.getDiscountRulesByUserType(_fakeUser.getTestUser(anyInt()).get().getUserType().getId()))
                .thenReturn(null);
        underTest = new BillCalculator(_discountService);

        _fakecartItems = new TestCartItems();
        _fakeUser = new TestUser();
        _fakeDiscount = new TestDiscount();
        _fakePrepItems = new TestPrepItems();
        userID = 1;
        invoice = null;
    }

    @Test
    @Disabled
    void getBill() {

        Mockito.when(underTest.GetBill(_fakecartItems.getUserCart(userID)))
                .thenReturn(invoice);

        //verify(_discountService).getCustomerById(_fakecartItems.getUserCart(userID).getUser());

        assertThat(invoice).isNull();
    }
}