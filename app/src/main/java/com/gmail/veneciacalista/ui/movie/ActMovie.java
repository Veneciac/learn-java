package com.gmail.veneciacalista.ui.movie;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.gmail.veneciacalista.R;
import com.gmail.veneciacalista.api.MovieApi;
import com.gmail.veneciacalista.dao.MovieDatabase;
import com.gmail.veneciacalista.dao.model.Movie;
import com.gmail.veneciacalista.dao.model.MovieResponse;
import com.gmail.veneciacalista.ui.movie.adapter.MyAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActMovie extends AppCompatActivity {

    private RecyclerView rvMovie;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public static String BaseUrl = "https://api.themoviedb.org/3/";
    MovieDatabase appDb = MovieDatabase.getInstance(this);

    @Override //ambil dari parent kek super
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initComponent();
        setupAdapter();
        checkRoom();

    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initComponent() {
        rvMovie = findViewById(R.id.rvMovie);
    }

    private void setupAdapter() {
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        rvMovie.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(addToArray());
        rvMovie.setAdapter(mAdapter);
        rvMovie.setNestedScrollingEnabled(false);// biar g ngelag

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void getMovie() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieApi service = retrofit.create(MovieApi.class);
        Call<MovieResponse> call = service.getMovie();

        call.enqueue(new Callback<MovieResponse>() {

            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                if (response.code() == 200) {
                    MovieResponse movieResponse = response.body();
                    assert movieResponse != null;

                    appDb.getMovieDao().delete();
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
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
            }

        });


    }

    private List<Movie> addToArray() {
        List<Movie> movieList = appDb.getMovieDao().getAll();

        return movieList;
    }

    private void checkRoom() {

        if (appDb.getMovieDao().getAll().size() == 0) {
            getMovie();
        }
    }
}
