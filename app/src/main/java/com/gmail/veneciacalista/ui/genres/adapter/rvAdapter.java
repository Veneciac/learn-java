package com.gmail.veneciacalista.ui.genres.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.veneciacalista.R;

import java.util.List;

public class rvAdapter extends RecyclerView.Adapter<rvAdapter.MyViewHolder> {

    private List<String> mDataset;

    public rvAdapter(List<String> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public rvAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horror, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull rvAdapter.MyViewHolder holder, int position) {

        holder.setView(mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textHorror;

        MyViewHolder(View v) {
            super(v);
            textHorror = v.findViewById(R.id.textHorror);
        }

        void setView(String menu) {
            textHorror.setText(menu);
        }
    }
}
