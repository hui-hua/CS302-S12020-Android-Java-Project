package com.example.tiesiyasuo;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListActivity extends AppCompatActivity {

    List<Game> gamelist = new ArrayList<>();
    ListActivityAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Bundle extras = getIntent().getExtras();
        assert extras != null;
        String console = extras.getString("sendConsole");
        this.setTitle(console);


        RecyclerView recyclerView = findViewById(R.id.listRecycler);

        // load games for chosen console
        updateDatabase temp = new updateDatabase(this);
        gamelist = temp.loadGames(console);

        // set up ListActivityAdapter for recyclerview
        myAdapter  = new ListActivityAdapter(this, gamelist);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);
    }

    public void sortItems(Integer ord){
        Comparator<Game> compare = new Comparator<Game>() {
            @Override
            public int compare(Game game, Game t1) {
                return 0;
            }
        };
        switch (ord) {
            case 0:
                break;
            case 1:
                compare = new Comparator<Game>() {
                    @Override
                    public int compare(Game g1, Game g2) {
                        return g1.getName().compareTo(g2.getName());
                    }
                };
                break;
            case 2:
                compare = new Comparator<Game>() {
                    @Override
                    public int compare(Game g1, Game g2) {
                        return g2.getName().compareTo(g1.getName());
                    }
                };
                break;
            case 3:
                compare = new Comparator<Game>() {
                    @Override
                    public int compare(Game g1, Game g2) {
                        Float price1 = g1.getPriceFloat();
                        Float price2 = g2.getPriceFloat();
                        return price1.compareTo(price2);
                    }
                };
                break;
            case 4:
                compare = new Comparator<Game>() {
                    @Override
                    public int compare(Game g1, Game g2) {
                        Float price1 = g1.getPriceFloat();
                        Float price2 = g2.getPriceFloat();
                        return price2.compareTo(price1);
                    }
                };
                break;
        }
        Collections.sort(gamelist, compare);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int temp = 0;
        switch (item.getItemId()) {
            // back button
            case android.R.id.home:
                finish();
                return true;
            // sort choices
            case R.id.one:
                temp = 1;
                break;
            case R.id.two:
                temp = 2;
                break;
            case R.id.three:
                temp = 3;
                break;
            case R.id.four:
                temp = 4;
                break;
        }
        sortItems(temp);
        myAdapter.notifyDataSetChanged();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.popup_menu, menu);
        return true;
    }
}