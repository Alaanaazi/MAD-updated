package com.example.madprojecttest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EmergencyEditPage extends AppCompatActivity {

    EditText txtno;
    Button btnUpdate,btnDelete;
    Hotline hotline;
    DatabaseReference upRef,delRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency_edit);

        final TextView contact = findViewById(R.id.textView30);
        txtno = findViewById(R.id.editText2);
        btnUpdate=findViewById(R.id.hotlineupdate);
        btnDelete=findViewById(R.id.hotlinedelete);

        String contact_name = "Null";
        Integer phone_no=0;

        Bundle extras = getIntent().getExtras();
        if(extras != null){

            contact_name = extras.getString("name");
            phone_no = extras.getInt("no");
        }

        contact.setText(contact_name);
        txtno.setText(String.valueOf(phone_no));


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upRef= FirebaseDatabase.getInstance().getReference().child("Hotline").child(contact.getText().toString());
                upRef.child("no").setValue(txtno.getText().toString().trim());

                Toast.makeText(getApplicationContext(), "Successfully updated", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),Emergency.class);
                startActivity(intent);

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delRef=FirebaseDatabase.getInstance().getReference().child("Hotline").child(contact.getText().toString());
                delRef.removeValue();

                Toast.makeText(getApplicationContext(), "Successfully deleted", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),Emergency.class);
                startActivity(intent);

            }
        });



    }
}
