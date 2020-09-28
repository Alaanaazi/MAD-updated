package com.example.madprojecttest;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EmergencyEditPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency_edit);

        TextView contact = findViewById(R.id.textView30);
        EditText no = findViewById(R.id.editText2);

        String contact_name = "Not inserted";
        String phone_no = "Not inserted";

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            contact_name = extras.getString("contact_name");
            phone_no = extras.getString("phone_no");
        }

        contact.setText(contact_name);
        no.setText(phone_no);
    }
}
