package ru.javawebinar.topjava.controller;

import ru.javawebinar.topjava.dao.MealToDao;
import ru.javawebinar.topjava.model.MealTo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class MealController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/meal.jsp";
    private static String LIST_MEAL = "/listMeal.jsp";
    private MealToDao dao;

    public MealController() {
        super();
        this.dao = new MealToDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("delete")){
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            dao.deleteMeal(mealId);
            forward = LIST_MEAL;
            request.setAttribute("meals", dao.getAllMeals());
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            MealTo meal = dao.getMealById(mealId);
            request.setAttribute("meal", meal);
        } else if (action.equalsIgnoreCase("listMeal")){
            forward = LIST_MEAL;
            request.setAttribute("meals", dao.getAllMeals());
        } else {
            forward = INSERT_OR_EDIT;
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);}

    @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            MealTo meal = new MealTo();
            LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"));
            meal.setDateTime(dateTime);
            meal.setDescription(request.getParameter("description"));
            meal.setCalories(Integer.parseInt(request.getParameter("calories")));
            meal.setExcess(Boolean.parseBoolean(request.getParameter("excess")));

            String mealId = request.getParameter("mealId");
            if(mealId == null || mealId.isEmpty())
            {
                dao.addMeal(meal);
            }
            else
            {
                meal.setMealId(Integer.parseInt(mealId));
                dao.updateMeal(Integer.parseInt(mealId), meal);
            }
            RequestDispatcher view = request.getRequestDispatcher(LIST_MEAL);
            request.setAttribute("meals", dao.getAllMeals());
            view.forward(request, response);
        }
    }


