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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Emergency extends AppCompatActivity {

    private ArrayList<Hotline> emergencyList;
    private RecyclerView recyclerView;
    private EmergencyAdapter.RecyclerViewClickListener listener_contacts;
    Hotline hotline;
    DatabaseReference readref;
    String user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency);
        emergencyList = new ArrayList<Hotline>();
        recyclerView = findViewById(R.id.recyclerView2);


        Button addhotline=findViewById(R.id.addhotline);

        addhotline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),AddEmergency.class);
                startActivity(intent);
            }
        });

        readref= FirebaseDatabase.getInstance().getReference().child("Hotline");
        readref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot contact:snapshot.getChildren()) {
                    if (contact.hasChildren()) {
                        hotline=new Hotline();
                        hotline.setName(contact.child("name").getValue().toString());
                        hotline.setNo(Integer.parseInt(contact.child("no").getValue().toString()));
                        emergencyList.add(hotline);

                        setOnClickListner();
                    } else {
                        Toast.makeText(getApplicationContext(), "Nothing to display", Toast.LENGTH_SHORT).show();
                    }


                }
                EmergencyAdapter adapter = new EmergencyAdapter(emergencyList, listener_contacts);
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
        listener_contacts = new EmergencyAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent1 = new Intent(getApplicationContext(), EmergencyEditPage.class);
                intent1.putExtra("name", emergencyList.get(position).getName());
                intent1.putExtra("no", emergencyList.get(position).getNo());
                startActivity(intent1);
            }
        };
    }

}