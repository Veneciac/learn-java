package com.gmail.veneciacalista.ui.movie;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.veneciacalista.R;
import com.gmail.veneciacalista.api.MovieApi;
import com.gmail.veneciacalista.dao.MovieDatabase;
import com.gmail.veneciacalista.dao.model.Movie;
import com.gmail.veneciacalista.dao.model.MovieResponse;
import com.gmail.veneciacalista.ui.movie.adapter.AdapterMovie;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragNewMovie extends Fragment {

    private final Activity act;
    private final String number;
    private Integer page = 1;
    RecyclerView rvMovie;
    MovieDatabase appDb;

    public FragNewMovie(Activity act, String number) {
        this.number = number;
        this.act = act;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.frag_new_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(act);
        setupAdapter(view);
        setComponent(view);
    }

    private void setComponent(View view) {
        TextView tvText = view.findViewById(R.id.tvText);
        tvText.setText("size movie array " + appDb.getMovieDao().getAll().size());
    }

    private void setupAdapter(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.rvMovie);
        LinearLayoutManager layoutManager = new LinearLayoutManager(act);
        recyclerView.setLayoutManager(layoutManager);
        appDb = MovieDatabase.getInstance(act);
        AdapterMovie mAdapter = new AdapterMovie(appDb.getMovieDao().getAll());

        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false); // biar g ngelag

        RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if ( !ActMovie.isLoading && page <= 47 ) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= page) {
                        ActMovie.isLoading = true;
                        getMovie();
                    }
                }
            }
        };

        recyclerView.addOnScrollListener(recyclerViewOnScrollListener);
    }

    private void getMovie() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ActMovie.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieApi service = retrofit.create(MovieApi.class);
        Call<MovieResponse> call = service.getMovie(ActMovie.api_key, page + 1);
        Log.e("URL -> from fragment", call.request().url() + "");
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                if (response.code() == 200) {
                    Toast.makeText(act, "Fetch page " + page +"--" + page + 1, Toast.LENGTH_SHORT).show();

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
                    page = page + 1;
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
            }

        });
    }

}
