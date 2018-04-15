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
import com.example.tejashree.societymanagementsystem.SharedPrefManagerUser;
import com.example.tejashree.societymanagementsystem.databasePhpMyadmin.societyRegistration.ConstantsSociety;
import com.example.tejashree.societymanagementsystem.databasePhpMyadmin.userRegistration.ConstantsUser;
import com.example.tejashree.societymanagementsystem.gui.MainActivity;
import com.example.tejashree.societymanagementsystem.gui.adminGUI.AdminActivity;
import com.example.tejashree.societymanagementsystem.gui.adminGUI.AdminSettingsActivity;
import com.example.tejashree.societymanagementsystem.gui.memberGUI.ChangePasswordActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.tejashree.societymanagementsystem.databasePhpMyadmin.societyRegistration.ConstantsSociety.URL_ADMINCHANGEPASSWORD;

public class ChangeAdminPasswordActivity extends AppCompatActivity {

    EditText oldPassword,newPassword,confirmPassword;
    Button updateButton;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_admin_password);

        oldPassword= (EditText) findViewById(R.id.oldPasswordFieldAdmin);
        newPassword= (EditText) findViewById(R.id.newPasswordFieldAdmin);
        confirmPassword= (EditText) findViewById(R.id.confirmPasswordFieldAdmin);


        {
            updateButton= (Button) findViewById(R.id.updatePasswordButtonAdmin);

            progressDialog=new ProgressDialog(this);
            updateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    if(!newPassword.getText().toString().trim().equals(confirmPassword.toString().trim()))
                    {
                        Toast.makeText(getApplicationContext(), "Password did not match", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        changeAdminPassword();
                    }

                }
            });
        }

    }
    @Override
    public void onBackPressed()
    {
        Intent intent=new Intent(ChangeAdminPasswordActivity.this, AdminSettingsActivity.class);
        startActivity(intent);
    }

    public void changeAdminPassword()
    {
        progressDialog.setMessage("Updating password...");
        progressDialog.show();
        StringRequest stringRequest =new StringRequest(Request.Method.POST, ConstantsSociety.URL_ADMINCHANGEPASSWORD,
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
                                Toast.makeText(ChangeAdminPasswordActivity.this, "Old Password is not valid", Toast.LENGTH_SHORT).show();
                            }

                            if(jsonObject.getBoolean("new"))
                            {
                                Toast.makeText(ChangeAdminPasswordActivity.this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(ChangeAdminPasswordActivity.this, "Error in updating password", Toast.LENGTH_SHORT).show();
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
                params.put("email", SharedPrefManagerAdmin.getKeyEmail());
                params.put("oldPassword",oldPassword.getText().toString().trim());
                params.put("newPassword",newPassword.getText().toString().trim());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
