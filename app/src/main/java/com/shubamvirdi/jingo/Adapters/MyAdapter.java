package com.shubamvirdi.jingo.Adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shubamvirdi.jingo.DataModels.JingoModel;
import com.shubamvirdi.jingo.DetailMusic;
import com.shubamvirdi.jingo.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    // ADAPTER for settig data to recycler view

    private Context context;
    private List<JingoModel> list;

    public MyAdapter(Context context, List<JingoModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.music_row,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        holder.Title.setText(list.get(position).getSong());
        holder.artists.setText(list.get(position).getArtists());
        Picasso.get().load(list.get(position).getCover_image()).into(holder.Icon);
        holder.url = list.get(position).getUrl();
        holder.image = list.get(position).getCover_image();
        holder.title = list.get(position).getSong();
        holder.artist = list.get(position).getArtists();

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder{

        TextView Title;
        TextView artists;
        ImageView Icon;
        String url,image,title,artist;

        View v;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            v = itemView;
            Title = v.findViewById(R.id.songname);
            artists = v.findViewById(R.id.artistname);
            Icon = v.findViewById(R.id.music_label);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i =new Intent(v.getContext(),DetailMusic.class);
                    i.putExtra("name",title);
                    i.putExtra("artists",artist);
                    i.putExtra("image",image);
                    i.putExtra("url",url);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    v.getContext().startActivity(i);
                }
            });
        }

    }
}
