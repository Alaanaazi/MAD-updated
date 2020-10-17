package com.example.madprojecttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PoliceDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.police_dashboard);

        CardView news= (CardView) findViewById(R.id.NewsCardViewPolice);
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), View_Alerts.class);
                startActivity(intent);
            }
        });


        CardView profile= (CardView) findViewById(R.id.CivilProfileCardViewPolice);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),PoliceProfile.class);
                startActivity(intent);
            }
        });

        CardView alertzone =findViewById(R.id.AlertZoneCardViewPolice);
        alertzone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),AlertZone.class);
                startActivity(intent);
            }
        });

        CardView wantedlist=findViewById(R.id.WantedlistCardViewPolice);
        wantedlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Police_Criminal_View.class);
                startActivity(intent);
            }
        });

        CardView emergency=findViewById(R.id.EmeregencyCardViewPolice);
        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Emergency.class);
                startActivity(intent);
            }
        });

        CardView logout =findViewById(R.id.logoutcardviewPolice);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionManagement sessionManagement=new SessionManagement(PoliceDashboard.this);
                //Toast.makeText(getApplicationContext(),sessionManagement.getSession(),Toast.LENGTH_SHORT).show();
                sessionManagement.removeSession();
                //Toast.makeText(getApplicationContext(),sessionManagement.getSession(),Toast.LENGTH_SHORT).show();
                //onDestroy();
                Intent intent=new Intent(getApplicationContext(),WelcomePage.class);
                startActivity(intent);
            }
        });

    }
}