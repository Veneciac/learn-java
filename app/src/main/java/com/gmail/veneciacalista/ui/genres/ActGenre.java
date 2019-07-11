package com.gmail.veneciacalista.ui.genres;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.veneciacalista.R;
import com.gmail.veneciacalista.ui.genres.adapter.rvAdapter;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

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
        displayBottomSheet();
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


    private void displayBottomSheet() {
        FrameLayout flBottomSheet = findViewById(R.id.fLBottomSheet);
//        flBottomSheet.setVisibility(View.VISIBLE);
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(flBottomSheet);

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        bottomSheetBehavior.setPeekHeight(340);

        bottomSheetBehavior.setHideable(true);

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                if (bottomSheetBehavior.getState() == bottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });
    }
}

