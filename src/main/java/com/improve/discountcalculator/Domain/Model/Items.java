package com.improve.discountcalculator.Domain.Model;

import javax.validation.constraints.NotNull;

public class Items {
    private final int id;
    @NotNull
    private final String itemName;
    @NotNull
    private final int categoryId;
    @NotNull
    private final double unitPrice;

    public int getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public Items(int id, String itemName, int categoryId, double unitPrice) {
        this.id = id;
        this.itemName = itemName;
        this.categoryId = categoryId;
        this.unitPrice = unitPrice;
    }
}
