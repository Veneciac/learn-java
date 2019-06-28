package com.gmail.veneciacalista.ui.movie.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.veneciacalista.R;

import java.util.List;

public class AdapterMenu extends RecyclerView.Adapter<AdapterMenu.MyViewHolder> {

    private List<String> mDataset;
    private MenuListener listener;

    public AdapterMenu(List<String> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterMenu.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMenu.MyViewHolder holder, int position) {

        holder.menuTitle.setOnClickListener(v -> {
            if (listener != null) listener.onClickMenu(position);
        });
        holder.setView(mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView menuTitle;

        MyViewHolder(View v) {
            super(v);
            menuTitle = v.findViewById(R.id.menuTitle);
        }

        void setView(String menu) {
            menuTitle.setText(menu);
        }
    }

    public void setListener(MenuListener listener) {
        this.listener = listener;
    }

    public interface MenuListener {
        void onClickMenu(int position);
    }
}
