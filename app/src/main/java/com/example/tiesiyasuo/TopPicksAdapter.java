package com.example.tiesiyasuo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class TopPicksAdapter extends RecyclerView.Adapter<TopPicksAdapter.ViewHolder> {

    private List<Game> mData;
    private Context context;

    // data is passed into the constructor
    public TopPicksAdapter(Context context, List<Game> data) {
        this.context = context;
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(mData.get(position));
        String name = mData.get(position).getName().replaceAll("\\s+", "");
        name = name.replaceAll("[-:'&]","");
        System.out.println(name);
        int id = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        new loadImage(context, id, holder.img);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView img;
        public ViewHolder(View itemview){
            super(itemview);
            img = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    Game temp = (Game) view.getTag();
                    temp.wasVisited();
                    updateDatabase test = new updateDatabase(context);
                    test.updateDatabaseGame(temp, context);
                    Game testingload = test.load(temp.getName(), temp.getConsole());

                    Toast.makeText(view.getContext(), testingload.getVisited().toString(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("GameName", temp.getName());
                    intent.putExtra("GamePrice", temp.getPrice());
                    intent.putExtra("GameDescription", temp.getDescription());
                    intent.putExtra("Console", temp.getConsole());
                    context.startActivity(intent);
                }
            });

        }
    }
}
