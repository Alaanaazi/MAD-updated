package com.example.madprojecttest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PoliceLogin extends AppCompatActivity {

    EditText uname,pwd;
    Button login,signup;
    DatabaseReference dbref;
    PoliceStation policeStation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_login);

        login = findViewById(R.id.loginPolice);

        policeStation=new PoliceStation();

        uname=findViewById(R.id.inputStationID);
        pwd=findViewById(R.id.inputPolicepwd);


        signup=findViewById(R.id.policesignup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String un=uname.getText().toString().trim();
                final String pw=pwd.getText().toString().trim();
                policeStation.setId(uname.getText().toString().trim());

                dbref= FirebaseDatabase.getInstance().getReference().child("PoliceStation");

                dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if ((snapshot.hasChild(un)) && (snapshot.child(un).child("pwd").getValue().toString().equals(pw))) {

                            // Toast.makeText(getApplicationContext(),un,Toast.LENGTH_SHORT).show();
                            SessionManagement sessionManagement = new SessionManagement(PoliceLogin.this);
                            sessionManagement.savePoliceSession(policeStation);
                            Intent intent = new Intent(getApplicationContext(), PoliceDashboard.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(getApplicationContext(),"Invalid ID or Password",Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PoliceRegister.class);
                startActivity(intent);
            }
        });


    }
}