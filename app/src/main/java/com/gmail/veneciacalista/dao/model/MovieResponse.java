package com.gmail.veneciacalista.dao.model;

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



