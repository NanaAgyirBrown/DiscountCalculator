package com.improve.discountcalculator.Interface;

import com.improve.discountcalculator.Domain.Model.*;

import java.util.List;
import java.util.Optional;

public interface IDiscountRepository {
    List<ItemsCategory> getAllCategories();

    List<Items> getAllShopItems();
    Optional<Items> getShopItemById(int id);

    List<Discounts> getDiscountRules();
    List<Discounts> getDiscountRuleByUserType(int userType);

    List<DiscountsType> getDiscountTypes();

    List<UserTypes> getUserTypes();

    List<User> getUsers();
    Optional<User> getUserById(int id);
}
