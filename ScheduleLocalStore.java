package com.example.tylerjackimowicz.fscmocsapp;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by Tyler Jackimowicz.
 * ScheduleLocalStore defines methods to be utilized by other classes
 */
public class ScheduleLocalStore {
    //variables
    public static final String SP_NAME = "scheduleDetails";
    SharedPreferences scheduleLocalDatabase;

    //ScheduleLocalStore defines the local database
    public ScheduleLocalStore(Context context){
        scheduleLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }//end ScheduleLocalStore()

    //storeSchedule stores the idNum, class_name, class_time, class_grade into the local database
    public void storeSchedule(Schedule schedule){
        SharedPreferences.Editor spEditor = scheduleLocalDatabase.edit();

        spEditor.putString("idNum", schedule.idNum);
        spEditor.putString("class_name", schedule.class_name);
        spEditor.putString("class_time", schedule.class_time);
        spEditor.putString("class_grade", schedule.class_grade);
        spEditor.commit();
    }//end storeSchedule()

    //getSchedule returns the Schedule object that is defined by the local database information
    public Schedule getSchedule() {
            String idNum = scheduleLocalDatabase.getString("idNum", "");
            String class_name = scheduleLocalDatabase.getString("class_name", "");
            String class_time = scheduleLocalDatabase.getString("class_time", "");
            String class_grade = scheduleLocalDatabase.getString("class_grade", "");
            Schedule storedSchedule = new Schedule(idNum, class_name, class_time, class_grade);
        return storedSchedule;
    }//end getSchedule()

    //clearScheduleData clears the local database
    public void clearScheduleData(){
        SharedPreferences.Editor spEditor = scheduleLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }//end clearScheduleData()
}//end ScheduleLocalStore class
