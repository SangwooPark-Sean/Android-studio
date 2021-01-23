package com.example.infren;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = (TextView)findViewById(R.id.textView);

        //센서 관리자 객체 불러오기
        SensorManager manager = (SensorManager)getSystemService(SENSOR_SERVICE);

        //단말기에있는 센서리스트 받기
        List<Sensor> sensor_list = manager.getSensorList(Sensor.TYPE_ALL);

        text1.setText("");

        for(Sensor sensor : sensor_list){
            text1.append("센서 이름: "+ sensor.getName() + "\n");
            text1.append("센서 종류: "+ sensor.getType() + "\n\n");
        }


    }
}