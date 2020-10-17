package com.example.madprojecttest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PoliceRegister extends AppCompatActivity {

    EditText txtid,txtaddress,txtphone,txtpwd,txtconpwd;
    Button btnregister;
    DatabaseReference dbRef;
    PoliceStation policeStation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_register);

         txtid=findViewById(R.id.stationid);
         txtaddress=findViewById(R.id.address);
         txtphone=findViewById(R.id.stationphone);
         txtpwd=findViewById(R.id.spwd);
         txtconpwd=findViewById(R.id.sconpwd);

         btnregister=findViewById(R.id.registerstation);

         policeStation=new PoliceStation();

         btnregister.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 if(TextUtils.isEmpty(txtid.getText().toString())){
                     Toast.makeText(getApplicationContext(),"ID cannot be empty",Toast.LENGTH_SHORT).show();
                 } else if(TextUtils.isEmpty(txtaddress.getText().toString())){
                     Toast.makeText(getApplicationContext(),"Address cannot be empty",Toast.LENGTH_SHORT).show();
                 } else if(TextUtils.isEmpty(txtphone.getText().toString())){
                     Toast.makeText(getApplicationContext(),"Phone no cannot be empty",Toast.LENGTH_SHORT).show();
                 } else if(TextUtils.isEmpty(txtpwd.getText().toString())){
                     Toast.makeText(getApplicationContext(),"Password cannot be empty",Toast.LENGTH_SHORT).show();
                 } else if(TextUtils.isEmpty(txtconpwd.getText().toString())){
                     Toast.makeText(getApplicationContext(),"Password cannot be empty",Toast.LENGTH_SHORT).show();
                 } else {



                 if(policeStation.isIdValid(txtid.getText().toString().trim())) {
                     if (txtpwd.getText().toString().trim().equals(txtconpwd.getText().toString().trim())) {

                         dbRef= FirebaseDatabase.getInstance().getReference().child("PoliceStation");

                         policeStation.setId(txtid.getText().toString().trim());
                         policeStation.setAddress(txtaddress.getText().toString().trim());
                         policeStation.setPhone(Integer.parseInt(txtphone.getText().toString().trim()));
                         policeStation.setPwd(txtpwd.getText().toString().trim());

                         dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                             @Override
                             public void onDataChange(@NonNull DataSnapshot snapshot) {
                                 if(snapshot.hasChild(policeStation.getId())) {
                                     Toast.makeText(getApplicationContext(), "Station already exists", Toast.LENGTH_SHORT).show();
                                 }else{
                                     dbRef.child(policeStation.getId()).setValue(policeStation);
                                     Toast.makeText(getApplicationContext(),"Successfully registered",Toast.LENGTH_SHORT).show();
                                     Intent intent = new Intent(getApplicationContext(),PoliceLogin.class);
                                     startActivity(intent);
                                 }
                             }

                             @Override
                             public void onCancelled(@NonNull DatabaseError error) {

                             }
                         });
                     } else {

                         Toast.makeText(getApplicationContext(),"Confirm Password doesn't match",Toast.LENGTH_SHORT).show();
                     }
                 } else {
                     Toast.makeText(getApplicationContext(),"ID is invalid",Toast.LENGTH_SHORT).show();
                 }

                 }
             }
         });


    }
}