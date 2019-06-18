package com.gmail.veneciacalista;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieResponse {
    @SerializedName("page")
    public int page;

    @SerializedName("results")
    public ArrayList<Movie> results = new ArrayList<Movie>();

    @SerializedName("total_results")
    public int total_results;

    @SerializedName("total_pages")
    public int total_pages;

    @SerializedName("dates")
    public Dates dates;
}

 class Dates {
    @SerializedName("maximum")
    public String maximum;

    @SerializedName("minimum")
    public String minimum;
}

 class Movie {
    @SerializedName("vote_count")
    private int vote_count;

    @SerializedName("id")
    private int id;

    @SerializedName("video")
    private boolean video;

    @SerializedName("vote_average")
    private float vote_average;

    @SerializedName("title")
    private String title;

    @SerializedName("popularity")
    private float popularity;

    @SerializedName("poster_path")
    private String poster_path;

    @SerializedName("original_language")
    private String original_language;

    @SerializedName("original_title")
    private String original_title;

    @SerializedName("genre_ids")
    private int[] genre_ids;

    @SerializedName("backdrop_path")
    private String backdrop_path;

    @SerializedName("adult")
    private boolean adult;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String release_date;

     public int getVote_count() {
         return vote_count;
     }

     public int getId() {
         return id;
     }

     public boolean isVideo() {
         return video;
     }

     public float getVote_average() {
         return vote_average;
     }

     public String getTitle() {
         return title;
     }

     public float getPopularity() {
         return popularity;
     }

     public String getPoster_path() {
         return poster_path;
     }

     public String getOriginal_language() {
         return original_language;
     }

     public String getOriginal_title() {
         return original_title;
     }

     public int[] getGenre_ids() {
         return genre_ids;
     }

     public String getBackdrop_path() {
         return backdrop_path;
     }

     public boolean isAdult() {
         return adult;
     }

     public String getOverview() {
         return overview;
     }

     public String getRelease_date() {
         return release_date;
     }
 }

