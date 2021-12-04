package com.improve.discountcalculator.Domain.Model;

import javax.validation.constraints.NotNull;

public class DiscountsType {
    public final int id;
    @NotNull
    public final String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public DiscountsType(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
