package com.example.madprojecttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class View_Alerts extends AppCompatActivity {

    private ArrayList<Alert> alertList;
    private RecyclerView recyclerView;
    private RecyclerAdapter.RecyclerViewClickListener listener;
    DatabaseReference dbRef;
    Alert alert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view__news);
        alertList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView1);

        Button addalert=findViewById(R.id.addalert);
        addalert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),AddAlert.class);
                startActivity(intent);
            }
        });

        setNewsInfo();
        setAdapter();
    }

    private void setAdapter() {
        setOnClickListner();
        RecyclerAdapter adapter = new RecyclerAdapter(alertList, listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }

    private void setOnClickListner() {
        listener = new RecyclerAdapter.RecyclerViewClickListener(){
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), NewsEditPage.class);
                intent.putExtra("title_name", alertList.get(position).getTitle());
                intent.putExtra("description_name", alertList.get(position).getDescription());
                startActivity(intent);
            }
        };
    }

    private void setNewsInfo() {
       // alertList.add(new Alert("Lost Case", "A lady had lost her ATM card near the bus stop, so if anyone find it please inform."));
        //alertList.add(new Alert("Searching the criminal", "The Police station is searching for the criminal."));

    }
}