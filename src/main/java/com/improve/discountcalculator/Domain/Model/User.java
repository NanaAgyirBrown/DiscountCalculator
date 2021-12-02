package com.improve.discountcalculator.Domain.Model;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class User {
    public final int Id;
    @NotNull
    public final String Name;
    @NotNull
    public final UserTypes UserType;
    @NotNull
    public final Date MembershipDate;
    public final List<Bill> Bills;

    public User(int id, String name, UserTypes userType, Date membershipDate, List<Bill> bills) {
        Id = id;
        Name = name;
        UserType = userType;
        MembershipDate = membershipDate;
        Bills = bills;
    }
}
