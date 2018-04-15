package com.example.tejashree.societymanagementsystem.gui.adminGUI.adminCircleMenus;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
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
import com.example.tejashree.societymanagementsystem.gui.adminGUI.AdminActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ViewComplaints2Activity extends AppCompatActivity {

    private TextView complaintTitle,complaintSubject,complaintBody;
    private Button button;
    private ProgressDialog progressDialog;
    private String senderEmail,subjectName,bodyName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_complaints2);

        complaintTitle= (TextView) findViewById(R.id.titleComplaintID);
        complaintSubject= (TextView) findViewById(R.id.subjectTextID);
        complaintBody= (TextView) findViewById(R.id.bodyField);
        button= (Button) findViewById(R.id.markAsReadButton);
        progressDialog=new ProgressDialog(this);

        senderEmail=getIntent().getStringExtra("sender");
        subjectName=getIntent().getStringExtra("subject");
        bodyName=getIntent().getStringExtra("body");

        complaintTitle.setText("Complaint By "+senderEmail);
        complaintSubject.setText(subjectName);
        complaintBody.setText(bodyName);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ViewComplaints2Activity.this,"Marked as read successfully", Toast.LENGTH_SHORT).show();
                markComplaintAsRead();
                Intent intent=new Intent(ViewComplaints2Activity.this, AdminActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed()
    {
        Intent intent=new Intent(ViewComplaints2Activity.this, ViewComplaintsActivity.class);
        startActivity(intent);
    }
    public void markComplaintAsRead()
    {
        progressDialog.setMessage("Marking as read...");
        progressDialog.show();
        StringRequest stringRequest =new StringRequest(Request.Method.POST, ConstantsSociety.URL_MARKCOMPLAINBODYNAME,
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
                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                        }
                        catch (JSONException e)
                        {
                            Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_LONG).show();
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
                params.put("tableName", ViewComplaintsActivity.tableName);
                params.put("email",senderEmail);
                params.put("subject",subjectName);
                params.put("body",bodyName);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}