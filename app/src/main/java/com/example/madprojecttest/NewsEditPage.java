package com.example.madprojecttest;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NewsEditPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);

        TextView title = findViewById(R.id.textView10);
        EditText description = findViewById(R.id.editText);

        String title_name = "Not inserted";
        String description_name = "Not inserted";

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            title_name = extras.getString("title_name");
            description_name = extras.getString("description_name");
        }

        title.setText(title_name);
        description.setText(description_name);
    }
}
