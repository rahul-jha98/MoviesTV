package com.example.rahul.moviestv.NetworkUtils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkCallUtils {

    private static final String TAG = NetworkCallUtils.class.getSimpleName();


    private static final String APPID1 = "17a9e2faba4b24afe4bbf42451dd32b0";


    private static final String TOP_RATED_MOVIE_URL = "https://api.themoviedb.org/3/movie/top_rated";



    private static final String APPID_PARAM = "api_key";
    private static final String LANGUAGE_PARAM = "language";
    private static final String PAGE_PARAM = "page";

    public static URL getUrlforTopRated(Context context, int pageNo){

        Uri topRatedQueryUri = Uri.parse(TOP_RATED_MOVIE_URL).buildUpon()
                .appendQueryParameter(APPID_PARAM, APPID1)
                .appendQueryParameter(LANGUAGE_PARAM, "en-US")
                .appendQueryParameter(PAGE_PARAM, Integer.toString(pageNo))
                .build();

        try {
            URL topRatedQueryUrl = new URL(topRatedQueryUri.toString());
            Log.v(TAG, "URL: " + topRatedQueryUrl);
            return topRatedQueryUrl;
        } catch (MalformedURLException e){
            e.printStackTrace();
            return null;
        }
    }

    public static String getResponseFromHttpUrl(Context context, URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            String response = null;

            if(hasInput) {
                response = scanner.next();
            }
            scanner.close();
            return response;
        } finally {
            urlConnection.disconnect();
        }
    }

}
