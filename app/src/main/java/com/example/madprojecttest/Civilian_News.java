package com.example.madprojecttest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Civilian_News extends AppCompatActivity {
    ImageButton menu;
    TextView title;
    private ArrayList<Alert> newsList1;
    private RecyclerView recycler1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.civilian_view_news);

        title = findViewById(R.id.ToolbarTitle);
        title.setText("Alerts");

        menu = findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CivilianDashboardActivity.class);
                startActivity(intent);
            }
        });

        Button addalert=findViewById(R.id.civilian_alertbtn);
        addalert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),AddAlert.class);
                startActivity(intent);
            }
        });

        recycler1 = findViewById(R.id.recyclerView3);
        newsList1 = new ArrayList<>();

        setCivilian_NewsInfo();
        setAdapter();
    }



    private void setAdapter() {
        Civilian_News_Adapter civilian_news_adapter = new Civilian_News_Adapter(newsList1);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        recycler1.setLayoutManager(layoutManager);
        recycler1.setItemAnimator(new DefaultItemAnimator());
        recycler1.setAdapter(civilian_news_adapter);

    }


    private void setCivilian_NewsInfo() {
        //newsList1.add(new Alert("Lost Case", "A lady had lost her ATM card near the bus stop, so if anyone find it please inform."));
        //newsList1.add(new Alert ("Searching the criminal", "The Police station is searching for the criminal."));

    }
}
