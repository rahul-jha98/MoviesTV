package com.example.rahul.moviestv.Data;

public class TopRated {
    private String name;
    private final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";
    private String imagePath;


    public TopRated(){

    }

    public TopRated(String name, String imagePath){
        this.name = name;
        this.imagePath = imagePath;
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

    public String getImageURl() {
        return IMAGE_BASE_URL + imagePath;
    }
}
