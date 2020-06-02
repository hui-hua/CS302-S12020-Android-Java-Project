package com.example.tiesiyasuo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.consoleRecycler);


        Map<Integer, String> consolesDict = returnConsoles();
        List<String> consoles = new ArrayList<String>(consolesDict.values());
        MainActivityAdapter myAdapter = new MainActivityAdapter(this, consoles);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);


    }

    public static Map<Integer, String> returnConsoles() {
        Map<Integer, String> words = new LinkedHashMap<Integer, String>();
        words.put(1, "PC");
        words.put(2, "Switch");
        words.put(3, "PS4");
        return words;
    }






}

