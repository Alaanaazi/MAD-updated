package com.example.madprojecttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CivilianDashboardActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView news= (CardView) findViewById(R.id.NewsCardView);
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),View_Alerts.class);
                startActivity(intent);
            }
        });


        CardView profile= (CardView) findViewById(R.id.CivilProfileCardView);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Civilian_Profile.class);
                startActivity(intent);
            }
        });

        CardView alertzone =findViewById(R.id.AlertZoneCardView);
        alertzone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),AlertZone.class);
                startActivity(intent);
            }
        });

        CardView wantedlist=findViewById(R.id.WantedlistCardView);
        wantedlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Criminal_View.class);
                startActivity(intent);
            }
        });

        CardView emergency=findViewById(R.id.EmeregencyCardView);
        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Civilian_Emergency.class);
                startActivity(intent);
            }
        });

        CardView logout =findViewById(R.id.logoutcardview);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             SessionManagement sessionManagement=new SessionManagement(CivilianDashboardActivity.this);
             //Toast.makeText(getApplicationContext(),sessionManagement.getSession(),Toast.LENGTH_SHORT).show();
             sessionManagement.removeSession();
             //Toast.makeText(getApplicationContext(),sessionManagement.getSession(),Toast.LENGTH_SHORT).show();
            //  onDestroy();
             Intent intent=new Intent(getApplicationContext(),WelcomePage.class);
             startActivity(intent);
            }
        });

    }
}