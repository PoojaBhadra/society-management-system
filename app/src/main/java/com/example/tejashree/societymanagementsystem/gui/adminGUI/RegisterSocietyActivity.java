package com.example.tejashree.societymanagementsystem.gui.adminGUI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.example.tejashree.societymanagementsystem.R;
import com.example.tejashree.societymanagementsystem.emailVerification.SendMail;

import java.util.Random;

public class RegisterSocietyActivity extends AppCompatActivity {

    Button b;
    int randomNo=0;
    public static EditText societyName,societyAddress,adminName,email,password,confirmPassword;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register_society_page1);
        societyName = (EditText) findViewById(R.id.societyNameID);
        societyAddress = (EditText) findViewById(R.id.societyAddressID);
        adminName = (EditText) findViewById(R.id.adminID);
        email = (EditText) findViewById(R.id.emailID);
        password = (EditText) findViewById(R.id.passwordID);
        confirmPassword = (EditText) findViewById(R.id.confirmPasswordID);

        progressDialog=new ProgressDialog(this);

        b= (Button) findViewById(R.id.registerSocietyButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //registerSociety();
                sendMail();
                Intent intent =new Intent(RegisterSocietyActivity.this,OtpMailVerifierAdminActivity.class);
                intent.putExtra("otp",Integer.toString(randomNo));//send otp
                startActivity(intent);

            }
        });

    }
    public void sendMail()
    {
        String email1 = email.getText().toString().trim();
        String subject = "Verification of email ID";

        Random r = new Random();
        randomNo = r.nextInt(9999 - 1000) + 1000;
        Toast.makeText(this, Integer.toString(randomNo), Toast.LENGTH_SHORT).show();

        String message = "Your OTP is "+Integer.toString(randomNo);

        //Creating SendMail object
        SendMail sm = new SendMail(this, email1, subject, message);//UNCOMMENT THIS AFTERWARDS

        //Executing sendmail to send email
        sm.execute();//UNCOMMENT THIS AFTERWARDS
    }
        /*b= (Button) findViewById(R.id.registerSocietyButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkForEmpty()) {
                    MainActivity.society.setSocietyId(Integer.parseInt(societyID.getText().toString().trim()));
                    MainActivity.society.setSocietyName(societyNameID.getText().toString().trim());
                    MainActivity.society.setSocietyAddress(addressID.getText().toString().trim());

                    Intent intent = new Intent(RegisterSocietyActivity.this, CreateNewAccountActivity.class);
                    startActivity(intent);
                }


            }
        });
    }

//*/

       // private  void registerSociety()
//    {
//        final String society_Name=societyName.getText().toString().trim();
//        final String society_Address=societyAddress.getText().toString().trim();
//        final String admin_Name=adminName.getText().toString().trim();
//        final String email_Id=email.getText().toString().trim();
//        final String pass_word=password.getText().toString().trim();
//
//        progressDialog.setMessage("Registering user...");
//        progressDialog.show();//start the progressDialog
//        StringRequest stringRequest =new StringRequest(Request.Method.POST, ConstantsSociety.URL_REGISTER,
//                new Response.Listener<String>()
//                {
//                    @Override
//                    public void onResponse(String response)
//                    {
//                        //if no error
//                        progressDialog.dismiss();
//
//                        try
//                        {
//                            JSONObject jsonObject=new JSONObject(response);
//                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"), Toast.LENGTH_LONG).show();
//
//                        }
//                        catch (JSONException e)
//                        {
//
//                        }
//                        Intent intent =new Intent(RegisterSocietyActivity.this,MainActivity.class);
//                        startActivity(intent);
//                    }
//                },
//                new Response.ErrorListener()
//                {
//                    @Override
//                    public void onErrorResponse(VolleyError error)
//                    {
//                        //if error
//                        progressDialog.hide();
//                        Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//                })
//        {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError
//            {
//                Map<String,String> params=new HashMap<>();
//                params.put("societyName",society_Name);
//                params.put("societyAddress",society_Address);
//                params.put("adminName",admin_Name);
//                params.put("emailID",email_Id);
//                params.put("password",pass_word);
//                return params;
//            }
//        };
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//
//    }

   /* private boolean checkForEmpty()
    {


        if(societyNameID.getText().toString().equals(""))
        {
            Toast.makeText(this, "Society Name cannot be empty", Toast.LENGTH_SHORT).show();
            return true;        }
        else if(societyID.getText().toString().equals(""))
        {
            Toast.makeText(this, "Society ID cannot be empty", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(addressID.getText().toString().equals(""))
        {
            Toast.makeText(this, "Address cannot be empty", Toast.LENGTH_SHORT).show();
            return true;
        }
        else
        {
            return false;
        }
    }*/
}
