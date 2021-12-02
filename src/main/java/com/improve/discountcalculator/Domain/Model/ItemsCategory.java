package com.improve.discountcalculator.Domain.Model;

import javax.validation.constraints.NotNull;

public class ItemsCategory {
    public final int Id;
    @NotNull
    public final String Category;

    public ItemsCategory(int id, String category){
        this.Id  = id;
        this.Category = category;
    }
}
