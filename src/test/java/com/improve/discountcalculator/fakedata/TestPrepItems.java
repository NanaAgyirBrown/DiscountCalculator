package com.improve.discountcalculator.fakedata;

import com.improve.discountcalculator.Domain.Model.PrepItems;

import java.util.ArrayList;
import java.util.List;

public class TestPrepItems {
    public List<PrepItems> getItems(){
        List<PrepItems> prepItemsList = new ArrayList<>();
        prepItemsList.add(new PrepItems(1, 20.00, 1, "Item A", 1, 20.00));
        prepItemsList.add(new PrepItems(2, 25.00, 2, "Item B", 2, 12.50));
        prepItemsList.add(new PrepItems(3, 30.00, 3, "Item C", 2, 10.00));
        prepItemsList.add(new PrepItems(4, 35.00, 4, "Item D", 1, 7.50));

        return prepItemsList;
    }
}
