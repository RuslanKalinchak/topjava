package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;
import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public List<MealTo> getAll(){
        log.info("getAll");
        return MealsUtil.getTos(service.getAll(), DEFAULT_CALORIES_PER_DAY);
    }

    public List<MealTo> getAllFilteredByDate(LocalTime startTime, LocalTime endTime){
        log.info("getAllFilteredByDate");
        return MealsUtil.getFilteredTos(service.getAll(), DEFAULT_CALORIES_PER_DAY, startTime, endTime);
    }

    public Meal get(int mealId) {
        if (service.get(mealId).getUserId()==SecurityUtil.authUserId()) {
            log.info("get {}", mealId);
            return service.get(mealId);
        } else throw new NotFoundException("not found meal");
    }

    public void delete(int mealId) {
        if (service.get(mealId).getUserId()==SecurityUtil.authUserId()){
            log.info("delete {}", mealId);
            service.delete(mealId);
        } else throw new NotFoundException("not found meal");
    }

    public void update(Meal meal) {
        if (meal.getUserId()==SecurityUtil.authUserId()){
            log.info("update {}", meal);
            service.update(meal);
        } else throw new NotFoundException("not found meal");

    }

    public void save(Meal meal) {
        if (meal.getUserId()==SecurityUtil.authUserId()){
            log.info("save {}", meal);
            service.save(meal);
        } else throw new NotFoundException("not found meal");
    }}