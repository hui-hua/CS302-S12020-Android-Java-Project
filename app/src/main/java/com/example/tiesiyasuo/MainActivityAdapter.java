package com.example.tiesiyasuo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.ViewHolder> {

    private List<String> mData;
    private LayoutInflater mInflater;
    private Context context;

    // data is passed into the constructor
    public MainActivityAdapter(Context context, List<String> data) {
        this.context = context;
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.consolesitem2, parent, false);

        return new ViewHolder(view);

    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(mData.get(position));
        holder.tview.setText(mData.get(position));
        switch (mData.get(position)) {
            case "Switch":
                holder.img.setImageResource(R.drawable.nintendoswitch);
                break;
            case "PC":
                holder.img.setImageResource(R.drawable.pc);
                break;
            case "PS4":
                holder.img.setImageResource(R.drawable.ps4);
                break;
        }

        //setText(animal);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tview;
        public ImageView img;
        public ViewHolder(View itemview){
            super(itemview);
            tview = itemView.findViewById(R.id.text);
            img = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    String temp = (String) view.getTag();
                    Toast.makeText(view.getContext(), temp, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, ListActivity.class);
                    intent.putExtra("sendConsole", temp);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);
                }
            });

        }
    }
}
