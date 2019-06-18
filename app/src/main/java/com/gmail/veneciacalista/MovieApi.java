package com.gmail.veneciacalista;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApi {
    @GET("movie/now_playing")
    Call<MovieResponse> getMovie();
}
