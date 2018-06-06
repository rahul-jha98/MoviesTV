package com.example.rahul.moviestv;

import android.content.Context;
import android.content.Intent;
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


public class HomeScreenActivity extends AppCompatActivity implements TopRatedAdapter.TopRatedAdapterOnClickHandler{

    private Context mContext;
    private RecyclerView topRatedRecyclerView;
    private TopRatedAdapter topRatedAdapter;
    private List<TopRated> movieList;

    GetTopRatedTask task1;

    private boolean loading;
    private int pageNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        mContext = HomeScreenActivity.this;

        topRatedRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        movieList = new ArrayList<>();
        topRatedAdapter = new TopRatedAdapter(this,movieList, this);

        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,   false);
        topRatedRecyclerView.setLayoutManager(mLayoutManager);
        topRatedRecyclerView.setItemAnimator(new DefaultItemAnimator());


        pageNo = 1;

        task1 = new GetTopRatedTask();
        task1.execute(pageNo);

        topRatedRecyclerView.setAdapter(topRatedAdapter);


        loading = true;


        topRatedRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                if(dx > 0) //check for scroll down
                {
                    int visibleItemCount = mLayoutManager.getChildCount();
                    int totalItemCount = mLayoutManager.getItemCount();
                    int pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (loading)
                    {
                        if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount-5)
                        {
                            loading = false;
                            Log.v("...", "Last Item Wow !");
                            task1 = new GetTopRatedTask();
                            task1.execute(++pageNo);
                        }
                    }
                }
            }
        });
    }













    @Override
    public void onTopRatedClick(String movieId) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("MovieId", movieId);
        startActivity(intent);
    }


    private class GetTopRatedTask extends AsyncTask<Integer, Void, Void>{

        @Override
        protected Void doInBackground(Integer... pages) {
            URL url = NetworkCallUtils.getUrlforTopRated(mContext, pages[0]);
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
            if(movieList == null)
                return;
            Log.v("Changed","I have changed" + movieList.size());
            topRatedAdapter.updateList(movieList);
            loading = true;
        }
    }

}
