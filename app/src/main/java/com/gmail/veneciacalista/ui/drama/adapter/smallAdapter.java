package com.gmail.veneciacalista.ui.drama.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.veneciacalista.R;

import java.util.List;

public class smallAdapter extends RecyclerView.Adapter<smallAdapter.MyViewHolder> {

    private List<String> mDataset;
    public smallAdapter(List<String> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public smallAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drama, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull smallAdapter.MyViewHolder holder, int position) {
        holder.setView(mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textDrama;

        MyViewHolder(View v) {
            super(v);
            textDrama = v.findViewById(R.id.textDrama);
        }

        void setView(String title) {
            textDrama.setText(title);
        }
    }

}
