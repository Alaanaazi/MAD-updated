package com.example.madprojecttest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class DeleteCriminal extends AppCompatActivity {
    EditText Name,Age,Height,Location,Case;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_criminal);

        Name = findViewById(R.id.criminalName);
        Age = findViewById(R.id.criminalage1);
        Height = findViewById(R.id.criminalheight1);
        Location = findViewById(R.id.suspectedarea1);
        Case = findViewById(R.id.crime1);


    }
}