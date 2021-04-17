package com.example.assetssm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment_mainProfileStaffAdminApp extends Fragment {
    private static View view;
    Toolbar toolbar;
    TextView txt_toolbarTitle;

    Custom_progressBarHandler progressBarHandler;
    Spinner spn_frag_staff_app;
    List<String> list_spn_frag_staff_app;
    SearchView searchView;
    ArrayAdapter<String> arrayAdapterCategory;
    private RecyclerView recyclerView;
    private ArrayList<POJO_Activity_mainOfficerListApp> data;
    private Adapter_Activity_mainOfficerListApp adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mainprofilestaffadminapp, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbarMain);
        txt_toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbarMain_title);
        txt_toolbarTitle.setText("Application");

        searchView = view.findViewById(R.id.searchview_admin_app);
        search(searchView);

        spn_frag_staff_app = view.findViewById(R.id.spn_frag_staff_app);
        AppCategorySpn();

        progressBarHandler = new Custom_progressBarHandler(getContext()); // In onCreate
        progressBarHandler.show();

        initViews();
        loadJSON();

        return view;
    }

    private void initViews() {
        recyclerView = (RecyclerView) view.findViewById(R.id.card_recycler_view_app);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
    }

    private void loadJSON() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.learn2crack.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceEndpoint__Activity_mainOfficerListApp request = retrofit.create(InterfaceEndpoint__Activity_mainOfficerListApp.class);
        Call<JSON_Activity_mainOfficerListApp> call = request.getJSON();
        call.enqueue(new Callback<JSON_Activity_mainOfficerListApp>() {
            @Override
            public void onResponse(Call<JSON_Activity_mainOfficerListApp> call, Response<JSON_Activity_mainOfficerListApp> response) {

                progressBarHandler.hide();

                JSON_Activity_mainOfficerListApp jsonResponse = response.body();
                data = new ArrayList<>(Arrays.asList(jsonResponse.getAndroid()));
                adapter = new Adapter_Activity_mainOfficerListApp(data);
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

                        ////////
                        ////////
                        //app crash prog issue nhi hai, api ka data hai
                        ////////
                        ////////

                        double api = Double.parseDouble(data.get(targetPosition).getVer());

                        if (api <= 4 & api >= 0) {
                            Intent intentTargetID = new Intent(getActivity(), Activity_mainOfficerList_cardProfile_more.class);
                            preference(4);
                            startActivity(intentTargetID);
                        } else if (api <= 8 & api > 4) {
                            Intent intentTargetID = new Intent(getActivity(), Activity_mainOfficerList_cardProfile_more.class);
                            preference(5);
                            startActivity(intentTargetID);
                        } else if (api <= 12 & api > 8) {
                            Intent intentTargetID = new Intent(getActivity(), Activity_mainOfficerList_cardProfile_more.class);
                            preference(6);
                            startActivity(intentTargetID);
                        }
                    }

                    @Override
                    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                        new RecyclerViewSwipeDecorator.Builder(getContext(), c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                                .addBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark))
                                .addActionIcon(R.drawable.iconcv)
                                .create()
                                .decorate();

                        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                    }

                });

                itemTouchHelper.attachToRecyclerView(recyclerView);
            }

            @Override
            public void onFailure(Call<JSON_Activity_mainOfficerListApp> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
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

    private void AppCategorySpn() {
        list_spn_frag_staff_app = new ArrayList<String>();
        list_spn_frag_staff_app.add("All");
        list_spn_frag_staff_app.add("Student");
        list_spn_frag_staff_app.add("Staff");
        list_spn_frag_staff_app.add("Driver");

        arrayAdapterCategory = new ArrayAdapter<String>(getActivity(), R.layout.spn_itemcustom, list_spn_frag_staff_app);
        spn_frag_staff_app.setAdapter(arrayAdapterCategory);
        spn_frag_staff_app.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    void preference(int val) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("itemClickType", val);
        editor.commit();
    }
}
