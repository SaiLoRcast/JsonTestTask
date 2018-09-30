package com.polygalov.jsontesttask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.polygalov.jsontesttask.adapter.NewAdapter;
import com.polygalov.jsontesttask.api.ApiUtil;
import com.polygalov.jsontesttask.model.Entrance;
import com.polygalov.jsontesttask.model.Example;
import com.polygalov.jsontesttask.model.House;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "Main_Activity";

    private RecyclerView mRecyclerView;
    LinearLayoutManager linearLayoutManager;

    private Button showList;
    private Button deleteList;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler);

        linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(false);

        showList = findViewById(R.id.button_show_list);
        showList.setOnClickListener(this);

        deleteList = findViewById(R.id.button_delete_list);
        deleteList.setOnClickListener(this);
    }

    public void getInfoFromApi() {

        ApiUtil.getServiceClass().getFullJson().enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.isSuccessful()) {

                    Gson gson = new Gson();
                    String successResponse = gson.toJson(response.body());

                    Example result = gson.fromJson(successResponse, Example.class);

                    List<House> houseList = result.getHouses();
                    Integer entrancesQty = result.getEntranceQty();
                    String date = result.getDate();

                    Log.d(TAG, "Количество домов: " + houseList.size() + " ,количество падиков: " + entrancesQty + " ,дата: " + date);

                    mRecyclerView.setAdapter(new NewAdapter(getApplicationContext(), houseList));

                    for (House address : houseList) {
                        Log.d(TAG, "Адреса: " + address.getAddress());
                        List<Entrance> entranceList = address.getEntrances();


                        for (Entrance entrance : entranceList) {
                            Log.d(TAG, "Падики: " + entrance.getNumber());

                        }

                    }

                }

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                t.getMessage();
                Log.d(TAG, "On failure message: " + t.getMessage() + Arrays.toString(t.getStackTrace()));
                Toast.makeText(MainActivity.this, "Ошибка загрузки из API", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_show_list:
                getInfoFromApi();
                Toast.makeText(this, "Показываем список", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_delete_list:
                Toast.makeText(this, "Удаляем список", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
