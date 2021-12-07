package com.improve.discountcalculator.Service;

import com.improve.discountcalculator.Domain.Model.User;
import com.improve.discountcalculator.Domain.Model.UserTypes;
import com.improve.discountcalculator.Interface.IDiscountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DiscountServiceTest {
    @Mock
    private IDiscountRepository _discountRepo;
    private DiscountService underTest;

    @BeforeEach
    void setUp() {
        underTest = new DiscountService(_discountRepo);
    }

    @Test
    void getAllCategories() {
        // Arrange
        underTest.getAllCategories();
        // Act and Assert
        verify(_discountRepo).getAllCategories();
    }

    @Test
    void getAllShopItems() {
        underTest.getAllShopItems();
        verify(_discountRepo).getAllShopItems();
    }

    @Test
    void getAllUserTypes() {
        underTest.getAllUserTypes();
        verify(_discountRepo).getUserTypes();
    }

    @Test
    void getAllDiscountTypes() {
        underTest.getAllDiscountTypes();
        verify(_discountRepo).getDiscountTypes();
    }

    @Test
    void getAllDiscountRules() {
        underTest.getAllDiscountRules();
        verify(_discountRepo).getDiscountRules();
    }

    @Test
    void getAllCustomers() {
        underTest.getAllCustomers();
        verify(_discountRepo).getUsers();
    }

    @Test
    void getCustomerById() {
        User user = new User(1,"Kofi", new UserTypes(2, "Customer", null), null, null);

        underTest.getCustomerById(user.getId());
        verify(_discountRepo).getUserById(user.getId());
    }

    @Test
    void getDiscountRulesByUserType() {
        UserTypes userType = new UserTypes(2, "Customer", null);
        underTest.getDiscountRulesByUserType(userType.getId());
        verify(_discountRepo).getDiscountRuleByUserType(userType.getId());
    }
}