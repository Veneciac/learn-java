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
        String title = getIntent().getStringExtra("title");
        String image = getIntent().getStringExtra("image");
        String overview = getIntent().getStringExtra("overview");
        String rating = getIntent().getStringExtra("rating");
        try {
            Picasso.get().load(image).into(ivMoviedetail);
        } catch (Exception e){
            e.printStackTrace();
        }
        tvMovieDTitle.setText(title);
        tvMovieDOverview.setText(overview);
    }


}
