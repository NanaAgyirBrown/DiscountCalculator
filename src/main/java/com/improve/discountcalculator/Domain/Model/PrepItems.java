package com.improve.discountcalculator.Domain.Model;

public class PrepItems extends Items {
    private final int quantity;
    private final double totalCost;

    public int getQuantity() {
        return quantity;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public PrepItems(int quantity, double totalCost, int id, String name,
                     int category, double unitPrice ) {
        super(id, name, category, unitPrice);
        this.quantity = quantity;
        this.totalCost = totalCost;
    }
}