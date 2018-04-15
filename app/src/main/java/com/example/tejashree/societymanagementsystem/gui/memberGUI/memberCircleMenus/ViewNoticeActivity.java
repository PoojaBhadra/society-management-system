package com.example.tejashree.societymanagementsystem.gui.memberGUI.memberCircleMenus;

import android.app.ProgressDialog;
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
import com.example.tejashree.societymanagementsystem.databasePhpMyadmin.userRegistration.ConstantsUser;
import com.example.tejashree.societymanagementsystem.gui.MainActivity;
import com.example.tejashree.societymanagementsystem.gui.memberGUI.MemberActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewNoticeActivity extends AppCompatActivity  {


    ProgressDialog progressDialog;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> sendNoticeArray = new ArrayList<>();
    String tableName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_notice);

        listView = (ListView) findViewById(R.id.viewNotice);
        populateNotices();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(ViewNoticeActivity.this,ViewNoticeActivity2.class);
                intent.putExtra("notice",sendNoticeArray.get(position));
                startActivity(intent);
            }
        });
        progressDialog = new ProgressDialog(this);
        //findNoticeTableName();
        String societyN=MainActivity.societyNameSelected.replaceAll("\\s","");

        tableName="notices_"+ societyN;
        Toast.makeText(this, tableName, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onBackPressed()
    {
        Intent intent=new Intent(ViewNoticeActivity.this, MemberActivity.class);
        startActivity(intent);
    }

/*    public void findNoticeTableName() {
        status = 1;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ConstantsUser.URL_GETNOTICETABLE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //if no "
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            noticeTableName = (String) jsonObject.getString("table");
                            Toast.makeText(ViewNoticeActivity.this,noticeTableName, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //if error
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("societyName", MainActivity.societyNameSelected);//changed code
                return params;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        //requestQueue.stop();
        requestQueue.addRequestFinishedListener(this);
    }*/

    public void populateNotices() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ConstantsUser.URL_GETNOTICES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //if no error
                        progressDialog.dismiss();

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            // Toast.makeText(ViewComplaintsActivity.this,Integer.toString(jsonArray.length()), Toast.LENGTH_SHORT).show();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                //  Toast.makeText(ViewComplaintsActivity.this,jsonArray.get(i).toString(), Toast.LENGTH_SHORT).show();
                                sendNoticeArray.add(jsonArray.get(i).toString());
                            }
                            arrayAdapter=new ArrayAdapter<>(getApplicationContext(),R.layout.list_view, sendNoticeArray);
                            listView.setAdapter(arrayAdapter);
                            arrayAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //if error
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("tableName",tableName);//changed code
                return params;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



  /*  public void sendNotice()
    {
        final String noticeText=notice.getText().toString().trim();
        progressDialog.setMessage("Sending notice...");
        progressDialog.show();
        StringRequest stringRequest =new StringRequest(Request.Method.POST, ConstantsUser.URL_REGISTERCOMPLAINT,
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
                            Toast.makeText(getApplicationContext(),jsonObject.getString("complaint"), Toast.LENGTH_LONG).show();
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
                params.put("email", SharedPrefManagerAdmin.getKeyEmail());
                params.put("notice",noticeText);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }*/
}

