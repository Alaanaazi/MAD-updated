package com.example.madprojecttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CivilianDashboardActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView news= (CardView) findViewById(R.id.NewsCardView);
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), View_Alerts.class);
                startActivity(intent);
            }
        });


        CardView profile= (CardView) findViewById(R.id.ProfileCardView);
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
                Intent intent=new Intent(getApplicationContext(),Emergency.class);
                startActivity(intent);
            }
        });

        CardView logout =findViewById(R.id.logoutcardview);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedpreferences = getSharedPreferences(CivilianLogin.SESSION_KEY, Context.MODE_PRIVATE);
                Toast.makeText(getApplicationContext(),CivilianLogin.SESSION_KEY,Toast.LENGTH_SHORT).show();
                editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();

            }
        });

    }
}