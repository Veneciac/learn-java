package com.gmail.veneciacalista.ui.movie;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.veneciacalista.R;
import com.gmail.veneciacalista.dao.MovieDatabase;
import com.gmail.veneciacalista.ui.movie.adapter.AdapterMovie;

import butterknife.ButterKnife;

public class FragNewMovie extends Fragment {

    private final Activity act;
    private final String number;

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
        recyclerView.setLayoutManager(new LinearLayoutManager(act));
        appDb = MovieDatabase.getInstance(act);
        AdapterMovie mAdapter = new AdapterMovie(appDb.getMovieDao().getAll());

        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false); // biar g ngelag
    }
}
