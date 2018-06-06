package com.example.rahul.moviestv;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.rahul.moviestv.Data.TopRated;

import java.util.List;

public class TopRatedAdapter extends RecyclerView.Adapter<TopRatedAdapter.MyViewHolder>{

    private Context mContext;
    private List<TopRated> albumList;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.top_rated_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TopRated movie = albumList.get(position);
        holder.title.setText(movie.getName());

        Glide.with(mContext).load(movie.getImageURl())
                .into(holder.poster);
    }

    @Override
    public int getItemCount() {
        Log.v("SIze", ""+albumList.size());
        return albumList.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView poster;
        public MyViewHolder(View view){
            super(view);
            poster = (ImageView) view.findViewById(R.id.iv_MoviePoster);
            title = (TextView) view.findViewById(R.id.title);
        }
    }

    public TopRatedAdapter(Context context, List<TopRated> moviesList) {
        mContext = context;
        albumList = moviesList;
    }

    public void updateList(List<TopRated> newList){
        albumList.clear();
        albumList.addAll(newList);
        this.notifyDataSetChanged();
    }

}
