package com.improve.discountcalculator.Service;

import com.improve.discountcalculator.Domain.Model.*;
import com.improve.discountcalculator.Interface.IDiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountService {
    private final IDiscountRepository iDiscountRepository;

    @Autowired
    public DiscountService(@Qualifier("postgres") IDiscountRepository iDiscountRepository) {
        this.iDiscountRepository = iDiscountRepository;
    }

    public List<ItemsCategory> getAllCategories(){
        return iDiscountRepository.getAllCategories();
    }

    public List<Items> getAllShopItems(){
        return iDiscountRepository.getAllShopItems();
    }

    public List<UserTypes> getAllUserTypes(){
        return iDiscountRepository.getUserTypes();
    }

    public List<DiscountsType> getAllDiscountTypes(){
        return iDiscountRepository.getDiscountTypes();
    }

    public List<Discounts> getAllDiscountRules(){
        return iDiscountRepository.getDiscountRules();
    }

    public List<User> getAllCustomers(){
        return iDiscountRepository.getUsers();
    }

    public Optional<User> getCustomerById(int user){
        return iDiscountRepository.getUserById(user);
    }

    public List<Discounts> getDiscountRulesByUserType(int userType) {
        return iDiscountRepository.getDiscountRuleByUserType(userType);
    }
}
