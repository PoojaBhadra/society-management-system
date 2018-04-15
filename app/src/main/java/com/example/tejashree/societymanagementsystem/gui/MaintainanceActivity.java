package com.example.tejashree.societymanagementsystem.gui;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.tejashree.societymanagementsystem.R;

public class MaintainanceActivity extends AppCompatActivity {

    ImageButton payWithPaytm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maintainance);
        payWithPaytm = (ImageButton) findViewById(R.id.payWithPaytm);
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
}