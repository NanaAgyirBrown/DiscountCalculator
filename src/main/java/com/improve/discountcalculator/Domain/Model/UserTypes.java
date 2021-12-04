package com.improve.discountcalculator.Domain.Model;

import javax.validation.constraints.NotNull;
import java.util.List;

public class UserTypes {
    private int id;
    @NotNull
    private String userType;
    private List<User> users;

    public int getId() {
        return id;
    }

    public String getUserType() {
        return userType;
    }

    public List<User> getUsers() {
        return users;
    }

    public UserTypes(int id, String userType, List<User> users) {
        this.id = id;
        this.userType = userType;
        this.users = users;
    }
}
