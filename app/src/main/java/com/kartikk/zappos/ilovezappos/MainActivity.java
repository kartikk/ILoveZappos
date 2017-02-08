package com.kartikk.zappos.ilovezappos;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.kartikk.zappos.ilovezappos.models.ZapposModel;
import com.kartikk.zappos.ilovezappos.util.Constants;
import com.kartikk.zappos.ilovezappos.util.Endpoints;
import com.kartikk.zappos.ilovezappos.util.Helper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    final String TAG = MainActivity.class.getSimpleName();
    RecyclerView searchRecyclerView;
    Endpoints endpoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchRecyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search_icon);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (endpoints == null) {
                    endpoints = Helper.getRetrofitEndpoints();
                }
                newText = newText.trim();
                Call<ZapposModel> call = endpoints.search(newText, Constants.authKey);
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
                return false;
            }
        });
        return true;
    }
}
