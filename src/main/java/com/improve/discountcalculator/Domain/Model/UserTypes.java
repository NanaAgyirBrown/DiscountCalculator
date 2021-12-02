package com.improve.discountcalculator.Domain.Model;

import javax.validation.constraints.NotNull;
import java.util.List;

public class UserTypes {
    public int Id;
    @NotNull
    public String UserType;
    public List<User> Users;

    public UserTypes(int id, String userType, List<User> users){
        this.Id = id;
        this.UserType = userType;
        this.Users = users;
    }
}
