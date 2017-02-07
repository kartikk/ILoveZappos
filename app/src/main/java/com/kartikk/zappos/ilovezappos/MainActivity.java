package com.kartikk.zappos.ilovezappos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.GsonBuilder;
import com.kartikk.zappos.ilovezappos.models.ZapposModel;
import com.kartikk.zappos.ilovezappos.util.Constants;
import com.kartikk.zappos.ilovezappos.util.Endpoints;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    final String TAG = MainActivity.class.getSimpleName();
    RecyclerView searchRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchRecyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.baseURL)
                .addConverterFactory(GsonConverterFactory
                        .create((new GsonBuilder()).create())).build();
        Endpoints endpoints = retrofit.create(Endpoints.class);
        Call<ZapposModel> call = endpoints.search("nike", Constants.authKey);
        call.enqueue(new Callback<ZapposModel>() {
            @Override
            public void onResponse(Call<ZapposModel> call, Response<ZapposModel> response) {
                Log.d(TAG, "Search success");
                SearchRecyclerAdapter searchRecyclerAdapter = new SearchRecyclerAdapter(response.body());
                searchRecyclerView.setAdapter(searchRecyclerAdapter);
                searchRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }

            @Override
            public void onFailure(Call<ZapposModel> call, Throwable t) {
                Log.d(TAG, "Search failure");
            }
        });
    }

}
