package com.gmail.veneciacalista.ui.genres;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.veneciacalista.R;
import com.gmail.veneciacalista.ui.genres.adapter.rvAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class ActGenre extends AppCompatActivity {
    public List<String> movieList = new ArrayList<>();

    @Override //ambil dari parent kek super
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_genre);
        ButterKnife.bind(this);
//        onGetData();
        setupAdapter();
    }

    private void onGetData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String genre = bundle.getString("horror");
            Toast.makeText(this, genre, Toast.LENGTH_SHORT).show();
        }
    }

    private void setupAdapter() {
        RecyclerView recyclerView = findViewById(R.id.rvHorror);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 5, GridLayoutManager.HORIZONTAL, false));
        for (int i = 1; i <= 50; i++) {
            movieList.add("item  " + i );
        }
        rvAdapter mAdapter = new rvAdapter(movieList);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

}

