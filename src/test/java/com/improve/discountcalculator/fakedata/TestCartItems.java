package com.improve.discountcalculator.fakedata;

import com.improve.discountcalculator.Domain.Model.Cart;
import com.improve.discountcalculator.Domain.Model.CartItem;

import java.util.ArrayList;
import java.util.List;

public class TestCartItems {
    private List<CartItem> userTestCart(){
        List<CartItem> cartItems = new ArrayList<>();

        cartItems.add(new CartItem(1, 10));
        cartItems.add(new CartItem(2, 3));
        cartItems.add(new CartItem(3, 6));
        cartItems.add(new CartItem(4, 9));
        cartItems.add(new CartItem(5, 12));

        return cartItems;
    }

    public Cart getUserCart(int userID){
        Cart cart = new Cart(userID, userTestCart());
        return cart;
    }
}

