package com.gmail.veneciacalista.ui.drama.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.veneciacalista.R;

import java.util.ArrayList;
import java.util.List;

public class bigAdapter  extends RecyclerView.Adapter<bigAdapter.MyViewHolder> {

    private List<String> mDataset;
    public bigAdapter(List<String> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public bigAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_drama, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull bigAdapter.MyViewHolder holder, int position) {
        holder.setView(mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rvDrama;
//        TextView t
        MyViewHolder(View v) {
            super(v);
            rvDrama = v.findViewById(R.id.rvDramaSmall);
        }

        void setView(String menu) {
            List<String> itemList = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                itemList.add("item    " + i);
            }
            rvDrama.setLayoutManager(new GridLayoutManager(rvDrama.getContext(), 1, GridLayoutManager.HORIZONTAL, false));
            smallAdapter mAdapter = new smallAdapter(itemList);
            rvDrama.setAdapter(mAdapter);
            rvDrama.setNestedScrollingEnabled(false);
        }
    }

}
