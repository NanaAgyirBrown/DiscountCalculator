package com.improve.discountcalculator.Service;

import com.improve.discountcalculator.Domain.Model.Cart;
import com.improve.discountcalculator.Domain.Model.CartItem;
import org.codehaus.plexus.util.cli.Arg;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BillCalculatorTest {
    @Mock
    private DiscountService _discountService;
    private BillCalculator underTest;

    @BeforeEach
    void setUp() {
        underTest = new BillCalculator(_discountService);
    }

    @Test
    void getBill() {
        // Arrange
        List<CartItem> cartItems = new ArrayList<>();

        cartItems.add(new CartItem(1, 10));
        cartItems.add(new CartItem(2, 3));
        cartItems.add(new CartItem(3, 6));
        cartItems.add(new CartItem(4, 9));
        cartItems.add(new CartItem(5, 12));

        Cart cart = new Cart(1, cartItems);

        //ArgumentCaptor<Cart> cartArgumentCaptor = ArgumentCaptor.forClass(Cart.class);

        // Act
        underTest.GetBill(cart);

        //var user = verify(_discountService).getCustomerById(cartArgumentCaptor.capture().User).get();
        verify(_discountService).getCustomerById(cart.getUser());

        //Cart captured = cartArgumentCaptor.getValue();

        // Assert
        //assertThat(cart.User).isEqualTo(cart.User);

    }
}