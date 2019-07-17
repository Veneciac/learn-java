package com.gmail.veneciacalista.ui.drama.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.veneciacalista.R;
import com.gmail.veneciacalista.firebase.response.ListBean;
import com.gmail.veneciacalista.ui.drama.ActDrama;

import java.util.List;

public class BigAdapter extends RecyclerView.Adapter<BigAdapter.MyViewHolder> {

    private List<ListBean> mDataset;
    private Activity act;

    public BigAdapter(List<ListBean> myDataset, ActDrama actDrama) {
        mDataset = myDataset;
        act = actDrama;
    }

    @Override
    public BigAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_drama, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull BigAdapter.MyViewHolder holder, int position) {
        holder.setView(mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rvDrama;
        TextView titleSection;

        MyViewHolder(View v) {
            super(v);
            rvDrama = v.findViewById(R.id.rvDramaSmall);
            titleSection = v.findViewById(R.id.dramaSection);
        }

        void setView(ListBean menu) {
            titleSection.setText(menu.getGenre());
            rvDrama.setLayoutManager(new GridLayoutManager(rvDrama.getContext(), 1, GridLayoutManager.HORIZONTAL, false));
            SmallAdapter mAdapter = new SmallAdapter(menu.getMovies(), act);
//            mAdapter.setListener(this);
            rvDrama.setAdapter(mAdapter);
            rvDrama.setNestedScrollingEnabled(false);
        }

//        @Override
//        public void onClickMenu(int position) {
//            Intent movieDetail = new Intent(rvDrama.getContext(), ActMovieDetail.class);
//            movieDetail.putExtra("movie", (Parcelable) mDataset.get(position));
//            rvDrama.getContext().startActivity(movieDetail);
//        }
    }

}
