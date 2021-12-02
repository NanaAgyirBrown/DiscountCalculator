package com.improve.discountcalculator.Domain.Model;

import javax.validation.constraints.NotNull;

public class DiscountsType {
    public final int Id;
    @NotNull
    public final String Name;

    public DiscountsType(int id, String name) {
        this.Id = id;
        this.Name = name;
    }
}
