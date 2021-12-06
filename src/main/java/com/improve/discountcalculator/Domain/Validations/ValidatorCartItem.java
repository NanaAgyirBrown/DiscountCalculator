package com.improve.discountcalculator.Domain.Validations;

import br.com.fluentvalidator.AbstractValidator;
import com.improve.discountcalculator.Domain.Model.CartItem;
import org.springframework.stereotype.Component;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;

@Component
public class ValidatorCartItem extends AbstractValidator<CartItem> {
    @Override
    public void rules() {
        setPropertyOnContext("cartItem");

        ruleFor(CartItem::getItem)
                .must(not(nullValue()))
                    .withMessage("Item list can not be empty")
                    .withFieldName("cartItem")
                .must(this::checkValidId)
                    .withMessage("Invalid item code")
                    .withFieldName("item");

        ruleFor(CartItem::getQuantity)
                .must(not(nullValue()))
                    .withMessage("Item quantity can not be empty")
                    .withFieldName("quantity")
                .must(this::checkQuantityId)
                    .withMessage("Invalid item quantity")
                    .withFieldName("quantity");
        }

    private boolean checkQuantityId(final int quantity) {
        return quantity > 0;
    }

    private boolean checkValidId(final int item) {
        return item > 0;
    }
}
