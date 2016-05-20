package com.example.tylerjackimowicz.fscmocsapp;

/**
 * Created by Tyler Jackimowicz.
 * GetScheduleCallback provides an interface for ServerRequests class to utilize for a Schedule object
 */
interface GetScheduleCallback {

    public abstract void done(Schedule returnedSchedule);

}//end GetScheduleCallback{}

