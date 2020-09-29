package com.example.madprojecttest;

import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class CivilianRegister extends AppCompatActivity {

    EditText txtname,txtnic,txtemail,txtphone,txtpwd,txtconpwd;
    Button btnregister;
    DatabaseReference dbRef;
    Civilian civilian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_civilian_register);

        txtname=findViewById(R.id.inputCivilianName);
        txtnic=findViewById(R.id.inputCivilianNIC);
        txtemail=findViewById(R.id.inputCivilianMail);
        txtphone=findViewById(R.id.inputCivilianphone);
        txtpwd=findViewById(R.id.inputCivilianpwd);
        txtconpwd=findViewById(R.id.inputCivilianConfirmpwd);

        btnregister=findViewById(R.id.CivilianRegister);

        civilian=new Civilian();

   /*     private void clearControls(){
            txtID.setText("");
            txtName.setText("");
            txtAdd.setText("");
            txtconNo.setText("");
        }
     */
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtpwd.getText().toString().trim().equals(txtconpwd.getText().toString().trim())){

                    dbRef= FirebaseDatabase.getInstance().getReference().child("Civilian");

                    civilian.setName(txtname.getText().toString().trim());
                    civilian.setNIC(txtnic.getText().toString().trim());
                    civilian.setEmail(txtemail.getText().toString().trim());
                    civilian.setPhone(Integer.parseInt(txtphone.getText().toString().trim()));
                    civilian.setPwd(txtpwd.getText().toString().trim());

                    dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(civilian.getNIC())){
                                Toast.makeText(getApplicationContext(),"NIC already exists",Toast.LENGTH_SHORT).show();
                            }else{
                                dbRef.child(civilian.getNIC()).setValue(civilian);
                                Toast.makeText(getApplicationContext(),"Successfully registered",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });



                }else{
                    Toast.makeText(getApplicationContext(),"Confirm Password doesn't match",Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
}