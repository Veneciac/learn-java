package com.gmail.veneciacalista.ui.movie;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gmail.veneciacalista.R;

import butterknife.ButterKnife;

public class FragMenu extends Fragment {
    private final Activity act;
    private final String title;


    public FragMenu(Activity act, String title) {
        this.act = act;
        this.title = title;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(act);
    }


    private void setComponent(View view) {
        TextView tvText = view.findViewById(R.id.menu_title);
        tvText.setText(title);
    }
}
