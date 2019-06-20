package com.gmail.veneciacalista.ui.movie.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.veneciacalista.R;
import com.gmail.veneciacalista.dao.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Movie> mDataset;

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<Movie> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.setView(mDataset.get(position));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        TextView tvTitle, tvRating, tvOverview, tvRelease;
        ImageView ivPoster;

        MyViewHolder(View v) {
            super(v);
            tvTitle = v.findViewById(R.id.tvTitle);
            tvRating = v.findViewById(R.id.tvRating);
            tvOverview = v.findViewById(R.id.tvOverview);
            tvRelease = v.findViewById(R.id.tvRelease);
            ivPoster = v.findViewById(R.id.ivPoster);

        }

        void setView(Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvRating.setText(Float.toString(movie.getVote_average()));
            tvOverview.setText(movie.getOverview());
            tvRelease.setText(movie.getRelease_date());
            try {

                String imageUrl = "http://image.tmdb.org/t/p/original" + movie.getPoster_path();
                Picasso.get().load(imageUrl).into(ivPoster);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}