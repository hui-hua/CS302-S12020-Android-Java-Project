package com.example.tiesiyasuo;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class DetailsActivityAdapter extends RecyclerView.Adapter<DetailsActivityAdapter.ViewHolder> {

    private List<String> mData;
    private String name;
    private LayoutInflater mInflater;
    private Context context;
    Integer buttonWidth;

    // data is passed into the constructor
    public DetailsActivityAdapter(Context context, List<String> data) {
        this.context = context;
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detailsscreens, parent, false);

        return new ViewHolder(view);

    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(mData.get(position));
        name = mData.get(position);
        int id = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        holder.img.setImageResource(id);

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView img;
        public ViewHolder(View itemview){
            super(itemview);
            img = itemView.findViewById(R.id.screen);

        }
    }
}

