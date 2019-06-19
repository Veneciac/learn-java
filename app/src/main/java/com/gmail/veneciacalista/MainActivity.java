package com.gmail.veneciacalista;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ListView listView;

//    ArrayList<Movie> arrayList = new ArrayList<>();
    ArrayList<String> arrayList = new ArrayList<>();

    private MovieDatabase movieDatabase;
    private Movie movie;

    public static String BaseUrl = "https://api.themoviedb.org/3/";
    private TextView textViewResult;
    MovieDatabase appDb = MovieDatabase.getInstance(this);

    @Override //ambil dari parent kek super
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);

        listView = (ListView)findViewById(R.id.listview);

//        arrayList.add("hahaha");

        boolean roomStatus = checkRoom();

//        arrayList.add("-->" + roomStatus);
        if (roomStatus) {
            getMovie();
        }
        addToArray();

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

//                    String stringBuilder = "--->" + movieResponse.results.get(0).getTitle();

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
//                        arrayList.add("test" + i);

                    }
//                    ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
//                    listView.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                textViewResult.setText(t.getMessage());
            }

        });


    }

    private void addToArray() {
        List<Movie> movieList = appDb.getMovieDao().getAll();
        String stringBuilder = "--->" + movieList.size();

//        textViewResult.setText(stringBuilder);
//        arrayList.add(stringBuilder);

        for (Movie movie : movieList) {
            arrayList.add(movie.getTitle());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
    }

    private boolean checkRoom() {
        if (appDb.getMovieDao().getAll().size() != 0) {
            return false;
        } else {
            return true;
        }
    }
}
