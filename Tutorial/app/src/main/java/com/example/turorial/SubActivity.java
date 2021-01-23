package com.example.turorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    private TextView sub_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        sub_id = findViewById(R.id.sub_id);

        Intent intent = getIntent();
        String str = intent.getStringExtra( "str");

        sub_id.setText(str);

    }
}