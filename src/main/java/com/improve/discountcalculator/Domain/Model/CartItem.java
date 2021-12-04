package com.improve.discountcalculator.Domain.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CartItem {
    @NotNull
    @JsonProperty("item")
    private final int item;

    @NotNull
    @NotBlank
    @JsonProperty("quantity")
    private final int quantity;

    public CartItem(int item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public int getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }
}
