package com.example.tejashree.societymanagementsystem.gui.memberGUI.memberCircleMenus;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.tejashree.societymanagementsystem.R;
import com.example.tejashree.societymanagementsystem.gui.adminGUI.AdminActivity;
import com.example.tejashree.societymanagementsystem.gui.adminGUI.adminCircleMenus.AdminNoticeActivity;
import com.example.tejashree.societymanagementsystem.gui.memberGUI.MemberActivity;

public class MaintainanceActivity extends AppCompatActivity {

    ImageButton payWithPaytm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        payWithPaytm = (ImageButton) findViewById(R.id.payWithPaytm);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintainance);

        payWithPaytm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.paytm.com";
                Intent paytm = new Intent(Intent.ACTION_VIEW);
                paytm.setData(Uri.parse(url));
                startActivity(paytm);
            }
        });

    }
    @Override
    public void onBackPressed()
    {
        Intent intent=new Intent(MaintainanceActivity.this, MemberActivity.class);
        startActivity(intent);
    }
}