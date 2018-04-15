package com.example.tejashree.societymanagementsystem.gui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tejashree.societymanagementsystem.R;
import com.example.tejashree.societymanagementsystem.RequestHandler;
import com.example.tejashree.societymanagementsystem.SharedPrefManagerAdmin;
import com.example.tejashree.societymanagementsystem.SharedPrefManagerUser;
import com.example.tejashree.societymanagementsystem.databasePhpMyadmin.societyRegistration.ConstantsSociety;
import com.example.tejashree.societymanagementsystem.databasePhpMyadmin.userRegistration.ConstantsUser;
import com.example.tejashree.societymanagementsystem.gui.adminGUI.AdminActivity;
import com.example.tejashree.societymanagementsystem.gui.adminGUI.RegisterSocietyActivity;
import com.example.tejashree.societymanagementsystem.gui.memberGUI.CreateNewMemberActivity;
import com.example.tejashree.societymanagementsystem.gui.memberGUI.MemberActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity /*implements GestureDetector.OnGestureListener*/ {

    Button createNewAccountButton,registerSocietyButton;
    ImageButton loginButton;
    private ProgressDialog progressDialog;
    EditText emailField,passwordField;
    public static ArrayList<String> societyNames=new ArrayList<>();//changed code
    public static String societyNameSelected=null;//changed code
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailField= (EditText) findViewById(R.id.loginUsernameID);
        passwordField= (EditText) findViewById(R.id.loginPasswordID);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Logging in ...");

        societyNames.clear();//clear the societyNames arrayList
        societyNames.add("Select Society name");
        populateSocietyNameSpinner();//get society names dynamically from database

        final Spinner spinner=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,societyNames);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {//changed code
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                //get the current society selected in societyNameSelected
                societyNameSelected=adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        createNewAccountButton= (Button) findViewById(R.id.createNewAccountID);
        createNewAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(societyNameSelected.equals("Select Society name"))
                {
                    //make sure the user does not select this
                    Toast.makeText(MainActivity.this, "Please Select Society Name", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //redirect to createNewMemberActivityPage via intent
                    Intent intent =new Intent(MainActivity.this,CreateNewMemberActivity.class);
                    startActivity(intent);
                }

            }
        });

        //Button to register new society
        registerSocietyButton= (Button) findViewById(R.id.registerSocietyID);
        registerSocietyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //redirect to RegisterSocietyActivity via intent
                Intent intent =new Intent(MainActivity.this,RegisterSocietyActivity.class);
                startActivity(intent);
            }
        });

        //Button to make admin or user login
        loginButton= (ImageButton) findViewById(R.id.imageButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(societyNameSelected.equals("Select Society name"))
                {
                    //make sure the user does not select this
                    Toast.makeText(MainActivity.this, "Please Select Society Name", Toast.LENGTH_SHORT).show();
                }
                else//changed code
                {
                    login();//this function logs in the user or the admin
                }
            }
        });

    }
    /*
    This function populates the spinner with the societynames from database.
     */
    private  void populateSocietyNameSpinner()
    {
//        societyNames.clear();//changed code
//        societyNames.add("Select Society name");//changed code

        StringRequest stringRequest =new StringRequest(Request.Method.POST, ConstantsSociety.URL_GETSOCIETYNAMES,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        //if no error
                        progressDialog.dismiss();

                        try
                        {
                            //catch the php response in JsonArray
                            JSONArray jsonArray=new JSONArray(response);
                            for(int i=0;i<jsonArray.length();i++)
                            {
                                societyNames.add(jsonArray.get(i).toString());

                            }


                        }
                        catch (JSONException e)
                        {

                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        //if error for example server not found or any other error
                        progressDialog.hide();
                        //Toast the exact error message
                        Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        //create a new volley request
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //add our request to the queue
        requestQueue.add(stringRequest);

    }
    /*
    This function is used to log in the admin or the user
     */
    private void login()
    {
        final  String email=emailField.getText().toString().trim();
        final  String password=passwordField.getText().toString().trim();
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                ConstantsUser.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj=new JSONObject(response);

                            if(!obj.getBoolean("error"))//if there is no error
                            {
                                //no error
                                if(obj.getBoolean("admin"))//if admin
                                {
                                    //Store admin credentials in SharedPrefManager
                                    SharedPrefManagerAdmin.getInstance(getApplicationContext())
                                            .userLogin(
                                                    obj.getString("societyName"),
                                                    obj.getString("adminName"),
                                                    obj.getString("societyAddress"),
                                                    obj.getString("emailID")
                                            );
                                    Toast.makeText(getApplicationContext(),obj.getString("message"), Toast.LENGTH_LONG).show();
                                    //Admin logged in successfully
                                    //Redirect to the AdminActivity class
                                    Intent intent =new Intent(MainActivity.this,AdminActivity.class);
                                    startActivity(intent);
                                }
                                else//if member
                                    {
                                        //Store member credentials in SharedPrefManager
                                    SharedPrefManagerUser.getInstance(getApplicationContext())
                                            .userLogin(
                                                    obj.getString("societyName"),
                                                    obj.getString("firstName"),
                                                    obj.getString("lastName"),
                                                    obj.getString("email"),
                                                    obj.getString("aadharNo"),
                                                    obj.getString("mobileNo"),
                                                    obj.getString("status")
                                            );
                                    Toast.makeText(MainActivity.this,SharedPrefManagerUser.getKeyStatus(), Toast.LENGTH_SHORT).show();
                                    Toast.makeText(getApplicationContext(),obj.getString("message"), Toast.LENGTH_LONG).show();
                                    //user logged in successfully
                                    Intent intent =new Intent(MainActivity.this,MemberActivity.class);
                                    startActivity(intent);
                                }

                            }
                            else
                            {
                                //error

                                Toast.makeText(getApplicationContext(),obj.getString("message"), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                //pass the following parameters to PHP
                params.put("email",email);
                params.put("password",password);
                params.put("societyName",MainActivity.societyNameSelected);
                //Toast.makeText(getApplicationContext(),societyNameSelected, Toast.LENGTH_SHORT).show();
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

   /* @Override
    public boolean onDown(MotionEvent e) {
        Toast.makeText(this, "down", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Toast.makeText(getApplicationContext(), "on fling", Toast.LENGTH_SHORT).show();
        populateSocietyNameSpinner();
        return true;
    }*/

}