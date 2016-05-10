package entelect.spring.example.beans.impl;

import entelect.spring.example.beans.FoodService;

/**
 * Created by whes on 2016/05/07.
 */
public class FoodServiceImpl implements FoodService {
    private String foodName;

    @Override
    public String getFoodName() {
        return foodName;
    }

    public FoodServiceImpl() {
        this.foodName = "Burger";
    }
}
