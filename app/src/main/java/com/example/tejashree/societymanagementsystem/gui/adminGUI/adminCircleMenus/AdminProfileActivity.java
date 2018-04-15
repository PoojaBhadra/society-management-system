package com.example.tejashree.societymanagementsystem.gui.adminGUI.adminCircleMenus;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tejashree.societymanagementsystem.R;
import com.example.tejashree.societymanagementsystem.SharedPrefManagerAdmin;
import com.example.tejashree.societymanagementsystem.databasePhpMyadmin.societyRegistration.ConstantsSociety;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AdminProfileActivity extends AppCompatActivity {

    TextView nameField;
    TextView societyName;
    TextView noOfNotices;
    TextView noOfComplaints;
    TextView complaintsMarked;
    TextView complaintsNotMarked;


    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

        progressDialog =new ProgressDialog(this);

        nameField = (TextView) findViewById(R.id.nameFieldAdminProfile);
        societyName = (TextView) findViewById(R.id.societyNameAdminProfile);
        noOfNotices = (TextView) findViewById(R.id.noOfNoticesField);
        noOfComplaints = (TextView) findViewById(R.id.totalNoOfComplaintsField);
        complaintsMarked = (TextView) findViewById(R.id.complaintsMarkedFieldAdmin);
        complaintsNotMarked = (TextView) findViewById(R.id.complaintsNotMarkedField);

        nameField.setText(SharedPrefManagerAdmin.getAdminName());
        societyName.setText(SharedPrefManagerAdmin.getKeySocietyname());

        setRestAdminProfile();

    }
    public void setRestAdminProfile()
    {
        StringRequest stringRequest =new StringRequest(Request.Method.POST, ConstantsSociety.URL_ADMINPROFILE,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        //if no error
                        progressDialog.dismiss();

                        try
                        {
                            JSONObject jsonObj=new JSONObject(response);
                            noOfNotices.setText(jsonObj.getString("noOfNotices"));
                            noOfComplaints.setText(jsonObj.getString("noOfComplaints"));
                            complaintsMarked.setText(jsonObj.getString("noOfMarkedComplaints"));
                            complaintsNotMarked.setText(jsonObj.getString("noOfNonMarked"));

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
                        //if error
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String,String> params=new HashMap<>();
                params.put("societyName", SharedPrefManagerAdmin.getKeySocietyname());//changed code
                //params.put("email",SharedPrefManagerAdmin.getKeyEmail());
                return params;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    }

