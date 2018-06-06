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

    private static final String TMDB_RESULTS = "results";

    private static final String TMDB_TITLE = "title";
    private static final String TMDB_POSTER_IMG = "poster_path";
    private static final String TMDB_MOVIE_ID = "id";

    public static List<TopRated> getTopMoviesStringsFromJson(Context context, String listJsonStr)
        throws JSONException {

        if(listJsonStr==null || listJsonStr.isEmpty()){
            return null;
        }
        List<TopRated> parsedTopMovies;

        JSONObject listJson = new JSONObject(listJsonStr);

        JSONArray listArray = listJson.getJSONArray(TMDB_RESULTS);

        parsedTopMovies = new ArrayList<>();


        JSONObject movieObject;
        TopRated movie;

        String title;
        String movieid;
        String imagePath;

        for(int i = 0; i < 20; i++) {
            movieObject = listArray.getJSONObject(i);
            movie = new TopRated();

            title = movieObject.getString(TMDB_TITLE);
            movieid = movieObject.getString(TMDB_MOVIE_ID);
            imagePath = movieObject.getString(TMDB_POSTER_IMG);

            movie.setName(title);
            movie.setImagePath(imagePath);
            movie.setMovieID(movieid);

            parsedTopMovies.add(movie);
        }

        return parsedTopMovies;
    }
}
