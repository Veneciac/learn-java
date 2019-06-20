package com.gmail.veneciacalista.api;

import com.gmail.veneciacalista.dao.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApi {
    @GET("movie/now_playing?api_key=d94b3ad2730dee34212c8f50fb87a861&page=1")
    Call<MovieResponse> getMovie();
}
