package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

//        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        boolean excess = false;
        List <UserMeal> filteredUserMealList = new ArrayList<>();
        int  consumedСaloriesCount = 0;
       for (UserMeal userMeal: meals){
           LocalTime currentTime = userMeal.getDateTime().toLocalTime();
           if (TimeUtil.isBetweenHalfOpen(currentTime, startTime, endTime)){
               consumedСaloriesCount = consumedСaloriesCount + userMeal.getCalories();
               filteredUserMealList.add(new UserMeal(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories()));
           }
       }
       if (consumedСaloriesCount>caloriesPerDay){
           excess=true;
       }

       return userMealToUserMealWithExcess(filteredUserMealList, excess);

        // TODO return filtered list with excess. Implement by cycles
    }

    public static List<UserMealWithExcess> userMealToUserMealWithExcess(List<UserMeal> userMealList, boolean excess){
        List<UserMealWithExcess> userMealWithExcessList = new ArrayList<>();
        for (UserMeal userMeal: userMealList){
            userMealWithExcessList.add(new UserMealWithExcess(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), excess));
        }
        return userMealWithExcessList;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExcess> userMealWithExcessList = meals.stream()
                .filter(t->TimeUtil.isBetweenHalfOpen(t.getDateTime().toLocalTime(), startTime, endTime))
                .map(t-> {
                    return new UserMealWithExcess(t.getDateTime(), t.getDescription(), t.getCalories(),  meals.stream().mapToInt(p->p.getCalories()).sum() > caloriesPerDay);
                })
                .collect(Collectors.toList());

        // TODO Implement by streams
        return userMealWithExcessList;
    }

}
