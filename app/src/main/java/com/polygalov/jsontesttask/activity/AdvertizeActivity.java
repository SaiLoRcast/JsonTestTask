package com.polygalov.jsontesttask.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.polygalov.jsontesttask.R;
import com.polygalov.jsontesttask.adapter.AdvertizeAdapter;
import com.polygalov.jsontesttask.model.Advertize;

import java.util.ArrayList;
import java.util.List;

public class AdvertizeActivity extends AppCompatActivity {

    private final String TAG = "Advertize_Activity";

    private RecyclerView mRecyclerView;
    private AdvertizeAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    List<Advertize> mAdvertizeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertize);

        mRecyclerView = findViewById(R.id.advertizes_recycler);

        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setHasFixedSize(false);

        getEntrancesFA();
        mAdapter = new AdvertizeAdapter(getApplicationContext(), mAdvertizeList);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void getEntrancesFA() {

        Intent intent = getIntent();

        String entranceNumber = intent.getStringExtra("entranceNumber");

        mAdvertizeList = new ArrayList<>();
        mAdvertizeList = (List<Advertize>) intent.getSerializableExtra("advertizes");


    }

}
