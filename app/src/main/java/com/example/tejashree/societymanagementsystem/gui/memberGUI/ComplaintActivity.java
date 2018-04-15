package com.example.tejashree.societymanagementsystem.gui.memberGUI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tejashree.societymanagementsystem.R;
import com.example.tejashree.societymanagementsystem.SharedPrefManagerUser;
import com.example.tejashree.societymanagementsystem.databasePhpMyadmin.userRegistration.ConstantsUser;
import com.example.tejashree.societymanagementsystem.gui.MainActivity;
import com.example.tejashree.societymanagementsystem.gui.memberGUI.memberCircleMenus.ViewNoticeActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ComplaintActivity extends AppCompatActivity {

    private EditText subjectField,bodyField;
    private Button sendComplaintButton;

    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        setTitle("Complaints/Issues");

        subjectField= (EditText) findViewById(R.id.subjectID);
        bodyField= (EditText) findViewById(R.id.bodyID);
        sendComplaintButton= (Button) findViewById(R.id.submitComplaintButtonID);
        progressDialog=new ProgressDialog(this);

        sendComplaintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(subjectField.getText().toString().trim().equals(""))
                {
                    Toast.makeText(ComplaintActivity.this, "Subject Field cannot be empty", Toast.LENGTH_SHORT).show();
                    subjectField.setFocusable(true);
                    subjectField.requestFocus();
                }
                else if(bodyField.getText().toString().trim().equals(""))
                {
                    Toast.makeText(ComplaintActivity.this, "Body Field cannot be empty", Toast.LENGTH_SHORT).show();
                    bodyField.setFocusable(true);
                    bodyField.requestFocus();
                }
                else
                {
                    makeComplaint();
                    Intent intent=new Intent(ComplaintActivity.this, MemberActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        Intent intent=new Intent(ComplaintActivity.this, MemberActivity.class);
        startActivity(intent);
    }
    public void makeComplaint()
    {
        progressDialog.setMessage("Issuing Complaint...");
        progressDialog.show();
        StringRequest stringRequest =new StringRequest(Request.Method.POST, ConstantsUser.URL_REGISTERCOMPLAINT,
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
                            Toast.makeText(getApplicationContext(),jsonObject.getString("complaint"), Toast.LENGTH_LONG).show();
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
                params.put("societyName", MainActivity.societyNameSelected);//changed code
                params.put("email", SharedPrefManagerUser.getKeyEmail());
                params.put("subject",subjectField.getText().toString().trim());
                params.put("body",bodyField.getText().toString().trim());

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
