package com.example.seulcode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView testLabel;
    private ImageView testImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testLabel = findViewById(R.id.test_label);
        testImage = findViewById(R.id.test_image);
    }



    public void changeTextType(View view){
        testLabel.setText("변경완료");
        testLabel.setTextColor(ContextCompat.getColor(this, R.color.mycolor));
        testLabel.setTextSize(24);
        testImage.setImageResource(R.drawable.sit2);
    }
    public void resetTextType(View view){
        testLabel.setText("리셋완료");
        testLabel.setTextColor(Color.parseColor("#00FF00"));
        testLabel.setTextSize(35);
        testImage.setImageResource(R.drawable.sit1);
    }
}