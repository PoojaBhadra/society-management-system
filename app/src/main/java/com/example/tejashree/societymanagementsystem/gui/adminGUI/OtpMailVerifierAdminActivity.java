package com.example.tejashree.societymanagementsystem.gui.adminGUI;

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
import com.example.tejashree.societymanagementsystem.databasePhpMyadmin.societyRegistration.ConstantsSociety;
import com.example.tejashree.societymanagementsystem.gui.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OtpMailVerifierAdminActivity extends AppCompatActivity {

    public Button b;
    public EditText emailVerify;
    boolean otpMatched=false;

    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_mail_verifier_admin);

        progressDialog=new ProgressDialog(this);
        emailVerify= (EditText) findViewById(R.id.emailOTPAdmin);
        b= (Button) findViewById(R.id.nextButton3IDAdmin);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s= getIntent().getStringExtra("otp");
                if(emailVerify.getText().toString().equals(s))
                {
                   // Toast.makeText(OtpMailVerifierAdminActivity.this,societyTable.databaseToString(), Toast.LENGTH_SHORT).show();

                    //Toast.makeText(OTPMailVerifierUserActivity.this, "", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(OTPMailVerifierUserActivity.this, "OTP Matched", Toast.LENGTH_SHORT).show();
                    otpMatched=true;
                }
                else
                {
                    Toast.makeText(OtpMailVerifierAdminActivity.this, "OTP did not match. Try Again", Toast.LENGTH_SHORT).show();
                    otpMatched=false;
                }

                if(otpMatched) {
                    //Insert to database
                    registerSociety();

                    Intent intent = new Intent(OtpMailVerifierAdminActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(OtpMailVerifierAdminActivity.this, "OTP did not match. Try Again", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private  void registerSociety()
    {
        final String society_Name=RegisterSocietyActivity.societyName.getText().toString().trim();
        final String society_Address=RegisterSocietyActivity.societyAddress.getText().toString().trim();
        final String admin_Name=RegisterSocietyActivity.adminName.getText().toString().trim();
        final String email_Id=RegisterSocietyActivity.email.getText().toString().trim();
        final String pass_word=RegisterSocietyActivity.password.getText().toString().trim();

        progressDialog.setMessage("Registering society...");
        progressDialog.show();//start the progressDialog
        StringRequest stringRequest =new StringRequest(Request.Method.POST, ConstantsSociety.URL_REGISTER,
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

                        }
                        Intent intent =new Intent(OtpMailVerifierAdminActivity.this,MainActivity.class);
                        startActivity(intent);
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
            protected Map<String, String> getParams() throws AuthFailureError//changed code
            {
                Map<String,String> params=new HashMap<>();
                params.put("societyName",society_Name);
                params.put("adminName",admin_Name);
                params.put("societyAddress",society_Address);
                params.put("emailID",email_Id);
                params.put("password",pass_word);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}
