package com.example.tylerjackimowicz.fscmocsapp;

/**
 * Created by Tyler Jackimowicz.
 * GetMealPlanCallback provides an interface for ServerRequests class to utilize for a MealPlan object
 */
interface GetMealPlanCallback {

    public abstract void done(MealPlan returnedMealPlan);

}//end GetMealPlanCallback{}

