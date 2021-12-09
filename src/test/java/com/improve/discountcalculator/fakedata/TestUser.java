package com.improve.discountcalculator.fakedata;

import com.improve.discountcalculator.Domain.Model.User;
import com.improve.discountcalculator.Domain.Model.UserTypes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class TestUser {
    private List<User> testUsers(){
        long millis = System.currentTimeMillis();

        List<User> userList = new ArrayList<>();

        userList.add(new User(1, "Customer Tester", new UserTypes(3, "Customer", null),new Date(new java.sql.Date((long) (millis - (3.154e+10 * 3))).getTime()), null));
        userList.add(new User(4, "Custer Tester", new UserTypes(3, "Customer", null), new Date(new java.sql.Date(millis).getTime()), null));
        userList.add(new User(2, "Employee Tester", new UserTypes(1, "Employee", null), new Date(new java.sql.Date(millis).getTime()), null));
        userList.add(new User(3, "Affiliate Tester", new UserTypes(2, "Affiliate", null), new Date(new java.sql.Date(millis).getTime()), null));

        return userList;
    }

    public Optional<User> getTestUser(int userId){
        return testUsers().stream().filter(u -> u.getId() == userId).findFirst();
    }
}

