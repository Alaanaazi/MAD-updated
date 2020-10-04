package com.example.madprojecttest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Civilian_Profile extends AppCompatActivity {

    EditText name,mail,phone,pwd;
    TextView nic;
    Button dltbtn,respwd;
    DatabaseReference dbRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_civilian__profile);

        final SessionManagement sessionManagement=new SessionManagement(Civilian_Profile.this);
        String un=sessionManagement.getSession();

        name=findViewById(R.id.getcivilname);
        mail=findViewById(R.id.getcivilmail);
        phone=findViewById(R.id.getcivilphone);
        pwd=findViewById(R.id.getcivilnewpwd);
        nic=findViewById(R.id.getcivilnic);

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


        dltbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                dbRef.removeValue();
                sessionManagement.removeSession();

            }
        });








    }
}