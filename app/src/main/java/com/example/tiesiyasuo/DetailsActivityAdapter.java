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

import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class DetailsActivityAdapter extends SliderViewAdapter<DetailsActivityAdapter.SliderAdapterVH> {

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
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detailsscreens, parent, false);

        return new SliderAdapterVH(view);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {
        viewHolder.itemView.setTag(mData.get(position));
        name = mData.get(position);
        int id = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        viewHolder.img.setImageResource(id);
    }

    @Override
    public int getCount() {
        return mData.size();
    }


    public class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView img;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.screen);
            this.itemView = itemView;
        }
    }
}

