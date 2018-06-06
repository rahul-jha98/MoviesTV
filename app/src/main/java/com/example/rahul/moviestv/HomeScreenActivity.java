package com.example.rahul.moviestv;

import android.content.Context;
import android.os.AsyncTask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.rahul.moviestv.Data.TopRated;
import com.example.rahul.moviestv.NetworkUtils.NetworkCallUtils;
import com.example.rahul.moviestv.NetworkUtils.PopularMovieJsonUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class HomeScreenActivity extends AppCompatActivity{

    private Context mContext;
    private RecyclerView topRatedRecyclerView;
    private TopRatedAdapter topRatedAdapter;
    private List<TopRated> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        mContext = HomeScreenActivity.this;

        topRatedRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        movieList = new ArrayList<>();
        topRatedAdapter = new TopRatedAdapter(this, movieList);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,   false);
        topRatedRecyclerView.setLayoutManager(mLayoutManager);
        topRatedRecyclerView.setItemAnimator(new DefaultItemAnimator());

        GetTopRatedTask task1 = new GetTopRatedTask();
        task1.execute();

        topRatedRecyclerView.setAdapter(topRatedAdapter);
    }


    private class GetTopRatedTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            URL url = NetworkCallUtils.getUrlforTopRated(mContext, 1);
            String json = null;
            try {
                json = NetworkCallUtils.getResponseFromHttpUrl(mContext, url);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                movieList = PopularMovieJsonUtils.getTopMoviesStringsFromJson(mContext, json);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.v("Changed","I have changed" + movieList.size());
            topRatedAdapter.updateList(movieList);

        }
    }

}
