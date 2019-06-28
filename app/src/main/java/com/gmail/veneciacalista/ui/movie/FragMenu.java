package com.gmail.veneciacalista.ui.movie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.veneciacalista.R;
import com.gmail.veneciacalista.ui.genres.ActGenre;
import com.gmail.veneciacalista.ui.movie.adapter.AdapterMenu;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class FragMenu extends Fragment implements AdapterMenu.MenuListener {

    private final Activity act;
    private final Integer width;
    private final Integer height;
    public List<String> menuList = new ArrayList<>();

    public FragMenu(Activity act, Integer width, Integer height) {
        this.act = act;
        this.width = width;
        this.height = height;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.frag_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(act);
        setupAdapter(view);
    }


    private void setupAdapter(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.rvMenu);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        menuList.add("Horror");
        menuList.add("Comedy");
        menuList.add("Drama");
        menuList.add("Romance");
        menuList.add("Thriller");
        menuList.add("Fantasy");

        AdapterMenu mAdapter = new AdapterMenu(menuList);
        mAdapter.setListener(this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false); // biar g ngelag
    }

    @Override
    public void onClickMenu(int position) {
        switch (menuList.get(position)){
            case "Horror":
                Intent intent = new Intent(getActivity(), ActGenre.class);
                intent.putExtra("horror", "Kuntilanak Beranak");
                startActivity(intent);
                break;
            case "Comedy":
                Toast.makeText(act, "Comedy", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
