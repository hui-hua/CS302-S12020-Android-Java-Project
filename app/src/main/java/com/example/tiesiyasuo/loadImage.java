package com.example.tiesiyasuo;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class loadImage {
    // method to load image into imageview
    public loadImage(Context context, Integer resID, ImageView img){
        Glide.with(context)
                .load(resID)
                .into(img);
    }
}
