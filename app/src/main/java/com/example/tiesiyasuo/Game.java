package com.example.tiesiyasuo;


public class Game{
    String name;
    String console;
    Float price;
    String ID;
    String Descrip;
    Integer Visited;
    static int count = 0;
    public Game(String name, String console, Float price, String description){
        count++;
        String temp = Integer.toString(count);
        while (temp.length() < 5){
            temp = "0" + temp;
        }

        this.name = name;
        this.console = console;
        this.price = (float) price;
        this.ID = temp;
        this.Descrip = description;
        this.Visited = 0;
    }

    public Game(String name, String console, Float price){
        count++;
        String temp = Integer.toString(count);
        while (temp.length() < 5){
            temp = "0" + temp;
        }

        this.name = name;
        this.console = console;
        this.price = (float) price;
        this.ID = temp;
        this.Descrip = "...";
        this.Visited = 0;
    }

    public String getName(){
        return this.name;
    }

    public String getConsole(){
        return this.console;
    }

    public String getPrice(){
        return "$" + this.price;
    }

    public Float getPriceFloat(){
        return this.price;
    }
    public String getID(){
        return this.ID;
    }

    public String getDescription(){
        return this.Descrip;
    }

    public void wasVisited(){
        this.Visited ++;
    }

    public Integer getVisited(){
        if (this.Visited != null){
            return this.Visited;
        }
        return 0;
    }

//    @Override
//    public int compareTo(Game game) {
//        return this.getName().compareTo(game.getName());
//    }
}
