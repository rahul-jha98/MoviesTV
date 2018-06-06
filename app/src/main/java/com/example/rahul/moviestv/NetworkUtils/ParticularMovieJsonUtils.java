package com.example.rahul.moviestv.NetworkUtils;

import android.content.Context;

import com.example.rahul.moviestv.Data.ParticularMovie;

import org.json.JSONException;
import org.json.JSONObject;



public class ParticularMovieJsonUtils {


    private static final String TMDB_TITLE = "title";
    private static final String TMDB_POSTER_IMG = "poster_path";
    private static final String TMDB_MOVIE_ID = "id";
    private static final String TMDB_BACKDROP_PATH = "backdrop_path";

    public static ParticularMovie getTopMoviesStringsFromJson(Context context, String listJsonStr)
            throws JSONException {

        if(listJsonStr == null || listJsonStr.isEmpty()){
            return null;
        }
        ParticularMovie movie = new ParticularMovie();

        JSONObject movieJson = new JSONObject(listJsonStr);






        String title;
        String movieid;
        String imagePath;
        String backdropImagePath;


        title = movieJson.getString(TMDB_TITLE);
        movieid = movieJson.getString(TMDB_MOVIE_ID);
        imagePath = movieJson.getString(TMDB_POSTER_IMG);
        backdropImagePath = movieJson.getString(TMDB_BACKDROP_PATH);


        movie.setName(title);
        movie.setImagePath(imagePath);
        movie.setMovieID(movieid);
        movie.setBackdropImagepath(backdropImagePath);



        return movie;
    }
}



