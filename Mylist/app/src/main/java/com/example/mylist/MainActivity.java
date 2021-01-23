package com.example.mylist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mylist = (ListView)findViewById(R.id.mylist);

        List<String> data = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);

        mylist.setAdapter(adapter);

        data.add("내가만든 리스트1");
        data.add("내가만든 리스트2");
        data.add("내가만든 리스트3");
        data.add("내가만든 리스트4");
        data.add("내가만든 리스트5");
        adapter.notifyDataSetChanged();

    }
}