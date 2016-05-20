package com.example.tylerjackimowicz.fscmocsapp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Tyler Jackimowicz.
 * StudentLocalStore defines methods to be utilized by other classes
 */
public class StudentLocalStore{
    //variables
    public static final String SP_NAME = "studentDetails";
    SharedPreferences studentLocalDatabase;

    //StudentLocalStore defines the local database
    public StudentLocalStore(Context context){
        studentLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }//end StudentLocalStore()

    //storeStudentData stores the idNum, password, and name into the local database
    public void storeStudentData(Student student){
        SharedPreferences.Editor spEditor = studentLocalDatabase.edit();
        spEditor.putString("idNum", student.idNum);
        spEditor.putString("password", student.password);
        spEditor.putString("name", student.name);
        spEditor.commit();
    }//end storeStudentData()

    //getLoggedInStudent returns the Student object that is defined by the local database information
    public Student getLoggedInStudent() {
        String idNum = studentLocalDatabase.getString("idNum", "");
        String password = studentLocalDatabase.getString("password", "");
        String name = studentLocalDatabase.getString("name", "");

        Student storedStudent = new Student(name, idNum, password);
        return storedStudent;
    }//end getLoggedInStudent()

    //setStudentLoggedIn sets the student state to loggedIn
    public void setStudentLoggedIn(boolean loggedIn){
        SharedPreferences.Editor spEditor = studentLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }//end setStudentLoggedIn()

    //getStudentLoggedIn checks if the student is logged in
    public boolean getStudentLoggedIn(){
        if (studentLocalDatabase.getBoolean("loggedIn", false) == true){
            return true;
        }
        else{
            return false;
        }//end else
    }//end getStudentLoggedIn()

    //clearStudentData clears the local database
    public void clearStudentData(){
        SharedPreferences.Editor spEditor = studentLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }//end clearStudentData()
}//end StudentLocalStore class
