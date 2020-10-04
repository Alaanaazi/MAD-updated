package com.example.madprojecttest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    }
}