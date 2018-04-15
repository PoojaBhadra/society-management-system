package com.example.tejashree.societymanagementsystem.gui.memberGUI.memberCircleMenus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tejashree.societymanagementsystem.R;
import com.example.tejashree.societymanagementsystem.gui.memberGUI.MemberActivity;

import org.w3c.dom.Text;

public class ViewNoticeActivity2 extends AppCompatActivity {

    TextView textView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notice2);

        textView= (TextView) findViewById(R.id.viewNoticeField);
        button= (Button) findViewById(R.id.noticeBackButton);

        String notice=getIntent().getStringExtra("notice");
        textView.setText(notice.trim());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ViewNoticeActivity2.this,ViewNoticeActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed()
    {
        Intent intent=new Intent(ViewNoticeActivity2.this, ViewNoticeActivity.class);
        startActivity(intent);
    }
}
