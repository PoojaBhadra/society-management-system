package com.example.tejashree.societymanagementsystem.gui.adminGUI.adminCircleMenus;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.R.attr.dial;
import static com.example.tejashree.societymanagementsystem.databasePhpMyadmin.societyRegistration.ConstantsSociety.URL_ADDMEMBERTOSOCIETY;

public class ViewRequestActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private ListView membersRequest;
    private static ArrayList<String> memberEmail=new ArrayList<>();
    private static ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_request);
        membersRequest= (ListView) findViewById(R.id.membersRequestListView);
        membersRequest.setAdapter(null);
        membersRequest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                //call dialog to display details
                //create dialog activity
                //Toast.makeText(ViewRequestActivity.this, "Hi", Toast.LENGTH_SHORT).show();
//                Intent intent=new Intent(getApplicationContext(),DialogActivity.class);
//                intent.putExtra("value",position);
//                startActivity(intent);
                AlertDialog.Builder builder=new AlertDialog.Builder(ViewRequestActivity.this);
                builder.setCancelable(false).setMessage("Do you want to add this member to the society")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                    //
                                viewRequestsFromUser();
                                addMemberToSociety(memberEmail.get(position));
                                Intent intent=new Intent(ViewRequestActivity.this, AdminActivity.class);
                                startActivity(intent);
                                //Toast.makeText(ViewRequestActivity.this, memberEmail.get(position), Toast.LENGTH_SHORT).show();
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
        progressDialog=new ProgressDialog(this);
        memberEmail=new ArrayList<>();

        viewRequestsFromUser();


    }
    @Override
    public void onBackPressed()
    {
        Intent intent=new Intent(ViewRequestActivity.this, AdminActivity.class);
        startActivity(intent);
    }

    public void viewRequestsFromUser()
    {
        //select * from societyTable where memberStatus="false";
        //display them here
        StringRequest stringRequest =new StringRequest(Request.Method.POST, ConstantsSociety.URL_GETINCOMINGREQUESTS,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        //if no error
                        progressDialog.dismiss();

                        try
                        {
                            JSONArray jsonArray=new JSONArray(response);
                            if(jsonArray.length()==0)
                            {
                                Toast.makeText(ViewRequestActivity.this, "No incoming requests", Toast.LENGTH_LONG).show();
                                return;
                            }

                            for(int i=0;i<jsonArray.length();i++)
                            {
                                //Toast.makeText(ViewRequestActivity.this,jsonArray.get(i).toString(), Toast.LENGTH_SHORT).show();
                                memberEmail.add(jsonArray.get(i).toString());
                            }
                            //Toast.makeText(ViewRequestActivity.this,memberEmail.get(), Toast.LENGTH_SHORT).show();
                            arrayAdapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.list_view,memberEmail);
                            membersRequest.setAdapter(arrayAdapter);
                            arrayAdapter.notifyDataSetChanged();
        //                    Toast.makeText(getApplicationContext(),Integer.toString(memberEmail.size()), Toast.LENGTH_SHORT).show();

                            //Toast.makeText(getApplicationContext(),jsonObject.getString("message"), Toast.LENGTH_LONG).show();


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
                return params;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    public void addMemberToSociety(final String email)
    {
        progressDialog.setMessage("Adding member to society...");
        progressDialog.show();
        StringRequest stringRequest =new StringRequest(Request.Method.POST, ConstantsSociety.URL_ADDMEMBERTOSOCIETY,
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
                params.put("societyName",SharedPrefManagerAdmin.getKeySocietyname());
                params.put("adminEmail",SharedPrefManagerAdmin.getKeyEmail());
                params.put("email",email);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
