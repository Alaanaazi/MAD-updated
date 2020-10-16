package com.example.madprojecttest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CivilianLogin extends AppCompatActivity {

    EditText uname,pwd;
    DatabaseReference dbref;
    Civilian civilian;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        civilian=new Civilian();

        uname=findViewById(R.id.inputUsername);
        pwd=findViewById(R.id.inputpwd);
        Button btnlogin=findViewById(R.id.login);

        Button btnsignup=findViewById(R.id.signup);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), CivilianRegister.class);
                startActivity(intent);

            }
        });



        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(uname.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Username cannot be empty",Toast.LENGTH_SHORT).show();
                } else if(TextUtils.isEmpty(pwd.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Password cannot be empty",Toast.LENGTH_SHORT).show();
                } else {



                final String un=uname.getText().toString().trim();
                final String pw=pwd.getText().toString().trim();
                civilian.setNIC(uname.getText().toString().trim());


                dbref= FirebaseDatabase.getInstance().getReference().child("Civilian");
                dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if((snapshot.hasChild(un))&&(snapshot.child(un).child("pwd").getValue().toString().equals(pw))){

                           // Toast.makeText(getApplicationContext(),un,Toast.LENGTH_SHORT).show();
                            SessionManagement sessionManagement=new SessionManagement(CivilianLogin.this);
                            sessionManagement.saveSession(civilian);
                            Intent intent = new Intent(getApplicationContext(), CivilianDashboardActivity.class);
                            startActivity(intent);
                        }else{
                            AlertDialog.Builder alertDialog=new AlertDialog.Builder(getApplicationContext());
                            alertDialog.setMessage("Invalid NIC or Password").setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            });

                         //   AlertDialog dialog=alertDialog.create();
                           // dialog.show();

                            Toast.makeText(getApplicationContext(),"Invalid NIC or Password",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                }

            }
        });

    }

}