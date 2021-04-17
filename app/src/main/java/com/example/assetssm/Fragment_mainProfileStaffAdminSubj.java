package com.example.assetssm;

import android.os.Bundle;
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
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class Fragment_mainProfileStaffAdminSubj extends Fragment {
    Toolbar toolbar;
    TextView txt_toolbarTitle;

    Spinner spn_fragStaffOfficer_subjGrade;
    List<Integer> arr_subjGrade;
    ArrayAdapter<Integer> arr_adpsubjGrade;

    SearchView sView_subjGrade;
    CardView cardView_admin_subj;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mainprofilestaffadminsubj, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbarMain);
        txt_toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbarMain_title);
        txt_toolbarTitle.setText("Subject");

        sView_subjGrade = view.findViewById(R.id.searchview_admin_subjGrade);
        //search(searchView);

        spn_fragStaffOfficer_subjGrade = view.findViewById(R.id.spn_fragStaffOfficer_subjGrade);
        subjGrade();

        return view;
    }

    private void subjGrade() {
        arr_subjGrade = new ArrayList<Integer>();
        arr_subjGrade.add(10);
        arr_subjGrade.add(9);
        arr_subjGrade.add(8);

        arr_adpsubjGrade = new ArrayAdapter<Integer>(getActivity(), R.layout.spn_itemcustom, arr_subjGrade);
        spn_fragStaffOfficer_subjGrade.setAdapter(arr_adpsubjGrade);
        spn_fragStaffOfficer_subjGrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

}

