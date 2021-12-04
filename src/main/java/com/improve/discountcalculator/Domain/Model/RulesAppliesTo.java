package com.improve.discountcalculator.Domain.Model;

import javax.validation.constraints.NotNull;

public class RulesAppliesTo {
    private final int id;
    @NotNull
    private final String applyTo;

    public int getId() {
        return id;
    }

    public String getApplyTo() {
        return applyTo;
    }

    public RulesAppliesTo(int id, String applyTo) {
        this.id = id;
        this.applyTo = applyTo;
    }
}
