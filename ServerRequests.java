package com.example.tylerjackimowicz.fscmocsapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;

/**
 * Created by Tyler Jackimowicz on 8/13/2015.
 * ServerRequests connects to the database and retrieves necessary information
 */
public class ServerRequests{

    //Variables
    ProgressDialog progressDialog;
    public static final int CONNECTION_TIMEOUT = 1000 * 15;
    public static final String SERVER_ADDRESS = "http://mocapp.hostei.com/";
    private Student student2;

    //ServerRequests provides a dialog when loading information
    public ServerRequests(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing");
        progressDialog.setMessage("Please wait ...");
    }//end ServerRequests()

    //fetchStudentDataInBackground executes the fetchStudentDataAsyncTask
    public void fetchStudentDataInBackground(Student student, GetStudentCallback callback) {
        progressDialog.show();
        new fetchStudentDataAsyncTask(student, callback).execute();
    }//end fetchStudentDataInBackground()

    //fetchStudentDataAsyncTask Creates a student and callback object, executes php code, and retrieves the data from database
    public class fetchStudentDataAsyncTask extends AsyncTask<Void, Void, Student> {
        Student student;
        GetStudentCallback studentCallback;

        public fetchStudentDataAsyncTask(Student student, GetStudentCallback studentCallback) {
            this.student = student;
            this.studentCallback = studentCallback;
        }

        @Override
        protected Student doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("idNum", student.idNum));
            dataToSend.add(new BasicNameValuePair("password", student.password));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            //connects to web server and executes php code to connect to database
            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "FetchStudentData.php");


            Student returnedStudent = null;
            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);
                JSONObject jObject = new JSONObject(result);



                if (jObject.length() == 0) {
                    returnedStudent = null;
                } else {
                    String name = jObject.getString("name");
                    returnedStudent = new Student(name, student.idNum, student.password);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            student2 = returnedStudent;
            return returnedStudent;
        }

        @Override
        protected void onPostExecute(Student returnedStudent) {
            progressDialog.dismiss();
            studentCallback.done(returnedStudent);
            super.onPostExecute(returnedStudent);
        }
    }//end FetchStudentDataAsyncTask

    //fetchMealDataInBackground executes the fetchMealDataAsyncTask
    public void fetchMealDataInBackground(MealPlan mealPlan, GetMealPlanCallback callback) {

        progressDialog.show();
        new fetchMealDataAsyncTask(mealPlan, callback).execute();
    }//end fetchMealDataInBackground()

    //fetchMealDataAsyncTask Creates a MealPlan and callback object, executes php code, and retrieves the data from database
    public class fetchMealDataAsyncTask extends AsyncTask<Void, Void, MealPlan> {
        MealPlan mealPlan;
        GetMealPlanCallback mealPlanCallback;

        public fetchMealDataAsyncTask(MealPlan mealPlan, GetMealPlanCallback mealPlanCallback) {
            this.mealPlan = mealPlan;
            this.mealPlanCallback = mealPlanCallback;
        }

        @Override
        protected MealPlan doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("idNum", mealPlan.idNum));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "FetchMealData.php");


            MealPlan returnedMealPlan = null;
            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);
                JSONObject jObject = new JSONObject(result);



                if (jObject.length() == 0) {
                    returnedMealPlan = null;
                } else {
                    String idNum = jObject.getString("idNum");
                    String meal_plan = jObject.getString("meal_plan");
                    int swipes = jObject.getInt("swipes");
                    int points = jObject.getInt("points");
                    int moc_bucks = jObject.getInt("moc_bucks");
                    returnedMealPlan = new MealPlan(idNum, meal_plan, swipes, points, moc_bucks);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return returnedMealPlan;
        }

        @Override
        protected void onPostExecute(MealPlan returnedMealPlan) {
            progressDialog.dismiss();
            mealPlanCallback.done(returnedMealPlan);
            super.onPostExecute(returnedMealPlan);
        }
    }//end fetchMealDataAsyncTask()

    //fetchScheduleInBackground executes the fetchScheduleAsyncTask
    public void fetchScheduleInBackground(Schedule schedule, GetScheduleCallback callback) {

        progressDialog.show();
        new fetchScheduleAsyncTask(schedule, callback).execute();
    }//fetchScheduleInBackground

    //fetchScheduleAsyncTask Creates a Schedule and callback object, executes php code, and retrieves the data from database
    public class fetchScheduleAsyncTask extends AsyncTask<Void, Void, Schedule> {
        Schedule schedule;
        GetScheduleCallback scheduleCallback;

        public fetchScheduleAsyncTask(Schedule schedule, GetScheduleCallback scheduleCallback) {
            this.schedule = schedule;
            this.scheduleCallback = scheduleCallback;
        }

        @Override
        protected Schedule doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("idNum", schedule.idNum));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "FetchScheduleData.php");

            Schedule returnedSchedule = null;
            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);
                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);

                //JSONArray jArray = new JSONArray(result);
                //JSONArray jArray = (JSONArray) new JSONTokener(result).nextValue();
                JSONObject jObject = new JSONObject(result);
                //JSONObject jObject = jArray.getJSONObject(0);

                //if (jArray.length() == 0) {
                if (jObject.length() == 0) {
                    returnedSchedule = null;
                } else {
                    String idNum = jObject.getString("idNum");
                    String class_name = jObject.getString("class_name");
                    String class_time = jObject.getString("class_time");
                    String class_grade = jObject.getString("class_grade");
                    returnedSchedule = new Schedule(idNum, class_name, class_time, class_grade);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return returnedSchedule;
        }

        @Override
        protected void onPostExecute(Schedule returnedSchedule) {
            progressDialog.dismiss();
            scheduleCallback.done(returnedSchedule);
            super.onPostExecute(returnedSchedule);
        }
    }//end fetchScheduleAsyncTask()
}//end ServerRequests class
