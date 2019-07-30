package com.gmail.veneciacalista.ui.drama;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gmail.veneciacalista.R;
import com.gmail.veneciacalista.firebase.response.MoviesBean;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Picasso;

public class FragMovieDetail extends BottomSheetDialogFragment {
    private final Activity act;
    private MoviesBean movie;

    public FragMovieDetail(Activity act, MoviesBean movie) {
        this.act = act;
        this.movie = movie;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.bottom_sheet, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(FragMovieDetail.STYLE_NO_FRAME,0);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnWatch = view.findViewById(R.id.btnWatch);
        btnWatch.setOnClickListener(v -> Toast.makeText(act, "Sorry movies not available yet", Toast.LENGTH_SHORT).show());
        setComponent(view);
    }

    private void setComponent(View view) {
        ImageView ivMoviedetail = view.findViewById(R.id.ivMoviedetail);
        TextView tvMovieDTitle = view.findViewById(R.id.tvMovieDTitle);
        TextView tvMovieDOverview = view.findViewById(R.id.tvMovieDOverview);

        try {
            Picasso.get().load(movie.getImg_url()).into(ivMoviedetail);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tvMovieDTitle.setText(movie.getTitle());
        tvMovieDOverview.setText(movie.getOver_view());
    }


}
