package com.gmail.veneciacalista.api;

import com.gmail.veneciacalista.dao.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {
    @GET("movie/now_playing?")
    Call<MovieResponse> getMovie( @Query("api_key") String api_key, @Query("page") Integer page);
}
