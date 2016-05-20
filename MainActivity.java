package com.example.tylerjackimowicz.fscmocsapp;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

/**
 * Created by Tyler Jackimowicz.
 * MainActivity displays the data retrieved from the database in a UI
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //variables
    Button bLogout;
    TextView nameView;
    StudentLocalStore studentLocalStore;
    MealLocalStore mealLocalStore;
    ScheduleLocalStore scheduleLocalStore;
    ListView foodList, scheduleList;

    //onCreate runs the following code when the activity is started
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //defines TextView, Button, ClickListener, and LocalStore variables
        nameView = (TextView) findViewById(R.id.nameView);
        bLogout = (Button) findViewById(R.id.bLogout);
        bLogout.setOnClickListener(this);
        studentLocalStore = new StudentLocalStore(this);
        mealLocalStore = new MealLocalStore(this);
        scheduleLocalStore = new ScheduleLocalStore(this);

        //Utilizes TabHost class to create tabs for Home, Classes, and Food
        TabHost tabHost = (TabHost)findViewById(R.id.tabHost);
        tabHost.setup();
        findViewById(android.R.id.tabs);

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("home");
        tabSpec.setContent(R.id.Home);
        tabSpec.setIndicator("Home");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("classes");
        tabSpec.setContent(R.id.Classes);
        tabSpec.setIndicator("Classes");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("food");
        tabSpec.setContent(R.id.Food);
        tabSpec.setIndicator("Food");
        tabHost.addTab(tabSpec);
    }//end onCreate()

    //onStart checks to ensure the user is logged in and displays the data.
    @Override
    protected void onStart(){
        super.onStart();

        if (authenticate()){
            displayStudentDetails();
        }else{
            startActivity(new Intent(MainActivity.this, Login.class));
        }//end else
    }//end onStart()

    //authenticate checks to ensure the user is logged in.
    private boolean authenticate(){
        return studentLocalStore.getStudentLoggedIn();
    }//end authenticate

    //displayStudentData displays the student, meal plan and schedule data in the UI.
    private void displayStudentDetails(){
        Student student = studentLocalStore.getLoggedInStudent();
        MealPlan mealPlan = mealLocalStore.getMealData();
        Schedule schedule = scheduleLocalStore.getSchedule();

        String[] myList = {"Meal Plan: " + mealPlan.meal_plan, "Swipes: " + mealPlan.swipes, "Points: " + mealPlan.points, "Moc Bucks: " + mealPlan.moc_bucks};
        String[] myList2 = {"Class: " + schedule.class_name + "\n" + "Time: " + schedule.class_time + "\n" + "Grade: " + schedule.class_grade};

        nameView.setText("WELCOME " + student.name.toUpperCase() + " TO THE FSC APP");
        foodList = (ListView) findViewById(R.id.list);
        foodList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, myList));
        scheduleList = (ListView) findViewById(R.id.list2);
        scheduleList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, myList2));
    }//end displayStudentDetails()

    //onClick clears the LocalStore data when the user clicks the logout button.
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bLogout:
                studentLocalStore.clearStudentData();
                mealLocalStore.clearMealData();
                scheduleLocalStore.clearScheduleData();
                studentLocalStore.setStudentLoggedIn(false);

                startActivity(new Intent(this, Login.class));
                break;
        }//end switch
    }//end onClick()
}//end MainActivity class

