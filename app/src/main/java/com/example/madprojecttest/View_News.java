package com.example.madprojecttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class View_News extends AppCompatActivity {

    private ArrayList<News> newsList;
    private RecyclerView recyclerView;
    private RecyclerAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view__news);
        newsList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView1);

        setNewsInfo();
        setAdapter();
    }

    private void setAdapter() {
        setOnClickListner();
        RecyclerAdapter adapter = new RecyclerAdapter(newsList, listener);
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
                intent.putExtra("title_name", newsList.get(position).getTitle());
                intent.putExtra("description_name", newsList.get(position).getDescription());
                startActivity(intent);
            }
        };
    }

    private void setNewsInfo() {
        newsList.add(new News("Lost Case", "A lady had lost her ATM card near the bus stop, so if anyone find it please inform."));
        newsList.add(new News ("Searching the criminal", "The Police station is searching for the criminal."));

    }
}