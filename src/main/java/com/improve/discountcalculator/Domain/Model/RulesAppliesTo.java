package com.improve.discountcalculator.Domain.Model;

import javax.validation.constraints.NotNull;

public class RulesAppliesTo {
    public final int Id;
    @NotNull
    public final String ApplyTo;


    public RulesAppliesTo(int id, String applyTo) {
        Id = id;
        ApplyTo = applyTo;
    }
}
