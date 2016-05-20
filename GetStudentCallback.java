package com.example.tylerjackimowicz.fscmocsapp;

/**
 * Created by Tyler Jackimowicz.
 * GetStudentCallback provides an interface for ServerRequests class to utilize for a Student object
 */
interface GetStudentCallback {

    public abstract void done(Student returnedStudent);

}//end GetStudentCallback{}

