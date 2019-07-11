package com.gmail.veneciacalista.ui.drama;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.veneciacalista.R;
import com.gmail.veneciacalista.firebase.response.ListBean;
import com.gmail.veneciacalista.firebase.response.Response;
import com.gmail.veneciacalista.ui.drama.adapter.BigAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;

public class ActDrama extends AppCompatActivity {
    public List<String> movieList = new ArrayList<>();
    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    public List<ListBean> genreList = new ArrayList<>();
    private VMDrama viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_drama);
        ButterKnife.bind(this);
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(VMDrama.class);
        setUpFirebase();
        setupAdapter();
    }

    private void setupAdapter() {
        RecyclerView recyclerView = findViewById(R.id.rvDramaBig);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        BigAdapter mAdapter = new BigAdapter(genreList);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

    private void setUpFirebase() {
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        defaultKey();
        mFirebaseRemoteConfig.fetchAndActivate()
                .addOnCompleteListener(this, new OnCompleteListener<Boolean>() {
                    @Override
                    public void onComplete(@NonNull Task<Boolean> task) {
                        if (task.isSuccessful()) {
                            boolean updated = task.getResult();
                            Log.d("taggg", "Config params updated: " + updated);
                            Toast.makeText(ActDrama.this, "Fetch and activate succeeded", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ActDrama.this, "Fetch failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void defaultKey() {
        Map<String, Object> map = new HashMap<>();
        map.put("genre_list", "");
        mFirebaseRemoteConfig.setDefaults(map);
        FirebaseRemoteConfigValue m = mFirebaseRemoteConfig.getValue("genre_list");
        Response json = new Gson().fromJson(m.asString(), Response.class);

        if (json != null ) {
            Log.d("Firebase !!!!!!!!!", " " + json.getList().size());
            for (int i = 0 ; i < json.getList().size(); i++) {
                genreList.add(json.getList().get(i));
                Log.d("GENRE " + i , json.getList().get(i).getMovies().get(0).getTitle());
            }

        } else {
            Log.e("JSON ", "NULL!!!!!!");
        }
    }
}
