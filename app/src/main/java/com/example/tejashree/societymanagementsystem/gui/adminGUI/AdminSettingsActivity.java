package com.example.tejashree.societymanagementsystem.gui.adminGUI;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tejashree.societymanagementsystem.R;
import com.example.tejashree.societymanagementsystem.gui.adminGUI.adminCircleMenus.ChangeAdminPasswordActivity;
import com.example.tejashree.societymanagementsystem.gui.memberGUI.ChangePasswordActivity;

public class AdminSettingsActivity extends AppCompatActivity {

    Button button,removeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_settings);
        button= (Button) findViewById(R.id.changePasswordAdmin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminSettingsActivity.this, ChangeAdminPasswordActivity.class);
                startActivity(intent);
            }
        });

        removeButton= (Button) findViewById(R.id.removeMemberButton);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminSettingsActivity.this, RemoveMemberActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Intent intent=new Intent(AdminSettingsActivity.this, AdminActivity.class);
        startActivity(intent);
    }
}
