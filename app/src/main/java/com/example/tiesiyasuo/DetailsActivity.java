package com.example.tiesiyasuo;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        TextView pricebutton = findViewById(R.id.detailsButtonPrice);
        ImageView background = findViewById(R.id.detailsbackground);

        // load name, price, details and views into layout
        Bundle extras = getIntent().getExtras();
        String gamePrice = extras.getString("GamePrice");
        pricebutton.setText(gamePrice);

        String gameName = extras.getString("GameName");
        TextView name = findViewById(R.id.gamename);
        name.setText(gameName);

        TextView views = findViewById(R.id.viewsText);
        updateDatabase tempo = new updateDatabase(this);
        Game game = tempo.load(gameName, extras.getString("Console"));


        views.setText(game.getVisited().toString());
        TextView consText = findViewById(R.id.consoleText);
        consText.setText(extras.getString("Console"));

        String gameDeets = extras.getString("GameDescription");
        TextView deets = findViewById(R.id.deets);
        deets.setText(gameDeets);

        assert gameName != null;
        String temp = gameName.replaceAll("\\s+", "");
        temp = temp.replaceAll("[-:'&]", "");
        System.out.println(temp);
        // setup the icon for game
        int id = this.getResources().getIdentifier(temp, "drawable", this.getPackageName());
        ImageView img = findViewById(R.id.gameIcon);
        new loadImage(this, id, img);

        // background image
        temp = temp.toLowerCase();
        int backid = this.getResources().getIdentifier(temp + "3", "drawable", this.getPackageName());
        background.setImageResource(backid);
        img.setImageResource(id);

        List<String> screens = new ArrayList<String>();

        // list of images for the screenshots
        screens.add(temp + "1");
        screens.add(temp + "2");
        screens.add(temp + "3");

        // setup sliderview
        SliderView sliderView = findViewById(R.id.imageSlider);
        sliderView.setSliderAdapter(new DetailsActivityAdapter(this, screens));
        sliderView.setIndicatorAnimation(IndicatorAnimations.SLIDE);
        sliderView.setSliderTransformAnimation(SliderAnimations.ZOOMOUTTRANSFORMATION);


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