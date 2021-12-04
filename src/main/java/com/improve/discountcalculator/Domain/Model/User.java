package com.improve.discountcalculator.Domain.Model;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class User {
    private final int id;
    @NotNull
    private final String name;
    @NotNull
    private final UserTypes userType;
    @NotNull
    private final Date membershipDate;
    private final List<Bill> bills;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public UserTypes getUserType() {
        return userType;
    }

    public Date getMembershipDate() {
        return membershipDate;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public User(int id, String name, UserTypes userType, Date membershipDate, List<Bill> bills) {
        this.id = id;
        this.name = name;
        this.userType = userType;
        this.membershipDate = membershipDate;
        this.bills = bills;
    }
}
