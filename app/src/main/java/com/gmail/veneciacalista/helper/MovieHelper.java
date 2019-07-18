package com.gmail.veneciacalista.helper;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.gmail.veneciacalista.api.MovieApi;
import com.gmail.veneciacalista.dao.MovieDatabase;
import com.gmail.veneciacalista.dao.model.Movie;
import com.gmail.veneciacalista.dao.model.MovieResponse;
import com.gmail.veneciacalista.ui.movie.ActMovie;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieHelper {


    public static Integer getMovie(Activity act, Integer page){
        MovieDatabase appDb;
        appDb = MovieDatabase.getInstance(act);

        Integer pageNum = page;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ActMovie.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieApi service = retrofit.create(MovieApi.class);
        Call<MovieResponse> call = service.getMovie(ActMovie.api_key, pageNum + 1);
        Log.e("URL -> from fragment", call.request().url() + "");
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                if (response.code() == 200) {

                    MovieResponse movieResponse = response.body();
                    assert movieResponse != null;

                    int i = 0;
                    for (Movie movie : movieResponse.results) {
                        i++;
                        appDb.getMovieDao().insert(
                                new Movie(
                                        movie.getVote_count(),
                                        movie.getId(),
                                        movie.isVideo(),
                                        movie.getVote_average(),
                                        movie.getTitle(),
                                        movie.getPopularity(),
                                        movie.getPoster_path(),
                                        movie.getOriginal_language(),
                                        movie.getOriginal_title(),
                                        movie.getBackdrop_path(),
                                        movie.isAdult(),
                                        movie.getOverview(),
                                        movie.getRelease_date()
                                )
                        );
                    }
                    ActMovie.isLoading = false;
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
            }

        });
        pageNum += 1;
        return pageNum;
    }
}
