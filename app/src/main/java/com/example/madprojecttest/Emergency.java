package com.example.madprojecttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class Emergency extends AppCompatActivity {

    private ArrayList<Contacts> emergencyList;
    private RecyclerView recyclerView;
    private EmergencyAdapter.RecyclerViewClickListener listener_contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency);
        emergencyList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView2);

        setContactsInfo();
        setAdapter();
    }

    private void setContactsInfo() {
        emergencyList.add(new Contacts("Police Emergency", "0112433333"));
        emergencyList.add(new Contacts("Fire and Ambulance", "0112422222"));
        emergencyList.add(new Contacts("Emergency Police Mobile Squad", "0115717171"));
        emergencyList.add(new Contacts("Police Head Quarters", "0112421111"));
    }

    private void setAdapter() {
        setOnClickListner();
        EmergencyAdapter adapter = new EmergencyAdapter(emergencyList, listener_contacts);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(layoutManager1);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }

    private void setOnClickListner() {
        listener_contacts = new EmergencyAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent1 = new Intent(getApplicationContext(), EmergencyEditPage.class);
                intent1.putExtra("contact_name", emergencyList.get(position).getContact());
                intent1.putExtra("phone_no", emergencyList.get(position).getNo());
                startActivity(intent1);
            }
        };
    }
}