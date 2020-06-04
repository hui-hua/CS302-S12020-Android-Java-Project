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

public class ListActivityAdapter extends RecyclerView.Adapter<ListActivityAdapter.ViewHolder> {

    private List<Game> mData;
    private String name;
    private LayoutInflater mInflater;
    private Context context;
    Integer buttonWidth;

    // data is passed into the constructor
    public ListActivityAdapter(Context context, List<Game> data) {
        this.context = context;
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem2, parent, false);

        return new ViewHolder(view);

    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(mData.get(position));
        holder.tview.setText(mData.get(position).getName());
        holder.pr.setText(mData.get(position).getPrice());
        String name = mData.get(position).getName().replaceAll("\\s+", "");
        name = name.replaceAll("[-:'&]","");
        int id = context.getResources().getIdentifier(name, "drawable", context.getPackageName());

        holder.img.setImageResource(id);



//        switch (mData.get(position).getConsole()) {
//            case "Switch":
//                holder.img.setImageResource(R.drawable.nintendoswitch);
//                break;
//            case "PC":
//                holder.img.setImageResource(R.drawable.pc);
//                break;
//            case "PS4":
//                holder.img.setImageResource(R.drawable.ps4);
//                break;
//        }

        //setText(animal);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tview;
        public TextView pr;
        public ImageView img;
        public ViewHolder(View itemview){
            super(itemview);
            tview = itemView.findViewById(R.id.text);
            pr = itemview.findViewById(R.id.price);
            img = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    Game temp = (Game) view.getTag();
                    Toast.makeText(view.getContext(), temp.getName(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("GameName", temp.getName());
                    intent.putExtra("GamePrice", temp.getPrice());
                    intent.putExtra("GameDescription", temp.getDescription());
                    context.startActivity(intent);
                }
            });

        }
    }
}
