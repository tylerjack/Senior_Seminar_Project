package com.example.tylerjackimowicz.fscmocsapp;

/**
 * Created by Tyler Jackimowicz.
 * Student establishes methods to create a Student Object
 */
public class Student {
    //variables
    String name, idNum, password;

    //Student creates a Student object when provided with the name, idNum, and password
    public Student (String name, String idNum, String password){
        this.name = name;
        this.idNum = idNum;
        this.password = password;
    }//end Student()

    //Student creates a Student object when provided the idNum and password and fills the name with blank data.
    public Student(String idNum, String password){
        this.idNum = idNum;
        this.password = password;
        this.name = "";
    }//end Student()
}//end Student class
