package com.example.tylerjackimowicz.fscmocsapp;

/**
 * Created by Tyler Jackimowicz.
 * MealPlan establishes methods to create a MealPlan Object
 */
public class MealPlan {
    //variables
    String idNum, meal_plan;
    int swipes, points, moc_bucks;

    //MealPlan creates a MealPlan object when provided the idNum, meal_plan, swipes, points, and moc_bucks data
    public MealPlan (String idNum, String meal_plan, int swipes, int points, int moc_bucks){
        this.idNum = idNum;
        this.meal_plan = meal_plan;
        this.swipes = swipes;
        this.points = points;
        this.moc_bucks = moc_bucks;
    }//end MealPlan()

    //MealPlan creates a MealPlan objects with the idNum and fills the other variables with blank data
    public MealPlan (String idNum){
        this.idNum = idNum;
        this.meal_plan = "";
        this.swipes = 0;
        this.points = 0;
        this.moc_bucks = 0;
    }//end MealPlan()
}//end MealPlan class
