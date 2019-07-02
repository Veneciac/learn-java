package com.gmail.veneciacalista.ui.drama;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.veneciacalista.R;
import com.gmail.veneciacalista.ui.drama.adapter.bigAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class ActDrama extends AppCompatActivity {
    public List<String> movieList = new ArrayList<>();

    @Override //ambil dari parent kek super
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_drama);
        ButterKnife.bind(this);
        setupAdapter();
    }

    private void setupAdapter() {
        RecyclerView recyclerView = findViewById(R.id.rvDramaBig);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        for (int i = 1; i <= 4; i++) {
            movieList.add("item  " + i );
        }
        bigAdapter mAdapter = new bigAdapter(movieList);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

}
