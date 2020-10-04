package com.example.madprojecttest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class View_Alerts extends AppCompatActivity {
    ImageButton menu;
    TextView title;
    private ArrayList<Alert> alertList;
    private RecyclerView recyclerView;
    private RecyclerAdapter.RecyclerViewClickListener listeneralerts;
    DatabaseReference readref;
    Alert alert;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view__news);
        alertList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView1);
        title = findViewById(R.id.ToolbarTitle);
        title.setText("Alerts");

        menu = findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PoliceDashboard.class);
                startActivity(intent);
            }
        });

        Button addalert=findViewById(R.id.addalert);
        addalert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),AddAlert.class);
                startActivity(intent);
            }
        });


        readref= FirebaseDatabase.getInstance().getReference().child("Alert");
        readref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot news:snapshot.getChildren()) {
                    if (news.hasChildren()) {
                        alert=new Alert();
                        alert.setTitle(news.child("title").getValue().toString());
                        alert.setDate(news.child("date").getValue().toString());
                        alert.setDescription(news.child("description").getValue().toString());
                        alert.setNic(news.child("nic").getValue().toString());
                        alertList.add(alert);

                        setOnClickListner();
                    } else {
                        Toast.makeText(getApplicationContext(), "Nothing to display", Toast.LENGTH_SHORT).show();
                    }
                }

                RecyclerAdapter adapter = new RecyclerAdapter(alertList, listeneralerts);
                RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getApplicationContext());

                recyclerView.setLayoutManager(layoutManager1);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });


    }
    private void setOnClickListner() {
        listeneralerts = new RecyclerAdapter.RecyclerViewClickListener(){
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), NewsEditPage.class);
                intent.putExtra("title_name", alertList.get(position).getTitle());
                intent.putExtra("description_name", alertList.get(position).getDescription());
                startActivity(intent);
            }
        };
    }

}