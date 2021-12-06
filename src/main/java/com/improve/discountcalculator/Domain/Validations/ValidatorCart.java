package com.improve.discountcalculator.Domain.Validations;

import br.com.fluentvalidator.AbstractValidator;
import com.improve.discountcalculator.Domain.Model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;

@Component
public class ValidatorCart extends AbstractValidator<Cart> {
    @Autowired
    ValidatorCartItem validatorCartItem;

    @Override
    public void rules(){
        setPropertyOnContext("cart");

        ruleFor(Cart::getUser)
                .must(not(nullValue()))
                    .withMessage("User details can not be empty")
                    .withFieldName("user")
                .must(this::checkValidId)
                    .withMessage("Invalid User details")
                    .withFieldName("user");

        ruleFor(Cart::getCartItem)
                .must(not(List::isEmpty))
                    .withMessage("Item list can not be empty")
                    .withFieldName("cartItem")
                .must(not(nullValue()))
                    .withMessage("Item list can not be empty")
                    .withFieldName("cartItem");
    }

    private boolean checkValidId(final int user) {
        return user > 0;
    }
}
