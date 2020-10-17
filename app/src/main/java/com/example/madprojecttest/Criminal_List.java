package com.example.madprojecttest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Criminal_List extends AppCompatActivity {
    ImageButton menu;
    TextView title;
    ViewPager viewPager;
    Adapter adapter;
    ArrayList<Criminal> criminals ;
    Integer [] colors=null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    DatabaseReference readRef;
    Criminal criminal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criminal__view);
        criminals=new ArrayList<>();

        title = findViewById(R.id.ToolbarTitle);
        title.setText("Wanted List");

        menu = findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CivilianDashboardActivity.class);
                startActivity(intent);
            }
        });

        readRef= FirebaseDatabase.getInstance().getReference().child("Criminal");
       // Toast.makeText(getApplicationContext(),readRef.toString(),Toast.LENGTH_SHORT);
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot wanted:snapshot.getChildren()){
                    if(wanted.hasChildren()){

                        criminal=new Criminal();

                        criminal.setName(wanted.child("name").getValue().toString());
                        criminal.setCrime(wanted.child("crime").getValue().toString());
                        criminal.setHeight(Integer.parseInt(wanted.child("height").getValue().toString()));
                        criminal.setAge(Integer.parseInt(wanted.child("age").getValue().toString()));
                        criminal.setArea(wanted.child("area").getValue().toString());
                        criminal.setPic(wanted.child("pic").getValue().toString());
                        criminals.add(criminal);
                    }else {
                        Toast.makeText(getApplicationContext(), "Nothing to display", Toast.LENGTH_SHORT).show();
                    }
                }
                adapter = new Adapter(criminals,getApplicationContext());
                viewPager = findViewById(R.id.viewPagers);
                viewPager.setAdapter(adapter);
                viewPager.setPadding(130,0,130,0);

                Integer[] colors_temp= {
                        getResources().getColor(R.color.color1),
                        getResources().getColor(R.color.color2),
                        getResources().getColor(R.color.color3),
                        getResources().getColor(R.color.color4)
                };

                colors = colors_temp;

                viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        if(position < (adapter.getCount() -1) && position< (colors.length-1)) {
                            viewPager.setBackgroundColor(
                                    (Integer) argbEvaluator.evaluate(
                                            positionOffset,
                                            colors[position],
                                            colors[position+1]
                                    )
                            );
                        } else {
                            viewPager.setBackgroundColor(colors[colors.length -1]);
                        }
                    }

                    @Override
                    public void onPageSelected(int position) {

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }
}