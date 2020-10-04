package com.example.madprojecttest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ViewCriminal extends AppCompatActivity {
    EditText Name,Age,Height,Location,Case;
    Button btnUpdate,btnDelete;
    DatabaseReference upRef,delRef;
    Criminal criminal;
    ImageView pic;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_criminal);

        criminal=new Criminal();

        Bundle extras = getIntent().getExtras();

        final String value = extras.getString("Name");

        pic=findViewById(R.id.criminalpic1);

        Name = findViewById(R.id.criminalName);
        Age = findViewById(R.id.criminalage1);
        Height = findViewById(R.id.criminalheight1);
        Location = findViewById(R.id.suspectedarea1);
        Case = findViewById(R.id.crime1);

        upRef=FirebaseDatabase.getInstance().getReference().child("Criminal").child(value);
        upRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Name.setText(snapshot.child("name").getValue().toString());
                Age.setText(snapshot.child("age").getValue().toString());
                Height.setText(snapshot.child("height").getValue().toString());
                Location.setText(snapshot.child("area").getValue().toString());
                Case.setText(snapshot.child("crime").getValue().toString());
                path=snapshot.child("pic").getValue().toString();
                Picasso.get().load(path).into(pic);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnUpdate=findViewById(R.id.updatecriminal);
        btnDelete=findViewById(R.id.deletecriminal);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upRef=FirebaseDatabase.getInstance().getReference().child("Criminal");
                criminal.setName(Name.getText().toString().trim());
                criminal.setAge(Integer.parseInt(Age.getText().toString().trim()));
                criminal.setHeight(Integer.parseInt(Height.getText().toString().trim()));
                criminal.setArea(Location.getText().toString().trim());
                criminal.setCrime(Case.getText().toString().trim());
                criminal.setPic(path);

                upRef.child(Name.getText().toString()).setValue(criminal);
                Toast.makeText(getApplicationContext(),"Successfully updated",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),Police_Criminal_View.class);
                startActivity(intent);

            }
        });


          btnDelete.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  delRef= FirebaseDatabase.getInstance().getReference().child("Criminal").child(value);
                  delRef.removeValue();
                  Toast.makeText(getApplicationContext(),"Successfully Deleted",Toast.LENGTH_SHORT).show();
                  Intent intent=new Intent(getApplicationContext(),Police_Criminal_View.class);
                  startActivity(intent);


              }
          });


    }
}