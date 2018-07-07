package com.polygalov.jsontesttask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = MainActivity.class.getName();

    private RecyclerView mRecyclerView;
    NewAdapter adapter;
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
        ApiUtil.getServiceClass().getAllPost().enqueue(new Callback<List<ApiObject>>() {
            @Override
            public void onResponse(Call<List<ApiObject>> call, Response<List<ApiObject>> response) {
                if (response.isSuccessful()) {
                    List<ApiObject> postList = response.body();
                    adapter = new NewAdapter(getApplicationContext(), postList);
                    mRecyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<List<ApiObject>> call, Throwable t) {

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
                adapter.delete();
                Toast.makeText(this, "Удаляем список", Toast.LENGTH_SHORT).show();
                break;
        }
    }


}
