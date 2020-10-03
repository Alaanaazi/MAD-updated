package com.example.madprojecttest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class CivilianLogin extends AppCompatActivity {

    EditText uname,pwd;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    DatabaseReference dbref;
    public static final String SESSION_KEY=" ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        sharedPreferences=getSharedPreferences(SESSION_KEY, Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.apply();

        uname=findViewById(R.id.inputUsername);
        pwd=findViewById(R.id.inputpwd);
        Button btnlogin=findViewById(R.id.login);




        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String un=uname.getText().toString().trim();
                final String pw=pwd.getText().toString().trim();

                dbref= FirebaseDatabase.getInstance().getReference().child("Civilian");
                dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if((snapshot.hasChild(un))&&(snapshot.child(un).child("pwd").getValue().toString().equals(pw))){

                            editor.putString(SESSION_KEY,un);
                            editor.commit();
                            Intent intent = new Intent(getApplicationContext(), CivilianDashboardActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(getApplicationContext(),snapshot.child(un).child("pwd").getValue().toString(),Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

    }
}