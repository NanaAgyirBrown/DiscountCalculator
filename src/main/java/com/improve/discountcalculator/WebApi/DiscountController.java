package com.improve.discountcalculator.WebApi;

import br.com.fluentvalidator.Validator;
import br.com.fluentvalidator.context.Error;
import br.com.fluentvalidator.context.ValidationResult;
import com.improve.discountcalculator.Domain.Model.*;
import com.improve.discountcalculator.Domain.Responses.ApiResponse;
import com.improve.discountcalculator.Domain.Validations.ValidatorCart;
import com.improve.discountcalculator.Domain.Validations.ValidatorCartItem;
import com.improve.discountcalculator.Service.BillCalculator;
import com.improve.discountcalculator.Service.DiscountService;
import com.improve.discountcalculator.WebApi.Helpers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/v1/discount")
@RestController
public class DiscountController {
    private final DiscountService discountService;
    private OperationStatus _operationStatus;
    private final Responses _responses;
    private final DiscountStatus _discountStatus;
    private final BillCalculator _billCalculator;
    private final Validator<Cart> _validateCart = new ValidatorCart();
    private final Validator<CartItem> _validateCartItem = new ValidatorCartItem();

    @Autowired
    public DiscountController(DiscountService discountService, Responses responses, DiscountStatus discountStatus, BillCalculator billCalculator) {
        this.discountService = discountService;
        _responses = responses;
        _discountStatus = discountStatus;
        _billCalculator = billCalculator;
    }

    @GetMapping("/categories")
    public ApiResponse ItemCategories (){
        List<String> messages = new ArrayList<>();

        try {
            var data = discountService.getAllCategories();

            if (data == null)
                messages.add("No data to display");
            else
                messages.add("Item Category List");

            return _responses.GetResponse(_operationStatus.Success, _discountStatus.Fetched, messages, data);
        } catch (Exception e) {
            messages.add( String.format("Server Error : %s", e.getMessage()));
            return _responses.GetResponse(_operationStatus.Exception, _discountStatus.Failed, messages, null);
        }
    }

    @GetMapping("/shopitems")
    public ApiResponse ShopItems(){
        List<String> messages = new ArrayList<>();

        try {
            var items = discountService.getAllShopItems();

            if (items == null)
                messages.add("No data to display");
            else
                messages.add("Item List");

            return _responses.GetResponse(_operationStatus.Success, _discountStatus.Fetched, messages, items);
        }catch (Exception e){
            messages.add( String.format("Server Error : %s", e.getMessage()));
            return _responses.GetResponse(_operationStatus.Exception, _discountStatus.Failed, messages, null);
        }
    }

    @GetMapping("/usertypes")
    public ApiResponse UserTypes(){
        List<String> messages = new ArrayList<>();

        try {
            var items = discountService.getAllUserTypes();

            if (items == null)
                messages.add("No data to display");
            else
                messages.add("List of User Types");

            return _responses.GetResponse(_operationStatus.Success, _discountStatus.Fetched, messages, items);
        }catch (Exception e){
            messages.add( String.format("Server Error : %s", e.getMessage()));
            return _responses.GetResponse(_operationStatus.Exception, _discountStatus.Failed, messages, null);
        }
    }

    @GetMapping("/discounttypes")
    public ApiResponse DiscountTypes(){
        List<String> messages = new ArrayList<>();

        try {
            var items = discountService.getAllDiscountTypes();

            if (items == null)
                messages.add("No data to display");
            else
                messages.add("List of Discount Types");

            return _responses.GetResponse(_operationStatus.Success, _discountStatus.Fetched, messages, items);
        }catch (Exception e){
            messages.add( String.format("Server Error : %s", e.getMessage()));
            return _responses.GetResponse(_operationStatus.Exception, _discountStatus.Failed, messages, null);
        }
    }

    @GetMapping("/discounts")
    public ApiResponse Discounts(){
        List<String> messages = new ArrayList<>();

        try {
            var items = discountService.getAllDiscountRules();

            if (items == null)
                messages.add("No data to display");
            else
                messages.add("List of Discount Rules");

            return _responses.GetResponse(OperationStatus.Success, _discountStatus.Fetched, messages, items);
        }catch (Exception e){
            messages.add( String.format("Server Error : %s", e.getMessage()));
            return _responses.GetResponse(OperationStatus.Exception, _discountStatus.Failed, messages, null);
        }
    }

    @GetMapping("/customers")
    public ApiResponse Users(){
        List<String> messages = new ArrayList<>();

        try {
            var items = discountService.getAllCustomers();

            if (items == null)
                messages.add("No data to display");
            else
                messages.add("List of Customers");

            return _responses.GetResponse(OperationStatus.Success, _discountStatus.Fetched, messages, items);
        }catch (Exception e){
            messages.add(String.format("Server Error : %s", e.getMessage()));
            return _responses.GetResponse(OperationStatus.Exception, _discountStatus.Failed, messages, null);
        }
    }

    @PostMapping("/shoppingcart")
    public ApiResponse Cart(@RequestBody Cart cart){
        List<String> messages = new ArrayList<>();

        ValidationResult valResult = _validateCart.validate(cart);

        if (!valResult.isValid()){
            for(Error error: valResult.getErrors()){
                messages.add(error.getMessage());
            };

            return _responses.GetResponse(_operationStatus.Failed, _discountStatus.InValidObject, messages, null);
        }

        List<ValidationResult> valItemResult = _validateCartItem.validate(cart.getCartItem());

        if (valItemResult.size() > 0){
            valItemResult.forEach((ValidationResult val) -> {
                if (!val.isValid()) {
                    for (Error error : val.getErrors()) {
                        messages.add(error.getMessage() + " : " + error.getField() + " : " + error.getAttemptedValue());
                    }
                }
            });
        }

        if(!messages.isEmpty())
            return _responses.GetResponse(_operationStatus.Failed, _discountStatus.InValidObject, messages, null);

        try{
            var invoice = _billCalculator.GetBill(cart);
            if(invoice == null || invoice.getBillId().equals("")){
                messages.add("Your invoice could not be calculated.");
                return _responses.GetResponse(_operationStatus.Failed, _discountStatus.NotFound, messages, invoice);
            }

            messages.add("Discounted Invoice attached");
            return _responses.GetResponse(_operationStatus.Success, _discountStatus.Fetched, messages, invoice);

        }catch (Exception e){
            messages.add("Server Error : " + e.getMessage());
            return _responses.GetResponse(OperationStatus.Exception, _discountStatus.Failed, messages, null);
        }
    }
}
