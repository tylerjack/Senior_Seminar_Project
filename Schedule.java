package com.example.tylerjackimowicz.fscmocsapp;

/**
 * Created by Tyler Jackimowicz.
 * Schedule establishes methods to create a Schedule Object
 */
public class Schedule {
    //variables
    String idNum, class_name, class_time, class_grade;

    //Schedule creates a Schedule object when provided idNum, class_name, class_time, and class_grade data
    public Schedule (String idNum, String class_name, String class_time, String class_grade){
        this.idNum = idNum;
        this.class_name = class_name;
        this.class_time = class_time;
        this.class_grade = class_grade;
    }//end Schedule()

    //Schedule creates a Schedule object when provided the idNum and fills the other variables with blank data
    public Schedule (String idNum){
        this.idNum = idNum;
        this.class_name = "";
        this.class_time = "";
        this.class_grade = "";
    }//end Schedule()
}//end Schedule class
