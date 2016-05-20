package com.example.tylerjackimowicz.fscmocsapp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Tyler Jackimowicz.
 * MealLocalStore defines methods to be utilized by other classes
 */
public class MealLocalStore {
    //variables
    public static final String SP_NAME = "mealDetails";
    SharedPreferences mealLocalDatabase;

    //MealLocalStore defines the local database
    public MealLocalStore(Context context){
        mealLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }//emd mealLocalStore

    //storeMealData stores the idNum, meal_plan, swipes, points, and moc_bucks into the local database
    public void storeMealData(MealPlan mealPlan){
        SharedPreferences.Editor spEditor = mealLocalDatabase.edit();
        spEditor.putString("idNum", mealPlan.idNum);
        spEditor.putString("meal_plan", mealPlan.meal_plan);
        spEditor.putInt("swipes", mealPlan.swipes);
        spEditor.putInt("points", mealPlan.points);
        spEditor.putInt("moc_bucks", mealPlan.moc_bucks);
        spEditor.commit();
    }//end storeMealData

    //getMealData returns the MealPlan object that is defined by the local database information
    public MealPlan getMealData() {
        String idNum = mealLocalDatabase.getString("idNum", "");
        String meal_plan = mealLocalDatabase.getString("meal_plan", "");
        int swipes = mealLocalDatabase.getInt("swipes", 0);
        int points = mealLocalDatabase.getInt("points", 0);
        int moc_bucks = mealLocalDatabase.getInt("moc_bucks", 0);

        MealPlan storedMealPlan = new MealPlan(idNum, meal_plan, swipes, points, moc_bucks);
        return storedMealPlan;
    }//end getMealData

    //clearMealData clears the local database
    public void clearMealData(){
        SharedPreferences.Editor spEditor = mealLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }//end clearMealData()
}//end MealLocalStore class
