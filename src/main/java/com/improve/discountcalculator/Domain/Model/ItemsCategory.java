package com.improve.discountcalculator.Domain.Model;

import javax.validation.constraints.NotNull;

public class ItemsCategory {
    private final int id;
    @NotNull
    private final String category;


    public ItemsCategory(int id, String category){
        this.id  = id;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }
}
