package com.improve.discountcalculator.Domain.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CartItem {
    @NotNull
    @JsonProperty("item")
    public int Item;

    @NotNull
    @NotBlank
    @JsonProperty("quantity")
    public int Quantity;

    public int getItem() {
        return Item;
    }

    public int getQuantity() {
        return Quantity;
    }
}
