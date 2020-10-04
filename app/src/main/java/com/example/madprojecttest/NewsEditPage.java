package com.example.madprojecttest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class NewsEditPage extends AppCompatActivity {
    ImageButton menu;
    TextView title;
    EditText txtdescription;
    TextView txttitle;
    Button btnUpdate,btnSolve;
    Hotline hotline;
    DatabaseReference upRef,delRef,dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);

        title = findViewById(R.id.ToolbarTitle);
        title.setText("Alert");

        menu = findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),View_Alerts.class);
                startActivity(intent);
            }
        });

        txttitle = findViewById(R.id.alertTitle);
        txtdescription = findViewById(R.id.editText);

        btnSolve=findViewById(R.id.solve);
        btnUpdate=findViewById(R.id.alertupdate);

        String title_name = "Not inserted";
        String description_name = "Not inserted";

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            title_name = extras.getString("title_name");
            description_name = extras.getString("description_name");
        }

        txttitle.setText(title_name);
        txtdescription.setText(description_name);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upRef= FirebaseDatabase.getInstance().getReference().child("Alert").child(txttitle.getText().toString());
                upRef.child("description").setValue(txtdescription.getText().toString().trim());

                Toast.makeText(getApplicationContext(), "Successfully updated", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),View_Alerts.class);
                startActivity(intent);

            }
        });

        btnSolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                delRef=FirebaseDatabase.getInstance().getReference().child("Alert").child(txttitle.getText().toString());
                delRef.removeValue();

                Toast.makeText(getApplicationContext(), "Successfully deleted", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),View_Alerts.class);
                startActivity(intent);

            }
        });
    }
}
