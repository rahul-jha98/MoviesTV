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

    final private TopRatedAdapterOnClickHandler mClickHandler;

    public TopRatedAdapter(Context context,List<TopRated> movieList, TopRatedAdapterOnClickHandler clickHandler) {
        mContext = context;
        mClickHandler = clickHandler;
        albumList = movieList;
    }



    public interface TopRatedAdapterOnClickHandler {
        void onTopRatedClick(String movieId);
    }




    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        final TextView title;
        final ImageView poster;

        public MyViewHolder(View view){
            super(view);
            poster = (ImageView) view.findViewById(R.id.iv_MoviePoster);
            title = (TextView) view.findViewById(R.id.title);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();

            String movieId = albumList.get(adapterPosition).getMovieID();

            mClickHandler.onTopRatedClick(movieId);
        }
    }







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





    public void updateList(List<TopRated> newList){
        albumList.clear();
        albumList.addAll(newList);
        this.notifyDataSetChanged();
    }

}
