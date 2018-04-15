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
import com.example.tejashree.societymanagementsystem.databasePhpMyadmin.userRegistration.ConstantsUser;
import com.example.tejashree.societymanagementsystem.gui.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OTPMailVerifierUserActivity extends AppCompatActivity {

    public Button b;
    public EditText emailVerify;
    boolean otpMatched=false;


    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpmail_verifier);
        emailVerify= (EditText) findViewById(R.id.emailOTP);


        //Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
        progressDialog=new ProgressDialog(this);



        b= (Button) findViewById(R.id.nextButton3ID);
        //final SocietyTable societyTable=new SocietyTable(this,null,null,1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s= getIntent().getStringExtra("otp");
                if(emailVerify.getText().toString().equals(s))
                {
                    otpMatched=true;
                }
                else
                {
                    Toast.makeText(OTPMailVerifierUserActivity.this, "OTP did not match. Try Again", Toast.LENGTH_SHORT).show();
                    otpMatched=false;
                }

                if(otpMatched) {
                    //Insert to database
                    registerUser();

                    Intent intent = new Intent(OTPMailVerifierUserActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(OTPMailVerifierUserActivity.this, "OTP did not match. Try Again", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private  void registerUser()
    {

        final String last_name= CreateNewMemberActivity.lastName.getText().toString().trim();
        final String email_id= CreateNewMemberActivity.email.getText().toString().trim();
        final String pass_word= CreateNewMemberActivity.pass.getText().toString().trim();
        final String aadhar= CreateNewMemberActivity.aadharNo.getText().toString().trim();
        final String mobile= CreateNewMemberActivity.mobileNo.getText().toString().trim();
        final String first_name= CreateNewMemberActivity.firstName.getText().toString().trim();

        progressDialog.setMessage("Registering user...");
        progressDialog.show();
        StringRequest stringRequest =new StringRequest(Request.Method.POST, ConstantsUser.URL_REGISTER,
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
                params.put("societyName",MainActivity.societyNameSelected);//changed code
                params.put("firstName",first_name);
                params.put("lastName",last_name);
                params.put("email",email_id);
                params.put("password",pass_word);
                params.put("aadharNo",aadhar);
                params.put("mobileNo",mobile);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        //RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }

}
