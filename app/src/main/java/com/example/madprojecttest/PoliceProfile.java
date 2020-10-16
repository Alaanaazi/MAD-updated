package com.example.madprojecttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

public class PoliceProfile extends AppCompatActivity {

    ImageButton menu;
    TextView title;
    EditText name,mail,phone,pwd;
    TextView nic;
    Button dltbtn,respwd;
    DatabaseReference dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_profile);

        title = findViewById(R.id.ToolbarTitle);
        title.setText("Civilian Profile");

        menu = findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CivilianDashboardActivity.class);
                startActivity(intent);
            }
        });


        final SessionManagement sessionManagement=new SessionManagement(PoliceProfile.this);
        String un=sessionManagement.getSession();




    }
}