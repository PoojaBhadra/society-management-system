package com.example.tejashree.societymanagementsystem.gui.memberGUI;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.Nullable;
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
import com.example.tejashree.societymanagementsystem.SharedPrefManagerUser;
import com.example.tejashree.societymanagementsystem.gui.MainActivity;
import com.example.tejashree.societymanagementsystem.gui.MaintainanceActivity;
import com.example.tejashree.societymanagementsystem.gui.memberGUI.memberCircleMenus.MemberProfileActivity;
import com.example.tejashree.societymanagementsystem.gui.memberGUI.memberCircleMenus.ViewNoticeActivity;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;

import com.example.tejashree.societymanagementsystem.helpActivities.HelpActivity;

public class MemberActivity extends AppCompatActivity {

    private static TextView profile, notice,maintainance,complaints,help;
    private static Animation fadeIn,fadeOut;

    private CircleMenu cmDemo;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_page);

        profile = (TextView) findViewById(R.id.profileField1);
        notice = (TextView) findViewById(R.id.noticesText1);
        maintainance = (TextView) findViewById(R.id.mainText1);
        complaints = (TextView) findViewById(R.id.compText1);
        help = (TextView) findViewById(R.id.helpText1);

        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        fadeOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);

        profile.setVisibility(View.GONE);
        notice.setVisibility(View.GONE);
        maintainance.setVisibility(View.GONE);
        complaints.setVisibility(View.GONE);
        help.setVisibility(View.GONE);

        initview();

    }


    public void onBackPressed()
    {
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Would you like to logout?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        logOutMember();
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
    public void logOutMember()
    {
        SharedPrefManagerUser.getInstance(this).logout();
        Toast.makeText(this, "User Logged out Successfully", Toast.LENGTH_SHORT).show();
        finish();
        Intent intent =new Intent(MemberActivity.this,MainActivity.class);
        startActivity(intent);

    }
    private void initview()
    {
        cmDemo = (CircleMenu) findViewById(R.id.cm_demo);
        cmDemo.setMainMenu(Color.WHITE, R.drawable.plus, R.drawable.cross);
        cmDemo.addSubMenu(Color.WHITE, R.drawable.profile);
        cmDemo.addSubMenu(Color.WHITE, R.drawable.notice1);
        cmDemo.addSubMenu(Color.WHITE, R.drawable.maintance1);
        cmDemo.addSubMenu(Color.WHITE, R.drawable.speech);
        cmDemo.addSubMenu(Color.WHITE, R.drawable.help2);
        cmDemo.setOnMenuSelectedListener(new OnMenuSelectedListener() {
            @Override
            public void onMenuSelected(int i) {

                profile.startAnimation(fadeOut);
                profile.setVisibility(View.GONE);

                notice.startAnimation(fadeOut);
                notice.setVisibility(View.GONE);

                maintainance.startAnimation(fadeOut);
                maintainance.setVisibility(View.GONE);

                complaints.startAnimation(fadeOut);
                complaints.setVisibility(View.GONE);

                help.startAnimation(fadeOut);
                help.setVisibility(View.GONE);

                if(i==1)//profile
                {

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run()
                        {
                            if(SharedPrefManagerUser.getKeyStatus().equals("accepted"))
                            {
                                Intent intent = new Intent(MemberActivity.this, ViewNoticeActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(MemberActivity.this, "Your request is not accepted by Admin", Toast.LENGTH_SHORT).show();
                            }

                        }
                    },550);
                }
                if(i==0)//notice
                {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run()
                        {
                            Intent intent = new Intent(MemberActivity.this, MemberProfileActivity.class);
                            startActivity(intent);
                        }
                    },550);
                }
                if(i==2)//maintainance
                {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run()
                        {
                            if(SharedPrefManagerUser.getKeyStatus().equals("accepted"))
                            {
                                Intent intent = new Intent(MemberActivity.this, MaintainanceActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(MemberActivity.this, "Your request is not accepted by Admin", Toast.LENGTH_SHORT).show();
                            }

                        }
                    },550);
                }
                if(i==3)//complaints
                {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run()
                        {
                            if(SharedPrefManagerUser.getKeyStatus().equals("accepted"))
                            {
                                Intent intent = new Intent(MemberActivity.this, ComplaintActivity.class);
                                startActivity(intent);    
                            }
                            else
                            {
                                Toast.makeText(MemberActivity.this, "Your request is not accepted by Admin", Toast.LENGTH_SHORT).show();
                            }
                            
                        }
                    },550);
                }
                if(i==4)//help
                {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run()
                        {
                            Intent intent = new Intent(MemberActivity.this, HelpActivity.class);
                            startActivity(intent);
                        }
                    },550);
                }
                /*if(i==5)
                {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run()
                        {
                            Intent intent = new Intent(MemberActivity.this, HelpActivity.class);
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
                profile.setVisibility(View.VISIBLE);
                profile.startAnimation(fadeIn);

                notice.setVisibility(View.VISIBLE);
                notice.startAnimation(fadeIn);

                maintainance.setVisibility(View.VISIBLE);
                maintainance.startAnimation(fadeIn);

                complaints.setVisibility(View.VISIBLE);
                complaints.startAnimation(fadeIn);

                help.setVisibility(View.VISIBLE);
                help.startAnimation(fadeIn);

            }

            @Override
            public void onMenuClosed()
            {
                profile.startAnimation(fadeOut);
                profile.setVisibility(View.GONE);

                notice.startAnimation(fadeOut);
                notice.setVisibility(View.GONE);

                maintainance.startAnimation(fadeOut);
                maintainance.setVisibility(View.GONE);

                complaints.startAnimation(fadeOut);
                complaints.setVisibility(View.GONE);

                help.startAnimation(fadeOut);
                help.setVisibility(View.GONE);

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
                logOutMember();
                 break;
            case R.id.settings:
                Intent intent1 =new Intent(MemberActivity.this,MemberSettingsActivity.class);
                startActivity(intent1);
                Toast.makeText(this, "You clicked settings", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
