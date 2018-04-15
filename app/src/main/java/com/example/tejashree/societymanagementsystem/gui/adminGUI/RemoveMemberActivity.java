package com.example.tejashree.societymanagementsystem.gui.adminGUI;

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
import com.example.tejashree.societymanagementsystem.databasePhpMyadmin.societyRegistration.ConstantsSociety;
import com.example.tejashree.societymanagementsystem.gui.MainActivity;
import com.example.tejashree.societymanagementsystem.gui.adminGUI.adminCircleMenus.AdminNoticeActivity;
import com.example.tejashree.societymanagementsystem.gui.adminGUI.adminCircleMenus.ViewRequestActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RemoveMemberActivity extends AppCompatActivity {

    ListView listView;
    ProgressDialog progressDialog;
    private static ArrayAdapter<String> arrayAdapter;
    private static ArrayList<String> names=new ArrayList<>();
    String fullname[]=new String[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_member);
        progressDialog=new ProgressDialog(this);
        names.clear();//clear the names array
        listView= (ListView) findViewById(R.id.viewMembersID);
        populateActiveMembers();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder=new AlertDialog.Builder(RemoveMemberActivity.this);
                builder.setCancelable(false).setMessage("Do you want to remove this member from the society")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Toast.makeText(ViewRequestActivity.this, memberEmail.get(position), Toast.LENGTH_SHORT).show();

                                fullname=names.get(position).split("\\s");
                                removeMemberFromSociety();
                                listView.setAdapter(null);////clear the listview
                                Intent intent =new Intent(RemoveMemberActivity.this,AdminSettingsActivity.class);
                                startActivity(intent);
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
        Intent intent=new Intent(RemoveMemberActivity.this, AdminActivity.class);
        startActivity(intent);
    }
    private void populateActiveMembers()
    {
            StringRequest stringRequest =new StringRequest(Request.Method.POST, ConstantsSociety.URL_POPULATEMEMBERS,
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
                                for(int i=0;i<jsonArray.length();i++)
                                {
                                    names.add(jsonArray.get(i).toString());
                                 //   Toast.makeText(RemoveMemberActivity.this, names.get(i), Toast.LENGTH_SHORT).show();
                                }
                                arrayAdapter=new ArrayAdapter<>(getApplicationContext(),R.layout.list_view, names);
                                listView.setAdapter(arrayAdapter);
                                arrayAdapter.notifyDataSetChanged();

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

                    return params;
                }
            };


            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

        }

        private void removeMemberFromSociety()
        {
            StringRequest stringRequest =new StringRequest(Request.Method.POST, ConstantsSociety.URL_REMOVEMEMBERS,
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
                                if(jsonObject.getBoolean("removal"))
                                {
                                    Toast.makeText(RemoveMemberActivity.this, "Member removed successfully", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(RemoveMemberActivity.this, "Error removing member", Toast.LENGTH_SHORT).show();
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
                    params.put("societyName",MainActivity.societyNameSelected);
                    params.put("firstName", fullname[0]);//first Name
                    params.put("lastName", fullname[1]);//last Name
                    return params;
                }
            };


            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

        }
}
