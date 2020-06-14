package com.example.tiesiyasuo;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


// this saves, updates and loads the game data using sharedpreferences
public class updateDatabase {
    SharedPreferences mPrefs;

    public updateDatabase(Context context) {
        mPrefs = context.getSharedPreferences("gameDatabase", MODE_PRIVATE);
    }

    public void updateDatabaseGame(Game game, Context context) {
        mPrefs = context.getSharedPreferences("gameDatabase", MODE_PRIVATE);
        save(game);
    }

    public void update(List<Game> gamelist, Context context){

        mPrefs = context.getSharedPreferences("gameDatabase", MODE_PRIVATE);
        for (Game temp:gamelist){
            save(temp);
        }

    }

    public int getlength(){
        return mPrefs.getAll().size();
    }

    public void save(Game game){
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson tempGson = new Gson();
        String json = tempGson.toJson(game);
        prefsEditor.putString(game.getName() + " " + game.getConsole(), json);
        prefsEditor.apply();
    }

    public Game load(String name, String console){
        Gson gson = new Gson();
        String json = mPrefs.getString(name + " " + console, "");
        return gson.fromJson(json, Game.class);
    }

    public List<Game> loadGames(String console){
        List<String> prefs = new ArrayList(mPrefs.getAll().values());
        List<Game> games = new ArrayList<>();
        for (String temp: prefs){
            Gson gson = new Gson();
            Game game = gson.fromJson(temp, Game.class);
            games.add(game);
            System.out.println(game.getName());
        }

        if (console.equals("All")){
            return games;
        }
        List<Game> consoleGames = new ArrayList<>();
        for (Game temp: games){
            if (temp.getConsole().equals(console)){
                consoleGames.add(temp);
            }
        }
        return consoleGames;
    }


}
