package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.util.List;

public class MealToDao {
    private List<MealTo> meals;

    public MealToDao() {
        this.meals = new MealsDataBasa().createDataBase();
    }

    public void addMeal(MealTo mealTo) {
        meals.add(mealTo);
    }

    public void deleteMeal(int mealId) {
       meals.remove(mealId-1);
    }

    public void updateMeal(int mealId, MealTo mealTo) {
        meals.set(mealId, mealTo);
    }

    public List<MealTo> getAllMeals() {
        return meals;
    }

    public MealTo getMealById(int mealId) {
        return meals.get(mealId-1);
    }
}


