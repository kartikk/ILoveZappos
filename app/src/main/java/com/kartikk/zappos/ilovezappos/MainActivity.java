package com.kartikk.zappos.ilovezappos;

import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.ContentViewEvent;
import com.kartikk.zappos.ilovezappos.models.ZapposModel;
import com.kartikk.zappos.ilovezappos.util.Constants;
import com.kartikk.zappos.ilovezappos.util.Endpoints;
import com.kartikk.zappos.ilovezappos.util.Helper;

import io.fabric.sdk.android.Fabric;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    final String TAG = MainActivity.class.getSimpleName();
    RecyclerView searchRecyclerView;
    Endpoints endpoints;
    SearchRecyclerAdapter searchRecyclerAdapter;
    String searchText = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        searchRecyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (searchText != null) {
            outState.putString("search", searchText);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        searchText = savedInstanceState.getString("search");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search_icon);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setIconified(false);
        if (searchText != null) {
            searchView.setQuery(searchText, false);
        } else {
            searchText = " ";
        }
        if (endpoints == null) {
            endpoints = Helper.getRetrofitEndpoints();
        }
        Call<ZapposModel> call = endpoints.search(searchText, Constants.authKey);
        call.enqueue(new Callback<ZapposModel>() {
            @Override
            public void onResponse(Call<ZapposModel> call, Response<ZapposModel> response) {
                if (searchRecyclerAdapter == null) {
                    searchRecyclerAdapter = new SearchRecyclerAdapter(response.body());
                    searchRecyclerView.setVisibility(View.VISIBLE);
                    searchRecyclerView.setAdapter(searchRecyclerAdapter);
                    searchRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                } else {
                    searchRecyclerAdapter.setZapposResultList(response.body());
                    searchRecyclerAdapter.notifyDataSetChanged();
                }
                Log.d(TAG, "Search success");
            }

            @Override
            public void onFailure(Call<ZapposModel> call, Throwable t) {
                Log.d(TAG, "Search failure");
                View parentLayout = findViewById(R.id.activity_main);
                Snackbar.make(parentLayout, getResources()
                        .getText(R.string.search_error), Snackbar.LENGTH_LONG).show();
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.trim();
                searchText = newText;
                Call<ZapposModel> call = endpoints.search(newText, Constants.authKey);
                call.enqueue(new Callback<ZapposModel>() {
                    @Override
                    public void onResponse(Call<ZapposModel> call, Response<ZapposModel> response) {
                        if (searchRecyclerAdapter == null) {
                            searchRecyclerAdapter = new SearchRecyclerAdapter(response.body());
                            searchRecyclerView.setVisibility(View.VISIBLE);
                            searchRecyclerView.setAdapter(searchRecyclerAdapter);
                            searchRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        } else {
                            searchRecyclerAdapter.setZapposResultList(response.body());
                            searchRecyclerAdapter.notifyDataSetChanged();
                        }
                        Log.d(TAG, "Search success");

                        Answers.getInstance().logContentView(new ContentViewEvent()
                                .putContentName("Search")
                                .putContentType("String")
                                .putContentId("12")
                                .putCustomAttribute("Search text", searchText));
                    }

                    @Override
                    public void onFailure(Call<ZapposModel> call, Throwable t) {
                        Log.d(TAG, "Search failure");
                        View parentLayout = findViewById(R.id.activity_main);
                        Snackbar.make(parentLayout, getResources()
                                .getText(R.string.search_error), Snackbar.LENGTH_LONG).show();
                    }
                });
                return false;
            }
        });
        return true;
    }
}
