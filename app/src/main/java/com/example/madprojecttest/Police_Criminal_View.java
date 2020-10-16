package com.example.madprojecttest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
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

public class Police_Criminal_View extends AppCompatActivity {
    ImageButton menu;
    TextView title;
    ViewPager viewPager;
    Adapter2 adapter2;
    ArrayList<Criminal> criminals ;
    Integer [] colors=null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    DatabaseReference readRef;
    Criminal criminal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.police__criminal__view);

        Button btn=findViewById(R.id.addnewcriminal);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),CriminalRegister.class);
                startActivity(intent);
            }
        });


        title = findViewById(R.id.ToolbarTitle);
        title.setText("Wanted List");

        menu = findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PoliceDashboard.class);
                startActivity(intent);
            }
        });

        criminals=new ArrayList<>();

        readRef= FirebaseDatabase.getInstance().getReference().child("Criminal");
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
                adapter2 = new Adapter2(criminals,getApplicationContext());
                viewPager = findViewById(R.id.viewPagers2);
                viewPager.setAdapter(adapter2);
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
                        if(position < (adapter2.getCount() -1) && position< (colors.length-1)) {
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