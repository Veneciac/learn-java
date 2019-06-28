package com.gmail.veneciacalista.ui.genres;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gmail.veneciacalista.R;

import butterknife.ButterKnife;

public class ActGenre extends AppCompatActivity {
    @Override //ambil dari parent kek super
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_genre);
        ButterKnife.bind(this);
        onGetData();
    }

    private void onGetData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String genre = bundle.getString("horror");
            Toast.makeText(this, genre, Toast.LENGTH_SHORT).show();
        }
    }


}

