package com.gmail.veneciacalista.ui.drama.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.veneciacalista.R;
import com.gmail.veneciacalista.firebase.response.MoviesBean;
import com.gmail.veneciacalista.ui.movieDetail.ActMovieDetail;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SmallAdapter extends RecyclerView.Adapter<SmallAdapter.MyViewHolder> {

    private List<MoviesBean> mDataset;

    public SmallAdapter(List<MoviesBean> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public SmallAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drama, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SmallAdapter.MyViewHolder holder, int position) {
        holder.setView(mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textDrama;
        CardView cvDrama;
        ImageView ivMovie;

        MyViewHolder(View v) {
            super(v);
            textDrama = v.findViewById(R.id.textDrama);
            cvDrama = v.findViewById(R.id.cvDrama);
            ivMovie = v.findViewById(R.id.ivMovie);

        }

        void setView(MoviesBean movies) {

            textDrama.setText(movies.getTitle());
            cvDrama.setCardBackgroundColor(Color.parseColor(movies.getBg_color()));

            try {

                Picasso.get().load(movies.getImg_url()).into(ivMovie);
            } catch (Exception e){
                e.printStackTrace();
            }
            ivMovie.setClickable(true);
            ivMovie.setOnClickListener(v -> {

                Intent movieDetail = new Intent(v.getContext(), ActMovieDetail.class);
                movieDetail.putExtra("title", movies.getTitle());
                movieDetail.putExtra("image", movies.getImg_url());
                movieDetail.putExtra("overview", movies.getOver_view());
                movieDetail.putExtra("rating", movies.getRating());
                v.getContext().startActivity(movieDetail);


            });
        }
    }

}
