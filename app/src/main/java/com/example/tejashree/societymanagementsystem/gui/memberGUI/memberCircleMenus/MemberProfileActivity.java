package com.example.tejashree.societymanagementsystem.gui.memberGUI.memberCircleMenus;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.tejashree.societymanagementsystem.SharedPrefManagerUser;
import com.example.tejashree.societymanagementsystem.databasePhpMyadmin.userRegistration.ConstantsUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MemberProfileActivity extends AppCompatActivity {


    ProgressDialog progressDialog;
    private String adminName;
    private String noOfComplaints;

    private TextView nameField;
    private TextView societyNameField;
    private TextView adminNameField;
    private TextView noOfComplaintsField;

    private static int status=0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_profile);
        progressDialog=new ProgressDialog(this);

        nameField= (TextView) findViewById(R.id.nameFieldProfile);
        societyNameField= (TextView) findViewById(R.id.societyNameProfileField);
        adminNameField= (TextView) findViewById(R.id.adminNameProfile);
        noOfComplaintsField= (TextView) findViewById(R.id.noOfComplaintsFieldProfile);

        nameField.setText(SharedPrefManagerUser.getFirstName()+" "+SharedPrefManagerUser.getLastName());
        societyNameField.setText(SharedPrefManagerUser.getSocietyName());

        setRemainingProfile();
    }

    public void setRemainingProfile()
    {
        status=1;

        StringRequest stringRequest =new StringRequest(Request.Method.POST, ConstantsUser.URL_SETMEMBERPROFILE,
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
                            // Toast.makeText(ViewComplaintsActivity.this,Integer.toString(jsonArray.length()), Toast.LENGTH_SHORT).show();

                                //  Toast.makeText(ViewComplaintsActivity.this,jsonArray.get(i).toString(), Toast.LENGTH_SHORT).show();

                                adminName=jsonObj.getString("adminName");
                            Toast.makeText(MemberProfileActivity.this,adminName, Toast.LENGTH_SHORT).show();
                                noOfComplaints=jsonObj.getString("noOfComplaints");
                            Toast.makeText(MemberProfileActivity.this,noOfComplaints, Toast.LENGTH_SHORT).show();
                                adminNameField.setText(adminName);
                                noOfComplaintsField.setText(noOfComplaints);
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
                params.put("societyName",SharedPrefManagerUser.getSocietyName());//changed code
                params.put("emailMember",SharedPrefManagerUser.getKeyEmail());
                return params;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
