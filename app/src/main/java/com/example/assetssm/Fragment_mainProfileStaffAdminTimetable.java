package com.example.assetssm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Fragment_mainProfileStaffAdminTimetable extends Fragment {
    private static View view;
    Toolbar toolbar;
    TextView txt_toolbarTitle;
    Button button;
    ArrayAdapter<String> arrayAdapterCategory;
    private RecyclerView recyclerView;
    private ArrayList<POJO_Activity_mainOfficerListApp> data;
    private Adapter_Activity_mainOfficerListApp adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mainprofilestaffadmintimetable, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbarMain);
        txt_toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbarMain_title);
        txt_toolbarTitle.setText("Timetable");

        initViews();
//        loadJSON();

        button = view.findViewById(R.id.btn_staffadmin_generatetimetabl);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("general", 3);
                editor.commit();

                Intent intent = new Intent(getActivity(), Activity_mainProfileStaffAdmin_general.class);
                startActivity(intent);
            }
        });


        return view;
    }

    private void initViews() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_staff_admin_timetable);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
    }

}

