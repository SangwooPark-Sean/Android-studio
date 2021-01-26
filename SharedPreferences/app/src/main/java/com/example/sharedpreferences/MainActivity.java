package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText et_save;
    String shared = "file";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_save = (EditText)findViewById(R.id.et_save);

        SharedPreferences sharedPreferences = getSharedPreferences(shared, 0);
        String value = sharedPreferences.getString("sangwoo", ""); // "sangwoo"key에 저장되어있는값 value에 저장
        et_save.setText(value); // value값을 et_save에 세팅

    }

    @Override
    protected void onDestroy() { //activity를 벗어났을때 실행
        super.onDestroy();

        SharedPreferences sharedPreferences = getSharedPreferences(shared, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit(); //editor 변수를 sharedPreferences에 연결
        String value = et_save.getText().toString(); // et_save에 있는 값 받아와서 value에 저장
        editor.putString("sangwoo", value); // 재실행시 불러올 value변수를 "sangwoo" 에 저장
        editor.commit(); // 이대로 저장후 마치라는 명령어
    }
}