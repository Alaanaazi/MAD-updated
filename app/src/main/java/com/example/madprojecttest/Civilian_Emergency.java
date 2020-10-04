package com.example.madprojecttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class Civilian_Emergency extends AppCompatActivity {
    ImageButton menu;
    TextView title;
    private ArrayList<Hotline> contactsList;
    private RecyclerView civilian_recycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.civilian__emergency);

        title = findViewById(R.id.ToolbarTitle);
        title.setText("Hotline Numbers");

        menu = findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CivilianDashboardActivity.class);
                startActivity(intent);
            }
        });

        civilian_recycle = findViewById(R.id.recyclerView4);
        contactsList = new ArrayList<>();

       // setEmergencyInfo();
        setAdapter();
    }

    private void setAdapter() {
        Civilian_Emergency_Adapter civilian_emergency_adapter = new Civilian_Emergency_Adapter(contactsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        civilian_recycle.setLayoutManager(layoutManager);
        civilian_recycle.setItemAnimator(new DefaultItemAnimator());
        civilian_recycle.setAdapter(civilian_emergency_adapter);
    }

    /*private void setEmergencyInfo() {
        contactsList.add(new Contacts("Police Emergency", "0112433333"));
        contactsList.add(new Contacts("Fire and Ambulance", "0112422222"));
        contactsList.add(new Contacts("Emergency Police Mobile Squad", "0115717171"));
        contactsList.add(new Contacts("Police Head Quarters", "0112421111"));

    }*/
}