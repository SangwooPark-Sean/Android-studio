package com.example.turorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText ET_id;
    private Button btn_move;
    private String str;
    ImageView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //어플 처음 실행했을때 실행되는 영역
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ET_id = findViewById(R.id.ET_id);
        btn_move = findViewById(R.id.btn_move);


        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str = ET_id.getText().toString();
                ET_id.setText("");
                Intent intent = new Intent(MainActivity.this, SubActivity.class);

                intent.putExtra("str", str);
                startActivity(intent);
            }
        });

        test = (ImageView) findViewById(R.id.test);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "이미지를 클릭하였습니다.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}