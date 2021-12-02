package com.improve.discountcalculator.Domain.Model;

import javax.validation.constraints.NotNull;

public class Items {
    public int Id;
    @NotNull
    public String ItemName;
    @NotNull
    public int CategoryId;
    @NotNull
    public double UnitPrice;

    public Items(int id, String name, int category, double unitPrice){
        this.Id = id;
        this.ItemName = name;
        this.CategoryId = category;
        this.UnitPrice = unitPrice;
    }
}
