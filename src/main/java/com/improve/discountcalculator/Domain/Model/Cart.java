package com.improve.discountcalculator.Domain.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Cart {
    @NotNull
    @JsonProperty("user")
    public int User;

    @NotNull
    @NotEmpty
    @JsonProperty("cartItem")
    public CartItem[] cartItem;
}
