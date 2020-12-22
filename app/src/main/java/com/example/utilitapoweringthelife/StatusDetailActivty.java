package com.example.utilitapoweringthelife;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ModelClass.Status;

public class StatusDetailActivty  extends AppCompatActivity {

    private Status status;
    TextView textName, textUrl,textResponseCode,textResponseTime,textClass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_detail);

        textClass=findViewById(R.id.textViewClass);
        textName=findViewById(R.id.textViewName);
        textResponseCode=findViewById(R.id.textViewResponseCode);
        textResponseTime=findViewById(R.id.textViewResponseTime);
        textUrl=findViewById(R.id.textViewUrl);

//fetching and displaying data from intent
        textUrl.setText(getIntent().getStringExtra("url"));
        textResponseTime.setText(getIntent().getStringExtra("responseTime"));
        textResponseCode.setText(getIntent().getStringExtra("responseCode"));
        textName.setText(getIntent().getStringExtra("name"));
        textClass.setText(getIntent().getStringExtra("classStatus"));

    }
}
