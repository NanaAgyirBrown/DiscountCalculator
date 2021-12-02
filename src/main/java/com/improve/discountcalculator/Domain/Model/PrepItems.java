package com.improve.discountcalculator.Domain.Model;

public class PrepItems extends Items {
    public final int Quantity;
    public final double TotalCost;

    public PrepItems(int quantity, double totalCost, int id, String name,
                     int category, double unitPrice ) {
        super(id, name, category, unitPrice);
        this.Quantity = quantity;
        this.TotalCost = totalCost;
    }
}
