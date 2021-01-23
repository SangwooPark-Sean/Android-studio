package com.example.seult;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void writeLog(View view) {
        Log.v(TAG, "이것은 verbose 로그입니다.");
        Log.d(TAG, "이것은 debug 로그입니다.");
        Log.i(TAG, "이것은 info 로그입니다.");
        Log.w(TAG, "이것은 warning 로그입니다.");
        Log.e(TAG, "이것은 error 로그입니다.");

    }
}
