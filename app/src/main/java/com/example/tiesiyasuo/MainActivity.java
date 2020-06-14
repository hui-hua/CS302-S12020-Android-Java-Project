package com.example.tiesiyasuo;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends AppCompatActivity {
    TopPicksAdapter tempAdapter;
    List<Game> topPicks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.consoleRecycler);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));


        //set up recyclerview for consoles
        List<String> consoles = returnConsoles();
        MainActivityAdapter myAdapter = new MainActivityAdapter(this, consoles);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);

        //create sharedpreference file if it doesnt exist
        updateDatabase test = new updateDatabase(this);
        if (test.getlength() == 0) {
            test.update(generateGames(), this);
            System.out.println("database created");
        }

        //top picks
        setUpTopPicks();

        //search function
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

        Collections.sort(games, compare);

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

    public List<Game> generateGames() {
        List<Game> gamelist = new ArrayList<>();
        gamelist.add(new Game("The Witcher 3", "Switch", 104.99f, "As war rages on throughout the Northern Realms, you take on the greatest contract of your life — tracking down the Child of Prophecy, a living weapon that can alter the shape of the world."));
        gamelist.add(new Game("Animal Crossing: New Horizons", "Switch", 99.99f, "Animal Crossing is a social simulation video game series developed and published by Nintendo and created by Katsuya Eguchi and Hisashi Nogami. "));
        gamelist.add(new Game("Pokemon: Sword", "Switch", 109.99f, "Pokémon Sword and Pokémon Shield are 2019 role-playing video games developed by Game Freak and published by The Pokémon Company and Nintendo for the Nintendo Switch."));
        gamelist.add(new Game("Pokemon: Shield", "Switch", 109.99f, "Pokémon Sword and Pokémon Shield are 2019 role-playing video games developed by Game Freak and published by The Pokémon Company and Nintendo for the Nintendo Switch."));
        gamelist.add(new Game("Xenoblade Chronicles", "Switch", 99.99f, "MONOLITHSOFT’s epic Xenoblade Chronicles game, which originally launched in 2012 on the Wii system, is destined to be reborn on Nintendo Switch as Xenoblade Chronicles: Definitive Edition."));
        gamelist.add(new Game("Super Mario Odyssey", "Switch", 94.99f, "Super Mario Odyssey is a platform game developed and published by Nintendo for the Nintendo Switch on October 27, 2017"));
        gamelist.add(new Game("Mario Kart 8 Deluxe", "Switch", 94.99f, "Mario Kart 8 Deluxe is an expanded version of the Wii U racing game, Mario Kart 8, for the Nintendo Switch, Released on April 28, 2017."));
        gamelist.add(new Game("Octopath Traveller", "Switch", 99.99f, "Octopath Traveler is a turn-based role-playing video game developed by Square Enix, in collaboration with Acquire. The game was released for the Nintendo Switch in July 2018, and was released for Microsoft Windows in June 2019, as well as for Stadia in April 2020."));
        gamelist.add(new Game("Mario & Sonic at the Olympic Games", "Switch", 99.99f, "Mario & Sonic at the Olympic Games Tokyo 2020 is a 2019 sports video game based on the 2020 Summer Olympics. It is the sixth game in the Mario & Sonic series, a crossover between Nintendo's Super Mario and Sega's Sonic the Hedgehog franchises, and the first since the Rio 2016 Olympic Games edition."));
        gamelist.add(new Game("GRIS", "Switch", 99.99f, "Gris is a platform-adventure game by Spanish indie developer Nomada Studio and published by Devolver Digital. The game was released for Nintendo Switch, macOS, and Microsoft Windows in December 2018, for iOS in August 2019, for PlayStation 4 in November 2019, and for Android in April 2020. "));
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
        gamelist.add(new Game("Uncharted 4", "PS4", 89.99f, "Uncharted 4: A Thief's End is a 2016 action-adventure game developed by Naughty Dog and published by Sony Computer Entertainment. It is the fourth main entry in the Uncharted series."));
        gamelist.add(new Game("God of War", "PS4", 84.99f, "It is a new beginning for Kratos. Living as a man, outside the shadow of the gods, he seeks solitude in the unfamiliar lands of Norse mythology. With new purpose and his son Atreus at his side, Kratos must fight for survival as powerful forces threaten to disrupt the new life he has created. A New Beginning — His vengeance against the gods of Olympus far behind him, Kratos now lives as a man in the lands of Norse Gods and monsters."));
        gamelist.add(new Game("Horizon Zero Dawn", "PS4", 94.99f, "Horizon Zero Dawn is an action role-playing game developed by Guerrilla Games and published by Sony Interactive Entertainment. The plot follows Aloy, a hunter in a world overrun by machines, who sets out to uncover her past."));
        gamelist.add(new Game("Fallout 4", "PS4", 79.99f, "Fallout 4 is an action role-playing game developed by Bethesda Game Studios and published by Bethesda Softworks. It is the fourth main game in the Fallout series and was released worldwide on November 10, 2015, for Microsoft Windows, PlayStation 4 and Xbox One. "));
        gamelist.add(new Game("Call of Duty: Modern Warfare", "PS4", 104.99f, "Call of Duty: Modern Warfare is a 2019 first-person shooter video game developed by Infinity Ward and published by Activision."));
        gamelist.add(new Game("Until Dawn", "PS4", 99.99f, "Until Dawn is a 2015 interactive drama survival horror video game developed by Supermassive Games and published by Sony Computer Entertainment for the PlayStation 4. Players assume control of eight young adults who have to survive on Blackwood Mountain when their lives are threatened. "));
        gamelist.add(new Game("Nioh", "PS4", 74.99f, "Ancient demons; feudal lands Take up your sword and travel to Japan’s blood-bathed Sengoku period – an era ravaged by warring states and dark, malevolent forces – and cut a violent path through the land as the masterless samurai, William. Cross blades in brutal hand-to-hand combat, wielding swords, axes, spears and even war hammers against foes both human and demon."));
        gamelist.add(new Game("The Last of Us", "PS4", 99.99f, "The Last of Us is a 2013 action-adventure game developed by Naughty Dog and published by Sony Computer Entertainment. Players control Joel, a smuggler tasked with escorting a teenage girl, Ellie, across a post-apocalyptic United States. The Last of Us is played from a third-person perspective. Players use firearms and improvised weapons, and can use stealth to defend against hostile humans and cannibalistic creatures infected by a mutated strain of the Cordyceps fungus."));
        gamelist.add(new Game("Persona 5", "PS4", 99.99f, "Persona 5 is a role-playing video game developed by Atlus. The game is chronologically the sixth installment in the Persona series, which is part of the larger Megami Tensei franchise. It was released for the PlayStation 3 and PlayStation 4 in Japan in September 2016 and worldwide in April 2017. "));
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

