package com.example.rahul.moviestv.NetworkUtils;

import android.content.Context;
import android.util.Log;

import com.example.rahul.moviestv.Data.TopRated;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PopularMovieJsonUtils {

    public static List<TopRated> getTopMoviesStringsFromJson(Context context, String listJsonStr)
        throws JSONException {

        if(listJsonStr.isEmpty() || listJsonStr==null){
            return null;
        }
        List<TopRated> parsedTopMovies;

        JSONObject listJson = new JSONObject(listJsonStr);

        JSONArray listArray = listJson.getJSONArray("results");

        parsedTopMovies = new ArrayList<>();

        for(int i = 0; i < 20; i++) {
            JSONObject movieObject = listArray.getJSONObject(i);
            TopRated movie = new TopRated(movieObject.getString("title"), movieObject.getString("poster_path"));
            Log.d("Got", movie.getName());
            parsedTopMovies.add(movie);
        }

        return parsedTopMovies;
    }
}
