package com.example.madprojecttest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Civilian_Profile extends AppCompatActivity {
    ImageButton menu;
    TextView title;
    EditText name,mail,phone,pwd;
    TextView nic;
    Button dltbtn,respwd;
    DatabaseReference dbRef,delRef,upRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_civilian__profile);

        title = findViewById(R.id.ToolbarTitle);
        title.setText("Civilian Profile");

        menu = findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CivilianDashboardActivity.class);
                startActivity(intent);
            }
        });

        final SessionManagement sessionManagement=new SessionManagement(Civilian_Profile.this);
        String un=sessionManagement.getSession();

        name=findViewById(R.id.getcivilname);
        mail=findViewById(R.id.getcivilmail);
        phone=findViewById(R.id.getcivilphone);
        nic=findViewById(R.id.getcivilnic);
        respwd = findViewById(R.id.Resetbtn);

        dltbtn=findViewById(R.id.deletecivil);

        dbRef= FirebaseDatabase.getInstance().getReference().child("Civilian").child(un);

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name.setText(snapshot.child("name").getValue().toString());
                mail.setText(snapshot.child("email").getValue().toString());
                nic.setText(snapshot.child("nic").getValue().toString());
                phone.setText(snapshot.child("phone").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        respwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upRef= FirebaseDatabase.getInstance().getReference().child("Civilian").child(nic.getText().toString());
                upRef.child("name").setValue(name.getText().toString().trim());
                upRef.child("email").setValue(mail.getText().toString().trim());
                upRef.child("phone").setValue(phone.getText().toString().trim());

                Toast.makeText(getApplicationContext(), "Successfully updated", Toast.LENGTH_SHORT).show();
            }
        });


        dltbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                delRef=FirebaseDatabase.getInstance().getReference().child("Civilian").child(nic.getText().toString());
                dbRef.removeValue();
                sessionManagement.removeSession();

                Toast.makeText(getApplicationContext(), "Successfully deleted", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),WelcomePage.class);
                startActivity(intent);

            }
        });








    }
}