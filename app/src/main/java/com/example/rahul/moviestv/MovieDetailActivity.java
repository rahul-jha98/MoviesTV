package com.example.rahul.moviestv;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rahul.moviestv.Data.ParticularMovie;
import com.example.rahul.moviestv.NetworkUtils.NetworkCallUtils;
import com.example.rahul.moviestv.NetworkUtils.ParticularMovieJsonUtils;
import com.example.rahul.moviestv.NetworkUtils.PopularMovieJsonUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

public class MovieDetailActivity extends AppCompatActivity {

    private Context mContext;
    private ParticularMovie movie;
    private String Url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        mContext = MovieDetailActivity.this;

        String movieId = getIntent().getStringExtra("MovieId");

        if(Url!=null){
            Glide.with(mContext).load(movie.getImageURl())
                    .into((ImageView) findViewById(R.id.iv_MoviePoster));
        }

        GetDetailTask task = new GetDetailTask();
        task.execute(movieId);

    }

    private class GetDetailTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... stringData) {
            URL url = NetworkCallUtils.getUrlforParticularMovie(stringData[0]);
            String json = null;

            try {
                json = NetworkCallUtils.getResponseFromHttpUrl(mContext, url);
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {
                movie = ParticularMovieJsonUtils.getTopMoviesStringsFromJson(mContext, json);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            changeViews();
        }

        public void changeViews(){
            if(movie == null)
                return;
            TextView title = (TextView) findViewById(R.id.tv_Title);

            ImageView poster = (ImageView) findViewById(R.id.iv_MoviePoster);
            ImageView backdrop = (ImageView) findViewById(R.id.iv_backDropPoster);

            title.setText(movie.getName());
            Url = movie.getImageURl();
            Glide.with(mContext).load(movie.getImageURl())
                    .into(poster);

            Glide.with(mContext).load(movie.getBackdropImageUrl())
                    .into(backdrop);
        }
    }
}
