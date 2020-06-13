package com.example.tiesiyasuo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Bundle extras = getIntent().getExtras();
        String searchTerm = extras.getString("search");

        TextView t1 = findViewById(R.id.searchText1);
        String temp = "Search results for \"" + searchTerm + "\"";
        t1.setText(temp);

        updateDatabase newDatabase = new updateDatabase(this);
        List<Game> gamesList = newDatabase.loadGames("All");
        List<Game> searchResults = new ArrayList<>();
        for (Game tempGame:gamesList){
            if (tempGame.getName().toLowerCase().contains(searchTerm.toLowerCase())){
                searchResults.add(tempGame);
            }
        }



        RecyclerView recyclerView = findViewById(R.id.listRecycler);
        ListActivityAdapter myAdapter  = new ListActivityAdapter(this, searchResults);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}