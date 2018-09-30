package com.polygalov.jsontesttask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.polygalov.jsontesttask.adapter.EntrancesAdapter;
import com.polygalov.jsontesttask.model.Entrance;

import java.util.ArrayList;
import java.util.List;

public class EntranceActivity extends AppCompatActivity {

    private final String TAG = "Entrance_Activity";

    private RecyclerView mRecyclerView;
    private EntrancesAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    List<Entrance> mEntranceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);

        mRecyclerView = findViewById(R.id.entrances_recycler);

        linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(false);

        getEntrancesFA();

    }

    public void getEntrancesFA() {

        Intent intent = getIntent();

        String addressName = intent.getStringExtra("addressName");
        String houseID = intent.getStringExtra("houseID");

        mEntranceList = new ArrayList<>();
        mEntranceList = (List<Entrance>) intent.getSerializableExtra("entrances");
        mRecyclerView.setAdapter(new EntrancesAdapter(getApplicationContext(), mEntranceList));

    }

}

