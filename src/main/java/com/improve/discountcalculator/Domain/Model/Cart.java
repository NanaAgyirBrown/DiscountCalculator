package com.improve.discountcalculator.Domain.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Cart {
    @NotNull
    @JsonProperty("user")
    private final int user;

    @NotNull
    @NotEmpty
    @JsonProperty("cartItem")
    private final List<CartItem> cartItem;

    public int getUser() {
        return user;
    }

    public List<CartItem> getCartItem() {
        return cartItem;
    }

    public Cart(int user, List<CartItem> cartItem) {
        this.user = user;
        this.cartItem = cartItem;
    }
}
