package com.example.tiesiyasuo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class DetailsActivityAdapter extends SliderViewAdapter<DetailsActivityAdapter.SliderAdapterVH> {

    private List<String> mData;
    private Context context;

    public DetailsActivityAdapter(Context context, List<String> data) {
        this.context = context;
        this.mData = data;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detailsscreens, parent, false);

        return new SliderAdapterVH(view);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {
        viewHolder.itemView.setTag(mData.get(position));
        String name = mData.get(position);
        int id = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        new loadImage(context, id, viewHolder.img);
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

