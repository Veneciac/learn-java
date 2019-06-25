package com.gmail.veneciacalista.ui.movie;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.gmail.veneciacalista.R;
import com.gmail.veneciacalista.api.MovieApi;
import com.gmail.veneciacalista.base.adapter.BaseAdapterPager;
import com.gmail.veneciacalista.dao.MovieDatabase;
import com.gmail.veneciacalista.dao.model.Movie;
import com.gmail.veneciacalista.dao.model.MovieResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActMovie extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.rvMovie)
    RecyclerView rvMovie;
    @BindView(R.id.bnvHome)
    BottomNavigationView bnvHome;
    @BindView(R.id.vpHome)
    ViewPager vpHome;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public static String BaseUrl = "https://api.themoviedb.org/3/";
    MovieDatabase appDb;

    @Override //ambil dari parent kek super
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_home);
        ButterKnife.bind(this);
        checkRoom();
        setupAdapter();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void checkRoom() {
        if (appDb == null) appDb = MovieDatabase.getInstance(this);
        if (appDb.getMovieDao().getAll().size() == 0) {
            getMovie();
        }
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
        return appDb.getMovieDao().getAll();
    }

    private void setupAdapter() {
//        // use a linear layout manager
//        layoutManager = new LinearLayoutManager(this);
//        rvMovie.setLayoutManager(layoutManager);
//
//        // specify an adapter (see also next example)
//        mAdapter = new AdapterMovie(addToArray());
//        rvMovie.setAdapter(mAdapter);
//        rvMovie.setNestedScrollingEnabled(false);// biar g ngelag


        // View Pager Adapter
        bnvHome.setOnNavigationItemSelectedListener(this);
        BaseAdapterPager adapterPager = new BaseAdapterPager(getSupportFragmentManager(), fragments());
        vpHome.setAdapter(adapterPager);
    }

    private List<Fragment> fragments(){
        List<Fragment> list = new ArrayList<>();
        list.add(new FragNewMovie(this, "Fragment " + 0));
        list.add(new FragNewMovie(this, "Fragment " + 1));
        list.add(new FragNewMovie(this, "Fragment " + 2));
        return list;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        boolean isChecked = menuItem.getItemId() == bnvHome.getSelectedItemId();
        menuItem.setChecked(isChecked);
        switch (menuItem.getItemId()) {
            case R.id.navigation_menu:
                vpHome.setCurrentItem(0);
                break;
            case R.id.navigation_home:
                vpHome.setCurrentItem(1);
                break;
            case R.id.navigation_inbox:
                vpHome.setCurrentItem(2);
                break;
        }
        return false;
    }
}
