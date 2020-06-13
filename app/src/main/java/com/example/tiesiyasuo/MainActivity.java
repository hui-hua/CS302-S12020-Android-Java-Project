package com.example.tiesiyasuo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    TopPicksAdapter tempAdapter;
    List<Game> topPicks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.consoleRecycler);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        List<String> consoles = returnConsoles();
        MainActivityAdapter myAdapter = new MainActivityAdapter(this, consoles);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);

        updateDatabase test = new updateDatabase(this);
        if (test.getlength() == 0){
            test.update(generateGames(), this);
            System.out.println("database created");
        }

        setUpTopPicks();
        final EditText searchBox = findViewById(R.id.searchTerm);
        searchBox.setInputType(InputType.TYPE_CLASS_TEXT);
        ImageView searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String searchTerm = searchBox.getText().toString();
                Toast.makeText(MainActivity.this, searchTerm, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("search", searchTerm);
                startActivity(intent);
            }
        });

        searchBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean done = false;
                if (i == EditorInfo.IME_ACTION_DONE) {
                    String searchTerm = searchBox.getText().toString();
                    Toast.makeText(MainActivity.this, searchTerm, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                    intent.putExtra("search", searchTerm);
                    done = true;
                    startActivity(intent);

                }
                return done;
            }
        });


    }

    public List<Game> getTopPicks(){
        updateDatabase test = new updateDatabase(this);
        List<Game> games = test.loadGames("All");
        Comparator<Game> compare = new Comparator<Game>() {
            @Override
            public int compare(Game g1, Game g2) {
                return g2.getVisited().compareTo(g1.getVisited());
            }
        };

        for (Game gg:games) {
            System.out.println(gg.getName());
        }
        System.out.println("-------------------------------");
        Collections.sort(games, compare);
        for (Game gg:games) {
            System.out.println(gg.getName());
        }
        List<Game> topPicks = new ArrayList<>();
        topPicks.add(games.get(0));
        topPicks.add(games.get(1));
        topPicks.add(games.get(2));
        topPicks.add(games.get(3));
        return topPicks;
    }

    public List<String> returnConsoles(){
        List<String> consoles = new ArrayList<>();
        consoles.add("PC");
        consoles.add("Switch");
        consoles.add("PS4");
        return consoles;
    }

    public List<Game> generateGames(){
        List<Game> gamelist = new ArrayList<>();
        gamelist.add(new Game("The Witcher 3", "Switch", 104.99f, "As war rages on throughout the Northern Realms, you take on the greatest contract of your life — tracking down the Child of Prophecy, a living weapon that can alter the shape of the world."));
        gamelist.add(new Game("Animal Crossing: New Horizons", "Switch", 99.99f));
        gamelist.add(new Game("Pokemon: Sword", "Switch", 109.99f));
        gamelist.add(new Game("Pokemon: Shield", "Switch", 109.99f));
        gamelist.add(new Game("Xenoblade Chronicles", "Switch", 99.99f));
        gamelist.add(new Game("Super Mario Odyssey", "Switch", 94.99f));
        gamelist.add(new Game("Mario Kart 8 Deluxe", "Switch", 94.99f));
        gamelist.add(new Game("Octopath Traveller", "Switch", 99.99f));
        gamelist.add(new Game("Mario & Sonic at the Olympic Games", "Switch", 99.99f));
        gamelist.add(new Game("GRIS", "Switch", 99.99f));
        gamelist.add(new Game("Red Dead Redemption 2", "PC", 89.99f, "Winner of over 175 Game of the Year Awards and recipient of over 250 perfect scores, RDR2 is the epic tale of outlaw Arthur Morgan and the infamous Van der Linde gang, on the run across America at the dawn of the modern age. Also includes access to the shared living world of Red Dead Online."));
        gamelist.add(new Game("XCOM 2", "PC", 79.99f, "XCOM 2 is the sequel to XCOM: Enemy Unknown, the 2012 award-winning strategy game of the year. Earth has changed and is now under alien rule. Facing impossible odds you must rebuild XCOM, and ignite a global resistance to reclaim our world and save humanity."));
        gamelist.add(new Game("Counter-Strike: Global Offensive", "PC", 9.99f, "Counter-Strike: Global Offensive (CS: GO) expands upon the team-based action gameplay that it pioneered when it was launched 19 years ago. CS: GO features new maps, characters, weapons, and game modes, and delivers updated versions of the classic CS content (de_dust2, etc.)."));
        gamelist.add(new Game("Payday 2", "PC", 4.99f, "PAYDAY 2 is an action-packed, four-player co-op shooter that once again lets gamers don the masks of the original PAYDAY crew - Dallas, Hoxton, Wolf and Chains - as they descend on Washington DC for an epic crime spree."));
        gamelist.add(new Game("The Witcher 3", "PC", 9.99f, "As war rages on throughout the Northern Realms, you take on the greatest contract of your life — tracking down the Child of Prophecy, a living weapon that can alter the shape of the world."));
        gamelist.add(new Game("Tom Clancy's Rainbow Six Seige", "PC", 24.99f, "Tom Clancy's Rainbow Six Siege is the latest installment of the acclaimed first-person shooter franchise developed by the renowned Ubisoft Montreal studio."));
        gamelist.add(new Game("Fallout 4", "PC", 74.99f, "Bethesda Game Studios, the award-winning creators of Fallout 3 and The Elder Scrolls V: Skyrim, welcome you to the world of Fallout 4 – their most ambitious game ever, and the next generation of open-world gaming."));
        gamelist.add(new Game("Far Cry 5", "PC", 69.99f, "Welcome to Hope County, Montana, home to a fanatical doomsday cult known as Eden’s Gate. Stand up to cult leader Joseph Seed & his siblings, the Heralds, to spark the fires of resistance & liberate the besieged community."));
        gamelist.add(new Game("Age of Empires II: Definitive Edition", "PC", 14.99f, "Age of Empires II: Definitive Edition celebrates the 20th anniversary of one of the most popular strategy games ever with stunning 4K Ultra HD graphics, a new and fully remastered soundtrack, and brand-new content, “The Last Khans” with 3 new campaigns and 4 new civilizations."));
        gamelist.add(new Game("Tekken 7", "PC", 69.99f, "Discover the epic conclusion of the long-time clan warfare between members of the Mishima family. Powered by Unreal Engine 4, the legendary fighting game franchise fights back with stunning story-driven cinematic battles and intense duels that can be enjoyed with friends and rivals."));
        gamelist.add(new Game("The Witcher 3", "PS4", 84.99f, "As war rages on throughout the Northern Realms, you take on the greatest contract of your life — tracking down the Child of Prophecy, a living weapon that can alter the shape of the world."));
        gamelist.add(new Game("Uncharted 4", "PS4", 89.99f));
        gamelist.add(new Game("God of War", "PS4", 84.99f));
        gamelist.add(new Game("Horizon Zero Dawn", "PS4", 94.99f));
        gamelist.add(new Game("Fallout 4", "PS4", 79.99f));
        gamelist.add(new Game("Call of Duty: Modern Warfare", "PS4", 104.99f));
        gamelist.add(new Game("Until Dawn", "PS4", 99.99f));
        gamelist.add(new Game("Nioh", "PS4", 74.99f));
        gamelist.add(new Game("The Last of Us", "PS4", 99.99f));
        gamelist.add(new Game("Persona 5", "PS4", 99.99f));
        return gamelist;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.refresh, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
        case R.id.refreshButton:
            setUpTopPicks();
            break;
        case R.id.searchButton:
            Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
            CardView card = findViewById(R.id.searchCardview);
            if (card.getVisibility() == View.VISIBLE){
                card.setVisibility(View.GONE);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
            }
            else if (card.getVisibility() == View.GONE){
                card.setVisibility(View.VISIBLE);
                EditText temp = findViewById(R.id.searchTerm);
                temp.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(temp, InputMethodManager.SHOW_IMPLICIT);
            }



        }
        return super.onOptionsItemSelected(item);
    }

    public void setUpTopPicks(){
        topPicks = getTopPicks();
        RecyclerView topPicksRe = findViewById(R.id.topPicksRecycler);
        tempAdapter  = new TopPicksAdapter(this, topPicks);
        LinearLayoutManager templayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);//, GridLayoutManager.HORIZONTAL, false);

        topPicksRe.setLayoutManager(templayoutManager);
        topPicksRe.setAdapter(tempAdapter);
        tempAdapter.notifyDataSetChanged();
    }
}

