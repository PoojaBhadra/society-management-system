package com.example.tejashree.societymanagementsystem.helpActivities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tejashree.societymanagementsystem.R;

public class ElectricianActivity extends AppCompatActivity {

    Button eleCallButton,eleMsgButton,eleCallButton1,eleMsgButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electrician);

        eleCallButton = (Button) findViewById(R.id.eleCallButton);
        eleMsgButton = (Button) findViewById(R.id.eleMsgButton);

        eleCallButton1=(Button) findViewById(R.id.eleCallButton1);
        eleMsgButton1=(Button) findViewById(R.id.eleMsgButton1);

        final String number="9970099630";
        final String number1="7758020747";
        eleCallButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+number));
                Toast.makeText(getApplicationContext(),"Calling",Toast.LENGTH_LONG).show();;
                if (ActivityCompat.checkSelfPermission(ElectricianActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
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

        eleMsgButton.setOnClickListener(new View.OnClickListener() {
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

        eleCallButton1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+number1));
                Toast.makeText(getApplicationContext(),"Calling",Toast.LENGTH_LONG).show();;
                if (ActivityCompat.checkSelfPermission(ElectricianActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
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

        eleMsgButton1.setOnClickListener(new View.OnClickListener() {
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
