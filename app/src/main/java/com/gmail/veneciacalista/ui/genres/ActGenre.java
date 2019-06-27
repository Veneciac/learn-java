package com.gmail.veneciacalista.ui.genres;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gmail.veneciacalista.R;

import butterknife.ButterKnife;

public class ActGenre extends AppCompatActivity {
    @Override //ambil dari parent kek super
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_genre);
        ButterKnife.bind(this);

    }


}

