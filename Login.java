package com.example.tylerjackimowicz.fscmocsapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Tyler Jackimowicz.
 * Login requests the users information, authenticates the data, and transitions to the main activity.
 */
public class Login extends AppCompatActivity implements View.OnClickListener{

    //variables
    Button bLogin;
    EditText etIdNum, etPassword;
    StudentLocalStore studentLocalStore;
    MealLocalStore mealLocalStore;
    ScheduleLocalStore scheduleLocalStore;

    //onCreate runs the following code when the activity is started
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //defines EditText and Button variables
        etIdNum = (EditText) findViewById(R.id.etIdNum);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        bLogin.setOnClickListener(this);

        //defines LocalStore objects
        studentLocalStore = new StudentLocalStore(this);
        mealLocalStore = new MealLocalStore(this);
        scheduleLocalStore = new ScheduleLocalStore(this);
    }//end onCreate()

    //onClick detects when the user clicks the login button and calls methods to authenticate and store information
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bLogin:
                //Stores values
                String idNum = etIdNum.getText().toString();
                String password = etPassword.getText().toString();

                //Creates objects
                Student student = new Student(idNum, password);
                MealPlan mealPlan = new MealPlan(idNum);
                Schedule schedule = new Schedule(idNum);

                //authenticates each object
                authenticate3(schedule);
                authenticate2(mealPlan);
                authenticate(student);
                break;
        }//end switch
    }//end onClick()

    //authenticate calls the ServerRequests class and ensures that the student id number and password provided matches with the database
    private void authenticate(Student student){
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.fetchStudentDataInBackground(student, new GetStudentCallback() {
            @Override
            public void done(Student returnedStudent) {
                if (returnedStudent == null) {
                    showErrorMessage();
                } else {
                    logStudentIn(returnedStudent);
                }//end else
            }
        });
    }//end authenticate()

    //authenticate2() calls the ServerRequests class and ensures that the mealPlan id number matches with the database
    private void authenticate2(MealPlan mealPlan){
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.fetchMealDataInBackground(mealPlan, new GetMealPlanCallback() {
            @Override
            public void done(MealPlan returnedMealPlan) {
                if (returnedMealPlan == null) {
                } else {
                    logStudentMeal(returnedMealPlan);
                }//end else
            }
        });
    }//end authenticate2()

    //authenticate3() calls the ServerRequests class and ensures that the schedule id number matches with the database
    private void authenticate3(Schedule schedule){
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.fetchScheduleInBackground(schedule, new GetScheduleCallback() {
            @Override
            public void done(Schedule returnedSchedule) {
                if (returnedSchedule == null) {
                } else {
                    logStudentSchedule(returnedSchedule);
                }//end else
            }
        });
    }//end authenticate3()

    //showErrorMessage displays a dialog message if the information provided by the user did not match with the database
    private void showErrorMessage(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Login.this);
        dialogBuilder.setMessage("Incorrect user details");
        dialogBuilder.setPositiveButton("OK", null);
        dialogBuilder.show();
    }//end showErrorMessage()

    //logStudentIn stores the student object to be utilized and transitions to the main activity
    private void logStudentIn(Student returnedStudent){
        studentLocalStore.storeStudentData(returnedStudent);
        studentLocalStore.setStudentLoggedIn(true);

        startActivity(new Intent(this, MainActivity.class));
    }//end logStudentIn()

    //logStudentMeal stores the meal plan data to be utilized
    private void logStudentMeal(MealPlan returnedMealPlan){
        mealLocalStore.storeMealData(returnedMealPlan);
    }//end logStudentMeal

    //logStudentSchedule stores the schedule data to be utilized
    private void logStudentSchedule(Schedule returnedSchedule){
        scheduleLocalStore.storeSchedule(returnedSchedule);
    }//end logStudentSchedule
}//end Login class

