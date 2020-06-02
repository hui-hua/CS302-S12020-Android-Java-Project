package com.example.tiesiyasuo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] sortlist = { "Alphabetical (A to Z)", "Alphabetical (Z to A)", "Price (Lowest to Highest)", "Price (Highest to Lowest)" };
    List<Game>[] filtlist = new List[]{new ArrayList<Game>()};
    ListActivityAdapter myAdapter = new ListActivityAdapter(this, filtlist[0]);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle extras = getIntent().getExtras();
        String console = extras.getString("sendConsole");
        this.setTitle(console);
        RecyclerView recyclerView = findViewById(R.id.listRecycler);
        List<Game> gamelist = new ArrayList<Game>();
        gamelist.add(new Game("Red Dead Redemption 2", "PC", 90));
        gamelist.add(new Game("XCOM 2", "PC", 80));
        gamelist.add(new Game("Counter-Strike: Global Offensive", "PC", 10, "Counter-Strike: Global Offensive (CS: GO) expands upon the team-based action gameplay that it pioneered when it was launched 19 years ago. CS: GO features new maps, characters, weapons, and game modes, and delivers updated versions of the classic CS content (de_dust2, etc.)."));
        gamelist.add(new Game("Payday 2", "PC", 5));
        gamelist.add(new Game("The Witcher 3", "PC", 10));
        gamelist.add(new Game("Tom Clancy's Rainbow Six Seige", "PC", 25));
        gamelist.add(new Game("Fallout 4", "PC", 75));
        gamelist.add(new Game("Far Cry 5", "PC", 70));
        gamelist.add(new Game("Age of Empires 2", "PC", 15));
        gamelist.add(new Game("The Witcher 3", "PS4", 85));
        gamelist.add(new Game("Uncharted 4", "PS4", 90));
        gamelist.add(new Game("God of War", "PS4", 85));
        gamelist.add(new Game("Horizon Zero Dawn", "PS4", 95));
        gamelist.add(new Game("Fallout 4", "PS4", 70));
        gamelist.add(new Game("Call of Duty: Modern Warfare", "PS4", 105));
        gamelist.add(new Game("Until Dawn", "PS4", 90));
        gamelist.add(new Game("Nioh", "PS4", 75));
        gamelist.add(new Game("The Last of Us", "PS4", 90));
        gamelist.add(new Game("Persona 5", "PS4", 90));
        gamelist.add(new Game("The Witcher 3", "Switch", 105));
        gamelist.add(new Game("Animal Crossing: New Horizons", "Switch", 90));
        gamelist.add(new Game("Pokemon: Sword", "Switch", 100));
        gamelist.add(new Game("Pokemon: Shield", "Switch", 100));
        gamelist.add(new Game("Zenoblade Chronicles", "Switch", 90));
        gamelist.add(new Game("Super Mario Odyssey", "Switch", 95));
        gamelist.add(new Game("Mario Kart 8 Deluxe", "Switch", 95));
        gamelist.add(new Game("Octopath Traveller", "Switch", 90));
        gamelist.add(new Game( "Mario & Sonic at the Olympic Games", "Switch", 90));


        for (int i = 0; i < gamelist.size(); i++){
            if (gamelist.get(i).getConsole().equals(console)){
                filtlist[0].add(gamelist.get(i));
            }
        }

        //ListActivityAdapter myAdapter = new ListActivityAdapter(this, filtlist[0]);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);



        Spinner spin = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sortlist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, sortlist[position],Toast.LENGTH_SHORT).show();
        filtlist[0] = sortitems(filtlist[0], position);
        myAdapter.notifyDataSetChanged();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    public List<Game> sortitems(List<Game> filtlist, Integer ord){
        for(int i=0;i<filtlist.size();i++){

            for(int j=i+1;j<filtlist.size();j++){
                if (ord == 0){
                    if(filtlist.get(i).getName().compareTo(filtlist.get(j).getName())>0){

                        Game temp = filtlist.get(i);
                        filtlist.set(i, filtlist.get(j));
                        filtlist.set(j,temp);

                    }
                }
                else if (ord == 1){
                    if(filtlist.get(i).getName().compareTo(filtlist.get(j).getName())<0){

                        Game temp = filtlist.get(i);
                        filtlist.set(i, filtlist.get(j));
                        filtlist.set(j,temp);

                    }
                }
                else if (ord == 2){
                    if(filtlist.get(i).getPriceInt().compareTo(filtlist.get(j).getPriceInt())>0){

                        Game temp = filtlist.get(i);
                        filtlist.set(i, filtlist.get(j));
                        filtlist.set(j,temp);

                    }
                }
                else if (ord == 3){
                    if(filtlist.get(i).getPriceInt().compareTo(filtlist.get(j).getPriceInt())<0){

                        Game temp = filtlist.get(i);
                        filtlist.set(i, filtlist.get(j));
                        filtlist.set(j,temp);

                    }
                }

            }
        }
        return filtlist;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}