package com.example.tejashree.societymanagementsystem.gui.memberGUI;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.example.tejashree.societymanagementsystem.gui.adminGUI.AdminActivity;
import com.example.tejashree.societymanagementsystem.gui.adminGUI.AdminSettingsActivity;
import com.example.tejashree.societymanagementsystem.gui.adminGUI.adminCircleMenus.ViewComplaintsActivity;
import com.example.tejashree.societymanagementsystem.gui.adminGUI.adminCircleMenus.ViewRequestActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.tejashree.societymanagementsystem.databasePhpMyadmin.userRegistration.ConstantsUser.URL_LEAVESOCIETY;
import static com.example.tejashree.societymanagementsystem.gui.adminGUI.adminCircleMenus.ViewComplaintsActivity.tableName;

public class MemberSettingsActivity extends AppCompatActivity {

    Button button1,button2;
    ProgressDialog progressDialog;
    private static boolean delete=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_settings);
        button1 = (Button) findViewById(R.id.changePasswordButton);
        button2= (Button) findViewById(R.id.leaveSocietyButton);
        progressDialog=new ProgressDialog(this);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MemberSettingsActivity.this,ChangePasswordActivity.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MemberSettingsActivity.this);
                builder.setCancelable(false).setMessage("Do you really want to leave the society")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //addMemberToSociety(memberEmail.get(position));
                                //Toast.makeText(ViewRequestActivity.this, memberEmail.get(position), Toast.LENGTH_SHORT).show();
                                leaveSociety();

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.create();
                builder.show();
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        Intent intent=new Intent(MemberSettingsActivity.this, MemberActivity.class);
        startActivity(intent);
    }
    public void leaveSociety()
    {
        StringRequest stringRequest =new StringRequest(Request.Method.POST, ConstantsUser.URL_LEAVESOCIETY,
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
                            if(jsonObj.getBoolean("deleted"))
                            {
                                delete=true;
                                    Toast.makeText(getApplicationContext(),"Successfully left the society", Toast.LENGTH_SHORT).show();
//                                    SharedPrefManagerUser.getInstance(getApplicationContext()).logout();
//                                     Toast.makeText(getApplicationContext(), "User Logged out Successfully", Toast.LENGTH_SHORT).show();
//                                    finish();
                                    Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(intent);

                            }
                            else
                            {
                                delete=false;
                                Toast.makeText(MemberSettingsActivity.this, "Error in leaving society", Toast.LENGTH_SHORT).show();
                            }
                            //Toast.makeText(ViewComplaintsActivity.this, Integer.toString(complaintSubjectNames.size()), Toast.LENGTH_SHORT).show();


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
                params.put("societyName", SharedPrefManagerUser.getSocietyName());//changed code
                params.put("email", SharedPrefManagerUser.getKeyEmail());//changed code
                return params;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
