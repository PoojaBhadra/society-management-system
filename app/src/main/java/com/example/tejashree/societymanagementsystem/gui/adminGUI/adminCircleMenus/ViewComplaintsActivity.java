package com.example.tejashree.societymanagementsystem.gui.adminGUI.adminCircleMenus;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
import com.example.tejashree.societymanagementsystem.gui.MainActivity;
import com.example.tejashree.societymanagementsystem.gui.adminGUI.AdminActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static junit.runner.Version.id;

public class ViewComplaintsActivity extends AppCompatActivity implements RequestQueue.RequestFinishedListener<Object> {

    private static ArrayList<String> complaints =new ArrayList<>();
    private static ArrayAdapter<String> arrayAdapter;
    private static ListView listView;
    public static String tableName;
    private static ArrayList<String> complaintSenderNames=new ArrayList<>();
    private static ArrayList<String> complaintSubjectNames=new ArrayList<>();
    private static ArrayList<String> complaintBodyNames=new ArrayList<>();
    private static int active=0;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_complaints);
        listView= (ListView) findViewById(R.id.showComplaints);
        progressDialog=new ProgressDialog(this);
        complaintSubjectNames.clear();
        complaintSubjectNames.clear();
        complaintBodyNames.clear();
        findComplaintTableName();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(ViewComplaintsActivity.this,ViewComplaints2Activity.class);
                intent.putExtra("sender",complaintSenderNames.get(position));
                intent.putExtra("subject",complaintSubjectNames.get(position));
                intent.putExtra("body",complaintBodyNames.get(position));
                startActivity(intent);
            }
        });


    }
    @Override
    public void onBackPressed()
    {
        Intent intent=new Intent(ViewComplaintsActivity.this, AdminActivity.class);
        startActivity(intent);
    }
    private void findComplaintBody()
    {
        active=4;
        StringRequest stringRequest =new StringRequest(Request.Method.POST, ConstantsSociety.URL_GETCOMPLAINTBODYNAME,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        //if no error
                        progressDialog.dismiss();

                        try
                        {
                            JSONArray jsonArray=new JSONArray(response);
                            for(int i=0;i<jsonArray.length();i++)
                            {
                                complaintBodyNames.add(jsonArray.get(i).toString());
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
                params.put("tableName",tableName);//changed code

                return params;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        requestQueue.addRequestFinishedListener(this);
    }


    public void findComplaintTableName()
    {
        active=1;
        StringRequest stringRequest =new StringRequest(Request.Method.POST, ConstantsSociety.URL_GETCOMPLAINTTABLENAME,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        //if no error
                        progressDialog.dismiss();

                        try
                        {
                            JSONObject jsonObject=new JSONObject(response);
                            tableName= (String) jsonObject.getString("table");


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
                params.put("societyName", MainActivity.societyNameSelected);//changed code
                params.put("email", SharedPrefManagerAdmin.getKeyEmail());
                return params;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        //requestQueue.stop();
        requestQueue.addRequestFinishedListener(this);
    }

    private void findComplaintSenderName()
    {
        active=2;
        StringRequest stringRequest =new StringRequest(Request.Method.POST, ConstantsSociety.URL_GETCOMPLAINTSENDERNAME,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        //if no error
                        progressDialog.dismiss();

                        try
                        {
                            JSONArray jsonArray=new JSONArray(response);
                            for(int i=0;i<jsonArray.length();i++)
                            {
                                complaintSenderNames.add(jsonArray.get(i).toString());
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
                params.put("tableName",tableName);//changed code

                return params;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        requestQueue.addRequestFinishedListener(this);
    }

    private void findComplaintSubject()
    {
        active=3;
        StringRequest stringRequest =new StringRequest(Request.Method.POST, ConstantsSociety.URL_GETCOMPLAINTSUBJECTNAME,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        //if no error
                        progressDialog.dismiss();

                        try
                        {
                            JSONArray jsonArray=new JSONArray(response);
                            for(int i=0;i<jsonArray.length();i++)
                            {
                                complaintSubjectNames.add(jsonArray.get(i).toString());
                            }
                            Toast.makeText(ViewComplaintsActivity.this, Integer.toString(complaintSubjectNames.size()), Toast.LENGTH_SHORT).show();


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
                params.put("tableName",tableName);//changed code
                return params;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        requestQueue.addRequestFinishedListener(this);
    }

    @Override
    public void onRequestFinished(Request<Object> request) {

        switch (active)
        {
            case 1:
                findComplaintSenderName();
                break;
            case 2:
                findComplaintSubject();
                break;
            case 3:
                Toast.makeText(this,Integer.toString(complaintSubjectNames.size()), Toast.LENGTH_SHORT).show();
                arrayAdapter=new ArrayAdapter<>(getApplicationContext(),R.layout.list_view, complaintSubjectNames);
                listView.setAdapter(arrayAdapter);
                arrayAdapter.notifyDataSetChanged();
                findComplaintBody();
                break;
            case 4:
                active=0;
                break;
        }




    }
}