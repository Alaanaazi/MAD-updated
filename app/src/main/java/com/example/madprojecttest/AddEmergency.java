package com.example.madprojecttest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddEmergency extends AppCompatActivity {

    EditText txtname,txtno;
    Button btnsave;
    Hotline hotline;
    DatabaseReference dbRef;
    long id=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_emergency);

        txtname=findViewById(R.id.emergencyaddname);
        txtno=findViewById(R.id.emergencyaddno);
        btnsave=findViewById(R.id.savehotline);

        hotline=new Hotline();

        dbRef= FirebaseDatabase.getInstance().getReference().child("Hotline");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    id=snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               hotline.setName(txtname.getText().toString().trim());
               hotline.setNo(Integer.parseInt(txtno.getText().toString().trim()));

               //dbRef.push().setValue(hotline);
               dbRef.child(hotline.getName()).setValue(hotline);

               Toast.makeText(getApplicationContext(), "Successfully added", Toast.LENGTH_SHORT).show();
               Intent intent=new Intent(getApplicationContext(),Emergency.class);
               startActivity(intent);



            }
        });

    }


}
