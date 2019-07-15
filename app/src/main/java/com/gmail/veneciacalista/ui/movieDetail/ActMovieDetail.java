package com.gmail.veneciacalista.ui.movieDetail;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.veneciacalista.R;
import com.gmail.veneciacalista.firebase.response.MoviesBean;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActMovieDetail extends AppCompatActivity {
    @BindView(R.id.ivMoviedetail)
    ImageView ivMoviedetail;

    @BindView(R.id.tvMovieDTitle)
    TextView tvMovieDTitle;

    @BindView(R.id.btnWatch)
    Button btnWatch;

    @BindView(R.id.tvMovieDOverview)
    TextView tvMovieDOverview;

    @BindView(R.id.rvSuggestionMD)
    RecyclerView rvSuggestionMD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_movie_detail);
        ButterKnife.bind(this);
        setUpAdapter();
        btnWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ActMovieDetail.this, "Sorry movies not available yet", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void setUpAdapter () {
        MoviesBean movie = new Gson().fromJson(getIntent().getStringExtra("movie"), MoviesBean.class);

        try {
            Picasso.get().load(movie.getImg_url()).into(ivMoviedetail);
        } catch (Exception e){
            e.printStackTrace();
        }
        tvMovieDTitle.setText(movie.getTitle());
        tvMovieDOverview.setText(movie.getOver_view());
    }


}
