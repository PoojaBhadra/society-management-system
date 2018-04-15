package com.example.tejashree.societymanagementsystem.gui.memberGUI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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
import com.example.tejashree.societymanagementsystem.databasePhpMyadmin.societyRegistration.ConstantsSociety;
import com.example.tejashree.societymanagementsystem.databasePhpMyadmin.userRegistration.ConstantsUser;
import com.example.tejashree.societymanagementsystem.gui.MainActivity;
import com.example.tejashree.societymanagementsystem.gui.adminGUI.adminCircleMenus.ChangeAdminPasswordActivity;
import com.example.tejashree.societymanagementsystem.gui.memberGUI.memberCircleMenus.ViewNoticeActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText oldPassword,newPassword,confirmPassword;
    Button updateButton;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        oldPassword= (EditText) findViewById(R.id.oldPasswordField);
        newPassword= (EditText) findViewById(R.id.newPasswordField);
        confirmPassword= (EditText) findViewById(R.id.confirmPasswordField);

        updateButton= (Button) findViewById(R.id.updatePasswordButton);
        progressDialog=new ProgressDialog(this);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newPassword.getText().toString().equals(confirmPassword.getText().toString())
                        )
                {
                    updatePassword();    
                }
                else
                {
                    Toast.makeText(ChangePasswordActivity.this, "New Password and Confirm Password did not match", Toast.LENGTH_SHORT).show();
                }
                
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        Intent intent=new Intent(ChangePasswordActivity.this, MemberSettingsActivity.class);
        startActivity(intent);
    }

    public void updatePassword()
    {
        progressDialog.setMessage("Updating password...");
        progressDialog.show();
        StringRequest stringRequest =new StringRequest(Request.Method.POST, ConstantsUser.URL_UPDATEPASSWORD,
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
                            //Toast.makeText(getApplicationContext(),jsonObject.getString("status"), Toast.LENGTH_LONG).show();
                            if(!jsonObject.getBoolean("old"))
                            {
                                Toast.makeText(ChangePasswordActivity.this, "Old Password is not valid", Toast.LENGTH_SHORT).show();
                            }

                            if(jsonObject.getBoolean("new"))
                            {
                                Toast.makeText(ChangePasswordActivity.this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(ChangePasswordActivity.this, "Error in updating password", Toast.LENGTH_SHORT).show();
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
                params.put("societyName", MainActivity.societyNameSelected);//changed code
                params.put("email", SharedPrefManagerUser.getKeyEmail());
                params.put("oldPassword",oldPassword.getText().toString().trim());
                params.put("newPassword",newPassword.getText().toString().trim());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
