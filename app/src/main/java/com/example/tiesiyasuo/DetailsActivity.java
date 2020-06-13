package com.example.tiesiyasuo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView pricebutton = findViewById(R.id.detailsButtonPrice);

        ImageView background = findViewById(R.id.detailsbackground);
//        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
        Bundle extras = getIntent().getExtras();
        String gamePrice = extras.getString("GamePrice");
        pricebutton.setText(gamePrice);
        String gameName = extras.getString("GameName");
        TextView name = findViewById(R.id.gamename);
        name.setText(gameName);
        TextView views = findViewById(R.id.viewsText);
        updateDatabase tempo = new updateDatabase(this);
        Game game = tempo.load(gameName, extras.getString("Console"));

//        System.out.println("views is " + getIntent().getIntExtra("Views",0));
        views.setText(game.getVisited().toString());
        TextView consText = findViewById(R.id.consoleText);
        consText.setText(extras.getString("Console"));
        String gameDeets = extras.getString("GameDescription");
        TextView deets = findViewById(R.id.deets);
        deets.setText(gameDeets);
        String temp = gameName.replaceAll("\\s+", "");
        temp = temp.replaceAll("[-:'&]","");
        System.out.println(temp);
        int id = this.getResources().getIdentifier(temp, "drawable", this.getPackageName());
        ImageView img = findViewById(R.id.gameIcon);
//        img.setImageResource(id);
        new loadImage(this, id, img);
        temp = temp.toLowerCase();
        int backid = this.getResources().getIdentifier(temp + "3", "drawable", this.getPackageName());
        background.setImageResource(backid);
        img.setImageResource(id);
//        Glide.with(this)
//                .load(id)
//                .into(img);
        String test = this.getResources().getResourceName(id);
        List<String> screens = new ArrayList<String>();

        screens.add(temp + "1");
        screens.add(temp + "2");
        screens.add(temp + "3");
        System.out.println(screens.get(0));
        System.out.println(screens.get(1));

//        DetailsActivityAdapter myAdapter = new DetailsActivityAdapter(this, screens);
        SliderView sliderView = findViewById(R.id.imageSlider);
        sliderView.setSliderAdapter(new DetailsActivityAdapter(this, screens));
//        sliderView.startAutoCycle();
//        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
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