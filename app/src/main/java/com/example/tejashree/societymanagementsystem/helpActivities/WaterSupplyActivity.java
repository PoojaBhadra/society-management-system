package com.example.tejashree.societymanagementsystem.helpActivities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tejashree.societymanagementsystem.R;

public class WaterSupplyActivity extends AppCompatActivity {

    Button pcCallButton,pcMsgButton,pcCallButton1,pcMsgButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_supply);

        pcCallButton = (Button) findViewById(R.id.pcCallButton);
        pcMsgButton = (Button) findViewById(R.id.pcMsgButton);

        pcCallButton1=(Button) findViewById(R.id.pcCallButton1);
        pcMsgButton1=(Button) findViewById(R.id.pcMsgButton1);

        final String number1="8329336959";
        final String number="9028032638";
        pcCallButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+number));
                Toast.makeText(getApplicationContext(),"Calling",Toast.LENGTH_LONG).show();;
                if (ActivityCompat.checkSelfPermission(WaterSupplyActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(callIntent);
            }
        });

        pcMsgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*try
                {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(number, null, "Hello!", null, null);
                    Toast.makeText(getApplicationContext(), "SMS Sent!", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again later!",
                            Toast.LENGTH_SHORT).show();
                    e.printStackTrace();

                }*/

                Intent msg = new Intent(Intent.ACTION_VIEW);
                msg.setData(Uri.parse("sms:"+ number));
                startActivity(msg);


            }
        });

        pcCallButton1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+number1));
                Toast.makeText(getApplicationContext(),"Calling",Toast.LENGTH_LONG).show();;
                if (ActivityCompat.checkSelfPermission(WaterSupplyActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(callIntent);
            }
        });

        pcMsgButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*try
                {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(number, null, "Hello!", null, null);
                    Toast.makeText(getApplicationContext(), "SMS Sent!", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again later!",
                            Toast.LENGTH_SHORT).show();
                    e.printStackTrace();

                }*/

                Intent msg = new Intent(Intent.ACTION_VIEW);
                msg.setData(Uri.parse("sms:"+ number1));
                startActivity(msg);


            }
        });

    }
}