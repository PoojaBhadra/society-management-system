package com.example.tejashree.societymanagementsystem.gui.adminGUI;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tejashree.societymanagementsystem.R;
import com.example.tejashree.societymanagementsystem.SharedPrefManagerAdmin;
import com.example.tejashree.societymanagementsystem.SharedPrefManagerUser;
import com.example.tejashree.societymanagementsystem.gui.MainActivity;
import com.example.tejashree.societymanagementsystem.gui.MaintainanceActivity;
import com.example.tejashree.societymanagementsystem.gui.adminGUI.adminCircleMenus.AdminNoticeActivity;
import com.example.tejashree.societymanagementsystem.gui.adminGUI.adminCircleMenus.AdminProfileActivity;
import com.example.tejashree.societymanagementsystem.gui.adminGUI.adminCircleMenus.ViewComplaintsActivity;
import com.example.tejashree.societymanagementsystem.gui.adminGUI.adminCircleMenus.ViewRequestActivity;
import com.example.tejashree.societymanagementsystem.helpActivities.HelpActivity;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;

import org.w3c.dom.Text;

public class AdminActivity extends AppCompatActivity {

    private static TextView notices,requests,maintainance,complaints,help,profile;
    private static Animation fadeIn,fadeOut;


    @Override
    public void onBackPressed()
    {
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Would you like to logout?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        logOutAdmin();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which) {
                        // user doesn't want to logout
                    }
                })
                .show();
    }

    void logOutAdmin()
    {
        SharedPrefManagerAdmin.logout();
        finish();
        Toast.makeText(this, "Log Out Successfully", Toast.LENGTH_SHORT).show();
        Intent intent =new Intent(AdminActivity.this,MainActivity.class);
        startActivity(intent);
    }



    private CircleMenu cmDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        notices = (TextView) findViewById(R.id.noticesText);
        requests = (TextView) findViewById(R.id.requestField);
        maintainance = (TextView) findViewById(R.id.mainText);
        complaints = (TextView) findViewById(R.id.compText);
        help = (TextView) findViewById(R.id.helpText);
        profile=(TextView) findViewById(R.id.profileField);

        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        fadeOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);

        notices.setVisibility(View.GONE);
        requests.setVisibility(View.GONE);
        maintainance.setVisibility(View.GONE);
        complaints.setVisibility(View.GONE);
        help.setVisibility(View.GONE);
        profile.setVisibility(View.GONE);

        initview();

    }



    private void initview()
    {
        cmDemo = (CircleMenu) findViewById(R.id.cm_demo);
        cmDemo.setMainMenu(Color.WHITE, R.drawable.plus, R.drawable.cross);
        cmDemo.addSubMenu(Color.WHITE, R.drawable.profile);
        cmDemo.addSubMenu(Color.WHITE, R.drawable.rqst);
        cmDemo.addSubMenu(Color.WHITE, R.drawable.notice1);
        cmDemo.addSubMenu(Color.WHITE, R.drawable.maintance1);
        cmDemo.addSubMenu(Color.WHITE, R.drawable.speech);
        cmDemo.addSubMenu(Color.WHITE, R.drawable.help2);
        cmDemo.setOnMenuSelectedListener(new OnMenuSelectedListener() {
            @Override
            public void onMenuSelected(int i) {

                notices.startAnimation(fadeOut);
                notices.setVisibility(View.GONE);

                requests.startAnimation(fadeOut);
                requests.setVisibility(View.GONE);

                maintainance.startAnimation(fadeOut);
                maintainance.setVisibility(View.GONE);

                complaints.startAnimation(fadeOut);
                complaints.setVisibility(View.GONE);

                help.startAnimation(fadeOut);
                help.setVisibility(View.GONE);

                profile.startAnimation(fadeOut);
                profile.setVisibility(View.GONE);


                if(i==0)//profile
                {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run()
                        {
                            Intent intent = new Intent(AdminActivity.this, AdminProfileActivity.class);
                            startActivity(intent);
                        }
                    },500);
                }
                if(i==1)//Requests
                {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run()
                        {
                            Intent intent = new Intent(AdminActivity.this, ViewRequestActivity.class);
                            startActivity(intent);
                        }
                    },550);
                }
                if(i==2)//notices
                {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run()
                        {
                            Intent intent = new Intent(AdminActivity.this, AdminNoticeActivity.class);
                            startActivity(intent);
                        }
                    },550);
                }
                if(i==3)//maintainance
                {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run()
                        {
                            Intent intent = new Intent(AdminActivity.this, MaintainanceActivity.class);
                            startActivity(intent);
                        }
                    },550);
                }
                if(i==4)//complaints
                {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run()
                        {
                            Intent intent = new Intent(AdminActivity.this, ViewComplaintsActivity.class);
                            startActivity(intent);
                        }
                    },550);
                }
                if(i==5)//help
                {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run()
                        {
                            Intent intent = new Intent(AdminActivity.this, HelpActivity.class);
                            startActivity(intent);
                        }
                    },550);
                }
                /*if(i==6)
                {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run()
                        {
                            Intent intent = new Intent(AdminActivity.this, AdminProfileActivity.class);
                            startActivity(intent);
                        }
                    },550);
                }*/

            }

        });

        cmDemo.setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {
            @Override
            public void onMenuOpened()
            {
                notices.setVisibility(View.VISIBLE);
                notices.startAnimation(fadeIn);

                requests.setVisibility(View.VISIBLE);
                requests.startAnimation(fadeIn);

                maintainance.setVisibility(View.VISIBLE);
                maintainance.startAnimation(fadeIn);

                complaints.setVisibility(View.VISIBLE);
                complaints.startAnimation(fadeIn);

                help.setVisibility(View.VISIBLE);
                help.startAnimation(fadeIn);

                profile.setVisibility(View.VISIBLE);
                profile.startAnimation(fadeIn);

            }

            @Override
            public void onMenuClosed()
            {
                notices.startAnimation(fadeOut);
                notices.setVisibility(View.GONE);

                requests.startAnimation(fadeOut);
                requests.setVisibility(View.GONE);

                maintainance.startAnimation(fadeOut);
                maintainance.setVisibility(View.GONE);

                complaints.startAnimation(fadeOut);
                complaints.setVisibility(View.GONE);

                help.startAnimation(fadeOut);
                help.setVisibility(View.GONE);

                profile.startAnimation(fadeOut);
                profile.setVisibility(View.GONE);

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)//changed code
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)//changed code
    {
        int itemID=item.getItemId();
        switch (itemID)
        {
            case R.id.menuLogout:
                logOutAdmin();
                break;
            case R.id.settings:
                //Toast.makeText(this, "You clicked settings", Toast.LENGTH_SHORT).show();

                Intent intent1=new Intent(AdminActivity.this,AdminSettingsActivity.class);
                startActivity(intent1);
                break;
        }
        return true;
    }
}

