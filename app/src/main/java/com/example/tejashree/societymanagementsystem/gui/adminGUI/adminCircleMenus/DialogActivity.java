package com.example.tejashree.societymanagementsystem.gui.adminGUI.adminCircleMenus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tejashree.societymanagementsystem.R;

public class DialogActivity extends AppCompatActivity {
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        Intent intent=getIntent();
        String val=intent.getStringExtra("value");

        tv= (TextView) findViewById(R.id.dialogText);
        tv.setText(val);
    }
}
