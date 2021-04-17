package com.example.assetssm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Activity_mainOfficerList extends AppCompatActivity {

    Toolbar toolbar;
    TextView txt_toolbarTitle;
    Custom_progressBarHandler progressBarHandler;
    private RecyclerView recyclerView;
    private ArrayList<POJO_Activity_mainOfficerListRegd> data;
    private Adapter_Activity_mainOfficerListRegd adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainofficerlist);

        toolbar = findViewById(R.id.toolbarMain);
        txt_toolbarTitle = toolbar.findViewById(R.id.toolbarMain_title);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int allvalue = preferences.getInt("itemClickType", -1);
        dafault(allvalue);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        progressBarHandler = new Custom_progressBarHandler(this); // In onCreate
        progressBarHandler.show();

        initViews();
        loadJSON();

    }

    protected void dafault(int allvalue) {
        switch (allvalue) {
            case 1:
                txt_toolbarTitle.setText("Students");
                break;
            case 2:
                txt_toolbarTitle.setText("Parents");
                break;
            case 3:
                txt_toolbarTitle.setText("Employees");
                break;
        }
    }

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
    }

    private void loadJSON() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.learn2crack.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceEndpoint__Activity_mainOfficerListRegd request = retrofit.create(InterfaceEndpoint__Activity_mainOfficerListRegd.class);
        Call<JSON_Activity_mainOfficerListRegd> call = request.getJSON();
        call.enqueue(new Callback<JSON_Activity_mainOfficerListRegd>() {
            @Override
            public void onResponse(Call<JSON_Activity_mainOfficerListRegd> call, Response<JSON_Activity_mainOfficerListRegd> response) {

                progressBarHandler.hide();

                JSON_Activity_mainOfficerListRegd jsonResponse = response.body();
                data = new ArrayList<>(Arrays.asList(jsonResponse.getAndroid()));
                adapter = new Adapter_Activity_mainOfficerListRegd(data);
                recyclerView.setAdapter(adapter);

                ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder target, int direction) {
                        int targetPosition = target.getAdapterPosition();
                        adapter.notifyDataSetChanged();

                        Intent intentTargetID = new Intent(Activity_mainOfficerList.this, Activity_mainOfficerList_cardProfile.class);
                        intentTargetID.putExtra("officer", true);
                        startActivity(intentTargetID);
                    }

                    @Override
                    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                        new RecyclerViewSwipeDecorator.Builder(Activity_mainOfficerList.this, c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                                .addBackgroundColor(ContextCompat.getColor(Activity_mainOfficerList.this, R.color.colorPrimaryDark))
                                .addActionIcon(R.drawable.iconopen_book)
                                .create()
                                .decorate();

                        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                    }

                });

                itemTouchHelper.attachToRecyclerView(recyclerView);
            }

            @Override
            public void onFailure(Call<JSON_Activity_mainOfficerListRegd> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main_staff, menu);

        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (adapter != null) adapter.getFilter().filter(newText);
                return true;
            }
        });
    }
}
