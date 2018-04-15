package com.example.tejashree.societymanagementsystem.gui.adminGUI.adminCircleMenus;

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
import com.example.tejashree.societymanagementsystem.SharedPrefManagerAdmin;
import com.example.tejashree.societymanagementsystem.databasePhpMyadmin.societyRegistration.ConstantsSociety;
import com.example.tejashree.societymanagementsystem.databasePhpMyadmin.userRegistration.ConstantsUser;
import com.example.tejashree.societymanagementsystem.gui.MainActivity;
import com.example.tejashree.societymanagementsystem.gui.adminGUI.AdminActivity;
import com.example.tejashree.societymanagementsystem.gui.memberGUI.MemberActivity;
import com.example.tejashree.societymanagementsystem.gui.memberGUI.MemberSettingsActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AdminNoticeActivity extends AppCompatActivity {

    private EditText notice;
    Button submitButton;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        notice= (EditText) findViewById(R.id.noticeField);
        submitButton= (Button) findViewById(R.id.sendNoticeButton);
        progressDialog=new ProgressDialog(this);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotice();
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        Intent intent=new Intent(AdminNoticeActivity.this, AdminActivity.class);
        startActivity(intent);
    }
    public void sendNotice()
    {
        final String noticeText=notice.getText().toString().trim();
        progressDialog.setMessage("Sending notice...");
        progressDialog.show();
        StringRequest stringRequest =new StringRequest(Request.Method.POST, ConstantsSociety.URL_SENDNOTICE,
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
                            Toast.makeText(getApplicationContext(),jsonObject.getString("status"), Toast.LENGTH_LONG).show();
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
                params.put("notice",noticeText);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
