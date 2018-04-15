package com.example.tejashree.societymanagementsystem.helpActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.tejashree.societymanagementsystem.R;

public class HelpActivity extends AppCompatActivity {

    ImageButton pcButton, eleButton, plumButton, waterSupplyButton, shopButton, ispButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        pcButton = (ImageButton) findViewById(R.id.pcButton);
        eleButton = (ImageButton) findViewById(R.id.eleButton);
        plumButton = (ImageButton) findViewById(R.id.plumberButton);
        waterSupplyButton = (ImageButton) findViewById(R.id.wsButton);
        shopButton = (ImageButton) findViewById(R.id.shopButton);
        ispButton = (ImageButton) findViewById(R.id.ispButton);

        eleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent eleDetails = new Intent(getApplicationContext(),ElectricianActivity.class);
                startActivity(eleDetails);
            }
        });

        pcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent eleDetails = new Intent(getApplicationContext(),PestControlActivity.class);
                startActivity(eleDetails);
            }
        });

        plumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent plumberDetails = new Intent(getApplicationContext(),PlumberActivity.class);
                startActivity(plumberDetails);
            }
        });

        waterSupplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent waterSupplyDetails = new Intent(getApplicationContext(),WaterSupplyActivity.class);
                startActivity(waterSupplyDetails);
            }
        });

        shopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shopDetails = new Intent(getApplicationContext(),ShopActivity.class);
                startActivity(shopDetails);
            }
        });

        ispButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ispDetails = new Intent(getApplicationContext(),ISPActivity.class);
                startActivity(ispDetails);
            }
        });

    }
}