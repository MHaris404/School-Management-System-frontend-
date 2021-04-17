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
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class Fragment_mainProfileStaffAdminEnrollment extends Fragment {
    Toolbar toolbar;
    TextView txt_toolbarTitle;

    Spinner spn_fragStaffOfficer_enrollmentGrade;
    List<Integer> arr_enrollGrade;
    ArrayAdapter<Integer> arr_adpgrade;

    SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mainprofilestaffadminenrollment, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbarMain);
        txt_toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbarMain_title);
        txt_toolbarTitle.setText("Enrollment");

        searchView = view.findViewById(R.id.searchview_admin_enroll);
        //search(searchView);

        spn_fragStaffOfficer_enrollmentGrade = view.findViewById(R.id.spn_fragStaffOfficer_enrollmentGrade);
        attendYearSpn();

        return view;
    }

    private void attendYearSpn() {
        arr_enrollGrade = new ArrayList<Integer>();
        arr_enrollGrade.add(1);
        arr_enrollGrade.add(2);
        arr_enrollGrade.add(3);

        arr_adpgrade = new ArrayAdapter<Integer>(getActivity(), R.layout.spn_itemcustom, arr_enrollGrade);
        spn_fragStaffOfficer_enrollmentGrade.setAdapter(arr_adpgrade);
        spn_fragStaffOfficer_enrollmentGrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

}

