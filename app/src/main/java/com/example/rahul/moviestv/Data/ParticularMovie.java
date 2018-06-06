package com.example.rahul.moviestv.Data;

public class ParticularMovie {

    private String name;
    private final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";
    private String imagePath;
    private String movieID;
    private String backdropImagepath;


    public ParticularMovie(){

    }


    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setBackdropImagepath(String backdropImagepath) {
        this.backdropImagepath = backdropImagepath;
    }

    public String getImageURl() {
        return IMAGE_BASE_URL + imagePath;
    }

    public String getBackdropImageUrl() {
        return IMAGE_BASE_URL + backdropImagepath;
    }

    public void setMovieID(String movieID){
        this.movieID = movieID;
    }

    public String getMovieID() {
        return movieID;
    }
}
