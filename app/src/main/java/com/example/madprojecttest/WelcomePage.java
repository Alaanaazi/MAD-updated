package com.example.madprojecttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class WelcomePage extends AppCompatActivity {
    Button police,civilian;

    @Override
    protected void onStart() {
        super.onStart();
        SessionManagement sessionManagement=new SessionManagement(WelcomePage.this);
        if(!sessionManagement.getSession().equals("No user")){
            Toast.makeText(getApplicationContext(),sessionManagement.getSession(),Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), CivilianDashboardActivity.class);
            startActivity(intent);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        police = findViewById(R.id.policeBtn);
        civilian = findViewById(R.id.civilianBtn);

        police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PoliceLogin.class);
                startActivity(intent);
            }
        });
        civilian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CivilianLogin.class);
                startActivity(intent);
            }
        });
    }
}